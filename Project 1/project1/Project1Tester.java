package project1;

//Jac Marois, O-O Java Programming, MWF 11am
public class Project1Tester {
	//Just a very simple tester class
	public static void main(String[] args) {
		StockDataADT sd = new Data("./dow_jones_index4_8.data");
		Broker prophet = new Broker(10000.0, sd);
		
		System.out.println("Beginning:\t" + prophet.getPortfolioAmount());
		prophet.predict();
		System.out.println("Ending:\t\t" + prophet.getPortfolioAmount());
		System.out.println("Profit:\t\t" + prophet.getProfit());
	}

}
