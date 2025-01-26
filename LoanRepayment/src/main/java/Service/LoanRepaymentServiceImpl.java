package Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import Model.LoanRequest;
import Model.RepaymentDto;

@Service
public class LoanRepaymentServiceImpl implements LoanRepaymentService {

	public List<RepaymentDto> calculateRepaymentSchedule(LoanRequest loanRequest) {
		List<RepaymentDto> schedule = new ArrayList<>();

		double principal = loanRequest.getLoanAmount();
		double roi = calculateMonthlyROI(loanRequest.getRateOfInterest());
		int tenure = loanRequest.getTenure();
		boolean isWeekly = isWeeklyRepayment(loanRequest.getRepaymentType());
		double emi = calculateEMI(principal, roi, tenure);
		generateSchedule(schedule, principal, roi, emi, tenure, isWeekly);

		return schedule;
	}

	private double calculateMonthlyROI(double annualRateOfInterest) {
		return annualRateOfInterest / 100 / 12;
	}

	private boolean isWeeklyRepayment(String repaymentType) {
		return repaymentType.equalsIgnoreCase("weekly");
	}

	private double calculateEMI(double principal, double roi, int tenure) {
		return (principal * roi * Math.pow(1 + roi, tenure)) / (Math.pow(1 + roi, tenure) - 1);
	}

	private void generateSchedule(List<RepaymentDto> schedule, double principal, double roi, double emi, int tenure,
			boolean isWeekly) {
		LocalDate startDate = LocalDate.now();
		double outstanding = principal;

		for (int i = 1; i <= tenure; i++) {
			// Calculate due date based on repayment type
			LocalDate dueDate = calculateDueDate(startDate, i, isWeekly);

			// Calculate interest and principal for this installment
			double dueInterest = calculateInterest(outstanding, roi);
			double principalPaid = emi - dueInterest;
			outstanding -= principalPaid;

			// Add repayment entry
			addRepaymentEntry(schedule, i, dueDate, outstanding, principalPaid, dueInterest, emi);
		}
	}

	private LocalDate calculateDueDate(LocalDate startDate, int installmentNumber, boolean isWeekly) {
		return isWeekly ? startDate.plusWeeks(installmentNumber) : startDate.plusMonths(installmentNumber);
	}

	private double calculateInterest(double outstanding, double roi) {
		return outstanding * roi; // For monthly interest
	}

	private void addRepaymentEntry(List<RepaymentDto> schedule, int installmentNumber, LocalDate dueDate,
			double outstanding, double principalPaid, double dueInterest, double emi) {

		// Round the amounts to 2 decimal places
		BigDecimal roundedOutstanding = new BigDecimal(outstanding).setScale(2, RoundingMode.HALF_UP);
		BigDecimal roundedDueInterest = new BigDecimal(dueInterest).setScale(2, RoundingMode.HALF_UP);
		BigDecimal roundedEMI = new BigDecimal(emi).setScale(2, RoundingMode.HALF_UP);

		schedule.add(new RepaymentDto(installmentNumber, dueDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")),
				dueDate.getDayOfWeek().toString(), roundedOutstanding.doubleValue(), roundedDueInterest.doubleValue(),
				roundedEMI.doubleValue(), roundedOutstanding.subtract(new BigDecimal(principalPaid)).doubleValue() 																					// paid
		));
	}
}
