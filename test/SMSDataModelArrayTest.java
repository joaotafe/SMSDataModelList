import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SMSDataModelArrayTest {
    private SMSDataModelArray dataModel;

    @BeforeEach
    public void setUp() {
        // Create a new SMSDataModelArray with an initial message and a capacity of 3 phone numbers.
        dataModel = new SMSDataModelArray("Hello, World", 3);
    }

    @Test
    public void testAddPhoneNumber() throws SMSDataModelFullException {
        // Test adding unique phone numbers and checking for FULL and DUPLICATE cases.

        // Add a unique phone number and expect it to be returned.
        assertEquals("1234567890", dataModel.addPhoneNumber("1234567890"));

        // Add another unique phone number and expect it to be returned.
        assertEquals("9876543210", dataModel.addPhoneNumber("9876543210"));

        // Commented out: Adding a phone number when the data model is full (testing for FULL).
        // assertEquals(SMSDataModelArray.FULL, dataModel.addPhoneNumber("5555555555"));

        // Add a phone number that is a DUPLICATE of the first number added.
        // Expect to receive DUPLICATE as it already exists.
        assertEquals(SMSDataModelArray.DUPLICATE, dataModel.addPhoneNumber("1234567890"));
    }

    @Test
    public void testUpdatePhoneNumber() throws SMSDataModelFullException {
        // Test updating phone numbers and checking for updates and invalid cases.

        // Add two phone numbers to the data model.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");

        // Update the phone number at index 0 with a new number. Expect the old number to be returned.
        assertEquals("1234567890", dataModel.updatePhoneNumber("5555555555", 0));

        // Try to update a phone number at an index that is out of bounds. Expect to get null.
        assertNull(dataModel.updatePhoneNumber("9999999999", 2));
    }

    @Test
    public void testGetPhoneNumber() throws SMSDataModelFullException {
        // Test getting phone numbers and checking for valid and invalid cases.

        // Add two phone numbers to the data model.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");

        // Get the phone number at index 0. Expect it to be "1234567890".
        assertEquals("1234567890", dataModel.getPhoneNumber(0));

        // Get the phone number at index 1. Expect it to be "9876543210".
        assertEquals("9876543210", dataModel.getPhoneNumber(1));

        // Try to get a phone number at an index that is out of bounds. Expect to get null.
        assertNull(dataModel.getPhoneNumber(2));
    }

    @Test
    public void testDeleteNumber() throws SMSDataModelFullException {
        // Test deleting phone numbers and checking for deleted numbers and invalid cases.

        // Add two phone numbers to the data model.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");

        // Delete the phone number at index 0. Expect the deleted number to be "1234567890".
        assertEquals("1234567890", dataModel.deleteNumber(0));

        // Try to delete a phone number at an index that is out of bounds. Expect to get null.
        assertNull(dataModel.deleteNumber(2));
    }

    @Test
    public void testIsFull() throws SMSDataModelFullException {
        // Test checking if the data model is full.

        // Initially, the data model is not full, so this test should return false.
        assertFalse(dataModel.isFull());

        // Add three phone numbers, filling the data model to its capacity.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");
        dataModel.addPhoneNumber("5555555555");

        // Now the data model is full, so this test should return true.
        assertTrue(dataModel.isFull());
    }
}
