package rest;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import rest.configuration.MvcConfig;
import rest.controller.Controller;

public class App {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MvcConfig.class);
        Controller controller = context.getBean("controller", Controller.class);

        controller.getSessionId();
    }
}
