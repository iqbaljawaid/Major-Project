package in.globalit.service;

import in.globalit.binding.CaseWorkerCreationBinder;
import in.globalit.entity.PlanEntity;
import in.globalit.entity.UserEntity;

public interface AdminService {
	
	public UserEntity findUserForLogin(String username);
	public String caseWorkerRegistration(CaseWorkerCreationBinder signup);
	public String createPlan(PlanEntity plan);
	public String viewAllAccounts();
	public String viewAllPlans();
	

}
