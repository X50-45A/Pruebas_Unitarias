package micromobility;
import data.GeographicPoint;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PMVehicleTest {
    private static PMVehicle vehicle;
    private static int id;
    private static GeographicPoint position;
    private static PMVState state;
    private static int charge;
    private GeographicPoint gP;

    @BeforeAll
    static void initValidVehicle(){
        id = 1;
        position = new GeographicPoint(50.0f,21.75f);
        state = PMVState.Available;
        charge = 50;
        vehicle = new PMVehicle(id, position, state, charge);
    }
    @BeforeEach
    void initVariables(){
        GeographicPoint gP = new GeographicPoint(20.0f, 20.0f);
    }

    @Test
    void setterAndGetter(){
        assertEquals(vehicle.state, state);

        vehicle.setNotAvailbe();
        assertEquals(vehicle.state, PMVState.NotAvailable);

        vehicle.setAvailable();
        assertEquals(vehicle.state, PMVState.Available);

        vehicle.setUnderWay();
        assertEquals(vehicle.state, PMVState.UnderWay);

        vehicle.setLocation(gP);

        assertEquals(vehicle.position, gP);
        assertEquals(vehicle.getVehicleId(), id);
        assertEquals(vehicle.getCharge(), charge);
        assertNotEquals(vehicle.getPosition(), position);
        assertEquals(vehicle.getPosition(), gP);
    }
    //Hay que testear posibles excepciones de valores incorrectos
}
