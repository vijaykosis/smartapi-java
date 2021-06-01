package com.angelbroking.smartapi.sample;

import com.angelbroking.smartapi.SmartConnect;
import com.angelbroking.smartapi.http.SessionExpiryHook;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import com.angelbroking.smartapi.models.User;

public class Test {

	public static void main(String[] args) throws SmartAPIException {
		try {

			SmartConnect smartConnect = new SmartConnect("<smartapi_key>"); // PROVIDE YOUR API KEY HERE
			
			// OPTIONAL - ACCESS_TOKEN AND REFRESH TOKEN
			// SmartConnect smartConnect = new SmartConnect("<smartapi_key>", "<YOUR_ACCESS_TOKEN>", "<YOUR_REFRESH_TOKEN>");

			// Set session expiry callback.
			smartConnect.setSessionExpiryHook(new SessionExpiryHook() {
				@Override
				public void sessionExpired() {
					System.out.println("session expired");
				}
			});

			User user = smartConnect.generateSession("S212741","*********");
			System.out.println(user.getAccessToken());
			smartConnect.setAccessToken(user.getAccessToken());
			smartConnect.setUserId(user.getUserId());

			// token re-generate testing
			/*
			 * TokenSet tokenSet = smartConnect.renewAccessToken(user.getAccessToken(),
			 * user.getRefreshToken());
			 * smartConnect.setAccessToken(tokenSet.getAccessToken());
			 */

			Examples examples = new Examples();

			System.out.println("getProfile");
			examples.getProfile(smartConnect);

			System.out.println("placeOrder");
			examples.placeOrder(smartConnect);

			System.out.println("modifyOrder");
//			examples.modifyOrder(smartConnect);

			System.out.println("cancelOrder");
//			examples.cancelOrder(smartConnect);

			System.out.println("getOrder");
//			examples.getOrder(smartConnect);
//
			System.out.println("getLTP");
//			examples.getLTP(smartConnect);
//
			System.out.println("getTrades");
//			examples.getTrades(smartConnect);
//
			System.out.println("getRMS");
//			examples.getRMS(smartConnect);
//
			System.out.println("getHolding");
//			examples.getHolding(smartConnect);
//
			System.out.println("getPosition");
//			examples.getPosition(smartConnect);
//
			System.out.println("convertPosition");
<<<<<<< HEAD
//			examples.convertPosition(smartConnect);
		
			System.out.println("createRule");
//			examples.createRule(smartConnect);
			
			System.out.println("ModifyRule");
//			examples.modifyRule(smartConnect);
			
			System.out.println("cancelRule");
//			examples.cancelRule(smartConnect);
			
=======
			examples.convertPosition(smartConnect);

			System.out.println("createRule");
			examples.createRule(smartConnect);

			System.out.println("ModifyRule");
			examples.modifyRule(smartConnect);

			System.out.println("cancelRule");
			examples.cancelRule(smartConnect);

>>>>>>> 19f1a71f12e4d6e730d6e8e69948bf9a95102d2b
			System.out.println("Rule Details");
//			examples.ruleDetails(smartConnect);
//			
			System.out.println("Rule List");
<<<<<<< HEAD
//			examples.ruleList(smartConnect);
		
			System.out.println("Historic candle Data");
//			examples.getCandleData(smartConnect);
			
=======
			examples.ruleList(smartConnect);

			System.out.println("Historic candle Data");
			examples.getCandleData(smartConnect);

>>>>>>> 19f1a71f12e4d6e730d6e8e69948bf9a95102d2b
			System.out.println("logout");
//			examples.logout(smartConnect);

			// SmartAPITicker
			String clientId = "<clientId>";

			// feedToken - User user = smartConnect.generateSession("<clientId>",
			// "<password>");
			String feedToken = user.getFeedToken();
			String strWatchListScript = "nse_cm|2885&nse_cm|1594&nse_cm|11536&mcx_fo|221658";
			String task = "mw";
<<<<<<< HEAD
//			examples.tickerUsage(clientId, feedToken, strWatchListScript, task);
=======
			// examples.tickerUsage(clientId, feedToken, strWatchListScript, task);
>>>>>>> 19f1a71f12e4d6e730d6e8e69948bf9a95102d2b

		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
			e.printStackTrace();
		}

	}
}
