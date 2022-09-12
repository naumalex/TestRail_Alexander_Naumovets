package enums.test_case;

import enums.common.BaseEnum;

import java.util.Arrays;

public enum AutomationType implements BaseEnum {
    NONE("None"), RANOREX("Ranorex");

    private final String name;

    AutomationType(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }

    public static AutomationType fromString(String stringValue) {
        return  Arrays.stream(AutomationType.values())
            .filter(p -> p.name.equals(stringValue)).findFirst().orElse(null);
    }
}
