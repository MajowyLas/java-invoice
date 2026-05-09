package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {

    private static int nextInvoiceNumber = 1;
    private final int invoiceNumber;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();


    public Invoice() {
        this.invoiceNumber = nextInvoiceNumber;
        nextInvoiceNumber++;
    }

    public static int getInvoiceNumber() {
        return nextInvoiceNumber;
    }

    public void addProduct(Product product) {
        addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null || quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int generateInvoiceNumber() {
        int invoiceNumber = 0;
        for (Product product : products.keySet()) {
            invoiceNumber = invoiceNumber + 1;
            products.put(product, invoiceNumber);
        }
        return invoiceNumber;
    }
}
