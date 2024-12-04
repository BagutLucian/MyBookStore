package model;

public class EmployeeSales {

    public EmployeeSales(String employeeName, int booksSold, double totalSales) {
        this.employeeName = employeeName;
        this.booksSold = booksSold;
        this.totalSales = totalSales;
    }

    private String employeeName;
    private int booksSold;
    private double totalSales;
    public String getEmployeeName() {
        return employeeName;
    }

    public int getBooksSold() {
        return booksSold;
    }

    public double getTotalSales() {
        return totalSales;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public void setBooksSold(int booksSold) {
        this.booksSold = booksSold;
    }

    public void setTotalSales(double totalSales) {
        this.totalSales = totalSales;
    }

}
