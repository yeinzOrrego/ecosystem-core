package com.ada.ecosystem.core.v1.enums;

/**
 * The Enum ExecutionContext.
 */
public enum ExecutionContext {	
	
	/** The dev. */
	DEV("dev"),
	
	/** The qa. */
	QA("qa"),
	
	/** The pre. */
	PRE("pre"),
	
	/** The prod. */
	PROD("prod");
	
	/** The value. */
    private String value;

    /**
     * Instantiates a new alias class name.
     *
     * @param value the value
     */
	ExecutionContext(final String value) {
        this.value = value;
    }

    /**
     * Gets the value.
     *
     * @return the value
     */
    public String getValue() {
        return value;
    }

    /**
     * To string.
     *
     * @return the string
     */
    @Override
    public String toString() {
        return this.getValue();
    }

}
