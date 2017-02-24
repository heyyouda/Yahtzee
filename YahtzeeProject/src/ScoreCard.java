/* 
 * @author Daniel Abrahms
 * Last Edited: 2/14/17
 * Class: CPSC 224-02
 * Class: ScoreCard
 * Description: This is the ScoreCard Class. It has an array of ScoreCardLines. 
 * 				It allows the main file to check the score of a given hand, and prints the possible plays. 
 */
public class ScoreCard {

	ScoreCardLine line[];
	
	/*
	@pre ScoreCard object is null
	@post ScoreCard object has been created
	@param sidesPerDice: the number of sides per dice
	@see ScoreCard
	 */
	public ScoreCard(int sidesPerDice) {
		line = new ScoreCardLine[7+sidesPerDice];

        for (int i = 0; i < sidesPerDice; i++) {
            line[i] = new ScoreCardLine(String.valueOf(i+1), i+1, false);
        }

		line[sidesPerDice + 0] = new ScoreCardLine("(3K): 3 of a Kind", 1, false);
		line[sidesPerDice + 1] = new ScoreCardLine("(4K): 4 of a Kind", 1, false);
		line[sidesPerDice + 2] = new ScoreCardLine("(FH): Full House", 25, false);
		line[sidesPerDice + 3] = new ScoreCardLine("(SS): Small Straight", 30, false);
		line[sidesPerDice + 4] = new ScoreCardLine("(LS): Large Straight", 40, false);
		line[sidesPerDice + 5] = new ScoreCardLine("(YA): Yahtzee", 50, false);
		line[sidesPerDice + 6] = new ScoreCardLine("(CH): Chance", 1, false);
	}
	
	// @print: Every possible score has been printed
    // @param hand: hand object that has been initiated
	// @see checkScore
	public void checkScore(Hand hand) {
		hand.sortHand();
		int sidesPerDice = hand.getDiceRange();
		
		boolean threeOfKindPrinted = false;
		boolean fourOfKindPrinted = false;
		boolean yahtzeePrinted = false;

		System.out.println("====================");
		System.out.println("Upper Section");
		System.out.println("====================");
		for (int i = 0; i < sidesPerDice; i++) {
			line[i].printUnused(totalOfNum(i+1, hand), false);
		}

		System.out.println("====================");
		System.out.println("Lower Section");
		System.out.println("====================");
		for (int i = 1; i <= sidesPerDice; i++) {
			if (isThreeOfKind(i, hand)){
				line[sidesPerDice + 0].printUnused(hand.sum(), false);
				threeOfKindPrinted = true;
				break;
			}
		}
		
		if (!threeOfKindPrinted) {
			line[sidesPerDice + 0].printUnused(0, false);
		}
		
		for (int i = 1; i <= sidesPerDice; i++) {
			if (isFourOfKind(i, hand)){
				line[sidesPerDice + 1].printUnused(hand.sum(), false);
				fourOfKindPrinted = true;
				break;
			}
		}
		
		if (!fourOfKindPrinted) {
			line[sidesPerDice + 1].printUnused(0, false);
		}
		if (isFullHouse(hand)) {
			line[sidesPerDice + 2].printUnused(1, false);
		} else {
			line[sidesPerDice + 2].printUnused(0, false);
		}
		if (isSmallStraight(hand)) {
			line[sidesPerDice + 3].printUnused(1, false);
		} else {
			line[sidesPerDice + 3].printUnused(0, false);
		}
		if (isLargeStraight(hand)) {
			line[sidesPerDice + 4].printUnused(1, false);
		} else {
			line[sidesPerDice + 4].printUnused(0, false);
		}
		for (int i = 1; i <= sidesPerDice; i++) {
			if (isYahtzee(i, hand)){
				line[sidesPerDice + 5].printUnused(1, false);
				yahtzeePrinted = true;
				break;
			}
		}
		if (!yahtzeePrinted) {
			line[sidesPerDice + 5].printUnused(0, false);
		}
		line[sidesPerDice + 6].printUnused(hand.sum(), false);

		System.out.println("====================");
	}

	/*
	@return int of how many score card lines havent been used
	@see howManyLeft
	 */
	public int howManyLeft() {
		int count = 0;
		for (int i = 0; i < line.length; i++) {
			if (!line[i].getUsed()) {
			    count++;
            }
		}
		return count;
	}

	/*
	@return scoreCardLine at the index
	@param the index you want the scorecardline at
	@see ScoreCardLine
	 */
	public ScoreCardLine getLine(int index) {
	    return line[index];
    }

