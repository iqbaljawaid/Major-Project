package in.globalit.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.globalit.binding.LoginBinder;
import in.globalit.entity.UserEntity;
import in.globalit.service.AdminService;

@RestController
public class AdminRestController {
	
	@Autowired
	private AdminService service;
	
	//@Autowired
	private BCryptPasswordEncoder bcryptPzzwdEncoder;
	
	/*
	 * @PostMapping("/login") public ResponseEntity<UserEntity>
	 * adminLogin(@RequestBody LoginBinder login){ UserEntity response =
	 * service.findUserForLogin(login.getUsername());
	 * 
	 * 
	 * 
	 * return new ResponseEntity<UserEntity>(response, HttpStatus.OK); }
	 */
	
	@PostMapping("/user")
	public ResponseEntity<String> login(@RequestBody LoginBinder login) {
		UserEntity status = service.findUserForLogin(login.getUsername());
		if(status.getUserId() != null && bcryptPzzwdEncoder.matches(login.getPassword(), status.getPassword())) {
			return new ResponseEntity<String>("user logged in",HttpStatus.OK);
		}else {
			return new ResponseEntity<String>("something went wrong",HttpStatus.INTERNAL_SERVER_ERROR);

		}
		
	}

}
