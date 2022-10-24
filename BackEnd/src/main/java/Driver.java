import com.revature.controller.Controller;
import com.revature.service.Service;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args) {



    Javalin app = Javalin.create(config -> {
        config.enableCorsForAllOrigins();
    }).start(8080);

    Service service = new Service();
    Controller controller = new Controller(service);

    app.post("/login", controller.login);
    app.post("/logout", controller.logout);

    app.post("/register", controller.createNewUser);

    app.post("/mood", controller.updateMood);

    }
}
