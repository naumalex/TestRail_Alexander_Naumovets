package enums.test_case;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum Template implements BaseEnum {
    EXPLORATORY_SESSION("Exploratory Session"), TEST_CASE_STEPS("Test Case (Steps)"),
    TEST_CASE_TEXT("Test Case (Text)");

    private final String name;

    Template(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static Template fromString(String stringValue) {
        return  Arrays.stream(Template.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
