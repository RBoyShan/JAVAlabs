package Model;

import Model.Data.Entity.Order;
import Model.Data.Entity.OrderStatus;
import Model.Data.Entity.Product;
import Model.Data.Entity.Storage;
import Model.Data.Model;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

abstract class ModelInternetShop implements Model {
    private ArrayList<Order> orders;
    private Storage mainStorage;
    private Storage reservedStorage;

    public ModelInternetShop() {
        this.orders = new ArrayList<>();
        this.reservedStorage = new Storage();
        this.mainStorage = new Storage(this.readDataBase());
    }

    //Все заказы
    @Override
    public ArrayList<Order> getOrders() {
        return this.orders;
    }

    //Добавить заказ
    @Override
    public boolean addOrder(Order order) {
        this.reserveProducts(order);
        return this.orders.add(order);
    }

    //Все заказы определенного пользователя
    @Override
    public ArrayList<Order> getOrders(Long customerId) {
        ArrayList<Order> requestedOrders = new ArrayList<>();
        this.orders.forEach(order -> {
            if(order.getCustomerId().equals(customerId))
                requestedOrders.add(order);
        });
        return requestedOrders;
    }

    //Вернуть заказ по id
    @Override
    public Order getOrder(Long id) {
        for (Order order : this.orders) {
            if(order.getId().equals(id))
                return order;
        }
        return null;
    }

    @Override
    public boolean addProductToOrder(Order order, Product product, int count) {
        if(this.orders.contains(order) && order.getStatus() == OrderStatus.NOTCOMPLETED) {
            if(this.mainStorage.contain(product, count)) {
                this.mainStorage.extractProduct(product, count);
                this.reservedStorage.addProduct(product, count);
                return order.addProduct(product,count);
            }
        }
        return false;
    }

    @Override
    public boolean removeProductFormOrder(Order order, Product product) {
        if(this.orders.contains(order) && order.getStatus() == OrderStatus.NOTCOMPLETED) {
            this.reservedStorage.extractProduct(product, order.getProducts().get(product));
            this.mainStorage.addProduct(product, order.getProducts().get(product));
            return order.removeProduct(product);
        }
        return false;
    }

    @Override
    public ArrayList<String> getProductsList() {
        return this.mainStorage.getProductsNames();
    }

    //Все продукты в хранилище
    @Override
    public ArrayList<Product> getAllProducts() {
        return new ArrayList<>(this.mainStorage.getProducts());
    }

    //Поиск продуктов по имени
    @Override
    public ArrayList<Product> findByName(String name) {
        return new ArrayList<>(this.mainStorage.getProducts(product -> {
            Pattern pattern = Pattern.compile(name.toLowerCase());
            Matcher matcher = pattern.matcher(product.getName().toLowerCase());
            return matcher.find();
        }));
    }

    //Поиск продуктов по производителю
    @Override
    public ArrayList<Product> findByManufacturer(String manufacturer) {
        return new ArrayList<>(this.mainStorage.getProducts(product -> {
            Pattern pattern = Pattern.compile(manufacturer.toLowerCase());
            Matcher matcher = pattern.matcher(product.getManufacturer().toLowerCase());
            return matcher.find();
        }));
    }

    @Override
    public Product getProduct(String name) {
        return this.mainStorage.getProduct(name);
    }

    //Проверка хранилища на наличие товара
    @Override
    public boolean checkProduct(String name, int count) {
        return this.mainStorage.contain(this.mainStorage.getProduct(name), count);
    }

    //Виполнение заказа
    @Override
    public boolean completeOrder(Long id) {
        AtomicBoolean result = new AtomicBoolean(true);
        Order order = this.getOrder(id);
        if(order.getStatus() != OrderStatus.COMPLETED) {
            order.setStatus(OrderStatus.COMPLETED);
            order.getProducts().forEach((product, count) -> {
                if(this.reservedStorage.extractProduct(product, count) == null){
                    result.set(false);
                }
            });
        } else {
            result.set(false);
        }
        return result.get();
    }

    //Перемещение продуктов из главного хранилища в зарезервированое
    protected void reserveProducts(Order order) {
        order.getProducts().forEach((product, count) -> {
            if(this.mainStorage.extractProduct(product, count) != null) {
                this.reservedStorage.addProduct(product, count);
            }
        });
    }
}