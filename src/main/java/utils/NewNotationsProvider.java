package utils;


import model.transitionfunction.TransitionFunctionInput;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

class NewNotationsProvider {

	static Map<Set<Character>, Character> forTransitionTable(Map<TransitionFunctionInput, Set<Character>> transitionTable,
			Set<Character> alreadyUsed) {
		Map<Set<Character>, Character> result = new HashMap<>();

		for (Map.Entry<TransitionFunctionInput, Set<Character>> entry : transitionTable.entrySet()) {
			if (entry.getKey().getState() instanceof Set) {
				if (!result.containsKey(entry.getKey().getState())) {
					final Character randomCharacter = RandomCharacterUtil.getRandom(alreadyUsed);
					alreadyUsed.add(randomCharacter);
					result.put((Set<Character>) entry.getKey().getState(), randomCharacter);
				}
			}
			if (entry.getValue().size() > 1) {
				if (!result.containsKey(entry.getValue())) {
					final Character randomCharacter = RandomCharacterUtil.getRandom(alreadyUsed);
					alreadyUsed.add(randomCharacter);
					result.put(entry.getValue(), randomCharacter);
				}
			}
		}

		return result;
	}

}