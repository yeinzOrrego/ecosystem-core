package com.ada.ecosystem.core.v1.query;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class SearchSpecificationsTest {

    @Mock
    private CriteriaBuilder criteriaBuilder;

    @Mock
    private CriteriaQuery<?> criteriaQuery;

    @Mock
    private Root<Object> root;

    @InjectMocks
    private SearchSpecifications<Object> searchSpecifications;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testToPredicateWithGreaterThan() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.GREATER_THAN, "10");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.greaterThan(root.get("testKey"), "10")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).greaterThan(root.get("testKey"), "10");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithLessThan() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.LESS_THAN, "20");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.lessThan(root.get("testKey"), "20")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).lessThan(root.get("testKey"), "20");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithGreaterThanEqual() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.GREATER_THAN_EQUAL, "30");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.greaterThanOrEqualTo(root.get("testKey"), "30")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).greaterThanOrEqualTo(root.get("testKey"), "30");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithLessThanEqual() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.LESS_THAN_EQUAL, "40");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.lessThanOrEqualTo(root.get("testKey"), "40")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).lessThanOrEqualTo(root.get("testKey"), "40");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithNotEqual() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.NOT_EQUAL, "50");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.notEqual(root.get("testKey"), "50")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).notEqual(root.get("testKey"), "50");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithEqual() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.EQUAL, "60");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.equal(root.get("testKey"), "60")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

        Predicate result = searchSpecifications.toPredicate(root, criteriaQuery, criteriaBuilder);

        verify(criteriaBuilder).equal(root.get("testKey"), "60");
        assertNotNull(result);
    }

    @Test
    public void testToPredicateWithMatch() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.MATCH, "value");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.like(criteriaBuilder.lower(root.get("testKey")), "%value%")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

    }

    @Test
    public void testToPredicateWithMatchStart() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.MATCH_START, "startValue");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.like(criteriaBuilder.lower(root.get("testKey")), "%startValue")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

    }

    @Test
    public void testToPredicateWithMatchEnd() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.MATCH_END, "endValue");
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.like(criteriaBuilder.lower(root.get("testKey")), "endValue%")).thenReturn(predicate);
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);


    }

    @SuppressWarnings("unchecked")
	@Test
    public void testToPredicateWithIn() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.IN, Arrays.asList("value1", "value2"));
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.in(root.get("testKey"))).thenReturn(mock(CriteriaBuilder.In.class));
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);

    }

    @Test
    public void testToPredicateWithNotIn() {
        SearchCriteria criteria = new SearchCriteria("testKey", SearchOperation.NOT_IN, Arrays.asList("value1", "value2"));
        searchSpecifications.add(criteria);

        Predicate predicate = mock(Predicate.class);
        when(criteriaBuilder.not(root.get("testKey"))).thenReturn(mock(CriteriaBuilder.In.class));
        when(criteriaBuilder.and(any(Predicate[].class))).thenReturn(predicate);
    }
}
