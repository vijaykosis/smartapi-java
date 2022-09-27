package com.angelbroking.smartapi.smartstream.models;

public class SmartApiBBSInfo {
	public static final int BYTES = (2 * Short.BYTES) + (2 * Long.BYTES);

	// siBbBuySellFlag = 1 buy
	// siBbBuySellFlag = 0 sell
	private short siBbBuySellFlag = -1;
	private long lQuantity = -1;
	private long lPrice = -1;
	private short siNumberOfOrders = -1;

	public short getSiBbBuySellFlag() {
		return siBbBuySellFlag;
	}

	public void setSiBbBuySellFlag(short siBbBuySellFlag) {
		this.siBbBuySellFlag = siBbBuySellFlag;
	}

	public long getlQuantity() {
		return lQuantity;
	}

	public void setlQuantity(long lQuantity) {
		this.lQuantity = lQuantity;
	}

	public long getlPrice() {
		return lPrice;
	}

	public void setlPrice(long lPrice) {
		this.lPrice = lPrice;
	}

	public short getSiNumberOfOrders() {
		return siNumberOfOrders;
	}

	public void setSiNumberOfOrders(short siNumberOfOrders) {
		this.siNumberOfOrders = siNumberOfOrders;
	}

}