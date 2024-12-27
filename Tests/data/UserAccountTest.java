package data;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserAccountTest {
    static UserAccount user1;
    static UserAccount user2;
    static UserAccount user3;


    @BeforeAll
    static void initId(){
        user1 = new UserAccount("user1@gmail.com");
        user2 = new UserAccount("user2@hotmail.es");
        user3 = new UserAccount("user3@outlook.org");
    }
    @Test
    void nullId(){
        assertThrows(NullPointerException.class, () -> {new UserAccount(null);});
    }

    @Test
    void invalidFormat(){
        assertThrows(IllegalArgumentException.class, () -> {new UserAccount("user");});
        assertThrows(IllegalArgumentException.class, () -> {new UserAccount("@hotmail.com");});
        assertThrows(IllegalArgumentException.class, () -> {new UserAccount("user.com");});
        assertThrows(IllegalArgumentException.class, () -> {new UserAccount("user@domain");});
        assertDoesNotThrow(() -> {new UserAccount("user@domain.org");});
    }

    @Test
    void equals(){
        assertEquals(user1, new UserAccount("user1@gmail.com"));
        assertNotEquals(user1, user2);
        assertNotEquals(user1, user3);
        assertNotEquals(user2, user3);
    }

    @Test
    void hashcode(){
        assertEquals(user1.hashCode(), "user1@gmail.com".hashCode());
        assertEquals(user2.hashCode(), "user2@hotmail.es".hashCode());
        assertEquals(user3.hashCode(), "user3@outlook.org".hashCode());
    }

    @Test
    void toStringTest(){
        assertEquals("UserAccount{" + "email='user1@gmail.com" + '\'' + '}', user1.toString());
        assertEquals("UserAccount{" + "email='user2@hotmail.es" + '\'' + '}', user2.toString());
        assertEquals("UserAccount{" + "email='user3@outlook.org" + '\'' + '}', user3.toString());
    }
}
