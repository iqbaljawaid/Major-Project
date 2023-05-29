package in.globalit.service.impl;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import in.globalit.entity.UserEntity;
import in.globalit.service.AdminService;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	private final AdminService adminService;

	public UserDetailsServiceImpl(AdminService adminService) {
		this.adminService = adminService;
	}

	/*
	 * @Autowired private UserRepo userRepo;
	 */

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserEntity user = adminService.findUserForLogin(username);
		if (user != null) {
			return User.withUsername(user.getUsername()).password(user.getPassword())
					.roles(user.getRole().getAuthority()).build();
		} else {
			throw new UsernameNotFoundException("User not found.");
		}
	}

}
