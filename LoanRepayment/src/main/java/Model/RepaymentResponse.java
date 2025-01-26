package Model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class RepaymentResponse {

	private List<RepaymentDto> schedule;
	private List<Error> error;
}
