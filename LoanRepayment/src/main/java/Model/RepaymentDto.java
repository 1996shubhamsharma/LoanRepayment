package Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class RepaymentDto {
	
    private int sno;
    private String date; 
    private String day; 
    private double outstandingStart;
    private double dueInterest;
    private double emi;
    private double outstandingEnd;
    
}
