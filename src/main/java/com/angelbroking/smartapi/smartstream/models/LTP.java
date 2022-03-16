package com.angelbroking.smartapi.smartstream.models;

public class LTP {
	public static final int PACKET_SIZE_IN_BYTES = 51;
	
	private TokenID token;
	private long sequenceNumber;
	private long exchangeFeedTimeEpochMillis;
	private long lastTradedPrice;

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

}
