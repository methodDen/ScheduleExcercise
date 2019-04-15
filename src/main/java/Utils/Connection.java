package Utils;

import Model.Day;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import Model.*;
import java.sql.SQLException;

public class Connection {
    private static ConnectionSource source;
    static {
        try {
            source = new JdbcConnectionSource("jdbc:sqlite:C:\\SQL\\Model.Schedule.db");
            TableUtils.createTableIfNotExists(source, Day.class);
            TableUtils.createTableIfNotExists(source, Schedule.class);
            TableUtils.createTableIfNotExists(source, Groups.class);
            TableUtils.createTableIfNotExists(source, Students.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured creating table");
        }
    }

    public static ConnectionSource getSource() {
        return source;
    }

}
