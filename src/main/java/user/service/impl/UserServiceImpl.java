package user.service.impl; //구현부

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import user.bean.UserDTO;
import user.bean.UserPaging;
import user.dao.UserDAO;
import user.service.UserService;

@Service  //웹하고는 상관 없으므로 root-context.xml에 빈 설정 해줘야 함
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private UserPaging userPaging;

	@Override
	public void execute() {
	}

	/* 아이디 중복 검사 */
	@Override
	public String getExistId(String id) {
		UserDTO userDTO = userDAO.getExistId(id);
		
		if(userDTO == null) 
			return "non_exist";
		else 
			return "exist";
	}

	/* 회원 가입 */
	@Override
	public void write(UserDTO userDTO) {
		userDAO.write(userDTO);
		
	}
	
	
	@Override
	public Map<String, Object> list(String pg) {
		//1페이지당 5개 씩
		int endNum = 5; //개수
		int startNum = (Integer.parseInt(pg)-1) * 5; //시작위치 (0부터 시작)
		Map<String, Integer> map = new HashMap<>();
		
		map.put("startNum", startNum);
		map.put("endNum", endNum);
		
		//DB
		List<UserDTO> list = userDAO.list(map);
		
		//페이징 처리
		int totalA = userDAO.TotalA(); // 총 글 수
		
		userPaging.setCurrentPage(Integer.parseInt(pg));
		userPaging.setPageBlock(3);
		userPaging.setPageSize(5);
		userPaging.setTotalA(totalA);
		userPaging.makePagingHTML();
		
		Map<String, Object> map2 = new HashMap<>();
		map2.put("list", list);
		map2.put("userPaging", userPaging);
		
		return map2;
	}

	@Override
	public UserDTO getUserById(String id) {
		UserDTO userDTO = userDAO.getUserById(id);
		
		return userDTO;
	}

	@Override
	public void update(UserDTO userDTO) {
		userDAO.update(userDTO);
		
	}

	@Override
	public UserDTO verifyPassword(String id, String pwd) {
	    return userDAO.verifyPassword(id, pwd);
	}


	@Override
	public void delete(String id) {
		userDAO.delete(id);
		
	}

}
