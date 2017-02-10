/* 
 * Author: Daniel Abrahms
 * Last Edited: 2/6/17
 * Class: CPSC 224-02
 * Class: ScoreCard
 * Description: This is the ScoreCard Class. It has an array of ScoreCardLines. 
 * 				It allows the main file to check the score of a given hand, and prints the possible plays. 
 */
public class ScoreCard {

	// the arrays of ScoreCardLines
	ScoreCardLine line[];
	
	// Constructor for ScoreCard
	// instantiates the array of scorecardlines
	public ScoreCard() {
		line = new ScoreCardLine[13];
		String names[] = {"Aces", "Twos", "Threes", "Fours", "Fives", "Sixes", 
				"3 of a Kind", "4 of a Kind", "Full House", "Sm. Straight", "Lg. Straight",
				"Yahtzee", "Chance"};
		int pointValues[] = {1, 2, 3, 4, 5, 6, 1, 1, 25, 30, 40, 50, 1};
		
		for (int i = 0; i < line.length; i++) {
			line[i] = new ScoreCardLine(names[i], pointValues[i], false);
		}
		 
	}
	
	// Checks score using private methods
	public void checkScore(Hand hand) {
		hand.sortHand();
		
		boolean threeOfKindPrinted = false;
		boolean fourOfKindPrinted = false;
		boolean yahtzeePrinted = false;

		
		System.out.println("Upper Section");
		for (int i = 0; i < 6; i++) {
			line[i].print(totalOfNum(i+1, hand));
		}
		
		System.out.println("Lower Section");
		for (int i = 0; i < 7; i++) {
			if (isThreeOfKind(i, hand)){
				line[6].print(hand.sum());
				threeOfKindPrinted = true;
			}
		}
		
		if (!threeOfKindPrinted) {
			line[6].print(0);
		}
		
		for (int i = 1; i < 7; i++) {
			if (isFourOfKind(i, hand)){
				line[7].print(hand.sum());
				fourOfKindPrinted = true;
			}
		}
		
		if (!fourOfKindPrinted) {
			line[7].print(0);
		}
		if (isFullHouse(hand)) {
			line[8].print(1);
		} else {
			line[8].print(0);
		}
		if (isSmallStraight(hand)) {
			line[9].print(1);
		} else {
			line[9].print(0);
		}
		if (isLargeStraight(hand)) {
			line[10].print(1);
		} else {
			line[10].print(0);
		}
		for (int i = 0; i < 7; i++) {
			if (isYahtzee(i, hand)){
				line[11].print(1);
				yahtzeePrinted = true;
			}
		}
		if (!yahtzeePrinted) {
			line[11].print(0);
		}
		line[12].print(hand.sum());
		
		System.out.println("Max Found: " + maxOfAKind(hand));
	}
	
	// returns the total of given number in given hand
	private int totalOfNum(int num, Hand hand) {
		int sum = 0;
		for (int i = 0; i < hand.getDiceNumber(); i++) {
			if (hand.getDice(i).getValue() == num) {
				sum++;
			}
		}
		return sum;
	}

	private int maxStraight(Hand hand) {
	    int maxFound = 0;
	    hand.sortHand();
		for (int i = 1; i <= hand.getDiceRange(); i++){
		    int maxFoundTemp = 0;
		    for (int j = 0; j < hand.getDiceNumber(); j++) {
		        if (hand.getDice(j).getValue() == i+j) {
		            maxFoundTemp++;
                }
            }
            maxFound = Math.max(maxFound, maxFoundTemp);
        }
        return maxFound;
	}

    private int maxOfAKind(Hand hand) {

    }
	// returns if given hand contains three of kind of given num
	private boolean isThreeOfKind(int num, Hand hand) {
		if (totalOfNum(num, hand) == 3) {
			return true;
		} 
		return false;
	}
	// returns if given hand contains four of kind of given num
	private boolean isFourOfKind(int num, Hand hand) {
		if (totalOfNum(num, hand) == 4) {
			return true;
		}
		return false;
	}
	// returns if given hand contains five of kind of given num
	private boolean isYahtzee(int num, Hand hand) {
		if (totalOfNum(num, hand) == 5) {
			return true;
		}
		return false;
	}
	// returns if given hand contains a small straight
	// @pre: hand is sorted lowest->highest
	private boolean isSmallStraight(Hand hand) {
		if (maxStraight(hand) > 3) {
		    return true;
        }
        return false;
	}
	// returns if given hand contains a large straight
	// @pre: hand is sorted lowest->highest
	private boolean isLargeStraight(Hand hand) {
		if (maxStraight(hand) > 4) {
		    return true;
        }
        return false;
	}
	// returns if given hand contains a full house
	private boolean isFullHouse(Hand hand) {
		if (hand.getDice(0).getValue() == hand.getDice(1).getValue() && hand.getDice(1).getValue() == hand.getDice(2).getValue()
				&& hand.getDice(3).getValue() == hand.getDice(4).getValue() && hand.getDice(2).getValue() != hand.getDice(3).getValue()) {
			return true;
		}
		if (hand.getDice(2).getValue() == hand.getDice(3).getValue() && hand.getDice(3).getValue() == hand.getDice(4).getValue()
				&& hand.getDice(0).getValue() == hand.getDice(1).getValue() && hand.getDice(1).getValue() != hand.getDice(2).getValue()) {
			return true;
		}
		return false; 
	}
}
