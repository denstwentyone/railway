package util;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Test;

public class ValidationTest {
    
    @Test
    public void isPasswordValidTest() {
        assertFalse(Validation.isPasswordValid(null));
        assertFalse(Validation.isPasswordValid("password"));
        assertFalse(Validation.isPasswordValid("password!"));
        assertFalse(Validation.isPasswordValid("Password!"));
        assertFalse(Validation.isPasswordValid("Password!1"));
        assertTrue(Validation.isPasswordValid("Password!1@A"));
    }

    @Test
    public void isEmailValidTest() {
        assertFalse(Validation.isEmailValid(null));
        assertFalse(Validation.isEmailValid("email"));
        assertFalse(Validation.isEmailValid("email.com"));
        assertFalse(Validation.isEmailValid("email@com"));
        assertFalse(Validation.isEmailValid("email.com@com"));
        assertTrue(Validation.isEmailValid("email@com.com"));
    }

    @Test
    public void isDateValidTest() {
        assertFalse(Validation.isDateValid(null));
        assertFalse(Validation.isDateValid("date"));
        assertFalse(Validation.isDateValid("10-12"));
        assertFalse(Validation.isDateValid("10-12-99"));
        assertFalse(Validation.isDateValid("10-12-1999"));
        assertFalse(Validation.isDateValid("1999-13-41"));
        assertTrue(Validation.isDateValid("1999-12-10"));
    }

    @Test
    public void isTimeValidTest() {
        assertFalse(Validation.isTimeValid(null));
        assertFalse(Validation.isTimeValid("12-31"));
        assertFalse(Validation.isTimeValid("25:31"));
        assertFalse(Validation.isTimeValid("23:66"));
        assertTrue(Validation.isTimeValid("23:01:01"));
    }
}
