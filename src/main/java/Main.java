import Controller.*;
import Model.Attendance;
import Utils.Connection;
import Utils.Constants;
import Utils.Role;
import io.javalin.Javalin;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.security.SecurityUtil.roles;

public class Main {
    public static void main(String[] args) throws Exception {
        Javalin app = Javalin.create().port(7000);

        app.accessManager(((handler, ctx, permittedRoles) -> {
            Role role = Connection.getUserRole(ctx);
            if (permittedRoles.contains(role))
            {
                handler.handle(ctx);
            }
            else
            {
                ctx.status(Constants.FORBIDDEN);
            }
        }));

        app.start();
        app.routes(()-> {
            path("students", ()-> {
                get("/secured", ctx -> new StudentController().getAll(ctx), roles(Role.ADMIN));
                get("/unsecured", ctx -> new StudentController().getAllForUsers(ctx), roles(Role.ANONYMOUS, Role.USER, Role.ADMIN));
                post(ctx-> new StudentController().create(ctx), roles(Role.ANONYMOUS, Role.USER, Role.ADMIN));
            });
            path("schedule", ()-> {
                get(ctx -> new ScheduleController().getAll(ctx), roles(Role.ADMIN, Role.USER));
                post(ctx-> new ScheduleController().create(ctx), roles(Role.ADMIN));
            });
            path("day", ()-> {
                get(ctx -> new DayController().getAll(ctx), roles(Role.ADMIN, Role.USER));

                post(ctx -> new DayController().create(ctx), roles(Role.ADMIN));
            });
            path("groups", ()->{
                get(ctx -> new GroupsController().getAll(ctx), roles(Role.ADMIN, Role.USER));
                post(ctx -> new GroupsController().create(ctx), roles(Role.ADMIN));
            });
            path("attendance", ()->{
                get(ctx -> new AttendanceController().getOne(ctx, )) // How to get attendance of individual
            });
        });
    }
}
