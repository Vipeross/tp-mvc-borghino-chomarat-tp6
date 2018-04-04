package kitchen;

import controller.FoodController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

import java.util.Optional;

public class WebApp extends AbstractVerticle {

    FoodController foodController;

    public void start() {
        TemplateEngine templateEngine = HandlebarsTemplateEngine.create();
        TemplateHandler templateHandler = TemplateHandler.create(templateEngine);

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());


        // === routes ===

        router.get("/hello.hbs").handler(context -> {
            context.put("message", "Hello Bob Morane");
            context.next();
        });

        router.get("/form_add.hbs").handler(context -> {
            if(this.foodController == null)
                this.foodController = new FoodController(context);
            this.foodController.setContext(context);
            this.foodController.list();
        });

        router.post("/action").handler(context -> {
            String choice = context.request().getFormAttribute("choice");
            String firstName = context.request().getFormAttribute("firstname");
            String lastName = context.request().getFormAttribute("lastname");

            context.response()
                    .putHeader("content-type", "text/html;charset=UTF-8")
                    .end(String.format("%s %s %s", choice, firstName, lastName));
        });

        router.post("/ajout").handler(context -> {
            this.foodController.setContext(context);
            this.foodController.add();
        });

        router.post("/remove").handler(context -> {
            this.foodController.setContext(context);
            this.foodController.removeIngredient();
        });

        // Redirection

        router.get("/hello").handler(context -> {
            context.response().putHeader("location", "/hello.hbs").setStatusCode(302).end();
        });

        router.post("/ajout").handler(context -> {
            context.response().putHeader("location", "/form_add.hbs").setStatusCode(302).end();
        });

        router.post("/remove").handler(context -> {
            context.response().putHeader("location", "/form_add.hbs").setStatusCode(302).end();
        });

        // ==============
        router.getWithRegex(".+\\.hbs").handler(templateHandler);

        router.route("/*").handler(StaticHandler.create());
        Integer httpPort = Integer.parseInt(Optional.ofNullable(System.getenv("PORT")).orElse("8080"));
        HttpServer server = vertx.createHttpServer();
        server.requestHandler(router::accept).listen(httpPort);

        System.out.println("Listening on " + httpPort);
    }


    public static void main(String[] args) {
        Launcher.executeCommand("run", WebApp.class.getName());

    }
}
