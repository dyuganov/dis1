package ru.nsu.dyuganov.ris1.Node;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Node {
    private String id;
    private String version;
    private String timestamp;
    private String uid;
    private String user;
    private String changeset;
    private String lat;
    private String lon;
    private List<Tag> tags = new ArrayList<>();

    public List<Tag> getTags() {
        return tags;
    }
}
