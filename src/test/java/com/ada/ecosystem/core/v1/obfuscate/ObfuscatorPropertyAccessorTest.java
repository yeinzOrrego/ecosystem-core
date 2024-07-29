package com.ada.ecosystem.core.v1.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import com.ada.ecosystem.core.v1.pageable.PageDto;

public class ObfuscatorPropertyAccessorTest {

    @Test
    void testConvertPage() {
        ObfuscatorPropertyAccessor<MySource, MyDestination> accessor = new ObfuscatorPropertyAccessor<>();
        List<MySource> sourceList = Arrays.asList(new MySource("source1"), new MySource("source2"));
        Page<MySource> sourcePage = new PageImpl<>(sourceList);
        Class<MyDestination> targetClass = MyDestination.class;

        PageDto<MyDestination> result = accessor.convertPage(sourcePage, targetClass);

        assertEquals(2, result.getContent().size(), "The content size should match the source list size");
        assertEquals(0, result.getNumber(), "The page number should be zero");
        assertEquals(2, result.getTotalElements(), "The total elements should be 2");
        assertEquals(1, result.getTotalPages(), "The total pages should be 1");
    }

    // Mock classes for testing
    private static class MySource {
        public MySource(String value) { }
    }

    private static class MyDestination {
    }
}
