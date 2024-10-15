package user.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web .bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;
import user.service.ObjectStorageService;
import user.service.UserService;
import user.service.UserUploadService;

@Controller
@RequestMapping(value = "user")
public class UserUploadController {
	@Autowired
	private UserUploadService userUploadService;
	@Autowired
	private ObjectStorageService objectStorageService;
	
	private String bucketName = "bitcamp-9th-bucket-124"; //ncp 홉페이지 -콘솔 - Object Storage - Bucket Management

	@RequestMapping(value="uploadForm")
	public String uploadForm() {
		return "/upload/uploadForm";
	}
	
	@RequestMapping(value="uploadAJaxForm")
	public String uploadAJaxForm() {
		return "/upload/uploadAJaxForm";
	}
	
	
	// 1개
	/*
	 * @RequestMapping(value="upload", method=RequestMethod.POST)
	 * 
	 * @ResponseBody public String upload(@ModelAttribute UserUploadDTO
	 * userUploadDTO,
	 * 
	 * @RequestParam MultipartFile img, HttpServletRequest request HttpSession
	 * session) { MultipartFile : Spring Framework에서 파일 업로드를 처리할 때 사용하는 인터페이스 HTTP
	 * 요청으로 전송된 파일 데이터를 처리할 수 있도록 도와줌 MultipartFile 객체(img)로 받아서 컨트롤러 메서드로 전달함
	 * 
	 * //실제폴더 //세션 생성 방법 1. String filePath =
	 * session.getServletContext().getRealPath("WEB-INF/storage"); HttpSession
	 * session = request.getSession(); /* 세션 생성 방법 2. 위의 HttpServletRequest request를
	 * 작성하고 이 문장 쓰기 System.out.println("실제 폴더 = " + filePath);
	 * 
	 * String imageOriginalFileName = img.getOriginalFilename();
	 * 
	 * File file = new File(filePath, imageOriginalFileName);
	 * 
	 * try { img.transferTo(file); //실제 파일경로(file)에 저장됨 } catch
	 * (IllegalStateException e) { e.printStackTrace(); } catch (IOException e) {
	 * e.printStackTrace(); }
	 * 
	 * String result = "<span>" + "<image src='/spring/storage/" +
	 * imageOriginalFileName + "' width='300' height='300'>" + "</span>";
	 * 
	 * System.out.println(userUploadDTO.getImageName() +
	 * userUploadDTO.getImageContent() + userUploadDTO.getImageFileName() +
	 * userUploadDTO.getImageOriginalFileName());
	 * userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
	 * 
	 * //DB
	 * 
	 * 
	 * return result; }
	 */

//2개인 경우
	/*
	@RequestMapping(value="upload", method=RequestMethod.POST)
	@ResponseBody
	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
						@RequestParam MultipartFile[] img, 
						HttpSession session) {  

		//실제폴더
		//세션 생성 방법 1.
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		//HttpSession session = request.getSession();  세션 생성 방법 2. 위의 HttpServletRequest request를 작성하고 이 문장 쓰기
		System.out.println("실제 폴더 = " + filePath);
		
		String imageOriginalFileName;
		File file;
		String result;
				
		imageOriginalFileName = img[0].getOriginalFilename();
		file = new File(filePath, imageOriginalFileName);
		
		try {
			img[0].transferTo(file);  //실제 파일경로(file)에 저장됨
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result = "<span>"
				+ "<image src='/spring/storage/" + imageOriginalFileName + "' width='300' height='300'>"
				+ "</span>";
		
		System.out.println(userUploadDTO.getImageName()
							+ userUploadDTO.getImageContent()
							+ userUploadDTO.getImageFileName()
							+ userUploadDTO.getImageOriginalFileName());
		userUploadDTO.setImageOriginalFileName(imageOriginalFileName);
		//----------------------------------------
		
		imageOriginalFileName = img[1].getOriginalFilename();
		file = new File(filePath, imageOriginalFileName);
		
		try {
			img[1].transferTo(file);  //실제 파일경로(file)에 저장됨
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		result += "<span>"
				+ "<image src='/spring/storage/" + imageOriginalFileName + "' width='300' height='300'>"
				+ "</span>";
		
		//DB
		
		
		return result;
	} */
	
