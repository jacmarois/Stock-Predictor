package project1;

public interface PredictorADT {
	public void setPortfolioAmount(double cash);	//Sets the current amount of cash on hand
	public double getPortfolioAmount();		//Gets the current amount of cash on hand
	public void setDataSet(StockDataADT sd);	//Sets the local DataADT object (sets which data is being used)
	public void predict();		//Executes the stock prediction based on current cash on hand and data
}