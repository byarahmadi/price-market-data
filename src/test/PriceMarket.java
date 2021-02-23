package test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class PriceMarket {
	String productId;
	Queue<Transaction> transactionQueue;
	Double fairValue;

	ArrayList<ConditionMeet> finalList;

	public PriceMarket(String productId) {
		this.productId = productId;
		this.transactionQueue = new LinkedList<>();
		this.fairValue = (double) 0;
		finalList = new ArrayList<>();
	}

	public void changeFairValue(double newFairValue) {
		fairValue = newFairValue;
	}

	public void addTtransaction(Transaction transaction) {
		transactionQueue.add(transaction);
		if (transactionQueue.size() > 5)
			transactionQueue.remove();
	}

	public void computeVWAP() {

		double VWAP = 0;

		long sumQuantity = 0;
		double sumQuantityPrice = 0;

		for (int i = 0; i < transactionQueue.size(); i++) {

			Transaction transaction = (Transaction) transactionQueue.toArray()[i];
			sumQuantity += transaction.getQuantity();
			sumQuantityPrice += transaction.getQuantity() * transaction.getPrice();

		}

		VWAP = sumQuantityPrice / sumQuantity;

		if (VWAP > fairValue) {
			finalList.add(new ConditionMeet(VWAP, fairValue));

		}
	}
	public ArrayList<ConditionMeet> returnFinalList() {
		return finalList;
	}
}
