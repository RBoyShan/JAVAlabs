import java.util.*;

class Storage {
    private HashMap<Product, Integer> products;

    public Storage(){
        products = new HashMap<>(0);
    }

    public Set<Product> getProducts() {
        return products.keySet();
    }

    public Set<Product> getProducts(String name, String manufacturer){
        Set<Product> matchesFound = new HashSet<Product>(0);
        if(name != null && !name.isEmpty()){
            for (Product product: products.keySet()) {
                if(product.getName().equalsIgnoreCase(name)){
                    matchesFound.add(product);
                }
            }
        }
        else if(manufacturer != null && !manufacturer.isEmpty()){
            for (Product product: products.keySet()) {
                if(product.getManufacturer().equalsIgnoreCase(manufacturer)){
                    matchesFound.add(product);
                }
            }
        }
        return matchesFound;
    }

    public void addProduct(Product newProduct, int count){
        if(products.containsKey(newProduct)){
            count += products.get(newProduct);
        }
        products.put(newProduct, count);
    }

    public Map.Entry<Product, Integer> extractProduct(Product product, int count){
        if(contain(product,count)){
            products.put(product, products.get(product) - count);
            return new AbstractMap.SimpleEntry<Product, Integer>(product, products.get(product) - count);
        }
        return null;
    }

    public boolean contain(Product product, int count){
        return  products.containsKey(product) && (products.get(product) >= count);
    }


}
