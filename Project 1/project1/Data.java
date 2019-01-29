package project1;

	import java.io.File;
	import java.io.IOException;
	import java.util.ArrayList;
	import java.util.Scanner;

//Jac Marois, O-O Java Programming, MWF 11am
public class Data implements StockDataADT{
	private ArrayList<Stock> stocks = new ArrayList<Stock>();	//Contains all stocks which contain all weeks
	
	public Data(String filename) {		
		
		try {
			Scanner s = new Scanner(new File(filename));
			s.next();				// Skip header row
			String wk = s.next();	//Get first real row
			ArrayList<Week> wks = new ArrayList<Week>();	//This ArrayList will hold one stock's weeks at a time
			while (s.hasNext()) {
				wks.add(new Week(wk));
				wk = s.next();
				if (wk.contains(",,,")) {	//If next stock
					stocks.add(new Stock(wks));		//Create a new stock with all its weeks
					wks = new ArrayList<Week>();	//I found that wks.clear() didn't work here
													//So this was the next best way to dump the data
				}
			}
			stocks.add(new Stock(wks));		//Add the very last group of weeks to the last stock
			s.close();	//Don't forget to close the file
		} catch (IOException e) {System.out.println("Couldn't open file...");}	//Just in case...
	}
	
	//Getter for the number of stocks
	@Override
	public int getCount() {
		return stocks.size();
	}

	//Getter for the number of weeks
	@Override
	public int getWeekCount() {
		return stocks.get(0).getNoWeeks();
	}

	//Getter for the week object of a given stock at a given week
	@Override
	public Week getStocksWeek(int stock, int week) {
		return stocks.get(stock).getWeek(week);
	}

	//Getter for the stock name... I didn't really use this
	@Override
	public String getStockName(int stock) {
		return stocks.get(stock).getSN();
	}

	//Getter for the date of a given week.. Didn't use this either
	@Override
	public String getWeekDate(int week) {
		return stocks.get(0).getWeek(week).getDate();
	}
	
	//Getter for a stock object given an index
	public Stock getStock(int st) {
		return stocks.get(st);
	}
	
	//Getter for a stock object given an index
	public Stock getStock(String sn) {
		for (int i = 0; i < stocks.size(); i++) {
			if (stocks.get(i).getSN().equals(sn)) {
				return stocks.get(i);
			}
		}
		return null;
	}
}
