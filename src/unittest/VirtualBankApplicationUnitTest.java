package unittest;

import controller.*;
import model.*;
import org.junit.Test;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * JUnit test with using TDD method
 */
public class VirtualBankApplicationUnitTest {
    private SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
    private SimpleDateFormat datetimeSdf = new SimpleDateFormat("yyyy-MM-dd|HH:mm:ss");
    private UserController userController = new UserController();
    private AccountController accountController = new AccountController();

    @Test
    public void testCreateUser() throws ParseException {
        User kid1 = new Kid("Kid", "Alan", "Alan123", "Male", dateSdf.parse("2000-01-01"), "1-12345678", "London");
        User kid2 = new Kid("Kid", "Bob", "Alan123", "Male", dateSdf.parse("2017-06-12"), "1-12345678", "London");
        User parent = new Parent("Parent", "Cindy", "Cindy789", "Female", dateSdf.parse("1995-09-30"), "1-34567890", "London", "Mother");

        assertEquals("Alan123", kid1.getPassword());
        assertEquals("Bob", kid2.getName());
        assertEquals("1995-09-30", dateSdf.format(parent.getDob()));
    }

    @Test
    public void testAddKid() throws ParseException {
        User kid1 = new Kid("Kid", "Alan", "Alan123", "Male", dateSdf.parse("2000-01-01"), "1-12345678", "London");
        User kid2 = new Kid("Kid", "Bob", "Bob456", "Male", dateSdf.parse("2017-06-12"), "1-12345678", "London");
        User parent = new Parent("Parent", "Cindy", "Cindy789", "Female", dateSdf.parse("1995-09-30"), "1-34567890", "London", "Mother");

        ((Parent) parent).addKid(kid1);
        ((Parent) parent).addKid(kid2);
        assertEquals(2, ((Parent) parent).getKidList().size());
        assertEquals("Kid Alan Alan123 Male 2000-01-01 1-12345678 London", ((Parent) parent).getKidList().get(0).toString());
        assertEquals("Kid Bob Bob456 Male 2017-06-12 1-12345678 London", ((Parent) parent).getKidList().get(1).toString());
    }

    @Test
    public void testLogin() throws ParseException {
        User kid1 = new Kid("Kid", "Alan", "Alan123", "Male", dateSdf.parse("2000-01-01"), "1-12345678", "London");
        User kid2 = new Kid("Kid", "Bob", "Bob456", "Male", dateSdf.parse("2017-06-12"), "1-12345678", "London");
        userController.addUser(kid1);
        userController.addUser(kid2);

        User loginUser1 = userController.login("Alan", "Kid", "Alan123");
        User loginUser2 = userController.login("Bob", "Kid", "Bob456");
        User loginUser3 = userController.login("Bob", "Parent", "Bob456");

        assertEquals(kid1, loginUser1);
        assertEquals(kid2, loginUser2);
        assertNull(null, loginUser3);
    }

    @Test
    public void testAddAccount() throws ParseException {
        User kid = new Kid("Kid", "Alan", "Alan123", "Male", dateSdf.parse("2000-01-01"), "1-12345678", "London");
        Account alanCurrentAccount = new CurrentAccount("Current", kid, "AlanCurrent001", datetimeSdf.parse("2020-02-14|00:00:00"), 10000.00, 1000.00);
        Account alanSavingAccount = new SavingAccount("Saving", kid, "BobSaving001",  datetimeSdf.parse("2020-02-14|00:00:00"), 3550.0, 0.05);
        accountController.addAccount(alanCurrentAccount);
        accountController.addAccount(alanSavingAccount);

        assertEquals("Alan", alanCurrentAccount.getUser().getName());
        assertEquals("Alan", alanSavingAccount.getUser().getName());
        assertEquals("2020-02-14|00:00:00", datetimeSdf.format(alanSavingAccount.getCreateTime()));
    }
}
