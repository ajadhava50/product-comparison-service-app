package com.demo.test.exception;

/**
 * The type Strategy not implemented exception.
 */
public class StrategyNotImplementedException extends RuntimeException {

    private String response;

    /**
     * Instantiates a new Strategy not implemented exception.
     *
     * @param message the message
     */
    public StrategyNotImplementedException(String message) {
        this.response = message;
    }
    @Override
    public String getMessage() {
        return response;
    }

}
