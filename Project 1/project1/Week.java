package project1;

//Jac Marois, O-O Java Programming, MWF 11am
public class Week {
	private String week;	//Contains one line from the data file
	
	//Default week constructor
	public Week(String wk) {
		week = wk;
	}
	
	//Getter for the stock name
	public String getWeekStock() {
		return week.split(",", 0)[1];
	}
	
	//Getter for the date
	public String getDate() {
		return week.split(",",0)[2];
	}
	
	//Getter for the open price
	public double getOpen() {
		return Double.parseDouble(week.split(",",0)[3].substring(1));
	}
	
	//Getter for the close price
	public double getClose() {
		return Double.parseDouble(week.split(",",0)[6].substring(1));
	}
	
	//Getter for the high price.. didn't really use this, but at least it's there
	public double getHigh() {
		return Double.parseDouble(week.split(",",0)[4].substring(1));
	}

	//Getter for the low price.. didn't really use this, but at least it's there
	public double getLow() {
		return Double.parseDouble(week.split(",",0)[5].substring(1));
	}
	
	//Getter for the percent change in stock price
	public double getPerChangePrice() {
		return Double.parseDouble(week.split(",",0)[8]);
	}
	
	//Very simple toString method
	public String toString() {
		return week;
	}
}
