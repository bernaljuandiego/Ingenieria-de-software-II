package co.edu.konradlorenz.excolnet.Activities;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoginActivityTest {


    private LoginActivity lActivity;

    @Before
    public void setUp() {
        lActivity = new LoginActivity();
    }

    //Testing method with an invalid password
    @Test
    public void validateUnsupportedPassword() throws Exception {
        assertEquals(false, lActivity.isPasswordValid("dadad"));

    }

    //Testing method with punctual security password
    @Test
    public void validateValidPassword() throws Exception {
        assertEquals(true, lActivity.isPasswordValid("TestingPr00ves2019#"));
    }

    //Testing method with a simple validPassword
    @Test
    public void validateInsecurePassword() throws Exception {
        assertEquals(false, lActivity.isPasswordValid("testing2013"));
    }

    //Testing method with valid email
    @Test
    public void validateValidEmail() throws Exception {
        assertEquals(true, lActivity.isEmailValid("lruizsua@konradlorenz.edu.co"));
    }

    //Testing method with invalid Email
    @Test
    public void validateUnsupportedEmail() {
        assertEquals(false, lActivity.isEmailValid(("asdfghghfgdfdgd")));
    }

    //Testing method with regular simple expressions
    @Test
    public void validateRegularExpession() {
        assertEquals(false, lActivity.isEmailValid("testing@testtest"));
    }


}