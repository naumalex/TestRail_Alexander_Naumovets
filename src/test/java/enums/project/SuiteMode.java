package enums.project;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum SuiteMode implements BaseEnum {
    USE_SINGLE_REPOSITORY_FOR_ALL_CASES("Use a single repository for all cases (recommended)"),
    USE_SINGLE_REPOSITORY_WITH_BASE_LINE_SUPPORT("Use a single repository with baseline support"),
    USE_MULTIPLE_TEST_SUITES_TO_MANAGE_CASES("Use multiple test suites to manage cases");

    private final String name;

    SuiteMode(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static SuiteMode fromString(String stringValue) {
        return  Arrays.stream(SuiteMode.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
