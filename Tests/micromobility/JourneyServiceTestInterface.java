package micromobility;

import org.junit.jupiter.api.Test;

public interface JourneyServiceTestInterface {

    /**
     * Test for initializing the service.
     */
    @Test
    void testSetServiceInit();

    /**
     * Test for finalizing the service.
     */
    @Test
    void testSetServiceFinish();

    /**
     * Test for verifying initial values of the journey.
     */
    @Test
    void testGetInitialValues();
}
