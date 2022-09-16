package api;

import com.google.gson.reflect.TypeToken;
import models.Project;
import models.api.Response;

import java.util.List;

public class ProjectAdaptor extends BaseAdaptor {

    public Response<List<Project>> getAllProjects(int statusCode) {
        return gson.fromJson(get("get_projects", statusCode),
            new TypeToken<Response<List<Project>>>() {
            }.getType());
    }

    public Response<Project> createProject(int statusCode, String requestBody) {
        return gson.fromJson(post("add_project", statusCode, requestBody),
            new TypeToken<Response<Project>>() {
            }.getType());
    }

    public Response<Project> getProject(int statusCode, String id) {
        return gson.fromJson(
            get("get_project" + "/" + id, statusCode),
            new TypeToken<Response<Project>>() {
            }.getType());
    }

    public void deleteProject(int statusCode, String id) {
        gson.fromJson(post("delete_project" + "/" + id, statusCode, ""),
            new TypeToken<Response<Project>>() {
            }.getType());
    }
}
