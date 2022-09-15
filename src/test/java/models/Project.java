package models;

import com.google.gson.annotations.SerializedName;
import enums.project.SuiteMode;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Project {
    int id;
    String name;
    String announcement;
    @SerializedName(value = "show_announcement")
    Boolean isShowAnnouncement;
    SuiteMode suiteMode;
    @SerializedName(value = "suite_mode")
    int suiteModeIndex; //used for API only
    Boolean isTheProjectCompleted;
}
