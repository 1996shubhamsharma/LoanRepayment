package Model;


import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoanRequest {

    @NotNull(message = "Loan amount is required.")
    @Positive(message = "Loan amount must be greater than zero.")
    private Double loanAmount;

    @NotNull(message = "Rate of interest is required.")
    @Min(value = 0, message = "Rate of interest cannot be negative.")
    private Double rateOfInterest;

    @NotNull(message = "Tenure is required.")
    @Min(value = 1, message = "Tenure must be at least 1 month.")
    private Integer tenure;

    @NotBlank(message = "Repayment type is required.")
    @Pattern(regexp = "^(?i)(weekly|monthly)$", message = "Repayment type must be 'weekly' or 'monthly'.")
    private String repaymentType;
}
