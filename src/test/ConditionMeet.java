package test;

public class ConditionMeet {
	double VWAP;
	double fairValue;
	public ConditionMeet(double VWAP, double fairValue) {
		this.VWAP = VWAP;
		this.fairValue = fairValue;
	}
	double getVWAP() {
		return VWAP;
	}
	double getFairValue() {
		return fairValue;
	}

}
