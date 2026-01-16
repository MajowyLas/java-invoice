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
        this.addProduct(product,1);
    }


    public void addProduct(Product product, Integer quantity) {
        if(product == null) {
            throw new IllegalArgumentException("Product cannot be null");

        } if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

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

        }return netValue;


    }


    public BigDecimal getTax() {
        return getGrossValue().subtract(getNetValue());
    }


    public BigDecimal getGrossValue() {
        BigDecimal netValue = BigDecimal.ZERO;

        for (Product product : this.productsMap.keySet()) {
            Integer quantity = this.productsMap.get(product);
            BigDecimal price = product.getPriceWithTax();
            price = price.multiply(BigDecimal.valueOf(quantity));
            netValue = netValue.add(price);

        }
        return netValue;

    }


}

