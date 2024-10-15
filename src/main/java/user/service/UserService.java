package user.service;

import java.util.Map;
import user.bean.UserDTO;

public interface UserService {
	public void execute();

	public String getExistId(String id);

	public void write(UserDTO userDTO);

	public Map<String, Object> list(String pg);

	public UserDTO getUserById(String id);

	public void update(UserDTO userDTO);

	public UserDTO  verifyPassword(String id, String pwd);

	public void delete(String id);

}
