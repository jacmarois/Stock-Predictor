package project1;

public interface StockDataADT {
	public int getCount();	//Returns the number of stocks
	public int getWeekCount();	//Returns the number of weeks
	public Week getStocksWeek(int stock, int week);	//Return a Week given index of which stock and index of which week
	public String getStockName(int stock);	//Return the name of a stock given its index
	public String getWeekDate(int week);	//Return the string representation of a week given its index
}
