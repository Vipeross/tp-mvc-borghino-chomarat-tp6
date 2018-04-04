package model;

import java.util.ArrayList;
import java.util.List;

public class Food {

    private List<String> kebab;
    static String[] ingredients = {"Salade","Tomate","Oignon","Veau","Porc","Boeuf","Ketchup","Barbecue","Algerienne","Tartare"};

    public Food() {
        this.kebab = new ArrayList<>();
    }

    public void add(String ingredient) {
        this.kebab.add(ingredient);
    }

    public static String[] getIngredientsDispo()
    {
        return ingredients;
    }

    public List<String> getIngredients() {
        return this.kebab;
    }

    public void removeIngredient(String i) {
        System.out.println(i);
        this.kebab.remove(Integer.parseInt(i));
    }

}
