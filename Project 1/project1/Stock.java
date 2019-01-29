package project1;

import java.util.ArrayList;

//Jac Marois, O-O Java Programming, MWF 11am
public class Stock {
	private ArrayList<Week> weeks;	//Contains this stocks array of weeks
	
	//Default stock constructor
	public Stock(ArrayList<Week> wks) {
		weeks = wks;
	}
	
	//Getter for a week given its index
	public Week getWeek(int index) {
		return weeks.get(index);
	}
	
	//Getter for this stock's name
	public String getSN() {
		return weeks.get(0).getWeekStock();
	}

	//Getter for the number of total weeks
	public int getNoWeeks() {
		return weeks.size();
	}
	
	//toString that prints each week on a new line
	public String toString() {
		String ret = "Stock:\t" + getSN() + "\n";
		for (Week week : weeks) {
			ret += week.toString() + "\n";
		}
		return ret;
	}
}
