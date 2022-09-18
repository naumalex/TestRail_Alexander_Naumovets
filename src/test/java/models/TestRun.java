package models;

import enums.test_run.IncludeTestCasesMode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TestRun {
    String name;
    String references;
    String milestone;
    String assignTo;
    String description;
    IncludeTestCasesMode includeTestCasesMode;
}
