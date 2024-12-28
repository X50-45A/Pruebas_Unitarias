package data;

import org.junit.jupiter.api.Test;

public interface StationIdTestInterface {

    /**
     * Test for null values in StationID.
     */
    @Test
    void nullId();

    /**
     * Test for invalid format validation in StationID.
     */
    @Test
    void invalidFormat();

    /**
     * Test for equals method in StationID.
     */
    @Test
    void equals();

    /**
     * Test for hashCode method in StationID.
     */
    @Test
    void hashcode();

    /**
     * Test for toString method in StationID.
     */
    @Test
    void toStringTest();
}
