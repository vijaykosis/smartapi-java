package com.angelbroking.smartapi;

import com.angelbroking.smartapi.http.SmartAPIRequestHandler;
import com.angelbroking.smartapi.http.exceptions.DataException;
import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.Optional;

import static com.angelbroking.smartapi.utils.Constants.IO_EXCEPTION_ERROR_MSG;
import static com.angelbroking.smartapi.utils.Constants.IO_EXCEPTION_OCCURRED;
import static com.angelbroking.smartapi.utils.Constants.JSON_EXCEPTION_ERROR_MSG;
import static com.angelbroking.smartapi.utils.Constants.JSON_EXCEPTION_OCCURRED;
import static com.angelbroking.smartapi.utils.Constants.SMART_API_EXCEPTION_ERROR_MSG;
import static com.angelbroking.smartapi.utils.Constants.SMART_API_EXCEPTION_OCCURRED;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.Silent.class)
@Slf4j
public class SmartConnectTest {

    @Mock
    private SmartAPIRequestHandler smartAPIRequestHandler;

    @Mock
    private SmartConnect smartConnect;

    @Mock
    private Routes routes;
    private String apiKey;
    private String accessToken;

    private static JSONObject createMarketDataResponse() {
        JSONObject jsonObject = new JSONObject();

        // Create "data" object
        JSONObject dataObject = new JSONObject();

        // Create the "unfetched" array
        JSONArray unfetchedArray = new JSONArray();
        dataObject.put("unfetched", unfetchedArray);

        // Create the "fetched" array and its elements
        JSONArray fetchedArray = new JSONArray();
        JSONObject fetchedElement = new JSONObject();
        fetchedElement.put("netChange", 3.15);
        fetchedElement.put("tradeVolume", 5718111);
        fetchedElement.put("lowerCircuit", 533.15);
        fetchedElement.put("percentChange", 0.53);
        fetchedElement.put("exchFeedTime", "19-Jul-2023 11:20:51");
        fetchedElement.put("avgPrice", 595.25);
        fetchedElement.put("ltp", 595.5);
        fetchedElement.put("exchTradeTime", "19-Jul-2023 11:20:51");
        fetchedElement.put("totSellQuan", 1924292);
        fetchedElement.put("upperCircuit", 651.55);
        fetchedElement.put("lastTradeQty", 46);
        fetchedElement.put("high", 599.6);
        fetchedElement.put("totBuyQuan", 890300);

        // Create the "depth" object and its "buy" and "sell" arrays
        JSONObject depthObject = new JSONObject();
        JSONArray buyArray = new JSONArray();
        JSONArray sellArray = new JSONArray();

        // Add elements to "buy" array
        JSONObject buyElement1 = new JSONObject();
        buyElement1.put("quantity", 1776);
        buyElement1.put("price", 595.3);
        buyElement1.put("orders", 3);
        buyArray.put(buyElement1);

        JSONObject buyElement2 = new JSONObject();
        buyElement2.put("quantity", 767);
        buyElement2.put("price", 595.25);
        buyElement2.put("orders", 3);
        buyArray.put(buyElement2);

        // Add elements to "sell" array
        JSONObject sellElement1 = new JSONObject();
        sellElement1.put("quantity", 249);
        sellElement1.put("price", 595.5);
        sellElement1.put("orders", 5);
        sellArray.put(sellElement1);

        JSONObject sellElement2 = new JSONObject();
        sellElement2.put("quantity", 1379);
        sellElement2.put("price", 595.55);
        sellElement2.put("orders", 4);
        sellArray.put(sellElement2);

        // Add "buy" and "sell" arrays to "depth" object
        depthObject.put("buy", buyArray);
        depthObject.put("sell", sellArray);

        fetchedElement.put("depth", depthObject);

        // Add remaining properties to "fetched" element
        fetchedElement.put("low", 592);
        fetchedElement.put("exchange", "NSE");
        fetchedElement.put("opnInterest", 0);
        fetchedElement.put("tradingSymbol", "SBIN-EQ");
        fetchedElement.put("symbolToken", "3045");
        fetchedElement.put("close", 592.35);
        fetchedElement.put("52WeekLow", 482.1);
        fetchedElement.put("open", 594.65);
        fetchedElement.put("52WeekHigh", 629.55);

        // Add the "fetched" element to the "fetched" array
        fetchedArray.put(fetchedElement);

        dataObject.put("fetched", fetchedArray);

        // Add "data" object, "message", "errorcode", and "status" properties to the main JSON object
        jsonObject.put("data", dataObject);
        jsonObject.put("message", "SUCCESS");
        jsonObject.put("errorcode", "");
        jsonObject.put("status", true);

        return jsonObject;
    }

