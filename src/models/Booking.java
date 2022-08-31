package models;

import java.time.LocalDate;
import java.util.Objects;

public class Booking {
    private String id;
    private LocalDate startDay;
    private LocalDate endDay;
    private String customerID;
    private String serviceName;
    private String typeOfService;
    private boolean isContract;

    public Booking(String id, LocalDate startDay, LocalDate endDay, String customerID, String serviceName, String typeOfService, boolean isContract) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.customerID = customerID;
        this.serviceName = serviceName;
        this.typeOfService = typeOfService;
        this.isContract = isContract;
    }

    public boolean isContract() {
        return isContract;
    }

    public void setContract(boolean contract) {
        isContract = contract;
    }

    public Booking() {
    }

    public Booking(String id, LocalDate startDay, LocalDate endDay) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public Booking(String id, LocalDate startDay, LocalDate endDay, String customerID, String serviceName, String typeOfService) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
        this.customerID = customerID;
        this.serviceName = serviceName;
        this.typeOfService = typeOfService;
    }

    public LocalDate getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDate startDay) {
        this.startDay = startDay;
    }

    public LocalDate getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDate endDay) {
        this.endDay = endDay;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Override
    public String toString() {
        return String.format("%s,,%s,,%s,,%s,,%s,,%s",
                id, startDay, endDay, customerID, serviceName, typeOfService);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return id.equals(booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
