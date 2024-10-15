package user.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;

@Service
public interface UserUploadService {

	public void upload(List<UserUploadDTO> imageUploadlist);

	public List<UserUploadDTO> uploadList();

	public UserUploadDTO getUploadDTO(String seq);

	public void uploadUpdate(UserUploadDTO userUploadDTO, MultipartFile img);

	public boolean uploadDelete(List<String> seqs);

}
