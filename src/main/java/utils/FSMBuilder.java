package utils;


import model.*;

import java.util.*;

public class FSMBuilder {

	private static final String ADDITIONAL_NONTERMINAL = "N";
	private static final String EMPTY_STRING = "ε";

	public static FiniteStateMachine buildFromGrammar(Grammar grammar) {

		if (GrammarUtil.getType(grammar) != GrammarType.TYPE_3) {
			throw new IllegalArgumentException("Grammar is not type 3 (regular)!");
		}

		final List<Rule> completedRules = completeRules(grammar);

		final FiniteStateMachine finiteStateMachine = new FiniteStateMachine();
		finiteStateMachine.setStates(RuleAnalyzer.getNonterminals(completedRules));
		finiteStateMachine.setInitialStates(new HashSet<>(StringUtil.splitToChars(grammar.getS())));
		finiteStateMachine.setInputSymbols(grammar.getT());

		final List<TransitionFunction> transitionFunctions = GrammarRulesParser.toTransitionFunctions(completedRules);

		finiteStateMachine.setTransitionFunctions(transitionFunctions);

		final Set<Character> finiteStates = getFiniteStates(transitionFunctions);
		finiteStates.addAll(getFiniteStatesEmptyString(completedRules));

		finiteStateMachine.setFiniteStates(finiteStates);

		return finiteStateMachine;
	}

	private static List<Rule> completeRules(Grammar grammar) {
		final List<Rule> rules = new ArrayList<>();
		for (Rule rule : grammar.getRules()) {
			String rightPart = rule.getRight();
			if (rightPart.length() == 1) {
				StringBuilder sb = new StringBuilder(rightPart);
				rule.setRight(sb.append(ADDITIONAL_NONTERMINAL)
						.toString());
			}
			rules.add(rule);
		}
		return rules;
	}

	public static Set<Character> getFiniteStates(List<TransitionFunction> functions) {
		Set<Character> finiteStates = new HashSet<>();
		for (TransitionFunction outer : functions) {
			boolean isFinite = true;
			for (TransitionFunction inner : functions) {
				if (outer.getOut() == inner.getIn()
						.getState()) {
					isFinite = false;
				}
			}
			if (isFinite) finiteStates.add(outer.getOut());
		}
		Map<Character, TransitionFunction> statesWithOneTransitionFunction = new HashMap<>();
		int count = 0;
		for (TransitionFunction outer : functions) {
			for (TransitionFunction inner : functions) {
				if (outer.getIn()
						.getState()
						.equals(inner.getIn()
								.getState())) count++;
			}
			if (count == 1) {
				statesWithOneTransitionFunction.put((Character) outer.getIn()
						.getState(), outer);
			}
			count = 0;
		}
		for (Character state : statesWithOneTransitionFunction.keySet()) {
			TransitionFunction function = statesWithOneTransitionFunction.get(state);
			for (TransitionFunction f : functions) {
				if (f.getIn()
						.getState()
						.equals(function.getOut())) {
					if (statesWithOneTransitionFunction.keySet()
							.contains(f.getIn()
									.getState())) finiteStates.add(state);
				}
			}
		}
		return finiteStates;
	}

	private static Set<Character> getFiniteStatesEmptyString(List<Rule> rules) {
		Set<Character> finiteStates = new HashSet<>();
		for (Rule rule : rules) {
			String right = rule.getRight();

			if (right.length() == 2 && right.contains(EMPTY_STRING)) {
				finiteStates.add(rule.getLeft()
						.toCharArray()[0]);
			}
		}
		return finiteStates;
	}
}