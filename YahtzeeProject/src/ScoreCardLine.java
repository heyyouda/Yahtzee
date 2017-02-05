
public class ScoreCardLine {
	String name;
	int points;
	boolean used;
	
	public ScoreCardLine(String newName, int newPoints, boolean newUsed) {
		name = newName;
		points = newPoints;
		used = newUsed;
	}
	
	public void setName(String newName) {
		name = newName;
	}
	
	public String getName() {
		return name;
	}
	
	public void setPoints(int newPoints) {
		points = newPoints;
	}
	
	public int getPoints() {
		return points;
	}
	
	public void setUsed(boolean newUsed) {
		used = newUsed;
	}
	
	public boolean getUsed() {
		return used;
	}
}
