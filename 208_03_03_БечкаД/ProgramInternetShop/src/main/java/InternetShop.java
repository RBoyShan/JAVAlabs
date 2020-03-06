import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InternetShop {
    private static InternetShop instance = new InternetShop();
    private List<Map.Entry<Customer, Order>> orders;
    private Storage storage;
    private Storage reservedStorage;

    private  InternetShop(){
        storage = new Storage();
        try {
            readDataBase("C:\\Users\\User\\Desktop\\storage_data_base.txt");
        }
        catch (FileNotFoundException ex){
            System.out.println(ex.getMessage());
        }
        reservedStorage = new Storage();
        orders = new ArrayList<>();
    }

    public static InternetShop getInstance(){
        return instance;
    }

    public HashSet<Order> getOrders(String customerID){
        HashSet<Order> customerOrder = new HashSet<>();
        for (Map.Entry<Customer, Order> order: orders) {
            if (order.getValue().getIdCustomer().equals(customerID)){
                customerOrder.add(order.getValue());
            }
        }
        return customerOrder;
    }

    public ArrayList<Order> getOrders(){
        ArrayList<Order> requestedOrders = new ArrayList<>();
        for (Map.Entry<Customer, Order> order : orders){
            requestedOrders.add(order.getValue());
        }
        return requestedOrders;
    }

    public Map.Entry<Customer, Order> getOrderByID(String orderID){
        for (Map.Entry<Customer, Order> order : orders){
            if(order.getValue().getId().equals(orderID)){
                return order;
            }
        }
        return null;
    }

    public ArrayList<Product> getProductsFromStorage(){
        return new ArrayList<Product>(storage.getProducts());
    }

    public Set<Product> findProductsByName(String name){
        return storage.getProducts(name, null);
    }

    public Set<Product> findProductsByManufacturer(String manufacturer){
        return  storage.getProducts(null, manufacturer);
    }

    public void addOrder(Customer customer, Order newOrder){
        orders.add( new AbstractMap.SimpleEntry<Customer, Order>(customer,newOrder));
        reserveProducts(newOrder);
    }

    public boolean checkStorage(Product product, int count){
        return storage.contain(product, count);
    }

    public Status checkOrderStatus(String orderID, String userID){
        Order current = getOrderByID(orderID).getValue();
        if(current != null && current.getIdCustomer().equals(userID)){
            return current.getStatus();
        }
        return Status.NOACCESS;
    }

    public boolean extractByOrderFromStock(Order order){
        for (Map.Entry<Product, Integer> orderItem: order.getProducts().entrySet()) {
            if(reservedStorage.extractProduct(orderItem.getKey(), orderItem.getValue()) == null){
                return false;
            }
        }
        return true;
    }

    private void readDataBase(String address) throws FileNotFoundException {
        Scanner input = new Scanner(new File(address));
        while(input.hasNextLine()){
            storage.addProduct(new Product(input.next(), input.next(), input.nextFloat()), input.nextInt());
        }
        input.close();
    }

    private void reserveProducts(Order orderToReserve){
        for (Map.Entry<Product,Integer> order: orderToReserve.getProducts().entrySet()) {
            Product product = order.getKey();
            int count = order.getValue();
            storage.extractProduct(product, count);
            reservedStorage.addProduct(product, count);
        }
    }
}
