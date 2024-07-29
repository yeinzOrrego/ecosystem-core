package com.ada.ecosystem.core.v1.database;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import feign.RequestInterceptor;
import feign.RequestTemplate;

public class FeignClientConfigTest {

    private FeignClientConfig feignClientConfig;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        feignClientConfig = new FeignClientConfig();
    }

    @Test
    public void testRequestInterceptor() {
        // Arrange
        RequestInterceptor interceptor = feignClientConfig.requestInterceptor();
        RequestTemplate requestTemplate = mock(RequestTemplate.class);
        
        // Set up the TokenContextHolder to return a test token
        TokenContextHolder.setToken("testToken");

        // Act
        interceptor.apply(requestTemplate);

        // Assert
        verify(requestTemplate, times(1)).header("token", "testToken");
    }

    @Test
    public void testRequestInterceptorWithoutToken() {
        // Arrange
        RequestInterceptor interceptor = feignClientConfig.requestInterceptor();
        RequestTemplate requestTemplate = mock(RequestTemplate.class);

        // Set up the TokenContextHolder to return null
        TokenContextHolder.setToken(null);

        // Act
        interceptor.apply(requestTemplate);

        // Assert
        verify(requestTemplate, never()).header("token", "testToken");
    }
}
