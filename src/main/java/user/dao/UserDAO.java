package user.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import user.bean.UserDTO;

@Mapper   //이러면 구현부인 mybatis로 이동하지 않고 바로 처리 가능하다.
public interface UserDAO {

	public void write(UserDTO userDTO);

	public List<UserDTO> getUserList();

	public UserDTO getExistId(String id);

	public void update(UserDTO userDTO);

	public void delete(String id);

	public List<UserDTO> list(Map<String, Integer> map);

	public int TotalA();

	public UserDTO getUserById(String id);

	public UserDTO verifyPassword(@Param("id") String id, @Param("pwd") String pwd);


}
