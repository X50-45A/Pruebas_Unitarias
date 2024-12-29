package micromobility;

import data.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class JourneyServiceTest implements JourneyServiceTestInterface {

    private JourneyService journeyService;

    @BeforeEach
    void setUp() {
        journeyService = new JourneyService(
                new UserAccount("testUser@example.com"),
                new VehicleID("PMV12345"),
                new StationID("ST1234"),
                new GeographicPoint(40.7128f, -74.0060f)
        );
    }

    @Test
    public void testSetServiceInit() {
        LocalDateTime startTime = LocalDateTime.now();

        journeyService.setServiceInit(startTime);

        assertEquals(startTime, journeyService.getStartTime());
    }

    @Test
    public void testSetServiceFinish() {
        LocalDateTime endTime = LocalDateTime.now();
        StationID endStation = new StationID("ST1234");
        GeographicPoint endLocation = new GeographicPoint(34.0522f, -118.2437f);
        float distance = 15.5f;
        float averageSpeed = 25.0f;
        BigDecimal cost = new BigDecimal("12.75");

        journeyService.setServiceFinish(endTime, endStation, endLocation, distance, averageSpeed, cost);

        assertEquals(endTime, journeyService.getEndTime());
        assertEquals(endStation, journeyService.getEndStation());
        assertEquals(endLocation, journeyService.getEndLocation());
        assertEquals(distance, journeyService.getDistance());
        assertEquals(averageSpeed, journeyService.getAverageSpeed());
        assertEquals(cost, journeyService.getCost());
    }

    @Test
    public void testGetInitialValues() {
        assertEquals("testUser@example.com".hashCode(), journeyService.getUser().hashCode());
        assertEquals("PMV12345".hashCode(), journeyService.getVehicle().hashCode());
        assertEquals("ST1234".hashCode(), journeyService.getStartStation().hashCode());
        assertEquals(40.7128f, journeyService.getStartLocation().getLatitude());
        assertEquals(-74.0060f, journeyService.getStartLocation().getLongitude());
    }
}
