package kitchen;

import java.util.List;

public class Kebab {

    private List<String> kebab;
    static String[] ingredients = {"Salade", "Tomate", "Oignon", "Veau", "Porc", "Boeuf", "Ketchup", "Barbecue", "Algerienne", "Tartare"};

    public Kebab(List<String> kebab) {
        this.kebab = kebab;
    }

    public void setKebab(List<String> kebab) {
        this.kebab = kebab;
    }

    public void add(String ingredient) {
        this.kebab.add(ingredient);
    }

    public static String[] getIngredientsDispo() {
        return ingredients;
    }

    public List<String> getIngredients() {
        return this.kebab;
    }

    public void removeIngredient(int i) {
        this.kebab.remove(i);
    }
}
