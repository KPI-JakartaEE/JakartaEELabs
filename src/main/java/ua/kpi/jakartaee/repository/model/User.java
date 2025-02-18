package ua.kpi.jakartaee.repository.model;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String username;
    private String jspPageName;
}
