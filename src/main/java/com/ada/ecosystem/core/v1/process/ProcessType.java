package com.ada.ecosystem.core.v1.process;

/**
 * The Enum ProcessType.
 */
public enum ProcessType {
	
	/** The pago contratista. */
	PAGO_CONTRATISTA("PAGO_CONTRATISTA"),
	
	/** The proceso estandar. */
	PROCESO_ESTANDAR("PROCESO_ESTANDAR")	
	;
	
	/** The value. */
    private String value;

    /**
     * Instantiates a new alias class name.
     *
     * @param value the value
     */
    ProcessType(final String value) {
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
