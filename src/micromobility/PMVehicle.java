package micromobility;

import data.GeographicPoint;

/**
 * Internal classes involved in in the use of the service
 */
public class PMVehicle {
    private final int vehicleId;
    PMVState state;
    GeographicPoint position;
    private int charge;
    public PMVehicle(int vehicleId, GeographicPoint position, PMVState state, int charge) {
        this.vehicleId = vehicleId;
        this.charge = charge;
        this.position = position;
        this.state = state;
    }
    // The constructor/s
    // All the getter methods
    // The setter methods to be used are only the following ones
    public void setNotAvailbe () {
        this.state = PMVState.NotAvailable;
    }
    public void setUnderWay () {
        this.state = PMVState.UnderWay;
    }
    public void setAvailable() {
        this.state = PMVState.Available;
    }
    public void setLocation(GeographicPoint gP) {
        this.position = gP;
    }

    public int getVehicleId() {
        return vehicleId;
    }
    public int getCharge() {
        return charge;
    }
    public GeographicPoint getPosition() {
        return position;
    }
}
