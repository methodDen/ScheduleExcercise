package Controller;

import Model.Tutors;
import Utils.Connection;
import Utils.Constants;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;

public class TutorController{
    public Dao<Tutors, Long> tutorDao;
    private Logger logger;

    public TutorController() {
        logger = LoggerFactory.getLogger(this.getClass());
        try {
            tutorDao = DaoFactory.createDao(Connection.getSource(), Tutors.class);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating dao");
        }

    }

    public void create(@NotNull Context context) {
        Tutors tutor = context.bodyAsClass(Tutors.class);
        try {
            tutorDao.create(tutor);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured creating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


    public void getAll(@NotNull Context context) {
        try {
            context.json(tutorDao.queryForAll());
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            logger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }


}
