package com.demo.test.utilis;

/**
 * The Application constants.
 */
public class ApplicationConstants {

    private ApplicationConstants() {
        throw new IllegalStateException("Utility class");
    }

    public static final String PUSH_IMPORT_STRATEGY_ERROR = "product data push import strategy not implemented";
    public static final String PULL_IMPORT_STRATEGY_ERROR = "product data pull import strategy not implemented";
    public static final String BATCH_IMPORT_STRATEGY_ERROR = "error occurred while importing product data";
    public static final String INVALID_STRATEGY = "invalid data import strategy";

}
