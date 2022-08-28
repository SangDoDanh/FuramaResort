package service.impl;

import controllers.BookingController;
import models.Booking;
import models.Contract;
import service.IContactService;
import utils.get_set_service.GetService;
import utils.read_write.ReadFile;
import utils.read_write.WriteFile;

import java.util.*;

public class ContactService implements IContactService {
    private static final String PATH_CONTRACT = "src/data/contract.csv";
    private static Queue<Contract> contracts;
    public void display() {
        contracts = readFileContract(PATH_CONTRACT);
        System.out.println("--CONTRACT LIST---");
        for(Contract ctr: contracts) {
            System.out.println(ctr);
        }
    }

    @Override
    public void edit() {
        String id = GetService.getStr("Enter contract id: ");
        Contract contract = finContractByID(id);
        if(contract == null) {
            System.out.println("Contract is not found!");
        } else {
            setPropertyContract(contract);
            writeFile(contracts, PATH_CONTRACT);
        }
    }

    private void setPropertyContract(Contract contract) {
        String propertyOfContract;
        int length = contract.toString().split(",,").length;
        int choice;
        while (true) {
            propertyOfContract = getAllPropertyOfContract(contract);
            System.out.println(propertyOfContract);
            System.out.printf("%d. exit\n",length);
            choice = GetService.getNumberInteger("Choice property you want to edit:", 0, length);
            if(choice == 5) {
                break;
            }
            setProperty(choice, contract);
        }
    }
    private void setProperty(int choice, Contract contract) {
        switch (choice) {
            case 0:
                String id = GetService.getStr("Enter new contract: ");
                contract.setId(id);
                break;
            case 1:
                Booking booking = GetService.getBookingByID();
                BookingService.bookingsToContracted.remove(booking.getId());
                contract.setBookingID(booking.getId());
                contract.setCustomerID(booking.getCustomerID());
                break;
            case 2:
                System.out.println("Customer id is updated by booking!");
                break;
            case 3:
                 double deposit = GetService.getNumberDouble("Enter ner deposit: ", 0, 100000000);
                 contract.setDeposit(deposit);
                break;
            case 4:
                double totalPay = GetService.getNumberDouble("Enter ner totalPay: ", 0, 100000000);
                contract.setTotalPay(totalPay);
                break;
        }
    }

    private String getAllPropertyOfContract(Contract contract) {
        String[] propertyContract = contract.toString().split(",,");
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < propertyContract.length; i++) {
            result.append(String.format("%d. %s    ",i, propertyContract[i]));
        }
        return result.toString();
    }

    private Contract finContractByID(String id) {
        contracts = readFileContract(PATH_CONTRACT);
        for(Contract ctr : contracts) {
            if(ctr.getId().equalsIgnoreCase(id)) {
                return ctr;
            }
        }
        return null;
    }

    public void add() {

    }

    @Override
    public void createContract(Booking bookingsToContract) {
        contracts = readFileContract(PATH_CONTRACT);
        String id = GetService.getStr("Enter contract id: ");
        double deposit = GetService.getNumberDouble("Enter deposit: ", 1, 50000000);
        double totalPay = calTotalPay();
        contracts.add(new Contract(id,
                bookingsToContract.getId(),
                bookingsToContract.getCustomerID(),
                deposit,
                totalPay));
        BookingService.bookingsToContracted.add(id);
        writeFile(contracts,PATH_CONTRACT);
        System.out.println("Create new contract success!");
    }

    private Queue<Contract> readFileContract(String pathContract) {
        List<String> contractString = ReadFile.readFile(pathContract);
        Queue<Contract> contractQueue = new LinkedList<>();
        String[] propertyOfContract;
        for(String c : contractString) {
            propertyOfContract = c.split(",,");
            contractQueue.offer(new Contract(propertyOfContract[0],
                    propertyOfContract[1],
                    propertyOfContract[2],
                    Double.parseDouble(propertyOfContract[3]),
                    Double.parseDouble(propertyOfContract[4])));
        }
        return contractQueue;
    }
    private void writeFile(Queue<Contract> contractQueue, String path) {
        List<String> contractString = new ArrayList<>();
        for(Contract c : contractQueue) {
            contractString.add(c.toString());
        }
        WriteFile.writeFile(path, contractString);
    }

    private double calTotalPay() {
        return 1;
    }

}
