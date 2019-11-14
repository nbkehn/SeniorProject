/**
 * Runs startup SQL
 * @author Noah Trimble
 */
package bco.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

@Component
public class Startup {

    // Used to get SQL connection
    @Autowired
    private DataSource dataSource;

    // Logging
    private static final Logger log = LoggerFactory.getLogger(Startup.class);

    /**
     * Run startup script to add table definitions and entries that
     * couldn't easily be added through Hibernate
     */
    @PostConstruct
    public void runNativeSql() {
        ClassPathResource resource = new ClassPathResource("scripts/startup.sql");
        try(Connection connection = dataSource.getConnection()) {
            ScriptUtils.executeSqlScript(connection, resource);
        }
        catch (SQLException | ScriptException e) {
            log.error(e.getMessage());
        }
    }
}