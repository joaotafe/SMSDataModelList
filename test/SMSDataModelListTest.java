import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class SMSDataModelListTest {

    private SMSDataModelList dataModel;

    @Before
    public void setUp() {
        dataModel = new SMSDataModelList();
    }

    @Test
    public void testAddPhoneNumber() throws SMSDataModelFullException {
        // Goal: Verify that a phone number can be successfully added to the model.
        assertEquals(0, dataModel.getNumPhoneNumbers());
        dataModel.addPhoneNumber("1234567890");
        assertEquals(1, dataModel.getNumPhoneNumbers());
        assertEquals("1234567890", dataModel.getPhoneNumber(0));
    }

    @Test
    public void testAddDuplicatePhoneNumber() {
        // Goal: Ensure that adding a duplicate phone number throws the expected exception.
        try {
            dataModel.addPhoneNumber("1234567890");
            dataModel.addPhoneNumber("1234567890"); // Attempt to add a duplicate
            fail("Expected SMSDataModelFullException for duplicate number");
        } catch (SMSDataModelFullException e) {
            assertEquals("Duplicate phone number: 1234567890", e.getMessage());
        }
    }

    @Test
    public void testUpdatePhoneNumber() throws SMSDataModelFullException {
        // Goal: Confirm that a phone number can be updated at a specified index.
        dataModel.addPhoneNumber("1234567890");
        String oldNumber = dataModel.updatePhoneNumber("9876543210", 0);
        assertEquals("1234567890", oldNumber);
        assertEquals("9876543210", dataModel.getPhoneNumber(0));
    }

    @Test
    public void testUpdatePhoneNumberWithDuplicate() throws SMSDataModelFullException {
        // Goal: Verify that updating a phone number with a duplicate throws an exception.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");
        try {
            dataModel.updatePhoneNumber("9876543210", 0); // Attempt to update with a duplicate
            fail("Expected SMSDataModelFullException for duplicate number");
        } catch (SMSDataModelFullException e) {
            assertEquals("Duplicate phone number: 9876543210", e.getMessage());
        }
    }

    @Test
    public void testFindPhoneNumberIndex() throws SMSDataModelFullException {
        // Goal: Ensure that the correct index of a phone number can be found in the model.
        dataModel.addPhoneNumber("1234567890");
        dataModel.addPhoneNumber("9876543210");
        int index = dataModel.findPhoneNumberIndex("9876543210");
        assertEquals(1, index);
    }

    @Test
    public void testFindPhoneNumberIndexNotFound() {
        // Goal: Confirm that searching for a number not in the model returns -1.
        int index = dataModel.findPhoneNumberIndex("5555555555"); // Number not in the model
        assertEquals(-1, index);
    }

    @Test
    public void testDeleteNumber() throws SMSDataModelFullException {
        // Goal: Verify that a phone number can be deleted from the model.
        dataModel.addPhoneNumber("1234567890");
        String deletedNumber = dataModel.deleteNumber(0);
        assertEquals("1234567890", deletedNumber);
        assertEquals(0, dataModel.getNumPhoneNumbers());
    }

    @Test
    public void testGetMaxNumPhoneNumbers() {
        // Goal: Ensure that the maximum number of phone numbers can be retrieved.
        assertEquals(SMSDataModelArray.DEFAULT_MAX_NUM_PHONE_NUMBERS, dataModel.getMaxNumPhoneNumbers());
    }

    @Test
    public void testIsFull() throws SMSDataModelFullException {
        // Goal: Verify that isFull() correctly detects when the model is full.
        assertFalse(dataModel.isFull());
        dataModel.addPhoneNumber("1234567890");
        assertFalse(dataModel.isFull());
    }

    @Test
    public void testSortNumbers() throws SMSDataModelFullException {
        // Goal: Confirm that the phone numbers in the model can be sorted in natural order.
        dataModel.addPhoneNumber("9876543210");
        dataModel.addPhoneNumber("1234567890");
        dataModel.sortNumbers();
        String[] sortedNumbers = dataModel.getPhoneNumbers();
        assertArrayEquals(new String[]{"1234567890", "9876543210"}, sortedNumbers);
    }
}
