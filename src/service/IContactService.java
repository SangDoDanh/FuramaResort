package service;

import models.Booking;

import java.util.Queue;

public interface IContactService{
    void createContract(Booking bookingsToContract);

    void display();

    void edit();
}
