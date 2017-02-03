import java.util.*;
/** 
 * @author Daniel
 *
 */
public class Dice {
	int range;
	int value;
	boolean kept;
	
	public Dice(int sides){
		range = sides;
		kept = false;
		Random rand = new Random();
		value = rand.nextInt((range - 1) + 1) + 1;
		
	}
	
	public void roll(){
		Random rand = new Random();
		value = rand.nextInt((range - 1) + 1) + 1;
	}
	
	public void setRange(int newRange) {
		range = newRange;
	}
	
	public int getRange() {
		return range;
	}
	
	public void setValue(int newValue) {
		value = newValue;
	}
	
	public int getValue() {
		return value;
	}
	public void setKept(boolean newKept) {
		kept = newKept;
	}
	
	public boolean getKept() {
		return kept;
	}
	
}
