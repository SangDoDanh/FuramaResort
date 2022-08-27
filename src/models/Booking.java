package models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Booking {
    private String id;
    private LocalDateTime startDay;
    private LocalDateTime endDay;
    private String customerID;
    private String serviceName;
    private String typeOfService;

    public Booking() {
    }

    public Booking(String id, LocalDateTime startDay, LocalDateTime endDay) {
        this.id = id;
        this.startDay = startDay;
        this.endDay = endDay;
    }

    public LocalDateTime getStartDay() {
        return startDay;
    }

    public void setStartDay(LocalDateTime startDay) {
        this.startDay = startDay;
    }

    public LocalDateTime getEndDay() {
        return endDay;
    }

    public void setEndDay(LocalDateTime endDay) {
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
