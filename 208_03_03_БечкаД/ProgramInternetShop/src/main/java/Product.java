import java.util.Objects;

class Product {
    private String name;
    private String manufacturer;
    private float price;

    public Product(String name, String manufacturer, float price){
        this.name = name;
        this.manufacturer = manufacturer;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public float getPrice(){
        return price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Product product = (Product) o;
        return Float.compare(product.price, price) == 0 &&
                name.equals(product.name) &&
                manufacturer.equals(product.manufacturer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, manufacturer, price);
    }

    @Override
    public String toString(){
        return "Name: \t" + name + ";\nManufacturer: " + manufacturer + ";\n" + "Price: \t" + price + ";\n";
    }
}
