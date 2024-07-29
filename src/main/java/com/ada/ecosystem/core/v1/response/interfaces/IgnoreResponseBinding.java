package com.ada.ecosystem.core.v1.response.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The Interface IgnoreResponseBinding.
 * @author Carlos Torres - carlos.torres@ada.co
 * @version 0.0.8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IgnoreResponseBinding {
}