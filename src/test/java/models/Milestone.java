package models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Milestone {
    int id;
    String name;
}
