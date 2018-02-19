package utils;


import model.Rule;
import model.TransitionFunction;
import model.transitionfunction.DeterministicTransitionFunctionInput;
import model.transitionfunction.TransitionFunctionInput;

import java.util.ArrayList;
import java.util.List;

public class GrammarRulesParser {

	public static List<TransitionFunction> toTransitionFunctions(List<Rule> completedRules) {
		List<TransitionFunction> transitionFunctions = new ArrayList<>();
		for (Rule rule : completedRules) {
			TransitionFunctionInput in = new DeterministicTransitionFunctionInput();

			String left = rule.getLeft();
			if (left.length() > 1) {
				throw new IllegalArgumentException();
			}

			in.setState(rule.getLeft().toCharArray()[0]);

			List<Character> right = StringUtil.splitToChars(rule.getRight());
			if (right.size() > 2) {
				throw new IllegalArgumentException();
			}

			Character out = null;
			for (Character c : right) {
				if (Character.isUpperCase(c)) {
					out = c;
				} else {
					in.setSignal(c);
				}
			}
			// создаем переход. например для правила K->1L
			// К будет по сигналу 1 переходить в состояние L
			//  in(K, 1), out - L
			transitionFunctions.add(new TransitionFunction(in, out));
		}
		return transitionFunctions;
	}

}
