package enums.test_case;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum Type implements BaseEnum {
    ACCEPTANCE("Acceptance"), ACCESSIBILITY("Accessibility"),
    AUTOMATED("Automated"), COMPATIBILITY("Compatibility"),
    DESTRUCTIVE("Destructive"), FUNCTIONAL("Functional"),
    OTHER("Other"), PERFORMANCE("Performance"),
    REGRESSION("Regression"), SECURITY("Security"),
    SMOKE_AND_SANITY("Smoke & Sanity"), USABILITY("Usability");

    private final String name;

    Type(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static Type fromString(String stringValue) {
        return  Arrays.stream(Type.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
