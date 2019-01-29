package project1;

import java.text.DecimalFormat;
import java.util.ArrayList;

//Jac Marois, O-O Java Programming, MWF 11am
public class Broker implements PredictorADT {
	private double begCash;		//Only used for getting profit
	private double curCash;		//The current amount of cash
	StockDataADT stockdata;		//The StockData that predict() will run through
	int week;					//The current week
	ArrayList<Stock> curStocks = new ArrayList<Stock>();	//The ArrayList of currently held stocks
	ArrayList<Stock> potStocks = new ArrayList<Stock>();	//All of which get sold at each week's end
	ArrayList<Integer> vols = new ArrayList<Integer>();
	
	//Default constructor
	public Broker() {
		begCash = curCash = 0.0;
		week = 0;
	}
	
	//Constructor for only cash
	public Broker(double cash) {
		begCash = curCash = cash;
		week = 0;
	}
	
	//Constructor for cash with a StockData object
	public Broker(double cash, StockDataADT sd) {
		begCash = curCash = cash;
		stockdata = sd;
		week = 0;
	}
	
	//Setter for cash 
	@Override
	public void setPortfolioAmount(double cash) {
		begCash = curCash = cash;
	}
	
	//Getter for cash
	@Override
	public double getPortfolioAmount() {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(curCash));
	}
	
	//Setter for the StockData
	@Override
	public void setDataSet(StockDataADT sd) {
		stockdata = sd;
	}

	//The predict method; Relies entirely on previous weeks percent change in price
	//Not the best, but it makes money
	@Override
	public void predict() {
		for (int i = 0; i < stockdata.getWeekCount()-1; i++) {		//Iterate through all weeks
			if (week >= 2) {		//Don't even look at stocks until the 3rd week
				for (int j = 0; j < stockdata.getCount()-1; j++) {	//Iterate through all stocks
					//Predict
					if( ( stockdata.getStocksWeek(j, i-1).getPerChangePrice() < 0 ) &&
							( stockdata.getStocksWeek(j, i-1).getPerChangePrice() > stockdata.getStocksWeek(j, i-2).getPerChangePrice() ) &&
							( stockdata.getStocksWeek(j, i-1).getPerChangePrice() > -3.0 ) )
					{
						potStocks.add(((Data)stockdata).getStock(j));
					}
					if( ( stockdata.getStocksWeek(j, i-1).getPerChangePrice() > 0 ) &&
							( stockdata.getStocksWeek(j, i-1).getPerChangePrice() > stockdata.getStocksWeek(j, i-2).getPerChangePrice() ) &&
							( stockdata.getStocksWeek(j, i-1).getPerChangePrice() < 5.0 ) )
					{
						potStocks.add(((Data)stockdata).getStock(j));
					}
				}
				sort();
				buyStocks();
				sellAllStocks();	//Sell at the end of each week
			}
		week++;		//Start next week
		}
	}
	
	private void buyStocks() {
		for (int i = 0; i < potStocks.size(); i++) {
			if (potStocks.size() - (potStocks.size()/4) > i) { //Don't invest in the bottom quarter
				System.out.println(potStocks.get(i).getSN()+", "+potStocks.get(i).getWeek(week).getOpen());
				int vol = (int)(curCash / potStocks.get(i).getWeek(week).getOpen());
				if (vol * potStocks.get(i).getWeek(week).getOpen() <= curCash) {
					System.out.println("Before:"+curCash);
					curCash -= vol * potStocks.get(i).getWeek(week).getOpen();
					System.out.println("After:"+curCash);
					curStocks.add(potStocks.get(i));
					vols.add(vol);
				}
			}
		}
		potStocks = new ArrayList<Stock>();
	}
	
	//Iterates through all stocks to sell them
	private void sellAllStocks() {
		for (int i = 0; i < curStocks.size(); i++) {
			System.out.println(curStocks.get(i).getSN()+", "+curStocks.get(i).getWeek(week).getClose());
			System.out.println("Before sell:"+curCash);
			curCash += vols.get(i) * curStocks.get(i).getWeek(week).getClose();
			System.out.println("After sell:"+curCash);
		}
		curStocks = new ArrayList<Stock>();
		vols = new ArrayList<Integer>();
	}
	
	//Helper method that returns the difference of the current amount and the beginning amount of cash
	public double getProfit() {
		DecimalFormat df = new DecimalFormat("#.##");
		return Double.parseDouble(df.format(curCash-begCash));
	}
	
	private void sort() {
		for (int i = 0; i < potStocks.size(); i++) {
			int maxin = i;
			for (int j = i+1; j < potStocks.size(); j++) {
				if (potStocks.get(j).getWeek(week).getOpen() > potStocks.get(maxin).getWeek(week).getOpen()) {
					maxin = j;
				}
			}
			Stock temp = potStocks.remove(maxin);
			potStocks.add(i,temp);
		}
	}

}
