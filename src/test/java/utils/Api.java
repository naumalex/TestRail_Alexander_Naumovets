package utils;

import api.ProjectAdaptor;
import com.google.gson.Gson;
import lombok.extern.log4j.Log4j2;
import models.Project;
import models.api.Response;
import org.apache.hc.core5.http.HttpStatus;

import java.util.List;

@Log4j2
public class Api {
    private static final Gson gson = new Gson();
    private static final ProjectAdaptor adaptor = new ProjectAdaptor();

    public static void AddProjectIfNotExists(String projectName) {
        Response<List<Project>> getAllResponse = adaptor.getAllProjects(HttpStatus.SC_OK);
        long count = getAllResponse.projects.stream()
            .filter(p -> p.getName().equals(projectName)).count();
        if (count == 0) {
            log.debug("Create new project using API");
            Project project = Project.builder().name(projectName)
                .suiteModeIndex(1)
                .build();
            adaptor.createProject(HttpStatus.SC_OK, gson.toJson(project));
        }
    }

    public static void deleteProjectIfExists(String projectName) {
        Response<List<Project>> getAllResponse = adaptor.getAllProjects(HttpStatus.SC_OK);
        List<Integer> projects= getAllResponse.projects.stream()
            .filter(p -> p.getName().equals(projectName)).map(Project::getId).toList();
        projects.stream().forEach(p -> adaptor.deleteProject(HttpStatus.SC_OK, p.toString()));
    }
}
