package model;

import lombok.Data;

import java.util.List;
import java.util.Set;

@Data
public class Grammar {

	private final String S = "K";
	private final List<Rule> rules;
	private final Set<Character> T;
	private final Set<Character> N;

}
