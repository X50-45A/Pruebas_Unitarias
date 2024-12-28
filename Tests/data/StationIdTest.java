package data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class StationIdTest implements StationUserVehicleInterface{
    static StationID id1;
    static StationID id2;
    static StationID id3;


    @BeforeAll
    static void initId(){
        id1 = new StationID("ST0001");
        id2 = new StationID("ST0002");
        id3 = new StationID("ST0003");
    }
    @Test
    public void nullId(){
        assertThrows(NullPointerException.class, () -> {new StationID(null);});
    }

    @Test
    public void invalidFormat(){
        assertThrows(IllegalArgumentException.class, () -> {new StationID("ST");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("ST0");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("AT2345");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("123456");});
        assertDoesNotThrow(() -> {new StationID("ST0001");});
    }

    @Test
    public void equals(){
        assertEquals(id1, new StationID("ST0001"));
        assertNotEquals(id1,id2);
        assertNotEquals(id1,id3);
        assertNotEquals(id2,id3);
    }

    @Test
    public void hashcode(){
        assertEquals(id1.hashCode(), "ST0001".hashCode());
        assertEquals(id2.hashCode(), "ST0002".hashCode());
        assertEquals(id3.hashCode(), "ST0003".hashCode());
    }

    @Test
    public void toStringTest(){
        assertEquals("StationID{" + "value='ST0001" + '\'' + '}' , id1.toString());
        assertEquals("StationID{" + "value='ST0002" + '\'' + '}' , id2.toString());
        assertEquals("StationID{" + "value='ST0003" + '\'' + '}' , id3.toString());
    }
}
