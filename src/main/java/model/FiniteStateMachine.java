package model;

import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
public class FiniteStateMachine {

	private List<TransitionFunction> transitionFunctions = new ArrayList<>();
	private Set<Character> states = new HashSet<>();
	private Set<Character> inputSymbols = new HashSet<>();
	private Set<Character> initialStates = new HashSet<>();
	private Set<Character> finiteStates = new HashSet<>();


	public void addState(Character character) {
		states.add(character);
	}

}
