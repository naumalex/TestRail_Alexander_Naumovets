package models;

import enums.test_case.AutomationType;
import enums.test_case.Priority;
import enums.test_case.Template;
import enums.test_case.Type;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder

public class TestCase {
    private String title;
    @Builder.Default
    private String section = "Test Cases";
    @Builder.Default
    private Template template = Template.TEST_CASE_TEXT;
    @Builder.Default
    private Type type = Type.OTHER;
    @Builder.Default
    private Priority priority = Priority.MEDIUM;
    private String estimate;
    private String reference;
    private AutomationType automationType;
    private String preconditions;
    private String postconditions;
    private String mission;
    private String goals;
    private String steps;
    private String expectedResult;
    private List<TestStep> stepsStructured;
}
