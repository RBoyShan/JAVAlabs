import java.util.Map;

public class Seller {

    private Map.Entry<Customer, Order> currentOrder;

    public void viewAllOrders(){
        for (Order order: InternetShop.getInstance().getOrders()) {
            order.printOrder();
        }
    }

    public boolean completeOrder(String orderID){
        InternetShop shop = InternetShop.getInstance();
        currentOrder = shop.getOrderByID(orderID);
        if(currentOrder != null && shop.extractByOrderFromStock(currentOrder.getValue())){
            currentOrder.getValue().switchStatus();
            currentOrder.getKey().message("Your order has been processed!");
            return true;
        }
        return false;
    }

}
