package enums.test_result;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum TestResult implements BaseEnum {
    PASSED("Passed"), BLOCKED("Blocked"),
    RETEST("Retest"), FAILED("Failed");

    private final String name;

    TestResult(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static TestResult fromString(String stringValue) {
        return  Arrays.stream(TestResult.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
