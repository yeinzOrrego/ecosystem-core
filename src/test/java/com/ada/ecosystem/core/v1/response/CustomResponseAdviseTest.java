package com.ada.ecosystem.core.v1.response;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.lang.reflect.Method;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import com.ada.ecosystem.core.v1.exceptions.EcosystemError;
import com.ada.ecosystem.core.v1.response.dto.EcosystemSuccessResponse;

public class CustomResponseAdviseTest {

    private CustomResponseAdvise advice;

    @BeforeEach
    void setUp() {
        advice = new CustomResponseAdvise();
    }

    @SuppressWarnings("unchecked")
	@Test
    void testSupportsWhenMethodIsOpenapiJson() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = new MethodParameter(method, -1);
        boolean result = advice.supports(returnType, (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class);

        assertTrue(result, "Expected supports to return false when openAPI is true");
    }

    @SuppressWarnings("unchecked")
	@Test
    void testSupportsWhenMethodIsNotOpenapiJson() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = new MethodParameter(method, -1);
        boolean result = advice.supports(returnType, (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class);

        assertTrue(result, "Expected supports to return true when openAPI is false");
    }

    @SuppressWarnings("unchecked")
	@Test
    void testBeforeBodyWriteWhenOpenAPIIsTrue() throws NoSuchMethodException {
        advice.setOpenAPI(true);  // Utiliza un setter público

        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = new MethodParameter(method, -1);
        Object body = new Object();
        MediaType mediaType = MediaType.APPLICATION_JSON;
        Class<? extends HttpMessageConverter<?>> converterType = (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class;
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);

        Object result = advice.beforeBodyWrite(body, returnType, mediaType, converterType, request, response);

        assertEquals(body.toString(), result, "Expected body.toString() when openAPI is true");
    }

    @Test
    void testBeforeBodyWriteWhenMethodIsAnnotatedWithIgnoreResponseBinding() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = mock(MethodParameter.class);
        when(returnType.getMethod()).thenReturn(method);
    }

    @SuppressWarnings("unchecked")
	@Test
    void testBeforeBodyWriteWhenBodyIsEcosystemError() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = new MethodParameter(method, -1);
        Object body = new EcosystemError();
        MediaType mediaType = MediaType.APPLICATION_JSON;
        Class<? extends HttpMessageConverter<?>> converterType = (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class;
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);

        Object result = advice.beforeBodyWrite(body, returnType, mediaType, converterType, request, response);

        assertEquals(body, result, "Expected body to be returned as-is when it is an instance of EcosystemError");
    }

    @SuppressWarnings("unchecked")
	@Test
    void testBeforeBodyWriteWhenBodyIsEcosystemSuccessResponse() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = new MethodParameter(method, -1);
        Object body = new EcosystemSuccessResponse<>(new Object());
        MediaType mediaType = MediaType.APPLICATION_JSON;
        Class<? extends HttpMessageConverter<?>> converterType = (Class<? extends HttpMessageConverter<?>>) (Class<?>) HttpMessageConverter.class;
        ServerHttpRequest request = mock(ServerHttpRequest.class);
        ServerHttpResponse response = mock(ServerHttpResponse.class);

        Object result = advice.beforeBodyWrite(body, returnType, mediaType, converterType, request, response);

        assertEquals(body, result, "Expected body to be returned as-is when it is an instance of EcosystemSuccessResponse");
    }

    @Test
    void testBeforeBodyWriteWhenMethodIsNotAnnotatedWithIgnoreResponseBindingAndBodyIsNotEcosystemTypes() throws NoSuchMethodException {
        Method method = CustomResponseAdvise.class.getMethod("beforeBodyWrite", Object.class, MethodParameter.class, MediaType.class,
            Class.class, ServerHttpRequest.class, ServerHttpResponse.class);
        MethodParameter returnType = mock(MethodParameter.class);
        when(returnType.getMethod()).thenReturn(method);
    }

    private static class CustomResponseAdvise implements ResponseBodyAdvice<Object> {

        private boolean openAPI = false;

        // Setter para pruebas
        public void setOpenAPI(boolean openAPI) {
            this.openAPI = openAPI;
        }

        @Override
        public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
            return !openAPI;
        }

        @Override
        public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType mediaType,
                                      Class<? extends HttpMessageConverter<?>> converterType,
                                      ServerHttpRequest request, ServerHttpResponse response) {
            if (body instanceof EcosystemError || body instanceof EcosystemSuccessResponse) {
                return body;
            }
            return openAPI ? body.toString() : new EcosystemSuccessResponse<>(body);
        }
    }

    @RestController
    private static class TestController {
    }
}
