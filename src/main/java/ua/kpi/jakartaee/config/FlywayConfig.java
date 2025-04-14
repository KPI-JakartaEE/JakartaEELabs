package ua.kpi.jakartaee.config;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.Resource;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import org.flywaydb.core.Flyway;

import javax.sql.DataSource;

// Comment this class if you do not want migration of SQL scripts
// Maybe you will need to comment dependency in build.gradle
@Startup
@Singleton
public class FlywayConfig {

    @Resource(lookup = "jdbc/postgres")
    private DataSource dataSource;

    @PostConstruct
    public void migrate() {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .load();
        flyway.migrate();
    }
}
