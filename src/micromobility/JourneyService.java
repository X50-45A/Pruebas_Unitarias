package micromobility;

import data.GeographicPoint;
import data.StationID;
import data.UserAccount;
import data.VehicleID;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class JourneyService {
 // Class members based on Domain Model
    private final UserAccount user;
    private final VehicleID vehicle;
    private final StationID startStation;
    private final GeographicPoint startLocation;
    private StationID endStation;
    private GeographicPoint endLocation;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private float distance;
    private float averageSpeed;
    private BigDecimal cost;
    // constructor
    public JourneyService(UserAccount user, VehicleID vehicle, StationID startStation, GeographicPoint startLocation) {
        this.user = user;
        this.vehicle = vehicle;
        this.startStation = startStation;
        this.startLocation = startLocation;
    }

    // All the getter methods
    public UserAccount getUser() {
        return user;
    }

    public VehicleID getVehicle() {
        return vehicle;
    }

    public StationID getStartStation() {
        return startStation;
    }

    public GeographicPoint getStartLocation() {
        return startLocation;
    }

    public StationID getEndStation() {
        return endStation;
    }

    public GeographicPoint getEndLocation() {
        return endLocation;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public float getDistance() {
        return distance;
    }

    public float getAverageSpeed() {
        return averageSpeed;
    }

    public BigDecimal getCost() {
        return cost;
    }
    // Among the setter methods must appear these ones:
    public void setServiceInit(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setServiceFinish(LocalDateTime endTime, StationID endStation, GeographicPoint endLocation, float distance, float averageSpeed, BigDecimal cost) {
        this.endTime = endTime;
        this.endStation = endStation;
        this.endLocation = endLocation;
        this.distance = distance;
        this.averageSpeed = averageSpeed;
        this.cost = cost;
    }
}

