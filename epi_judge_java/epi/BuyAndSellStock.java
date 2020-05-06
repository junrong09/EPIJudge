package epi;
import epi.test_framework.EpiTest;
import epi.test_framework.GenericTest;

import java.util.List;
public class BuyAndSellStock {
  @EpiTest(testDataFile = "buy_and_sell_stock.tsv")
  public static double computeMaxProfit(List<Double> prices) {
    // TODO - you fill in here.
    double max = 0, stock = Double.MAX_VALUE;
    int p = 0;
    while (p < prices.size()) {
	// Always attempt to calculate profit/loss and record max
	max = Math.max(max, prices.get(p) - stock);
	// Try to get the cheapest stock
	stock = Math.min(stock, prices.get(p));
	++p;
    }
    return max;
  }

  public static void main(String[] args) {
    System.exit(
        GenericTest
            .runFromAnnotations(args, "BuyAndSellStock.java",
                                new Object() {}.getClass().getEnclosingClass())
            .ordinal());
  }
}
