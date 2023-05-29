package in.globalit.binding;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PlanBinder {

	private String planCategory;
	private String planName;
	private LocalDate planStartDate;
	private LocalDate planEndDate;
}
