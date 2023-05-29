package in.globalit.service.impl;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.globalit.binding.CaseWorkerCreationBinder;
import in.globalit.constants.AppConstants;
import in.globalit.entity.PlanEntity;
import in.globalit.entity.RoleEntity;
import in.globalit.entity.UserEntity;
import in.globalit.repository.PlanRepo;
import in.globalit.repository.UserRepo;
import in.globalit.service.AdminService;
import in.globalit.utils.EmailSender;
import in.globalit.utils.GenerateOTP;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PlanRepo planRepo;

	// private HttpSession session;
	
	//@Autowired
	private BCryptPasswordEncoder bcryptPzzwdEncoder;
	
	@Autowired
	private GenerateOTP otp;
	
	@Autowired
	private EmailSender mailSender;

	@Override
	public UserEntity findUserForLogin(String username) {
		return userRepo.findByUsername(username);
	}

	@Override
	public String caseWorkerRegistration(CaseWorkerCreationBinder signup) {
		UserEntity userEntity = new UserEntity();
		RoleEntity roleEntity = new RoleEntity();
		BeanUtils.copyProperties(signup, userEntity);
		String generateOTP = otp.generateOTP();
		userEntity.setPassword(bcryptPzzwdEncoder.encode(generateOTP));
		userEntity.setAccStatus("LOCKED");
		userEntity.setAccAction("DEACTIVATED");
		roleEntity.setAuthority("USER");
		roleEntity.setUsername(userEntity.getUsername());
		userEntity.setRole(roleEntity);
		String email = signup.getUsername();
		
		String to = email;
		String subject = "Mail from Global IT";
		String body = "<body style='font-weight: bold;font-size: 15px'>"
				+ "<h2 style='font-family: 'Trocchi', serif; font-size: 45px; font-weight: normal; line-height: 48px; margin: 0;'>Your onetime password for registration is : "
				+ generateOTP + "</h2>" + "<br>"
				+ "<"+AppConstants.H4_HEADER_MAIL_STR+" style='font-family: 'Trocchi', serif; font-size: 15px; font-weight: normal; line-height: 18px; margin: 0;'> Please use this OTP to complete your new user registration."
				+ "</"+AppConstants.H4_HEADER_MAIL_STR+ "<br>"
				+ "<"+AppConstants.H4_HEADER_MAIL_STR+"style='font-family: 'Trocchi', serif; font-size: 15px; font-weight: normal; line-height: 18px; margin: 0;'>To unlock your account, click this link."
				+ "<"+AppConstants.H4_HEADER_MAIL_STR + "<a href='http://localhost:9091/unlock?email=" + to + "'>Unlock</a>" + "</body>";

		UserEntity save = userRepo.save(userEntity);
		if(save.getUserId() != null) {
			mailSender.sendEmail(to, subject, body);
			return "Success";
		}else {
			return "Mail not sent due to some internal reason";
		}

	}

	@Override
	public String createPlan(PlanEntity plan) {
		PlanEntity save = planRepo.save(plan);
		if (save != null) {
			return "Plan Created Successfully";
		} else {
			return "Plan Not Created";
		}
	}

	@Override
	public String viewAllAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String viewAllPlans() {
		// TODO Auto-generated method stub
		return null;
	}

}
