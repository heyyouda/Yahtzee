/**
 * @author - Daniel Abrahms
 * @version 1.0
 * Last Edited- 2/14/17
 * Class- CPSC 224-02
 * Class- ScoreCardLine
 * Description- This is the ScoreCardLine Class. It has a name, points, and used factor.
 * It has a method for printing itself and the possible points, based on a multiplier.
 */
public class ScoreCardLine {
	// the name of the ScoreCardLine (i.e. Full House)
	String name;
	// how many points this will be scored for, works in tatum with multiplier
	int points;
	// how many points this was scored for
	int pointsEarned;
	// whether or not the ScoreCardLine has been used this game.
	boolean used;

	private int potentialPoints;

	public int getPotentialPoints() {
		return potentialPoints;
	}


	/**
	 *  @param newName- String value of name attribute
	 *  @param newPoints- int value of points attribute
	 *  @param newUsed- boolean value of used attribute
	 */
	public ScoreCardLine(String newName, int newPoints, boolean newUsed) {
	    pointsEarned = 0;
	    potentialPoints = 0;
		name = newName;
		points = newPoints;
		used = newUsed;
	}

	/**
	 * @param newName- String value of the name attribute
 	 */
	public void setName(String newName) {
		name = newName;
	}

	/**
	 * @return the attribute name
	*/
	public String getName() {
		return name;
	}

	/**
	 * @param newPoints- int value of points attribute
	 */
	public void setPoints(int newPoints) {
		points = newPoints;
	}

	/**
	 * @return the attribute points
	 */
	public int getPoints() {
		return points;
	}

    public void setPointsEarned() {
		pointsEarned = points;
    }

	public void setPointsEarned(int newPointsEarned) {
		pointsEarned = newPointsEarned;
	}

	/**
	 * @return the attribute pointsEarned
	*/
    public int getPointsEarned() {
        return pointsEarned;
    }

	/**
	 *  @param newUsed- boolean value of used attribute
 	 */
	public void setUsed(boolean newUsed) {
		used = newUsed;
	}

	/**
	 * @return the attribute used
	*/
	public boolean getUsed() {
		return used;
	}



	public void setPotentialPoints(int potentialPoints) {
		this.potentialPoints = potentialPoints;
	}
}
