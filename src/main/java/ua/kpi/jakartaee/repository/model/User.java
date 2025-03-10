package ua.kpi.jakartaee.repository.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;

    private String name;
    private String group;

    private String githubLink;
    private String githubAvatarLink;

    private String page;

    public String getGithubLink() {
        return githubLink == null || githubLink.isBlank() ? "" : githubLink;
    }

    public String getGithubAvatarLink() {
        return githubAvatarLink == null || githubAvatarLink.isBlank() ? "" : githubAvatarLink;
    }
}
