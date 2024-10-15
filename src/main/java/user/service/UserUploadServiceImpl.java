package user.service;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;
import user.dao.UserUploadDAO;

@Service
public class UserUploadServiceImpl implements UserUploadService {
	@Autowired
	private UserUploadDAO userUploadDAO;
	@Autowired
	private HttpSession session;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-124";
	

	@Override
	public void upload(List<UserUploadDTO> imageUploadlist) {
		userUploadDAO.upload(imageUploadlist);
		
	}

	@Override
	public List<UserUploadDTO> uploadList() {
		
		return userUploadDAO.uploadList();
	}

	@Override
	public UserUploadDTO getUploadDTO(String seq) {
		return userUploadDAO.getUploadDTO(seq);
	}

	@Override
	public void uploadUpdate(UserUploadDTO userUploadDTO, MultipartFile img) {
		//실제폴더   Controller에서 처리해도 되지만 Service에서 처리해도 된다. 다만, service에는 세션이 없다. 
														//그러므로 위에 private HttpSession session; 이렇게 적으면 스프링이 알아서 세션을 제공한다. 
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		System.out.println("실제 폴더 = " + filePath);
		
		//Object Storage(NCP)는 이미지를 덮어쓰기를 하지 않는다.
		//DB에서 seq에 해당하는 imageFileName을 꺼내와서 Object Storage(NCP)의 이미지를 삭제하고, (update가 안되기 떄문)
		//새로운 이미지를 올린다.
		String imageFileName = userUploadDAO.getImageFileName(userUploadDTO.getSeq());
		System.out.println("imageFileName = " + imageFileName);
		
		//Object Storage(NCP)는 이미지 삭제
		objectStorageService.deleteFile(bucketName, "storage/", imageFileName);
		
		//Object Storage(NCP)는 새로운 이미지 올리기
		objectStorageService.uploadFile(bucketName, "storage/", img);
		
		String imageOriginalFileName = img.getOriginalFilename();
		File file = new File(filePath, imageOriginalFileName);
		
		try {
			img.transferTo(file);  //실제 파일경로(file)에 저장됨
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		userUploadDTO.setImageFileName(imageFileName);
		userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
		
		
		//DB
		userUploadDAO.uploadUpdate(userUploadDTO);
		
	}

	@Override
	public boolean uploadDelete(List<String> seqs) {
		//삭제할 이미지 파일 이름들 가져오기
		List<String> imageFileNames = userUploadDAO.getImageFileNames(seqs);
		for(String fileName : imageFileNames) {
			objectStorageService.deleteFile(bucketName, "storage/", fileName);
		}
		
		userUploadDAO.uploadDelete(seqs); //DB에서 삭제하는 것
		return true;
	}
}
