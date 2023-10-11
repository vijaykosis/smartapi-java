package com.angelbroking.smartapi.smartstream.models;

import static com.angelbroking.smartapi.utils.Constants.AVG_TRADED_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.CLOSE_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.EXCHANGE_FEED_TIME_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.EXCHANGE_TYPE;
import static com.angelbroking.smartapi.utils.Constants.HIGH_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.LAST_TRADED_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.LAST_TRADED_QTY_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.LOW_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.OPEN_PRICE_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.SEQUENCE_NUMBER_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.SUBSCRIPTION_MODE;
import static com.angelbroking.smartapi.utils.Constants.TOTAL_BUY_QTY_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.TOTAL_SELL_QTY_OFFSET;
import static com.angelbroking.smartapi.utils.Constants.VOLUME_TRADED_TODAY_OFFSET;

import java.nio.ByteBuffer;

import com.angelbroking.smartapi.utils.ByteUtils;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Quote {
	private byte subscriptionMode;
	private ExchangeType exchangeType;
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

	public Quote(ByteBuffer buffer) {
        this.subscriptionMode = buffer.get(SUBSCRIPTION_MODE);
        this.token = ByteUtils.getTokenID(buffer);
        this.exchangeType = this.token.getExchangeType();
        this.sequenceNumber = buffer.getLong(SEQUENCE_NUMBER_OFFSET);
        this.exchangeFeedTimeEpochMillis = buffer.getLong(EXCHANGE_FEED_TIME_OFFSET);
        this.lastTradedPrice = buffer.getLong(LAST_TRADED_PRICE_OFFSET);
        this.lastTradedQty = buffer.getLong(LAST_TRADED_QTY_OFFSET);
        this.avgTradedPrice = buffer.getLong(AVG_TRADED_PRICE_OFFSET);
        this.volumeTradedToday = buffer.getLong(VOLUME_TRADED_TODAY_OFFSET);
        this.totalBuyQty = buffer.getLong(TOTAL_BUY_QTY_OFFSET);
        this.totalSellQty = buffer.getLong(TOTAL_SELL_QTY_OFFSET);
        this.openPrice = buffer.getLong(OPEN_PRICE_OFFSET);
        this.highPrice = buffer.getLong(HIGH_PRICE_OFFSET);
        this.lowPrice = buffer.getLong(LOW_PRICE_OFFSET);
        this.closePrice = buffer.getLong(CLOSE_PRICE_OFFSET);
    }
}
