package user.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.servlet.ModelAndView;

import user.bean.UserUploadDTO;

@Mapper
public interface UserUploadDAO {

	public void upload(List<UserUploadDTO> imageUploadlist);

	public List<UserUploadDTO> uploadList();

	public UserUploadDTO getUploadDTO(String seq);

	public String getImageFileName(int seq);

	public void uploadUpdate(UserUploadDTO userUploadDTO);

	public List<String> getImageFileNames(List<String> seqs);
	
	public boolean uploadDelete(List<String> seqs);

}
