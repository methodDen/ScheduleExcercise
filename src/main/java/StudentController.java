import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.spring.DaoFactory;
import io.javalin.Context;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.sql.SQLException;

public class StudentController {
    public Dao<Students, Long> studentDao;
    public Logger studentLogger;

    public StudentController() {
        try {
            studentDao = DaoFactory.createDao(Connection.getSource(), Students.class);
            studentLogger = LoggerFactory.getLogger(this.getClass());
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error occured creating Dao");
        }
    }

    public void getAll(@NotNull Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        module.addSerializer(new StudentSerializer());
        try {
            context.result(mapper.writeValueAsString(studentDao.queryForAll()));
            context.status(Constants.OK_200);
        } catch (SQLException e) {
            e.printStackTrace();
            studentLogger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }

    public void create(@NotNull Context context) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        module.addDeserializer(Students.class, new StudentDeserializer());
        String json = context.body();
        Students student = mapper.readValue(json, Students.class);
        try {
            studentDao.create(student);
            context.status(Constants.CREATED_201);
        } catch (SQLException e) {
            e.printStackTrace();
            studentLogger.error("Error occured creating a record");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }

    }

    public void getAllForUsers(@NotNull Context context) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        mapper.registerModule(module);
        module.addSerializer(new StudentUserSerializer());
        try {
            context.result(mapper.writeValueAsString(studentDao.queryForAll()));
            context.status(Constants.OK_200);
        }  catch (SQLException e) {
            e.printStackTrace();
            studentLogger.error("Error occured getting records");
            context.status(Constants.INTERNAL_SERVER_ERROR);
        }
    }
}
