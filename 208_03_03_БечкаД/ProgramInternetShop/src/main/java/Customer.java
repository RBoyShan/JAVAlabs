import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public class Customer {
    private String id;
    private Order currentOrder;
    private String massage;

    public Customer() {
        id = IdCreator.createCustomerId();
        currentOrder = new Order(id, new HashMap<>(0));
    }

    public void viewOrders(){
        printer(InternetShop.getInstance().getOrders(id));
    }

    public void checkOrderStatus(String orderID){
        Status status = InternetShop.getInstance().checkOrderStatus(orderID, id);
        switch (status){
            case COMPLETED:
                message("Order completed!");
                break;
            case NOTCOMPLETED:
                message("Order in progress!");
                break;
            case NOACCESS:
                message("You do not have access to this order!");
                break;
        }
    }

    public void findByName(String name){
        printer(InternetShop.getInstance().findProductsByName(name));
    }

    public void findByManufacturer(String manufacturer){
        printer(InternetShop.getInstance().findProductsByManufacturer(manufacturer));
    }

    public void addToOrder(Product product, int count){
        if(InternetShop.getInstance().checkStorage(product, count)) {
            currentOrder.addProduct(product, count);
        }
        else{
            message("There is no such quantity of this product in stock!");
        }

    }

    public void removeProductInOrder(Product product){
        if(currentOrder.getProducts().containsKey(product)){
            currentOrder.removeProduct(product);
        }
        else{
            message("There is no such product in the order!");
        }
    }

    public void changeProductQuantity(Product product, int newCount){
        if(InternetShop.getInstance().checkStorage(product, newCount)) {
            currentOrder.setProductCount(product, newCount);
        }
        else{
            message("There is no such quantity of products in stock!");
        }
    }

    public void replaceProduct(Product oldProduct, Product newProduct) {
        if (currentOrder.containsProduct(oldProduct)) {
            int count = currentOrder.getProducts().get(oldProduct);
            if(InternetShop.getInstance().checkStorage(newProduct, count)) {
                currentOrder.replaceProduct(oldProduct, newProduct);
            }
            else {
                message("There is no such quantity of products in stock!");
            }
        }
        else {
            message("There is no such product in the order!");
        }
    }

    public void sendOrder(){
        InternetShop.getInstance().addOrder(this, currentOrder);
        currentOrder = new Order(id, new HashMap<>(0));
    }

    public void message(String mess){
        setMessage(mess);
        printMessage();
    }

    private void setMessage(String massage) {
        this.massage = massage;
    }

    private void printMessage(){
        System.out.println("(" + id + ") New massage: " + massage);
    }

    private int countProduct(Product product){
        return currentOrder.getProducts().get(product);
    }

    private <E> void printer(Collection<E> collection){
        if(!collection.isEmpty()){
            for (E item: collection) {
                System.out.println(item);
            }
            return;
        }
        message("No matches found!");
    }

    private void printer(HashSet<Order> orders){
        if(!orders.isEmpty()) {
            for (Order order : orders) {
                order.printOrder();
            }
            return;
        }
        message("No matches found!");
    }

}
