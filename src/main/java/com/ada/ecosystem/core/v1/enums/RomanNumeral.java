package com.ada.ecosystem.core.v1.enums;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The Enum RomanNumeral.
 */
public enum RomanNumeral {

	/** The i. */
	I(1),
	/** The iv. */
	IV(4),
	/** The v. */
	V(5),
	/** The ix. */
	IX(9),
	/** The x. */
	X(10),

	/** The xl. */
	XL(40),
	/** The l. */
	L(50),
	/** The xc. */
	XC(90),
	/** The c. */
	C(100),

	/** The cd. */
	CD(400),
	/** The d. */
	D(500),
	/** The cm. */
	CM(900),
	/** The m. */
	M(1000);

	/** The value. */
	private int value;

	/**
	 * Instantiates a new roman numeral.
	 *
	 * @param value the value
	 */
	RomanNumeral(Integer value) {
		this.value = value;
	}

	/**
	 * Gets the value.
	 *
	 * @return the value
	 */
	public Integer getValue() {
		return value;
	}

	/**
	 * Gets the reverse sorted values.
	 *
	 * @return the reverse sorted values
	 */
	public static List<RomanNumeral> getReverseSortedValues() {
		return Arrays.stream(values()).sorted(Comparator.comparing((RomanNumeral e) -> e.value).reversed())
				.collect(Collectors.toList());
	}
}