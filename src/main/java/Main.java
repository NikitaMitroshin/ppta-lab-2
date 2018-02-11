import model.FiniteStateMachine;
import model.Grammar;
import utils.*;

public class Main {

	public static void main(String[] args) {
		final Grammar grammar = GrammarUtil.fromFile("my_rules.txt");
		final FiniteStateMachine finiteStateMachine = FSMBuilder.buildFromGrammar(grammar);
		GraphDrawerUtil.drawGraph(finiteStateMachine);
		FSMDetermineUtil.determine(finiteStateMachine);
		GraphDrawerUtil.drawGraph(finiteStateMachine);
	}

}