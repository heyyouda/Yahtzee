/* 
 * Author: Daniel Abrahms
 * Last Edited: 2/24/17
 * Class: CPSC 224-02
 * Class: YahtzeeProject
 * Description: This is the main file. It plays one round of the game using the included function. It will eventually be migrated into another class. 
 */

import java.util.Scanner;

public class YahtzeeProject {
	public static void main(String[] args) {

	    Scanner scan = new Scanner(System.in);

		int sidesPerDice = 6;
		int diceInGame = 5;
		int rollsPerTurn = 3;
		String changeSettings;

		SaveFile file = new SaveFile(sidesPerDice, diceInGame, rollsPerTurn);

        file.read();
        sidesPerDice = file.getSidesPerDice();
        diceInGame = file.getDiceInGame();
        rollsPerTurn = file.getRollsPerTurn();

        System.out.println("Current Settings: ");
        System.out.println("-Sides per Dice:  " + sidesPerDice);
        System.out.println("-Dice in Game:    " + diceInGame);
        System.out.println("-rollsPerTurn:    " + rollsPerTurn);

        ScoreCard playerOneScoreCard = new ScoreCard(sidesPerDice);

		System.out.print("Load Game (Y/N)? ");
		String loadGame = scan.nextLine();
		if (loadGame.equalsIgnoreCase("y")) {
		    file.readScoreCard(playerOneScoreCard);
        } else {
            System.out.print("Would you like to change settings? (Y/N): ");
            changeSettings = scan.next();
            if (changeSettings.toLowerCase().equals("y")) {
                System.out.println("How many sides per dice? (1-100): ");
                sidesPerDice = scan.nextInt();
                System.out.println("How many dice in game? (1-100): ");
                diceInGame = scan.nextInt();
                System.out.println("How many rolls per turn? (1-100): ");
                rollsPerTurn = scan.nextInt();
                file.write(sidesPerDice, diceInGame, rollsPerTurn);
            }
        }




		while (playerOneScoreCard.howManyLeft() != 0) {
            Hand playerOneHand = new Hand(diceInGame, rollsPerTurn, sidesPerDice);
            file.writeScoreCard(playerOneScoreCard, playerOneHand);
            playerOneHand.displayHand();

            Round r = new Round("Player 1", playerOneScoreCard, playerOneHand);
            r.playRound(rollsPerTurn, sidesPerDice);
            System.out.println("You have " + playerOneScoreCard.howManyLeft() + " lines left");
            file.writeScoreCard(playerOneScoreCard, playerOneHand);
        }

        playerOneScoreCard.showScoreCard(sidesPerDice);

		System.out.println("Game Over");
	}

}
