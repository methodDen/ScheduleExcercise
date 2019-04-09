import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import io.javalin.Context;
import io.javalin.apibuilder.CrudHandler;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class DayController {
    public Dao<Day, Long> dayDao;
    public DayController() {
        try {
            dayDao = DaoManager.createDao(Connection.getSource(), Day.class);
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("SQL Exception");
        }
    }

    public void create(@NotNull Context context) {

    }


    public void getAll(@NotNull Context context) {

    }



}
