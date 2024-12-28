package data;

import org.junit.jupiter.api.Test;

public interface UserAccountTestInterface {

    /**
     * Test for null values in UserAccount.
     */
    @Test
    void nullId();

    /**
     * Test for invalid email format in UserAccount.
     */
    @Test
    void invalidFormat();

    /**
     * Test for equals method in UserAccount.
     */
    @Test
    void equals();

    /**
     * Test for hashCode method in UserAccount.
     */
    @Test
    void hashcode();

    /**
     * Test for toString method in UserAccount.
     */
    @Test
    void toStringTest();
}
