package com.angelbroking.smartapi.smartstream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.models.User;
import com.angelbroking.smartapi.smartstream.models.ExchangeType;
import com.angelbroking.smartapi.smartstream.models.SmartStreamSubsMode;
import com.angelbroking.smartapi.smartstream.models.TokenID;
import com.angelbroking.smartapi.smartstream.ticker.SmartStreamTicker;
import com.neovisionaries.ws.client.WebSocketException;

public class SmartStreamTickerTest {
	
	private static String clientID;
	private static String clientPass;
	private static String apiKey;
	private static String feedToken;
	
	@BeforeAll
	public static void initClass() {
		clientID = System.getProperty("clientID");
		clientPass = System.getProperty("clientPass");
		apiKey = System.getProperty("apiKey");
		SmartConnect smartConnect = new SmartConnect(apiKey);
		User user = smartConnect.generateSession(clientID, clientPass);
		feedToken = user.getFeedToken();
	}
	
	@Test
	void testSmartStreamTicketLTP() throws WebSocketException, InterruptedException {
		SmartStreamTicker ticker = new SmartStreamTicker(clientID, feedToken, new SmartStreamListenerImpl());
		ticker.connect();
		ticker.subscribe(SmartStreamSubsMode.QUOTE, getTokens());
		// uncomment the below line to allow test thread to keep running so that ticks can be received in the listener
		Thread.sleep(30000);
//		Thread.currentThread().join();
	}
	
	private Set<TokenID> getTokens(){
		// find out the required token from https://margincalculator.angelbroking.com/OpenAPI_File/files/OpenAPIScripMaster.json
		Set<TokenID> tokenSet = new HashSet<>();
//		tokenSet.add(new TokenID(ExchangeType.NSE_CM, "26009")); // NIFTY BANK
//		tokenSet.add(new TokenID(ExchangeType.NSE_CM, "1594")); // NSE Infosys
//		tokenSet.add(new TokenID(ExchangeType.NSE_CM, "19000"));
//		tokenSet.add(new TokenID(ExchangeType.NCX_FO, "GUAREX31MAR2022")); // GUAREX (NCDEX)
//		tokenSet.add(new TokenID(ExchangeType.NSE_FO, "57919"));
		tokenSet.add(new TokenID(ExchangeType.BSE_CM, "522034"));
		return tokenSet;
	}
	
	@Test
	void testTokenID() {
		TokenID t1 = new TokenID(ExchangeType.NSE_CM, "1594");
		TokenID t2 = new TokenID(ExchangeType.NSE_CM, "4717");
		TokenID t3 = new TokenID(ExchangeType.NSE_CM, "1594");
		TokenID t4 = new TokenID(ExchangeType.NCX_FO, "GUAREX31MAR2022");
		
		assertNotEquals(t1, t2);
		assertEquals(t1, t3);
		
	}
}
