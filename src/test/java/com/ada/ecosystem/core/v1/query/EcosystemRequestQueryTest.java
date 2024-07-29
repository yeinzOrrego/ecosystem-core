package com.ada.ecosystem.core.v1.query;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EcosystemRequestQueryTest {

    @Test
    public void testDefaultConstructor() {
        // Act
        EcosystemRequestQuery query = new EcosystemRequestQuery(0, 0, new ArrayList<>(), new ArrayList<>());

        // Assert
        assertEquals(0, query.getPage(), "Default page should be 0");
        assertEquals(0, query.getSize(), "Default size should be 0");
        assertTrue(query.getOrdersBy().isEmpty(), "OrdersBy should be empty");
        assertTrue(query.getSearchsBy().isEmpty(), "SearchsBy should be empty");
    }

    @Test
    public void testParameterizedConstructor() {
        // Arrange
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        int expectedPage = 1;
        int expectedSize = 10;
        SearchCriteria searchCriteria = new SearchCriteria("key", SearchOperation.EQUAL, "value");
        SortOperation direction = null;
        SortCriteria sortCriteria = new SortCriteria("field", direction);
        searchCriteriaList.add(searchCriteria);
        sortCriteriaList.add(sortCriteria);

        // Act
        EcosystemRequestQuery query = new EcosystemRequestQuery(expectedPage, expectedSize, sortCriteriaList, searchCriteriaList);

        // Assert
        assertEquals(expectedPage, query.getPage(), "Page number should be 1");
        assertEquals(expectedSize, query.getSize(), "Size should be 10");
        assertEquals(sortCriteriaList, query.getOrdersBy(), "OrdersBy list should match");
        assertEquals(searchCriteriaList, query.getSearchsBy(), "SearchsBy list should match");
    }

    @Test
    public void testGettersAndSetters() {
        // Arrange
        List<SearchCriteria> searchCriteriaList = new ArrayList<>();
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        int expectedPage = 1;
        int expectedSize = 10;
        SearchCriteria searchCriteria = new SearchCriteria("key", SearchOperation.EQUAL, "value");
        SortOperation direction = null;
        SortCriteria sortCriteria = new SortCriteria("field", direction);
        searchCriteriaList.add(searchCriteria);
        sortCriteriaList.add(sortCriteria);

        EcosystemRequestQuery query = new EcosystemRequestQuery(expectedSize, expectedSize, sortCriteriaList, searchCriteriaList);
        query.setPage(expectedPage);
        query.setSize(expectedSize);
        query.setOrdersBy(sortCriteriaList);
        query.setSearchsBy(searchCriteriaList);

        // Act & Assert
        assertEquals(expectedPage, query.getPage(), "Page number should be 1");
        assertEquals(expectedSize, query.getSize(), "Size should be 10");
        assertEquals(sortCriteriaList, query.getOrdersBy(), "OrdersBy list should match");
        assertEquals(searchCriteriaList, query.getSearchsBy(), "SearchsBy list should match");
    }

    @Test
    public void testEmptyLists() {
        // Arrange
        EcosystemRequestQuery query = new EcosystemRequestQuery(1, 10, new ArrayList<>(), new ArrayList<>());

        // Act & Assert
        assertTrue(query.getOrdersBy().isEmpty(), "OrdersBy should be empty");
        assertTrue(query.getSearchsBy().isEmpty(), "SearchsBy should be empty");
    }
}
