package event.cart;

import java.util.ArrayList;

import event.components.Components;

/**
 * Created by nikhil on 30-08-2015.
 */
public class EventCart {
    private ArrayList<Components> products;
    private String customerID;

    public EventCart(String customerID){
        this.customerID=customerID;
        products = new ArrayList<>();
    }

    public void addComponent(Components c){
        products.add(c);
    }

    public void removeComponent(Components c) {
        products.remove(c);
    }

    public String getCustomerID(){
        return customerID;
    }

    public int getItemCount(){
        return products.size();
    }

    public double getTotalPrice(){
        double finalPrice=0;
        for(int i=0;i<products.size();i++){
            finalPrice+=products.get(i).getCost();
        }
        return finalPrice;
    }

}
