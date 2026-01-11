package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products;

    public Invoice() {
        this.products = new ArrayList<>();
    }


    public void addProduct(Product product) {
        this.products.add(product);
    }


    public void addProduct(Product product, Integer quantity) {
        this.productsMap.put(product,quantity);
        }


    private Map<Product,Integer> productsMap = new HashMap<>();

        public BigDecimal getNetValue() {
            BigDecimal netValue = BigDecimal.ZERO;

            for (Product product : this.productsMap.keySet()) {
                Integer quantity = this.productsMap.get(product);
                BigDecimal price = product.getPrice();
                price = price.multiply(BigDecimal.valueOf(quantity));
                netValue = netValue.add(price);

                                }
                return netValue;
            }


    public BigDecimal getTax() {

           return BigDecimal.ZERO;
    }

    public BigDecimal getGrossValue() {
        return BigDecimal.ZERO;
    }
}
