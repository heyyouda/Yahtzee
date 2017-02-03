import java.util.*;
public class YahtzeeProject {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		
		final int rollsPerTurn = 3;
		final int diceInGame = 5;
		
		Hand playerOneHand = new Hand(diceInGame, rollsPerTurn);
		playerOneHand.displayHand();
		
		for (int i = 1; i < rollsPerTurn; i++) {
			
			playerOneHand.changeHand();
			playerOneHand.displayHand();
		}
		System.out.println("Game Over");
	}

}
