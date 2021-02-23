package test;

import java.util.*;

import org.apache.log4j.Logger;

import com.hsoft.codingtest.DataProvider;
import com.hsoft.codingtest.DataProviderFactory;
import com.hsoft.codingtest.MarketDataListener;
import com.hsoft.codingtest.PricingDataListener;

public class Test {
	final static Logger logger = Logger.getLogger("TEST_PRODUCT");
	private final static Object lock = new Object();

	static HashMap<String, PriceMarket> priceMarketMap = new HashMap<String, PriceMarket>();

	public final static void main(String[] args) {

		DataProvider provider = DataProviderFactory.getDataProvider();

		provider.addMarketDataListener(new MarketDataListener() {
			public void transactionOccured(String productId, long quantity, double price) {
				synchronized (lock) {

					Transaction transaction = new Transaction(quantity, price);
					PriceMarket priceMarket = priceMarketMap.get(productId);
					if (priceMarket == null) {
						priceMarket = new PriceMarket(productId);
						priceMarketMap.put(productId, priceMarket);

					}
					priceMarket.addTtransaction(transaction);

					priceMarket.computeVWAP();

				}
			}
		});
		provider.addPricingDataListener(new PricingDataListener() {
			public void fairValueChanged(String productId, double fairValue) {
				synchronized (lock) {

					PriceMarket priceMarket = priceMarketMap.get(productId);
					if (priceMarket == null) {
						priceMarket = new PriceMarket(productId);
						priceMarketMap.put(productId, priceMarket);

					}
					priceMarket.changeFairValue(fairValue);
					priceMarket.computeVWAP();

				}

			}
		});

		provider.listen();
		
		// The output for Test_Product
		PriceMarket priceMarket = priceMarketMap.get("TEST_PRODUCT");
		ArrayList<ConditionMeet> finalList = priceMarket.returnFinalList();
		for (ConditionMeet conditionMeet : finalList) {
			logger.debug("VWAP(" + conditionMeet.getVWAP() + ") > FairValue(" + conditionMeet.getFairValue() +")");
			
		}

	}
}