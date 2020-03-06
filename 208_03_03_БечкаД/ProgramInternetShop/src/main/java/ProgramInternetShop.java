import java.util.ArrayList;
import java.util.HashSet;

public class ProgramInternetShop {

    public static void main(String[] args) {
        ArrayList<Product> availableGoods = InternetShop.getInstance().getProductsFromStorage();

        for (Product pr: availableGoods) {
            System.out.println(pr);
        }

        Customer user_one = new Customer();
        Customer user_two = new Customer();
        Seller seller_one = new Seller();

        //user_one create order
        user_one.addToOrder(availableGoods.get(0), 100);
        user_one.addToOrder(availableGoods.get(0), 300);
        user_one.addToOrder(availableGoods.get(1), 120);
        user_one.addToOrder(availableGoods.get(3), 9000);
        user_one.sendOrder();

        user_one.addToOrder(availableGoods.get(4), 2);
        user_one.sendOrder();

        System.out.println("\nUSER 1 PRINT_ORDER:");

        user_one.viewOrders();

        //user_two create order
        user_two.addToOrder(availableGoods.get(1), 20);
        user_two.addToOrder(availableGoods.get(2), 30);
        user_two.addToOrder(availableGoods.get(3), 100);

        user_two.changeProductQuantity(availableGoods.get(1), 100);
        user_two.replaceProduct(availableGoods.get(3), availableGoods.get(0));
        user_two.removeProductInOrder(availableGoods.get(1));
        user_two.sendOrder();

        System.out.println("\n\nUSER 2 PRINT_ORDER:");

        user_two.viewOrders();

        //seller

        System.out.println("SELLER 1:");
        seller_one.completeOrder("1R");

        System.out.println("USER 1: ");
        user_one.checkOrderStatus("1R");
        user_one.checkOrderStatus("2R");
        System.out.println("USER 2: ");
        user_two.checkOrderStatus("2R");

    }
}
