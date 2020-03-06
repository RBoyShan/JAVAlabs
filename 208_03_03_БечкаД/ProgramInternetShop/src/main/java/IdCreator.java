import java.util.concurrent.atomic.AtomicInteger;

class IdCreator {
    private static final AtomicInteger _orderId = new AtomicInteger(1);
    private static final AtomicInteger _customerId = new AtomicInteger(1);

    public static String createOrderId(){
        return create(_orderId, "R");
    }

    public static String createCustomerId(){
        return create(_customerId, "C");
    }

    private static String create(AtomicInteger id, String index){

        return Integer.toString(id.getAndIncrement()) + index;
    }
}



