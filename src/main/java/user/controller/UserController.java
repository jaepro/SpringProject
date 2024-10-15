package user.controller;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import user.bean.UserDTO;
import user.service.UserService;

@Controller
@RequestMapping("user") // => 밑에서 /user를 일일히 다 적을 필요가 없어진다.
public class UserController {
	@Autowired
	private UserService userService;
	
	@RequestMapping(value="writeForm", method = RequestMethod.GET)
	public String writeForm() {
		return "/user/writeForm";
	}
	
	@RequestMapping(value="getExistId", method = RequestMethod.POST)
	@ResponseBody
	public String getExistId(String id) {   //핸들러 매핑으로 
		
		
		return userService.getExistId(id); /*스프링에서는 String으로 잡고 리턴할 때 단순 문자열만 작성하면 단순 문자열이 아니라 jsp파일 명으로 인식한다!!  
		 			그래서 단순 문자열로 인식할 수 있도록 @ResponseBody 작성*/
	}
	
	@RequestMapping(value="write", method = RequestMethod.POST)
	@ResponseBody
	public void write(@ModelAttribute UserDTO userDTO) {
		userService.write(userDTO);
		
	}
	
	@RequestMapping(value="list", method = RequestMethod.GET) 
	public String list(@RequestParam(required = false, defaultValue = "1") String pg, Model model) { 
		Map<String, Object> map2 = userService.list(pg);
		
		map2.put("pg", pg);
		model.addAttribute("map2", map2);
		/*혹은  map2로 한번에 보내지 말고 이렇게 분해해서 보내도 된다.
		 model.addAttribute("list", map2.get("list"));
		 model.addAttribute("userPaging", map2.get("userPaging"));
		 */
		return "/user/list";   // => /WEB-INF/user/list.jsp } //ModelAnmdView , ModelMap. Model이 가능함.
	}
	
	@RequestMapping(value="updateForm", method = RequestMethod.GET)
	public String updateForm(@RequestParam String id, @RequestParam String pg, ModelMap modelMap) {
		UserDTO userDTO = userService.getUserById(id);
		
		modelMap.put("userDTO", userDTO);
		modelMap.put("pg", pg);
		modelMap.addAttribute("userDTO", userDTO);
		return "/user/updateForm";
	}
	
	@RequestMapping(value="update", method = RequestMethod.POST)
	@ResponseBody
	public void update(@ModelAttribute UserDTO userDTO) {
		userService.update(userDTO);
	}
	
	@RequestMapping(value="delete", method = RequestMethod.POST)
	@ResponseBody
	public void delete(@RequestParam String id, @RequestParam String pwd) {
		UserDTO userDTO = userService.verifyPassword(id, pwd);
	    if (userDTO != null) {
	        userService.delete(id);
	    }
	}
}