	//1개 또는 여러 개(드래그)  -> list로 받음
	 //produces: ~~ : 한글 처리, 파일명에 공백 있어도 업로드 가능하게 함
	@RequestMapping(value="upload", method=RequestMethod.POST, produces = "text/html; charset=UTF-8")
	@ResponseBody
	public String upload(@ModelAttribute UserUploadDTO userUploadDTO,
						@RequestParam("img[]") List<MultipartFile> list, //원본으로 MultipartFile로 받고있는 list
						HttpSession session) throws UnsupportedEncodingException {  

		//실제폴더
		//세션 생성 방법 1.
		String filePath = session.getServletContext().getRealPath("WEB-INF/storage");
		//HttpSession session = request.getSession();  세션 생성 방법 2. 위의 HttpServletRequest request를 작성하고 이 문장 쓰기
		System.out.println("실제 폴더 = " + filePath);
		
		String imageFileName;
		String imageOriginalFileName;
		File file;
		String result="";
		
		//파일들을 모아서 DB로 보내기
		List<UserUploadDTO> imageUploadlist = new ArrayList<>();
		
		for(MultipartFile img : list) {
			//imageFileName = UUID.randomUUID().toString(); //고유 키
			
			//Naver Cloud Object Storage
			imageFileName = objectStorageService.uploadFile(bucketName, "storage/", img);
			
			
			imageOriginalFileName = img.getOriginalFilename();
			file = new File(filePath, imageOriginalFileName);
			
			try {
				img.transferTo(file);  //실제 파일경로(file)에 저장됨
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			result += "<span>"
					+ "<img src='/spring/storage/"
				//	+ URLEncoder.encode(imageOriginalFileName, "UTF-8")  //한글 처리 해줌 , 단점: 파일명에 공백이 있으면 안됨...ㅜㅜ
					+ imageOriginalFileName
					+ "' width='300' height='300'>"
					+ "</span>";
			
			UserUploadDTO dto = new UserUploadDTO();
			dto.setImageName(userUploadDTO.getImageName());
			dto.setImageContent(userUploadDTO.getImageContent());
			dto.setImageFileName(imageFileName);
			dto.setImageOriginalFileName(imageOriginalFileName);
			
			imageUploadlist.add(dto);
		}		
		//DB
		userUploadService.upload(imageUploadlist);
		
		return result;
	}
	
	@RequestMapping(value="uploadList")
	public ModelAndView uploadList() {
		List<UserUploadDTO> list = userUploadService.uploadList();
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("list", list);
		mav.setViewName("/upload/uploadList");
		return mav;
	}
	
	@RequestMapping(value="uploadView")
	public String uploadView(@RequestParam String seq, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
		model.addAttribute("userUploadDTO", userUploadDTO);
		
		return "/upload/uploadView";
	}
	
	@RequestMapping(value="uploadUpdateForm")
	public String uploadUpdateForm(@RequestParam String seq, Model model) {
		UserUploadDTO userUploadDTO = userUploadService.getUploadDTO(seq);
		model.addAttribute("userUploadDTO", userUploadDTO);
		
		return "/upload/uploadUpdateForm";
	}
	
	@RequestMapping(value="uploadUpdate")
	@ResponseBody
	public String uploadUpdate(@ModelAttribute UserUploadDTO userUploadDTO,
							@RequestParam("img") MultipartFile img) {
		userUploadService.uploadUpdate(userUploadDTO, img);
		
		return "이미지 수정 완료";
	}
	
	@RequestMapping(value="uploadDelete", method=RequestMethod.POST)
	@ResponseBody
	public boolean  uploadDelete(@RequestBody  List<String> seqs) {
		userUploadService.uploadDelete(seqs);
		return true;
	}
	
	
}
