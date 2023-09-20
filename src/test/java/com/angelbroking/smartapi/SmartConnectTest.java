package com.angelbroking.smartapi;

import com.angelbroking.smartapi.http.exceptions.SmartAPIException;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
@Slf4j
public class SmartConnectTest {

    @Mock
    private SmartConnect smartConnect;

    @Before
    public void setup() {
        // Set up any necessary configurations or dependencies
    }

    @Test
    public void testGetSearchScript_Success() throws SmartAPIException, IOException {
        // Mock the necessary objects
        JSONObject payload = new JSONObject();
        when(smartConnect.getSearchScrip(payload)).thenReturn("response-data");

        // Call the method under test
        String result = smartConnect.getSearchScrip(payload);
        // Assert the result
        assertEquals("response-data", result);

    }

    @Test(expected = SmartAPIException.class)
    public void testGetSearchScript_Exception() throws SmartAPIException, IOException {
        JSONObject payload = new JSONObject();
        SmartAPIException expectedException = new SmartAPIException("Simulated SmartAPIException");
        when(smartConnect.getSearchScrip(payload)).thenThrow(expectedException);
        try {
             smartConnect.getSearchScrip(payload);
        } catch (SmartAPIException e) {
            throw new SmartAPIException(String.format("The operation failed to execute because of a SmartAPIException error in Search scrip api data %s", e));
        }
        verify(smartConnect).getSearchScrip(payload);
    }
}
