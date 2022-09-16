package enums.test_run;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum IncludeTestCasesMode implements BaseEnum {
    INCLUDE_ALL_TEST_CASES("Include all test cases"),
    SELECT_SPECIFIC_TEST_CASES("Select specific test cases"),
    DYNAMIC_FILTERING("Dynamic Filtering");

    private final String name;

    IncludeTestCasesMode(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static IncludeTestCasesMode fromString(String stringValue) {
        return  Arrays.stream(IncludeTestCasesMode.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