    public static JSONObject getAllHoldingsResponse() {
        JSONObject response = new JSONObject();

        // Create the "data" object
        JSONObject dataObject = new JSONObject();
        dataObject.put("totalholdingvalue", 77);
        dataObject.put("totalprofitandloss", 0.5);
        dataObject.put("totalpnlpercentage", 0.65);
        dataObject.put("totalinvvalue", 77);

        // Create the "holdings" array
        JSONArray holdingsArray = new JSONArray();

        // Create individual "holding" objects and add them to the array
        JSONObject holding1 = new JSONObject();
        holding1.put("t1quantity", 0);
        holding1.put("authorisedquantity", 0);
        holding1.put("product", "DELIVERY");
        holding1.put("quantity", 1);
        holding1.put("ltp", 47.95);
        holding1.put("haircut", 0);
        holding1.put("profitandloss", 0);
        holding1.put("collateraltype", Optional.ofNullable(null));
        holding1.put("averageprice", 48.15);
        holding1.put("tradingsymbol", "CENTRALBK-EQ");
        holding1.put("pnlpercentage", -0.42);
        holding1.put("exchange", "NSE");
        holding1.put("close", 49.95);
        holding1.put("isin", "INE483A01010");
        holding1.put("realisedquantity", 0);
        holding1.put("symboltoken", "14894");
        holding1.put("collateralquantity", Optional.ofNullable(null));
        holdingsArray.put(holding1);

        JSONObject holding2 = new JSONObject();
        holding2.put("t1quantity", 0);
        holding2.put("authorisedquantity", 0);
        holding2.put("product", "DELIVERY");
        holding2.put("quantity", 2);
        holding2.put("ltp", 11.2);
        holding2.put("haircut", 0);
        holding2.put("profitandloss", 1);
        holding2.put("collateraltype", Optional.ofNullable(null));
        holding2.put("averageprice", 10.85);
        holding2.put("tradingsymbol", "IDEA-EQ");
        holding2.put("pnlpercentage", 3.23);
        holding2.put("exchange", "NSE");
        holding2.put("close", 10.95);
        holding2.put("isin", "INE669E01016");
        holding2.put("realisedquantity", 0);
        holding2.put("symboltoken", "14366");
        holding2.put("collateralquantity", Optional.ofNullable(null));
        holdingsArray.put(holding2);

        JSONObject holding3 = new JSONObject();
        holding3.put("t1quantity", 0);
        holding3.put("authorisedquantity", 0);
        holding3.put("product", "DELIVERY");
        holding3.put("quantity", 2);
        holding3.put("ltp", 3.6);
        holding3.put("haircut", 0);
        holding3.put("profitandloss", 0);
        holding3.put("collateraltype", Optional.ofNullable(null));
        holding3.put("averageprice", 3.6);
        holding3.put("tradingsymbol", "VIKASECO-BE");
        holding3.put("pnlpercentage", 0);
        holding3.put("exchange", "NSE");
        holding3.put("close", 3.75);
        holding3.put("isin", "INE806A01020");
        holding3.put("realisedquantity", 0);
        holding3.put("symboltoken", "25760");
        holding3.put("collateralquantity", Optional.ofNullable(null));
        holdingsArray.put(holding3);

        // Add the "data" object and "holdings" array to the main response object
        dataObject.put("holdings", holdingsArray);
        response.put("data", dataObject);

        // Add other properties to the response object if needed
        response.put("message", "SUCCESS");
        response.put("errorcode", "");
        response.put("status", true);

        // Print the JSON response
        return response;
    }
    @Before
    public void setup() {
        apiKey = System.getProperty("apiKey");
        accessToken = System.getenv("accessToken");
    }

