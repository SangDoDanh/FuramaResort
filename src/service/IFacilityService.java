package service;

import models.facility.Facility;

public interface IFacilityService extends IService{
    void edit();

    Facility finFacilityByID(String facilityID);
}
