package models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class TestStep {
    private String description;
    private String expectedResult;
}

