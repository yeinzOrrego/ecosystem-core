package com.ada.ecosystem.core.v1.obfuscate;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionUtil {
	
	/** The Constant OBFUSCATION_VALUE. */
	private static final String OBFUSCATION_VALUE = "********";

	/**
	 * Instantiates a new reflection util.
	 */
	private ReflectionUtil() {
	}

	/**
	 * Copy fields.
	 *
	 * @param <S> the generic type
	 * @param <T> the generic type
	 * @param source the source
	 * @param target the target
	 */
	public static <S, T> void copyFields(S source, T target) {
		Class<?> sourceClass = source.getClass();
		Class<?> targetClass = target.getClass();

		// Iterate over fields in the source class
		for (Field sourceField : sourceClass.getDeclaredFields()) {
			// Get the corresponding field in the target class
			Field targetField;
			try {
				targetField = targetClass.getDeclaredField(sourceField.getName());
			} catch (NoSuchFieldException e) {
				continue; // Skip if the field doesn't exist in the target class
			}			

			// Get the getter and setter methods for the fields
			Method sourceGetter = getGetterMethod(sourceClass, sourceField.getName());
			Method targetSetter = getSetterMethod(targetClass, targetField.getName());

			// Check if both methods exist
			if (sourceGetter == null || targetSetter == null) {
				// continue; // Skip if either getter or setter is missing
			}

			// Get the value from the source field using the getter
			Object value;
			try {
				value = sourceGetter.invoke(source);
			} catch (IllegalAccessException | InvocationTargetException e) {
				continue; // Skip if there's an issue accessing the value
			}

			// Set the value in the target field using the setter
			try {
				if(Boolean.TRUE.equals(isObfuscated(sourceField))) value = OBFUSCATION_VALUE;
				targetSetter.invoke(target, value);
			} catch (IllegalAccessException | InvocationTargetException e) {
				// continue; // Skip if there's an issue setting the value
			}
		}
	}
	
	/**
	 * Copy column values.
	 *
	 * @param <S> the generic type
	 * @param <T> the generic type
	 * @param sourceObject the source object
	 * @param targetObject the target object
	 */
	public static <S, T> void copyColumnValues(S sourceObject, T targetObject) {
	    Class<?> sourceClass = sourceObject.getClass();
	    Class<?> targetClass = targetObject.getClass();

	    // Get all fields of the source class
	    Field[] sourceFields = sourceClass.getDeclaredFields();

	    for (Field sourceField : sourceFields) {
	        sourceField.setAccessible(true); // Make private fields accessible
	        // Get the corresponding field in the target class
	        String fieldName = sourceField.getName();
	        if(!fieldName.equalsIgnoreCase("serialVersionUID")) {
		        Field targetField;
				try {
					targetField = targetClass.getDeclaredField(fieldName);
					targetField.setAccessible(true); // Make private fields accessible
			        // Check if the field types are compatible
			        if (sourceField.getType().isAssignableFrom(targetField.getType())) {
			            // Get the values of the fields
			            Object sourceValue = sourceField.get(sourceObject);
			            if(Boolean.TRUE.equals(isObfuscated(sourceField))) sourceValue = OBFUSCATION_VALUE;
			            // Set the value in the target object
			            targetField.set(targetObject, sourceValue);
			        } else {
			            // Handle incompatible field types (throw an exception or log a warning)
			            throw new IllegalArgumentException("Incompatible field types: " + sourceField.getName());
			        }
	
				} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
					log.error("fieldName {} Error {}", fieldName, e.getMessage());
				}
	        }
		}
	}


	/**
	 * Gets the getter method.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @return the getter method
	 */
	private static Method getGetterMethod(Class<?> clazz, String fieldName) {
		String getterMethodName = "get" + capitalize(fieldName);
		try {
			return clazz.getMethod(getterMethodName);
		} catch (NoSuchMethodException e) {
			return null;
		}
	}

	/**
	 * Gets the setter method.
	 *
	 * @param clazz the clazz
	 * @param fieldName the field name
	 * @return the setter method
	 */
	private static Method getSetterMethod(Class<?> clazz, String fieldName) {
		String setterMethodName = "set" + capitalize(fieldName);
		try {
			return clazz.getMethod(setterMethodName, clazz.getDeclaredField(fieldName).getType());
		} catch (NoSuchMethodException | NoSuchFieldException e) {
			return null;
		}
	}

	/**
	 * Capitalize.
	 *
	 * @param str the str
	 * @return the string
	 */
	private static String capitalize(String str) {
		return Character.isUpperCase(str.charAt(0)) ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	/**
	 * Checks if is obfuscated.
	 *
	 * @param field the field
	 * @return the boolean
	 */
	private static Boolean isObfuscated(Field field) {
		return field != null && field.isAnnotationPresent(Obfuscate.class);
	}
}
