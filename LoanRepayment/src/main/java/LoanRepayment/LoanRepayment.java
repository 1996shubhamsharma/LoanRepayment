package LoanRepayment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "Controller","Service"})
public class LoanRepayment {

	public static void main(String[] args) {
		SpringApplication.run(LoanRepayment.class, args);
	}

}
