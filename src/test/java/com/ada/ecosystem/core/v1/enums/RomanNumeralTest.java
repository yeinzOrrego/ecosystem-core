package com.ada.ecosystem.core.v1.enums;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;

public class RomanNumeralTest {

    @Test
    public void testRomanNumeralValues() {
        // Assert
        assertThat(RomanNumeral.I.getValue()).isEqualTo(1);
        assertThat(RomanNumeral.IV.getValue()).isEqualTo(4);
        assertThat(RomanNumeral.V.getValue()).isEqualTo(5);
        assertThat(RomanNumeral.IX.getValue()).isEqualTo(9);
        assertThat(RomanNumeral.X.getValue()).isEqualTo(10);
        assertThat(RomanNumeral.XL.getValue()).isEqualTo(40);
        assertThat(RomanNumeral.L.getValue()).isEqualTo(50);
        assertThat(RomanNumeral.XC.getValue()).isEqualTo(90);
        assertThat(RomanNumeral.C.getValue()).isEqualTo(100);
        assertThat(RomanNumeral.CD.getValue()).isEqualTo(400);
        assertThat(RomanNumeral.D.getValue()).isEqualTo(500);
        assertThat(RomanNumeral.CM.getValue()).isEqualTo(900);
        assertThat(RomanNumeral.M.getValue()).isEqualTo(1000);
    }

    @Test
    public void testGetReverseSortedValues() {
        // Act
        List<RomanNumeral> sortedValues = RomanNumeral.getReverseSortedValues();

        // Assert
        assertThat(sortedValues).containsExactly(
            RomanNumeral.M,
            RomanNumeral.CM,
            RomanNumeral.D,
            RomanNumeral.CD,
            RomanNumeral.C,
            RomanNumeral.XC,
            RomanNumeral.L,
            RomanNumeral.XL,
            RomanNumeral.X,
            RomanNumeral.IX,
            RomanNumeral.V,
            RomanNumeral.IV,
            RomanNumeral.I
        );
    }
}
