package event.components;

/**
 * Created by nikhil on 30-08-2015.
 */
public interface Components {
    double getCost();
    String getCompID();
    String getCompName();

    boolean updateCost(double cost);
    boolean setCompID(String componentID);
    boolean setCompName(String componentName);
}
