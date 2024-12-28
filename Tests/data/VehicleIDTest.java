package data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class VehicleIDTest implements StationUserVehicleInterface{
    static VehicleID id1;
    static VehicleID id2;
    static VehicleID id3;


    @BeforeAll
    static void initId(){
        id1 = new VehicleID("PMV00001");
        id2 = new VehicleID("PMV00002");
        id3 = new VehicleID("PMV00003");
    }
    @Test
    public void nullId(){
        assertThrows(NullPointerException.class, () -> {new VehicleID(null);});
    }

    @Test
    public void invalidFormat(){
        assertThrows(IllegalArgumentException.class, () -> {new VehicleID("00000000");});
        assertThrows(IllegalArgumentException.class, () -> {new VehicleID("@MV99999");});
        assertThrows(IllegalArgumentException.class, () -> {new VehicleID("PMVAAAAA");});
        assertDoesNotThrow(() -> {new VehicleID("PMV12345");});
    }

    @Test
    public void equals(){
        assertEquals(id1, new VehicleID("PMV00001"));
        assertNotEquals(id1,id2);
        assertNotEquals(id1,id3);
        assertNotEquals(id2,id3);
    }

    @Test
    public void hashcode(){
        assertEquals(id1.hashCode(), "PMV00001".hashCode());
        assertEquals(id2.hashCode(), "PMV00002".hashCode());
        assertEquals(id3.hashCode(), "PMV00003".hashCode());
    }

    @Test
    public void toStringTest(){
        assertEquals("VehicleID{" + "value='PMV00001" + '\'' + '}', id1.toString());
        assertEquals("VehicleID{" + "value='PMV00002" + '\'' + '}', id2.toString());
        assertEquals("VehicleID{" + "value='PMV00003" + '\'' + '}', id3.toString());
    }
}
