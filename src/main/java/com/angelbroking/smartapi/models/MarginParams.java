package com.angelbroking.smartapi.models;

/**
 * A wrapper class for margin params to get the margin using smartAPI margin url
 */

public class MarginParams {

    /**
     * Exchange in which instrument is listed (NSE, BSE, NFO, BFO, CDS, MCX).
     */
    public String exchange;

    /**
     * Quantity to transact
     */
    public Integer quantity;


    public Double price;

    /**
     * producttype code (NRML, MIS, CNC, NRML, MARGIN, BO, CO).
     */
    public String productType;

    /**
     * symboltoken of the instrument.
     */
    public String token;

    /**
     * Buy or Sell
     */
    public String tradeType;


}
