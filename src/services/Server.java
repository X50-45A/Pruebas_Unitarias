package services;

import data.GeographicPoint;
import data.StationID;
import data.UserAccount;
import data.VehicleID;
import micromobility.JourneyService;
import micromobility.PMVehicle;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import exceptions.*;
/**
 * External services involved in the shared micromobility system
 */
public interface Server { // External service for the persistent storage
    // To be invoked by the use case controller
    void checkPMVAvail(PMVehicle vhID) throws PMVNotAvailException, ConnectException;
    void registerPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date)
            throws InvalidPairingArgsException, ConnectException;
    void stopPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date, float avSp, float dist, int dur, BigDecimal imp)
            throws InvalidPairingArgsException, ConnectException;
    // Internal operations
    void setPairing(UserAccount user, VehicleID veh, StationID st, GeographicPoint loc, LocalDateTime date);
    void unPairRegisterService(JourneyService s)
            throws PairingNotFoundException;
    void registerLocation(VehicleID veh, StationID st);
}
