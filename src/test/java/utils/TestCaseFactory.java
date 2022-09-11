package utils;

import enums.test_case.AutomationType;
import enums.test_case.Priority;
import enums.test_case.Template;
import enums.test_case.Type;
import models.TestCase;
import models.TestStep;

import java.util.List;

public class TestCaseFactory {
    public static TestCase getMandatoryInfoOnlyTestCaseTextTemplate() {
        return TestCase.builder()
            .title(PropertyReader.getProperty("test_rail.test_case.text.title"))
            .build();
    }

    public static TestCase getTextTemplateFullInfoTestCase() {
        return TestCase.builder()
            .title(PropertyReader.getProperty("test_rail.test_case.text.title"))
            .section(PropertyReader.getProperty("test_rail.test_case.text.section"))
            .template(Template.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.template")))
            .type(Type.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.type")))
            .priority(Priority.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.priority")))
            .estimate(PropertyReader.getProperty("test_rail.test_case.text.estimate"))
            .reference(PropertyReader.getProperty("test_rail.test_case.text.references"))
            .automationType(AutomationType.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.automation_type")))
            .preconditions(PropertyReader.getProperty("test_rail.test_case.text.preconditions"))
            .steps(PropertyReader.getProperty("test_rail.test_case.text.steps"))
            .expectedResult(PropertyReader.getProperty("test_rail.test_case.text.expected_result"))
            .build();
    }

    public static TestCase getStepsTemplateFullInfoTestCase() {
        return TestCase.builder()
            .title(PropertyReader.getProperty("test_rail.test_case.text.title"))
            .section(PropertyReader.getProperty("test_rail.test_case.text.section"))
            .template(Template.fromString  (PropertyReader.getProperty(
                "test_rail.test_case.steps.template")))
            .type(Type.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.type")))
            .priority(Priority.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.priority")))
            .estimate(PropertyReader.getProperty("test_rail.test_case.text.estimate"))
            .reference(PropertyReader.getProperty("test_rail.test_case.text.references"))
            .automationType(AutomationType.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.automation_type")))
            .preconditions(PropertyReader.getProperty("test_rail.test_case.text.preconditions"))
            .stepsStructured(List.of(
                TestStep.builder().description(PropertyReader.
                        getProperty("test_rail.test_case.steps.stepOne.description"))
                .expectedResult(PropertyReader
                    .getProperty("test_rail.test_case.steps.stepOne.expectedResult")).build(),
                TestStep.builder().description(PropertyReader
                        .getProperty("test_rail.test_case.steps.stepTwo.description"))
                    .expectedResult(PropertyReader
                        .getProperty("test_rail.test_case.steps.stepTwo.expectedResult")).build(),
                TestStep.builder().description(PropertyReader
                        .getProperty("test_rail.test_case.steps.stepThree.description"))
                    .expectedResult(PropertyReader
                        .getProperty("test_rail.test_case.steps.stepThree.expectedResult")).build(),
                TestStep.builder().description(PropertyReader
                        .getProperty("test_rail.test_case.steps.stepFour.description"))
                    .expectedResult(PropertyReader
                    .getProperty("test_rail.test_case.steps.stepFour.expectedResult")).build()))
            .build();
    }

    public static TestCase getExploratorySessionFullInfoTestCase() {
        return TestCase.builder()
            .title(PropertyReader.getProperty("test_rail.test_case.text.title"))
            .section(PropertyReader.getProperty("test_rail.test_case.text.section"))
            .template(Template.fromString  (PropertyReader.getProperty(
                "test_rail.test_case.exploratory_session.template")))
            .type(Type.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.type")))
            .priority(Priority.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.priority")))
            .estimate(PropertyReader.getProperty("test_rail.test_case.text.estimate"))
            .reference(PropertyReader.getProperty("test_rail.test_case.text.references"))
            .automationType(AutomationType.fromString(PropertyReader.getProperty(
                "test_rail.test_case.text.automation_type")))
            .mission(PropertyReader.getProperty("test_rail.test_case.exploratory_session.mission"))
            .goals(PropertyReader.getProperty("test_rail.test_case.exploratory_session.goals"))
            .build();
    }
}
