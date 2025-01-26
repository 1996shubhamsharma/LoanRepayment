package Controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Model.*;
import Model.Error;
import Service.LoanRepaymentService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/loan")
public class LoanRepaymentController {
	
	@Autowired
	private LoanRepaymentService service;

	@PostMapping("/repayment-schedule")
	public ResponseEntity<RepaymentResponse> calculateRepaymentSchedule(@Valid @RequestBody LoanRequest loanRequest, BindingResult bindingResult) {
		RepaymentResponse response = new RepaymentResponse();
  
		if (bindingResult.hasErrors()) {
			List<Error> errors = new ArrayList<>();
			 errors = bindingResult.getFieldErrors().stream()
				    .map(fieldError -> new Error(fieldError.getField(), fieldError.getDefaultMessage()))
				    .collect(Collectors.toList());
		    response.setError(errors);

		    return ResponseEntity.badRequest().body(response);
		}
	
	    response.setSchedule(this.service.calculateRepaymentSchedule(loanRequest));
	    return ResponseEntity.ok(response);
	}
}
