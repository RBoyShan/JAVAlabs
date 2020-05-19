import Auth.Authorization;
import Auth.AuthorizationException;
import Model.*;
import Model.Actors.User;
import Model.Data.Entity.Order;
import Model.Data.Entity.Product;
import Model.Data.Model;

import java.util.ArrayList;
import java.util.HashMap;

public class MVCInternetShop {
    public static void main(String[] args) {
        //DEMONSTRATION model

        User user;
        Model model = new FileModelInternetShop();
        Authorization auth = new Authorization(model);

        try {
            auth.singIn();

        } catch (AuthorizationException ex) {
            System.out.println(ex.getMessage());
        }

        user = auth.getCurrentUser();

        //All products
        System.out.println("All products:\n");
        model.getAllProducts().forEach(product -> {
            System.out.println(product);
        });

        //Find by name
        System.out.println("\n\nFind by pattern 't':\n");
        model.findByName("t").forEach(product -> {
            System.out.println(product);
        });

        //Find by manufacturer
        System.out.println("\n\nFind by pattern 'aSU':\n");
        model.findByManufacturer("aSU").forEach(product -> {
            System.out.println(product);
        });

        //Create order
        System.out.println("\n\n\t\tCreate Order\n");
        ArrayList<String> names = model.getProductsList();  //Список названий товаров

        System.out.println("Products names:");
        names.forEach(name -> System.out.println(name));

        HashMap<Product, Integer> toOrder = new HashMap<>();
        Product product = model.getProduct(names.get(0));     //Получение товара по названию
        toOrder.put(product, 20);

        if(model.addOrder(new Order(user.getId(), toOrder))){ //Создание нового заказа
            System.out.println("\nOrder:");
            model.getOrders().forEach(order -> System.out.println(order)); // Все заказы
        }

        //Добавить товар в заказ
        for (String name : names) { //К товару который уже находится в заказе добавится 10 едениц
            product = model.getProduct(name);
            model.addProductToOrder(
              model.getOrders().get(0),
                    product,
                    10
            );
        }

        Order order =  model.getOrder((long) 1); // Получить заказ по id
        System.out.println(order);

        //Удалить товар из заказа
        order.removeProduct(product);
        System.out.println(order);

        //Статус заказа
        System.out.println(order.getStatus());
        model.completeOrder((long)1);       // Выполнить заказ
        System.out.println(order.getStatus());

    }
}
