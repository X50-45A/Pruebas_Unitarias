package data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountTest {
    static UserAccount id1;
    static UserAccount id2;
    static UserAccount id3;


    @BeforeAll
    static void initId(){
        id1 = new UserAccount("user1@gmail.com");
        id2 = new UserAccount("user2@hotmail.es");
        id3 = new UserAccount("user3@outlook.org");
    }
    @Test
    void nullId(){
        assertThrows(NullPointerException.class, () -> {new UserAccount(null);});
    }

    @Test
    void invalidFormat(){
        assertThrows(IllegalArgumentException.class, () -> {new StationID("user");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("@hotmail.com");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("user.com");});
        assertThrows(IllegalArgumentException.class, () -> {new StationID("user@domain");});
        assertDoesNotThrow(() -> {new StationID("user@domain.org");});
    }

    @Test
    void equals(){
        assertEquals(id1, new StationID("user1@gmail.com"));
        assertNotEquals(id1,id2);
        assertNotEquals(id1,id3);
        assertNotEquals(id2,id3);
    }

    @Test
    void hashcode(){
        assertEquals(id1.hashCode(), "user1@gmail.com".hashCode());
        assertEquals(id2.hashCode(), "user2@hotmail.es".hashCode());
        assertEquals(id3.hashCode(), "user3@outlook.org".hashCode());
    }

    @Test
    void toStringTest(){
        assertEquals("UserAccount{" + "email=user1@gmail.com'" + '\'' + '}', id1.toString());
        assertEquals("UserAccount{" + "email=user2@hotmail.es'" + '\'' + '}', id2.toString());
        assertEquals("UserAccount{" + "email=user3@outlook.org'" + '\'' + '}', id3.toString());
    }
}
