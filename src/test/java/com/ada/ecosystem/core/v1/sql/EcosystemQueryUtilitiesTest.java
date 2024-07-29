package com.ada.ecosystem.core.v1.sql;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EcosystemQueryUtilitiesTest {

    @Test
    void testSingletonInstance() {
        // Get two instances of EcosystemQueryUtilities
        EcosystemQueryUtilities instance1 = EcosystemQueryUtilities.getInstance();
        EcosystemQueryUtilities instance2 = EcosystemQueryUtilities.getInstance();
        
        // Verify that both instances are the same
        assertSame(instance1, instance2, "Instances should be the same for singleton pattern");
    }

    @Test
    void testQueryConstant() {
        // Define the expected query string
        String expectedQuery = """
            SELECT  NVL(MT.FE_NO_OBLIGADO_FACTURAR, 'N')      
            FROM    TESORE01.MAESTRO_TERCEROS MT
            WHERE   MT.CODIGO_TERCERO = :an_ctercero
            """;

        // Verify that the query constant matches the expected value
        assertEquals(expectedQuery, EcosystemQueryUtilities.QUERY_001_TERCERO_NO_OBLIGADO, "Query constant should match the expected value");
    }
}
