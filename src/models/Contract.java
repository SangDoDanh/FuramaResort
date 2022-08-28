package models;

public class Contract {
    private String id;
    private String bookingID;
    private String customerID;
    private double deposit;
    private double totalPay;

    public Contract() {
    }

    public Contract(String id) {
        this.id = id;
    }

    public Contract(String id, String bookingID, String customerID, double deposit, double totalPay) {
        this.id = id;
        this.bookingID = bookingID;
        this.customerID = customerID;
        this.deposit = deposit;
        this.totalPay = totalPay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBookingID() {
        return bookingID;
    }

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public double getDeposit() {
        return deposit;
    }

    public void setDeposit(double deposit) {
        this.deposit = deposit;
    }

    public double getTotalPay() {
        return totalPay;
    }

    public void setTotalPay(double totalPay) {
        this.totalPay = totalPay;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s", id, bookingID, customerID, deposit, totalPay);
    }
}
