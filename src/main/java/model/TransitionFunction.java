package model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import model.transitionfunction.TransitionFunctionInput;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransitionFunction {

	private TransitionFunctionInput in;
	private Character out;

}
