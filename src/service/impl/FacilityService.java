package service.impl;

import models.facility.Facility;
import service.IFacilityService;
import utils.read_write.ReadFile;

import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FacilityService implements IFacilityService {
    List<Facility> facilityList;
    @Override
    public void display() {

    }
    public Map<Facility,Integer> readFileFacility(String path) {
        Map<Facility,Integer> facilityMap = new LinkedHashMap<>();
        List<String> facilityString = ReadFile.readFile(path);

    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }
}
