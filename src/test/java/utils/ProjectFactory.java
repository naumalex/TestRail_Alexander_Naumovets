package utils;

import com.github.javafaker.Faker;
import enums.project.SuiteMode;
import models.Project;

public class ProjectFactory {
    private static final Faker faker = new Faker();

    public static Project getMandatoryInfoOnlyProject() {
        return Project.builder()
            .name(PropertyReader.getProperty("test_rail.all.project_name"))
            .build();
    }

    public static Project getFullInfoProject() {
        return Project.builder()
            .name(PropertyReader.getProperty("test_rail.all.project_name"))
            .announcement(faker.harryPotter().quote())
            .isShowAnnouncement(true)
            .suiteMode(SuiteMode.USE_SINGLE_REPOSITORY_WITH_BASE_LINE_SUPPORT)
            .build();
    }


}
