package utils;

import com.github.javafaker.Faker;
import enums.test_case.AutomationType;
import enums.test_case.Priority;
import enums.test_case.Template;
import enums.test_case.Type;
import enums.test_run.IncludeTestCasesMode;
import models.TestCase;
import models.TestRun;
import models.TestStep;

import java.sql.Time;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class TestRunFactory {
    private static final Faker faker = new Faker();

    public static TestRun getMandatoryInfoOnlyTestRun() {
        return TestRun.builder()
            .name("Test Run " + LocalDateTime.now())
            .build();
    }

    public static TestRun getFullInfoTestRun() {
        return TestRun.builder()
            .name(PropertyReader.getProperty("test_rail.test_run.name"))
            .description(faker.harryPotter().quote())
            .assignTo("Me")
            .milestone(PropertyReader.getProperty("test_rail.test_run.milestone"))
            .references("different references")
            .includeTestCasesMode(IncludeTestCasesMode.INCLUDE_ALL_TEST_CASES)
            .build();
    }
}
