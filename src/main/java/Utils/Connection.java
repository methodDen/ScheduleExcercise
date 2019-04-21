package Utils;

import Model.Day;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.spring.DaoFactory;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import Model.*;
import io.javalin.Context;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Connection {
    private static ConnectionSource source;
    static {
        try {
            source = new JdbcConnectionSource("jdbc:sqlite:C:\\SQL\\Sch.db");
            TableUtils.createTableIfNotExists(source, Day.class);
            TableUtils.createTableIfNotExists(source, Schedule.class);
            TableUtils.createTableIfNotExists(source, Groups.class);
            TableUtils.createTableIfNotExists(source, Students.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured creating table");
        }
    }
    public static Role getUserRole (Context context) throws SQLException {
        if (context.header("Authorization") == null) {
            return Role.ANONYMOUS;}
        String login = context.basicAuthCredentials().getUsername();
        String password = context.basicAuthCredentials().getPassword();
        Dao <Students, Long> daoForStudents = DaoFactory.createDao(Connection.getSource(), Students.class);
        List<Students> whoGotLogin = new ArrayList<>();
        whoGotLogin = daoForStudents.queryBuilder().where().eq("Login", login).query();
        if (whoGotLogin.size() == 1) {
            Students student = whoGotLogin.get(0);
            if (BCrypt.checkpw(password, student.getPasswordOfStudent())) { // В ARC, в авторизации - писать нехэшированный пароль
                return student.getRole();
            }
        }
        return Role.ANONYMOUS;
    }

    public static ConnectionSource getSource() {
        return source;
    }

}
