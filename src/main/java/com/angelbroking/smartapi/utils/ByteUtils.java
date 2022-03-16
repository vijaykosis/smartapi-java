package com.angelbroking.smartapi.utils;

import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;

import com.angelbroking.smartapi.smartstream.models.ExchangeType;
import com.angelbroking.smartapi.smartstream.models.LTP;
import com.angelbroking.smartapi.smartstream.models.Quote;
import com.angelbroking.smartapi.smartstream.models.SnapQuote;
import com.angelbroking.smartapi.smartstream.models.TokenID;

public class ByteUtils {
	
	private static final int CHAR_ARRAY_SIZE = 25;
	
	private ByteUtils() {
		
	}

	public static LTP mapToLTP(ByteBuffer packet) {
		LTP pojo = new LTP();
		pojo.setToken(getTokenID(packet));
		pojo.setSequenceNumber(packet.getLong(27));
		pojo.setExchangeFeedTimeEpochMillis(packet.getLong(35));
		pojo.setLastTradedPrice(packet.getLong(43));
		return pojo;
	}

	public static Quote mapToQuote(ByteBuffer packet) {
		Quote pojo = new Quote();
		pojo.setToken(getTokenID(packet));
		pojo.setSequenceNumber(packet.getLong(27));
		pojo.setExchangeFeedTimeEpochMillis(packet.getLong(35));
		pojo.setLastTradedPrice(packet.getLong(43));
		return pojo;
	}
	
	public static SnapQuote mapToSnapQuote(ByteBuffer packet) {
		SnapQuote pojo = new SnapQuote();
		pojo.setToken(getTokenID(packet));
		pojo.setSequenceNumber(packet.getLong(27));
		pojo.setExchangeFeedTimeEpochMillis(packet.getLong(35));
		pojo.setLastTradedPrice(packet.getLong(43));
		return pojo;
	}
	
	public static TokenID getTokenID(ByteBuffer byteBuffer) {
		byte[] token = new byte[CHAR_ARRAY_SIZE];
//		byteBuffer.get(token, 2, CHAR_ARRAY_SIZE);
		for(int i=0; i<CHAR_ARRAY_SIZE; i++) {
			token[i] = byteBuffer.get(2+i);
		}
		return new TokenID(ExchangeType.findByValue(byteBuffer.get(1)), new String(token, StandardCharsets.UTF_8));
	}
}
