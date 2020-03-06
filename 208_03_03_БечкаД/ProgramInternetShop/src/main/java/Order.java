import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

class Order {
    private final String id;
    private final String idCustomer;
    private float price;
    private Status status;
    private HashMap<Product, Integer> products;

    public Order(String idCustomer, HashMap<Product, Integer> productsList){
        products = new HashMap<>();
        if(!productsList.isEmpty()){
            products.putAll(productsList);
        }
        status = Status.NOTCOMPLETED;
        this.idCustomer = idCustomer;
        id = IdCreator.createOrderId();
    }

    public String getId() {
        return id;
    }

    public String getIdCustomer(){
        return idCustomer;
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Status getStatus(){
        return status;
    }

    public float getTotalPrice(){
        return price;
    }

    public void setProductCount(Product product, int newCount){
        if(products.containsKey(product)){
            products.put(product, newCount);
        }
    }

    public boolean containsProduct(Product product){
        return products.containsKey(product);
    }

    public void replaceProduct(Product oldProduct ,Product newProduct){
        if(products.containsKey(oldProduct)){
            int count = products.get(oldProduct);
            products.remove(oldProduct, count);
            products.put(newProduct, count);
        }
    }

    public void switchStatus(){
        if(status == Status.NOTCOMPLETED){
            status = Status.COMPLETED;
        }
    }

    public void addProduct(Product product, int count){
        if(products.containsKey(product)){
            count  += products.get(product);
        }
        products.put(product, count);
    }

    public void removeProduct(Product product){
        products.remove(product);
    }

    public void printOrder(){
        System.out.println("ID : " + id);
        System.out.println("Products:");
        for (Map.Entry<Product, Integer> product: products.entrySet()) {
            System.out.println(product.getKey() + "Count: " + product.getValue() + '\n');
        }
        calculatePrice(products);
        System.out.println("Total price: " + price);
    }

    private float calculatePrice(HashMap<Product,Integer> productsList){
        price = 0f;
        for (Map.Entry<Product, Integer> product: productsList.entrySet()) {
            price += (product.getKey().getPrice() * product.getValue());
        }
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Order order = (Order) o;
        return Float.compare(order.price, price) == 0 &&
                id.equals(order.id) &&
                idCustomer.equals(order.idCustomer) &&
                Objects.equals(products, order.products);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idCustomer, price, products);
    }
}

enum Status{
    COMPLETED,
    NOTCOMPLETED,
    NOACCESS
}

