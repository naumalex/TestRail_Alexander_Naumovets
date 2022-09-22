package api;

import com.google.gson.reflect.TypeToken;
import models.Milestone;
import models.api.Response;

import java.util.List;

public class MilestoneAdaptor extends BaseAdaptor {

    public Response<List<Milestone>> getAllMilestones(int statusCode, int projectId) {
        return gson.fromJson(get("get_milestones/" + projectId, statusCode),
            new TypeToken<Response<List<Milestone>>>() {
            }.getType());
    }

    public Milestone createMilestone(int statusCode, int projectId, String requestBody) {
        return gson.fromJson(post("add_milestone/" + projectId, statusCode, requestBody),
            new TypeToken<Milestone>() {
            }.getType());
    }
}
