package rest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rest.configuration.MvcConfig;
import rest.controller.APIController;
import rest.model.User;

public class App {
    public static void main(String[] args) {

        ApplicationContext appContext = new AnnotationConfigApplicationContext(MvcConfig.class);
        APIController controller = appContext.getBean("APIController", APIController.class);


        User add = new User(3L, "James", "Brown", (byte) 99);
        User update = new User(3L, "Thomas", "Shelby", (byte) 99);
        User delete = new User(3L);


        String sessionId = controller.getSessionId();

        String finalCode = controller.addUser(add, sessionId).getBody()
                + controller.updateUser(update, sessionId).getBody()
                + controller.deleteUser(delete, sessionId).getBody();

        System.out.println(finalCode);
    }
}
