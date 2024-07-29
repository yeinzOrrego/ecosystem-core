package com.ada.ecosystem.core.v1.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import com.ada.ecosystem.core.v1.pageable.PageDto;
import com.ada.ecosystem.core.v1.query.SearchCriteria;
import com.ada.ecosystem.core.v1.query.SearchOperation;
import com.ada.ecosystem.core.v1.query.SearchSpecifications;
import com.ada.ecosystem.core.v1.query.SortCriteria;
import com.ada.ecosystem.core.v1.query.SortOperation;

public class EcosystemServiceTest {

    private EcosystemService ecosystemService;

    @BeforeEach
    void setUp() {
        ecosystemService = new EcosystemService() {
        };
    }

    @Test
    void testGetPageableWithSortBy() {
        Integer pageNo = 0;
        String sortBy = "name:desc,age:asc";
        Pageable pageable = ecosystemService.getPageable(pageNo, sortBy);

        assertNotNull(pageable);
        assertEquals(pageNo, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize()); // Default PAGE_SIZE
        assertTrue(pageable.getSort().isSorted());
    }

    @Test
    void testGetPageableWithPageSizeAndSortBy() {
        Integer pageNo = 1;
        Integer pageSize = 20;
        String sortBy = "name:desc";
        Pageable pageable = ecosystemService.getPageable(pageNo, pageSize, sortBy);

        assertNotNull(pageable);
        assertEquals(pageNo, pageable.getPageNumber());
        assertEquals(pageSize, pageable.getPageSize());
        assertTrue(pageable.getSort().isSorted());
    }

    @Test
    void testGetPageableWithOrdersBy() {
        Integer pageNo = 0;
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        sortCriteriaList.add(new SortCriteria("name", SortOperation.DESC));
        Pageable pageable = ecosystemService.getPageable(pageNo, sortCriteriaList);

        assertNotNull(pageable);
        assertEquals(pageNo, pageable.getPageNumber());
        assertEquals(10, pageable.getPageSize()); // Default PAGE_SIZE
        assertTrue(pageable.getSort().isSorted());
    }

    @Test
    void testGetPageableWithPageSizeAndOrdersBy() {
        Integer pageNo = 1;
        Integer pageSize = 20;
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        sortCriteriaList.add(new SortCriteria("name", SortOperation.ASC));
        Pageable pageable = ecosystemService.getPageable(pageNo, pageSize, sortCriteriaList);

        assertNotNull(pageable);
        assertEquals(pageNo, pageable.getPageNumber());
        assertEquals(pageSize, pageable.getPageSize());
        assertTrue(pageable.getSort().isSorted());
    }

    @Test
    void testBuildAdvancedSort() {
        String sortBy = "name:desc,age:asc";
        Sort sort = ecosystemService.buildAdvancedSort(sortBy);

        assertNotNull(sort);
        assertEquals(2, sort.get().count());
        assertEquals(Sort.Direction.DESC, sort.getOrderFor("name").getDirection());
        assertEquals(Sort.Direction.ASC, sort.getOrderFor("age").getDirection());
    }

    @Test
    void testBuildAdvancedSortWithList() {
        List<SortCriteria> sortCriteriaList = new ArrayList<>();
        sortCriteriaList.add(new SortCriteria("name", SortOperation.DESC));
        sortCriteriaList.add(new SortCriteria("age", SortOperation.ASC));

        Sort sort = ecosystemService.buildAdvancedSort(sortCriteriaList);

        assertNotNull(sort);
        assertEquals(2, sort.get().count());
        assertEquals(Sort.Direction.DESC, sort.getOrderFor("name").getDirection());
        assertEquals(Sort.Direction.ASC, sort.getOrderFor("age").getDirection());
    }

    @Test
    void testGetSearchSpecifications() {
        SearchSpecifications<String> searchSpecifications = new SearchSpecifications<>();
        Object value = null;
        SearchOperation operation = null;
        searchSpecifications.add(new SearchCriteria("name", operation, value));

        assertNotNull(searchSpecifications);
        // The criteria list is not directly accessible, so you may want to validate other aspects
    }

    @Test
    void testReturnPageDto() {
        Page<String> entitiesPage = new PageImpl<>(List.of("Entity1", "Entity2"));
        PageDto<String> pageDto = ecosystemService.returnPageDto(entitiesPage, String.class);

        assertNotNull(pageDto);
        assertEquals(2, pageDto.getContent().size());
    }

    @Test
    void testReturnListDto() {
        List<String> entitiesList = List.of("Entity1", "Entity2");
        List<String> dtoList = ecosystemService.returnListDto(entitiesList, String.class);

        assertNotNull(dtoList);
        assertEquals(2, dtoList.size());
    }

    @Test
    void testReturnDto() {
        String source = "Entity";
        String dto = ecosystemService.returnDto(source, String.class);

        assertNotNull(dto);
        assertEquals(source, dto);
    }

    @Test
    void testReturnObfuscateListDto() {
        List<String> sourceList = List.of("SensitiveEntity1", "SensitiveEntity2");
        List<String> obfuscatedList = ecosystemService.returnObfuscateListDto(sourceList, String.class);

        assertNotNull(obfuscatedList);
        assertEquals(2, obfuscatedList.size());
    }

    @Test
    void testReturnObfuscatePageDto() {
        Page<String> sourcePage = new PageImpl<>(List.of("SensitiveEntity1", "SensitiveEntity2"));
        PageDto<String> obfuscatedPageDto = ecosystemService.returnObfuscatePageDto(sourcePage, String.class);

        assertNotNull(obfuscatedPageDto);
        assertEquals(2, obfuscatedPageDto.getContent().size());
    }

    @Test
    void testPrintInfoLog() {
        StringBuilder logProcess = new StringBuilder();
        String message = "Info Message";

        ecosystemService.printInfoLog(logProcess, message);

        assertEquals(message, logProcess.toString());
    }

    @Test
    void testPrintErrorLog() {
        StringBuilder logProcess = new StringBuilder();
        String message = "Error Message";

        ecosystemService.printErrorLog(logProcess, message);

        assertEquals(message, logProcess.toString());
    }

    @Test
    void testPrintWarnLog() {
        StringBuilder logProcess = new StringBuilder();
        String message = "Warn Message";

        ecosystemService.printWarnLog(logProcess, message);

        assertEquals(message, logProcess.toString());
    }

    @Test
    void testPrintDebugLog() {
        StringBuilder logProcess = new StringBuilder();
        String message = "Debug Message";

        ecosystemService.printDebugLog(logProcess, message);

        assertEquals(message, logProcess.toString());
    }
}
