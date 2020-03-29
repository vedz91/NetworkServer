package org.ServerInteractionDemo.api.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InvalidOperandException extends RuntimeException {

    private static final Logger LOGGER = LoggerFactory.getLogger(InvalidOperandException.class);

    private static final long serialVersionUID = -311110184052856839L;

    public InvalidOperandException(final String message) {
        super(message);
        LOGGER.error("InvalidOperandException: " + message);
    }
}