    /*
    @param diceRange: the int of how many sides are per dice
    @print The scorecard with titles, uses - for those unused
    @see showScoreCard
     */
    public void showScoreCard(int diceRange) {
	    int subTotal = 0;
	    int lowerTotal = 0;
	    int bonus = 0;
	    System.out.println("========================");
	    System.out.println("Line            Score    ");
        for (int i = 0; i < diceRange + 7; i++) {
            if (i < diceRange) subTotal+=line[i].getPointsEarned();
            if (i == diceRange) {
                System.out.println("=======================");
                System.out.println("Sub Total:            " + subTotal);
                System.out.print("Bonus:                ");
                if (calculateBonus(diceRange)){
                    bonus = 35;
                    System.out.print(bonus);
                }
                System.out.println();
				System.out.println("=======================");
            }
            if (i >= diceRange) lowerTotal+=line[i].getPointsEarned();
            line[i].printUsed();
            if (i == diceRange+6) {
                System.out.println("=======================");
                System.out.println("Lower Total:          " + lowerTotal);
                System.out.println("=======================");
                System.out.println("Grand Total:          " + (lowerTotal+subTotal+bonus));
            }

        }
    }
	/* @param num: int value of what number to check total of
	   @param hand: hand object that has been initiated
	   @return int value of total number of times that num exists in hand
	   @see totalOfNum
	 */
	private int totalOfNum(int num, Hand hand) {
		int sum = 0;
		for (int i = 0; i < hand.getDiceNumber(); i++) {
			if (hand.getDice(i).getValue() == num) {
				sum++;
			}
		}
		return sum;
	}

    /*
       @param hand: hand object that has been initiated
       @return int the longest straight that exists within hand
       @see maxStraight
     */
	private int maxStraight(Hand hand) {
        int maxFound = 1;
        int maxFoundTemp = 1;
        for(int i = 0; i < hand.getDiceNumber()-1; i++)
        {
            if ((hand.getDice(i).getValue()) + 1 == hand.getDice(i + 1).getValue()) {
                maxFoundTemp++;
            } else if ((hand.getDice(i).getValue() + 1) < (hand.getDice(i+1).getValue())) {
            maxFoundTemp = 1;
        }
            maxFound = Math.max(maxFound, maxFoundTemp);
        }
        return maxFound;
	}

    /* @param num: int value of what number to check
       @param hand: hand object that has been initiated
       @return boolean value of whether a three of num exists in hand
       @see isThreeOfKind
     */
	private boolean isThreeOfKind(int num, Hand hand) {
		if (totalOfNum(num, hand) >= 3) {
			return true;
		} 
		return false;
	}
	/* @param num: int value of what number to check
       @param hand: hand object that has been initiated
       @return boolean value of whether a four of num exists in hand
       @see isFourOfKind
     */
	private boolean isFourOfKind(int num, Hand hand) {
		if (totalOfNum(num, hand) >= 4) {
			return true;
		}
		return false;
	}

    /* @param num: int value of what number to check
       @param hand: hand object that has been initiated
       @return boolean value of whether a five of num exists in hand
       @see isYahtzee
     */
	private boolean isYahtzee(int num, Hand hand) {
		if (totalOfNum(num, hand) >= 5) {
			return true;
		}
		return false;
	}
	// @return boolean value if given hand contains a small straight
	// @pre: hand is sorted lowest->highest
	// @see isSmallStraight
	private boolean isSmallStraight(Hand hand) {
		if (maxStraight(hand) > 3) {
		    return true;
        }
        return false;
	}
    // @return boolean value if given hand contains a large straight
    // @pre: hand is sorted lowest->highest
	// @see isLargeStraight
	private boolean isLargeStraight(Hand hand) {
		if (maxStraight(hand) > 4) {
		    return true;
        }
        return false;
	}

	 /*
	 @pre Hand is sorted
     @post Hand is unchanged
     @param hand, a custom Hand class object
     @return boolean value on whether hand contains a Full House
     @see isFullHouse
     */
     private boolean isFullHouse(Hand hand) {
         boolean fullHouseFound = false;
         boolean threeFound = false;
         boolean twoFound = false;
         int counter ;
         for (int i = 1; i <=hand.getDiceRange(); i++) {
             counter = 0;
             for (int j = 0; j < hand.getDiceNumber(); j++)
             {
                 if (hand.getDice(j).getValue() == i)
                     counter++;
             }
             if (counter == 2)
                 twoFound = true;
             if (counter == 3)
                 threeFound = true;
         }
         if (twoFound && threeFound) {
             fullHouseFound = true;
         }
         return fullHouseFound;
     }


     /*
     @param diceRange: the number of sides per dice
     @return whether or not the 63 grand total has been reached
     @see calculateBonus
      */
     private boolean calculateBonus(int diceRange) {
         int sum = 0;
         for (int i = 0; i < diceRange+7; i++) {
             sum += line[i].getPointsEarned();
         }
         if (sum >= 63) return true;
         return false;
     }

}
