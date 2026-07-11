package org.example;

public class CustomerStats {
    private long totalOrders;
    private double totalSpend;
    private double averageOrderValue;
    private String mostPurchasedCategory;

    public CustomerStats(long totalOrders,
                         double totalSpend,
                         double averageOrderValue,
                         String mostPurchasedCategory) {
        this.totalOrders = totalOrders;
        this.totalSpend = totalSpend;
        this.averageOrderValue = averageOrderValue;
        this.mostPurchasedCategory = mostPurchasedCategory;
    }

    public long getTotalOrders() {
        return totalOrders;
    }

    public double getTotalSpend() {
        return totalSpend;
    }

    public double getAverageOrderValue() {
        return averageOrderValue;
    }

    public String getMostPurchasedCategory() {
        return mostPurchasedCategory;
    }

    @Override
    public String toString() {
        return "CustomerStats{" +
                "totalOrders=" + totalOrders +
                ", totalSpend=" + totalSpend +
                ", averageOrderValue=" + averageOrderValue +
                ", mostPurchasedCategory='" + mostPurchasedCategory + '\'' +
                '}';
    }
}
