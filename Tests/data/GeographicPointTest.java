package data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GeographicPointTest {
    static GeographicPoint point1;
    static GeographicPoint point2;
    static GeographicPoint point3 ;

    @BeforeAll
    static void Initialize(){
          point1 = new GeographicPoint(30.5f, 10.85f);
          point2 = new GeographicPoint(40.7f, 40.76f);
          point3 = new GeographicPoint(0.0f, 0.0f);
    }

    @Test
    void getterTest() {
        assertEquals(30.5f, point1.getLatitude());
        assertEquals(10.85f, point1.getLongitude());

        assertEquals(40.7f, point2.getLatitude());
        assertEquals(40.76f, point2.getLongitude());

        assertEquals(0.0f, point3.getLatitude());
        assertEquals(0.0f, point3.getLongitude());
    }


    @Test
    void hashCodeTest() {
        assertEquals(point1.hashCode(),31 * (31 * 1 + Float.floatToIntBits(point1.getLatitude())) + Float.floatToIntBits(point1.getLongitude()));
        assertEquals(point2.hashCode(),31 * (31 * 1 + Float.floatToIntBits(point2.getLatitude())) + Float.floatToIntBits(point2.getLongitude()));
        assertEquals(point3.hashCode(),31 * (31 * 1 + Float.floatToIntBits(point3.getLatitude())) + Float.floatToIntBits(point3.getLongitude()));
    }

    @Test
    void toStringTest() {
        assertEquals("Geographic point {party='30.5'longitude='10.85}", point1.toString());
        assertEquals("Geographic point {party='40.7'longitude='40.76}", point2.toString());
        assertEquals("Geographic point {party='0.0'longitude='0.0}", point3.toString());
    }
}
