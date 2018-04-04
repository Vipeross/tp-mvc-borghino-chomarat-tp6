package controller;

import io.vertx.ext.web.RoutingContext;
import model.Food;

public class FoodController {

    private RoutingContext context;
    private Food kebab;

    public FoodController(RoutingContext context) {
        this.context = context;
        this.kebab = new Food();
    }

    public void list() {
        this.context.put("ingredients_list", kebab.getIngredients());
        this.context.put("ingredients_dispo_list", Food.getIngredientsDispo());
        this.context.next();
    }

    public void add() {
        this.kebab.add(this.context.request().getFormAttribute("ingredient"));
        this.context.next();
    }

    public void setContext(RoutingContext context) {
        this.context = context;
    }

}
