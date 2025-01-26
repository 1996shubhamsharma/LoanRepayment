package Service;

import java.util.List;

import Model.LoanRequest;
import Model.RepaymentDto;

public interface LoanRepaymentService {

	List<RepaymentDto> calculateRepaymentSchedule(LoanRequest loanRequest);
	
}
