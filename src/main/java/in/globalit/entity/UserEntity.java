package in.globalit.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "User_Table")
public class UserEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private Long userId;
	private String fullName;
	private String username;
	private String password;
	private Long mobileNo;
	private String gender;
	private LocalDate dob;
	private Long ssn;
	
	private String accStatus;
	private String accAction;
	
	@OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
	private List<PlanEntity> plans;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id")
	private RoleEntity role;

}
