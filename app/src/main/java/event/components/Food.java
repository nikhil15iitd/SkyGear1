package event.components;

/**
 * Created by nikhil on 27-08-2015.
 */
public class Food implements Components {
    private String name;
    private String foodID;
    private double cost;

    /**
     * This array is populated with Food items that are available for use.
     *
     * Use this array any time you need a Food object.  The constructor for
     * the Food class is private, so the elements of this array are the only
     * Food objects available.  (You cannot create new ones.)
     */
    public static final Food[] FOOD_OBJECTS =
            { new Food("Bacon", 185),
                    new Food("Waffle", 350),
                    new Food("Egg", 89),
                    new Food("Orange Juice", 199),
                    new Food("Milk", 179),
                    new Food("Toast", 125),
                    new Food("Hashbrowns",195),
                    new Food("Pancakes", 179),
                    new Food("Coffee", 67),
                    new Food("Cereal", 129),
                    new Food("Donut", 89),
                    new Food("Melon", 98),
                    new Food("Pie", 195),
                    new Food("Croissant", 106)
            };

    private Food(String name, double cost) {
        this.name = name;
        this.cost = cost;

    }


    /**
     * Checks if the current object is equal to the parameter.  Note:
     * only the NAMES of the foods are compared.  If the two foods have
     * the same name, they are considered equal!
     *
     * @param other Food item to be compared with the current object
     * @return true if the two Foods have the same name, false otherwise
     */
    public boolean equals(Food other) {
        return (name.equals(other.name));
    }

    public double getCost(){
        return cost;
    }
    public String getCompID(){
        return foodID;
    }
    public String getCompName(){
        return name;
    }

    public boolean updateCost(double cost){
        this.cost = cost;
        return true;
    }
    public boolean setCompID(String componentID){
        this.foodID = componentID;
        return true;
    }
    public boolean setCompName(String componentName){
        this.name = componentName;
        return true;
    }

}
