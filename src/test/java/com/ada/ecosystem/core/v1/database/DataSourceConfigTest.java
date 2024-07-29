package com.ada.ecosystem.core.v1.database;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@ExtendWith(MockitoExtension.class)
public class DataSourceConfigTest {

	@InjectMocks
    private DataSourceConfig dataSourceConfig;

    @Mock
    private DatasourceInterceptor datasourceInterceptor;

    @Mock
    private InterceptorRegistry interceptorRegistry;

    @Mock
    private InterceptorRegistration interceptorRegistration;
    
    @BeforeEach
    public void setUp() {
        dataSourceConfig = new DataSourceConfig();
    }

    @Test
    public void testDatasourceInterceptorBean() {
        DatasourceInterceptor interceptor = dataSourceConfig.datasourceInterceptor();
        assertNotNull(interceptor);
    }
}