    // Testing market data success for Full payload
    @Test
    public void testMarketData_Success() throws SmartAPIException, IOException {
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("FULL");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken))).thenReturn(createMarketDataResponse());
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(this.apiKey, url, params, this.accessToken);
            JSONObject data = response.getJSONObject("data");
            assertNotNull(data);
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    // Testing market data failure for Full payload
    @Test(expected = SmartAPIException.class)
    public void testMarketData_Failure() throws SmartAPIException, IOException {
        // Stub the postRequest method
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("FULL");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken)))
                .thenThrow(new SmartAPIException("API request failed"));
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(apiKey, url, params, accessToken);
            response.getJSONObject("data");
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    // Testing market data success for LTP payload
    @Test
    public void testMarketDataLTP_Success() throws SmartAPIException, IOException {
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("LTP");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken))).thenReturn(createMarketDataResponse());
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(this.apiKey, url, params, this.accessToken);
            JSONObject data = response.getJSONObject("data");
            assertNotNull(data);
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    // Testing market data failure for LTP payload
    @Test(expected = SmartAPIException.class)
    public void testMarketDataLTP_Failure() throws SmartAPIException, IOException {
        // Stub the postRequest method
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("LTP");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken)))
                .thenThrow(new SmartAPIException("API request failed"));
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(apiKey, url, params, accessToken);
            response.getJSONObject("data");
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    // Testing market data success for OHLC payload
    @Test
    public void testMarketDataOHLC_Success() throws SmartAPIException, IOException {
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("OHLC");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken))).thenReturn(createMarketDataResponse());
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(this.apiKey, url, params, this.accessToken);
            JSONObject data = response.getJSONObject("data");
            assertNotNull(data);
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    // Testing market data failure for OHLC payload
    @Test(expected = SmartAPIException.class)
    public void testMarketDataOHLC_Failure() throws SmartAPIException, IOException {
        // Stub the postRequest method
        String url = routes.get("api.market.data");
        JSONObject params = getMarketDataRequest("OHLC");
        when(smartAPIRequestHandler.postRequest(eq(this.apiKey), eq(url), eq(params), eq(this.accessToken)))
                .thenThrow(new SmartAPIException("API request failed"));
        try {
            JSONObject response = smartAPIRequestHandler.postRequest(apiKey, url, params, accessToken);
            response.getJSONObject("data");
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    private JSONObject getMarketDataRequest(String mode) {
        JSONObject payload = new JSONObject();
        payload.put("mode", mode);
        JSONObject exchangeTokens = new JSONObject();
        JSONArray nseTokens = new JSONArray();
        nseTokens.put("3045");
        exchangeTokens.put("NSE", nseTokens);
        payload.put("exchangeTokens", exchangeTokens);
        return payload;
    }

    @Test
    public void testRetrieveAllHoldings_successfully() throws SmartAPIException, IOException {
        String url = routes.get("api.order.rms.AllHolding");
        when(smartAPIRequestHandler.getRequest(eq(this.apiKey), eq(url), eq(this.accessToken))).thenReturn(getAllHoldingsResponse());

        try {
            JSONObject response = smartAPIRequestHandler.getRequest(apiKey, url, accessToken);
            JSONObject data = response.getJSONObject("data");
            JSONArray holding = data.getJSONArray("holdings");

            assertNotNull(data);
            assertNotNull(holding);
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }

    @Test(expected = SmartAPIException.class)
    public void testRetrieveAllHoldings_failure() throws SmartAPIException, IOException {
        String url = routes.get("api.order.rms.AllHolding");
        when(smartAPIRequestHandler.getRequest(eq(this.apiKey), eq(url), eq(this.accessToken))).thenThrow(SmartAPIException.class);

        try {
            JSONObject response = smartAPIRequestHandler.getRequest(apiKey, url, accessToken);
            JSONObject data = response.getJSONObject("data");
            assertNotNull(data);
        } catch (SmartAPIException ex) {
            log.error("{} while placing order {}", SMART_API_EXCEPTION_OCCURRED, ex.toString());
            throw new SmartAPIException(String.format("%s in placing order %s", SMART_API_EXCEPTION_ERROR_MSG, ex));
        } catch (IOException ex) {
            log.error("{} while placing order {}", IO_EXCEPTION_OCCURRED, ex.getMessage());
            throw new IOException(String.format("%s in placing order %s", IO_EXCEPTION_ERROR_MSG, ex.getMessage()));
        } catch (JSONException ex) {
            log.error("{} while placing order {}", JSON_EXCEPTION_OCCURRED, ex.getMessage());
            throw new JSONException(String.format("%s in placing order %s", JSON_EXCEPTION_ERROR_MSG, ex.getMessage()));
        }
    }
}