package com.angelbroking.smartapi.smartstream.models;

public class SnapQuote {
	public static final int PACKET_SIZE_IN_BYTES = 379;

	private TokenID token;
	private long sequenceNumber;
	private long exchangeFeedTimeEpochMillis;
	private long lastTradedPrice;
	private long lastTradedQty;
	private long avgTradedPrice;
	private long volumeTradedToday;
	private double totalBuyQty;
	private double totalSellQty;
	private long openPrice;
	private long highPrice;
	private long lowPrice;
	private long closePrice;
	private long lastTradedTimestamp = 0;
	private long openInterest = 0;
	private double openInterestChangePerc = 0;
	private SmartApiBBSInfo[] bestFiveBuy;
	private SmartApiBBSInfo[] bestFiveSell;
	private long upperCircuit = 0;
	private long lowerCircuit = 0;
	private long yearlyHighPrice = 0;
	private long yearlyLowPrice = 0;

	public TokenID getToken() {
		return token;
	}

	public void setToken(TokenID token) {
		this.token = token;
	}

	public long getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(long sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}

	public long getExchangeFeedTimeEpochMillis() {
		return exchangeFeedTimeEpochMillis;
	}

	public void setExchangeFeedTimeEpochMillis(long exchangeFeedTimeEpochMillis) {
		this.exchangeFeedTimeEpochMillis = exchangeFeedTimeEpochMillis;
	}

	public long getLastTradedPrice() {
		return lastTradedPrice;
	}

	public void setLastTradedPrice(long lastTradedPrice) {
		this.lastTradedPrice = lastTradedPrice;
	}

	public long getLastTradedQty() {
		return lastTradedQty;
	}

	public void setLastTradedQty(long lastTradedQty) {
		this.lastTradedQty = lastTradedQty;
	}

	public long getAvgTradedPrice() {
		return avgTradedPrice;
	}

	public void setAvgTradedPrice(long avgTradedPrice) {
		this.avgTradedPrice = avgTradedPrice;
	}

	public long getVolumeTradedToday() {
		return volumeTradedToday;
	}

	public void setVolumeTradedToday(long volumeTradedToday) {
		this.volumeTradedToday = volumeTradedToday;
	}

	public double getTotalBuyQty() {
		return totalBuyQty;
	}

	public void setTotalBuyQty(double totalBuyQty) {
		this.totalBuyQty = totalBuyQty;
	}

	public double getTotalSellQty() {
		return totalSellQty;
	}

	public void setTotalSellQty(double totalSellQty) {
		this.totalSellQty = totalSellQty;
	}

	public long getOpenPrice() {
		return openPrice;
	}

	public void setOpenPrice(long openPrice) {
		this.openPrice = openPrice;
	}

	public long getHighPrice() {
		return highPrice;
	}

	public void setHighPrice(long highPrice) {
		this.highPrice = highPrice;
	}

	public long getLowPrice() {
		return lowPrice;
	}

	public void setLowPrice(long lowPrice) {
		this.lowPrice = lowPrice;
	}

	public long getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(long closePrice) {
		this.closePrice = closePrice;
	}

	public long getLastTradedTimestamp() {
		return lastTradedTimestamp;
	}

	public void setLastTradedTimestamp(long lastTradedTimestamp) {
		this.lastTradedTimestamp = lastTradedTimestamp;
	}

	public long getOpenInterest() {
		return openInterest;
	}

	public void setOpenInterest(long openInterest) {
		this.openInterest = openInterest;
	}

	public double getOpenInterestChangePerc() {
		return openInterestChangePerc;
	}

	public void setOpenInterestChangePerc(double openInterestChangePerc) {
		this.openInterestChangePerc = openInterestChangePerc;
	}

	public SmartApiBBSInfo[] getBestFiveBuy() {
		return bestFiveBuy;
	}

	public void setBestFiveBuy(SmartApiBBSInfo[] bestFiveBuy) {
		this.bestFiveBuy = bestFiveBuy;
	}

	public SmartApiBBSInfo[] getBestFiveSell() {
		return bestFiveSell;
	}

	public void setBestFiveSell(SmartApiBBSInfo[] bestFiveSell) {
		this.bestFiveSell = bestFiveSell;
	}

	public long getUpperCircuit() {
		return upperCircuit;
	}

	public void setUpperCircuit(long upperCircuit) {
		this.upperCircuit = upperCircuit;
	}

	public long getLowerCircuit() {
		return lowerCircuit;
	}

	public void setLowerCircuit(long lowerCircuit) {
		this.lowerCircuit = lowerCircuit;
	}

	public long getYearlyHighPrice() {
		return yearlyHighPrice;
	}

	public void setYearlyHighPrice(long yearlyHighPrice) {
		this.yearlyHighPrice = yearlyHighPrice;
	}

	public long getYearlyLowPrice() {
		return yearlyLowPrice;
	}

	public void setYearlyLowPrice(long yearlyLowPrice) {
		this.yearlyLowPrice = yearlyLowPrice;
	}
	
}
