package com.ada.ecosystem.core.v1.pageable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class PageDtoTest {

    private PageDto<String> pageDto;

    @BeforeEach
    void setUp() {
        pageDto = new PageDto<>();
    }

    @Test
    void testGettersAndSetters() {
        // Set values
        pageDto.setSize(10);
        pageDto.setNumber(1);
        pageDto.setNumberOfElements(5);
        pageDto.setTotalElements(50L);
        pageDto.setTotalPages(5);
        pageDto.setContent(Arrays.asList("item1", "item2", "item3"));

        // Test getters
        assertEquals(10, pageDto.getSize(), "Size should be 10");
        assertEquals(1, pageDto.getNumber(), "Number should be 1");
        assertEquals(5, pageDto.getNumberOfElements(), "Number of elements should be 5");
        assertEquals(50L, pageDto.getTotalElements(), "Total elements should be 50");
        assertEquals(5, pageDto.getTotalPages(), "Total pages should be 5");
        assertEquals(Arrays.asList("item1", "item2", "item3"), pageDto.getContent(), "Content should match the given list");
    }

    @Test
    void testNullValues() {
        // Set null values
        pageDto.setSize(null);
        pageDto.setNumber(null);
        pageDto.setNumberOfElements(null);
        pageDto.setTotalElements(null);
        pageDto.setTotalPages(null);
        pageDto.setContent(null);

        // Test getters
        assertNull(pageDto.getSize(), "Size should be null");
        assertNull(pageDto.getNumber(), "Number should be null");
        assertNull(pageDto.getNumberOfElements(), "Number of elements should be null");
        assertNull(pageDto.getTotalElements(), "Total elements should be null");
        assertNull(pageDto.getTotalPages(), "Total pages should be null");
        assertNull(pageDto.getContent(), "Content should be null");
    }

    @Test
    void testEqualsAndHashCode() {
        PageDto<String> pageDto1 = new PageDto<>();
        pageDto1.setSize(10);
        pageDto1.setNumber(1);
        pageDto1.setNumberOfElements(5);
        pageDto1.setTotalElements(50L);
        pageDto1.setTotalPages(5);
        pageDto1.setContent(Arrays.asList("item1", "item2", "item3"));

        PageDto<String> pageDto2 = new PageDto<>();
        pageDto2.setSize(10);
        pageDto2.setNumber(1);
        pageDto2.setNumberOfElements(5);
        pageDto2.setTotalElements(50L);
        pageDto2.setTotalPages(5);
        pageDto2.setContent(Arrays.asList("item1", "item2", "item3"));

        PageDto<String> pageDto3 = new PageDto<>();
        pageDto3.setSize(10);
        pageDto3.setNumber(1);
        pageDto3.setNumberOfElements(5);
        pageDto3.setTotalElements(50L);
        pageDto3.setTotalPages(5);
        pageDto3.setContent(Collections.singletonList("item1"));

        // Test equality
        assertEquals(pageDto1, pageDto2, "Objects should be equal");
        assertNotEquals(pageDto1, pageDto3, "Objects with different content should not be equal");

        // Test hash code
        assertEquals(pageDto1.hashCode(), pageDto2.hashCode(), "Hash codes should be equal");
        assertNotEquals(pageDto1.hashCode(), pageDto3.hashCode(), "Hash codes should be different for different content");
    }

    @Test
    void testToString() {
        pageDto.setSize(10);
        pageDto.setNumber(1);
        pageDto.setNumberOfElements(5);
        pageDto.setTotalElements(50L);
        pageDto.setTotalPages(5);
        pageDto.setContent(Arrays.asList("item1", "item2", "item3"));

        String expectedString = "PageDto(size=10, number=1, numberOfElements=5, totalElements=50, totalPages=5, content=[item1, item2, item3])";
        assertEquals(expectedString, pageDto.toString(), "ToString should match expected format");
    }

    @Test
    void testNullEquals() {
        // Arrange
        PageDto<String> dto1 = new PageDto<>();
        dto1.setSize(10);
        dto1.setNumber(1);

        // Act & Assert
        assertNotEquals(dto1, null, "PageDto should not be equal to null");
    }

    @Test
    void testEqualityWithDifferentTypes() {
        // Arrange
        PageDto<String> dto = new PageDto<>();
        dto.setSize(10);
        dto.setNumber(1);
        dto.setNumberOfElements(5);
        dto.setTotalElements(50L);
        dto.setTotalPages(5);
        dto.setContent(Arrays.asList("item1", "item2", "item3"));

        // Act & Assert
        assertNotEquals(dto, new Object(), "PageDto should not be equal to an object of different type");
    }

    @Test
    void testEmptyContent() {
        pageDto.setSize(10);
        pageDto.setNumber(1);
        pageDto.setNumberOfElements(0);
        pageDto.setTotalElements(50L);
        pageDto.setTotalPages(5);
        pageDto.setContent(Collections.emptyList());

        assertEquals(Collections.emptyList(), pageDto.getContent(), "Content should be an empty list");
    }

    @Test
    void testStringRepresentationWithNulls() {
        pageDto.setSize(null);
        pageDto.setNumber(null);
        pageDto.setNumberOfElements(null);
        pageDto.setTotalElements(null);
        pageDto.setTotalPages(null);
        pageDto.setContent(null);

        String expectedString = "PageDto(size=null, number=null, numberOfElements=null, totalElements=null, totalPages=null, content=null)";
        assertEquals(expectedString, pageDto.toString(), "ToString should match expected format with null values");
    }
}
