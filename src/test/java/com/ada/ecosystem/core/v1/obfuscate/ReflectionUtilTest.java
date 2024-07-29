package com.ada.ecosystem.core.v1.obfuscate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ReflectionUtilTest {

    private Source source;
    private Target target;

    @BeforeEach
    void setUp() {
        source = new Source("John", 30);
        target = new Target();
    }

    @Test
    void testCopyFields() {
        ReflectionUtil.copyFields(source, target);
        assertFieldsCopied(source, target);
    }

    @Test
    void testCopyColumnValues() {
        Target localTarget = new Target();
        ReflectionUtil.copyColumnValues(source, localTarget);
        assertFieldsCopied(source, localTarget);

        // Reset the values
        source.setName("");
        source.setAge(0);
        localTarget.setAge(0);
        localTarget.setName("");
    }

    private void assertFieldsCopied(Source source, Target target) {
        assertEquals(source.getName(), target.getName(), "Name should be copied");
        assertEquals(source.getAge(), target.getAge(), "Age should be copied");
    }

    private static class Source {
        private String name;
        private Integer age;

        public Source(String name, Integer age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }

    private static class Target {
        private String name;
        private Integer age;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
            this.age = age;
        }
    }
}
