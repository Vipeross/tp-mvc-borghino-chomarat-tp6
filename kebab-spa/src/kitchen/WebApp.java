package kitchen;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Launcher;
import io.vertx.core.http.HttpServer;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.ext.web.handler.TemplateHandler;
import io.vertx.ext.web.templ.HandlebarsTemplateEngine;
import io.vertx.ext.web.templ.TemplateEngine;

import java.util.Optional;

public class WebApp extends AbstractVerticle {


    public void start() {

        Router router = Router.router(vertx);
        router.route().handler(BodyHandler.create());

        // === routes ===


        router.get("/hello").handler(context -> {
            context.response()
                    .putHeader("content-type", "application/json;charset=UTF-8")
                    .end(new JsonObject().put("message", "hello").encodePrettily());
        });

        router.get("/messages").handler(context -> {
            System.out.println("messages");
            context.response()
                    .putHeader("content-type", "application/json;charset=UTF-8")
                    .end(new JsonArray()
                            .add(new JsonObject().put("username", "Bob Morane").put("subject", "hello"))
                            .add(new JsonObject().put("username", "John Doe").put("subject", "hi"))
                            .add(new JsonObject().put("username", "Jane Doe").put("subject", "hey"))
                            .encodePrettily()
                    );
        });

        router.post("/hello").handler(context -> {
            String choice = context.getBodyAsJson().getString("choice");
            String firstname = context.getBodyAsJson().getString("firstname");
            String lastname = context.getBodyAsJson().getString("lastname");

            System.out.println(choice + " " + firstname + " " + lastname);

            context.response()
                    .putHeader("content-type", "application/json;charset=UTF-8")
                    .end(new JsonObject().put("message", "good").encodePrettily());

        });

        // ==============

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
