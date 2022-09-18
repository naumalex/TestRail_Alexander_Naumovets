package utils;

import api.MilestoneAdaptor;
import api.ProjectAdaptor;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import models.Milestone;
import models.Project;
import models.api.Response;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Log4j2
public class ApiUtils {
    private static final Gson gson = new Gson();
    private static final ProjectAdaptor adaptor = new ProjectAdaptor();

    public static void addProjectIfNotExists(String projectName) {
        log.info(String.format("Check if project with name %s exists using API", projectName));
        Response<List<Project>> getAllResponse = adaptor.getAllProjects(HttpStatus.SC_OK);
        long count = getAllResponse.projects.stream()
            .filter(p -> p.getName().equals(projectName)).count();
        if (count == 0) {
            log.info("Create new project using API");
            Project project = Project.builder().name(projectName)
                .suiteModeIndex(1)
                .build();
            adaptor.createProject(HttpStatus.SC_OK, gson.toJson(project));
        }
    }

    public static void deleteProjectsIfExists(String projectName) {
        log.info(String.format("Delete project with name %s using API", projectName));
        Response<List<Project>> getAllResponse = adaptor.getAllProjects(HttpStatus.SC_OK);
        List<Integer> projects= getAllResponse.projects.stream()
            .filter(p -> p.getName().equals(projectName)).map(Project::getId).toList();
        projects.stream().forEach(p -> adaptor.deleteProject(HttpStatus.SC_OK, p.toString()));
    }

    public static void addMilestoneIfNotExist(String projectName, String milestoneName) {
        final MilestoneAdaptor adaptor = new MilestoneAdaptor();
        int projectId = getProjects(projectName).stream().map(Project::getId).findFirst().orElse(0);
        if (projectId == 0) {
            log.fatal(String.format("Project %s does not exist", projectName));
        }
        log.info(String.format("Check if milestone with name %s exists using API", milestoneName));
        List<Milestone> milestones = adaptor.getAllMilestones(
            HttpStatus.SC_OK, projectId).milestones.stream()
            .filter(p -> p.getName().equals(milestoneName)).toList();
        if (milestones.isEmpty()) {
            Milestone milestone = Milestone.builder().name(milestoneName).build();
            log.info("Add milestone using API");
            adaptor.createMilestone(HttpStatus.SC_OK, projectId,gson.toJson(milestone));
        }
    }
    private static List<Project> getProjects(String name) {
        Response<List<Project>> getAllResponse = adaptor.getAllProjects(HttpStatus.SC_OK);
        return getAllResponse.projects.stream()
            .filter(p -> p.getName().equals(name)).toList();
    }
}
