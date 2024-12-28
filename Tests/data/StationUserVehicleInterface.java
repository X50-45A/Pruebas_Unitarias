package data;

import org.junit.jupiter.api.Test;

public interface StationUserVehicleInterface {

    /**
     * Test for null values in VehicleID.
     */
    @Test
    void nullId();

    /**
     * Test for invalid format validation in VehicleID.
     */
    @Test
    void invalidFormat();

    /**
     * Test for equals method in VehicleID.
     */
    @Test
    void equals();

    /**
     * Test for hashCode method in VehicleID.
     */
    @Test
    void hashcode();

    /**
     * Test for toString method in VehicleID.
     */
    @Test
    void toStringTest();

}
