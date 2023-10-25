import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Implementation of SMSDataModelInterface that uses a List to store phone numbers.
 * It allows adding, updating, and managing phone numbers while handling exceptions for full and duplicate numbers.
 */
public class SMSDataModelList implements SMSDataModelInterface {
    private List<String> phoneNumbers; // A list to store phone numbers
    private int maxPhoneNumbers;       // The maximum number of phone numbers allowed

    /**
     * Default constructor that uses the default maximum capacity.
     */
    public SMSDataModelList() {
        this(SMSDataModelArray.DEFAULT_MAX_NUM_PHONE_NUMBERS);
    }

    /**
     * Constructor that allows specifying the maximum number of phone numbers.
     *
     * @param maxPhoneNumbers The maximum number of phone numbers this model can hold.
     */
    public SMSDataModelList(int maxPhoneNumbers) {
        this.maxPhoneNumbers = maxPhoneNumbers;
        this.phoneNumbers = new ArrayList<>();
    }

    /**
     * Adds a new phone number to the model.
     *
     * @param newPhoneNumber The phone number to be added.
     * @return The added phone number if successful.
     * @throws SMSDataModelFullException If the model is full or the number is a duplicate.
     */
    @Override
    public String addPhoneNumber(String newPhoneNumber) throws SMSDataModelFullException {
        if (maxPhoneNumbers != -1 && phoneNumbers.size() >= maxPhoneNumbers) {
            throw new SMSDataModelFullException("The model is full. Cannot add more phone numbers.", newPhoneNumber);
        }

        if (phoneNumbers.contains(newPhoneNumber)) {
            throw new SMSDataModelFullException("Duplicate phone number: " + newPhoneNumber, newPhoneNumber);
        }

        phoneNumbers.add(newPhoneNumber);
        return newPhoneNumber;
    }

    /**
     * Finds the index of the target phone number in the model.
     *
     * @param targetNumber The target phone number to search for.
     * @return The index of the number in the list if found, or -1 if not found.
     */
    @Override
    public int findPhoneNumberIndex(String targetNumber) {
        for (int i = 0; i < phoneNumbers.size(); i++) {
            if (phoneNumbers.get(i).equals(targetNumber)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Updates a phone number at a specified position in the model.
     *
     * @param newPhoneNumber The new phone number to use.
     * @param i The position to update.
     * @return The replaced phone number if successful, or null if the index is out of range.
     * @throws SMSDataModelFullException If the new number is a duplicate.
     */
    @Override
    public String updatePhoneNumber(String newPhoneNumber, int i) throws SMSDataModelFullException {
        if (i < 0 || i >= phoneNumbers.size()) {
            return null;
        }

        String oldPhoneNumber = phoneNumbers.get(i);

        if (!oldPhoneNumber.equals(newPhoneNumber) && phoneNumbers.contains(newPhoneNumber)) {
            throw new SMSDataModelFullException("Duplicate phone number: " + newPhoneNumber, newPhoneNumber);
        }

        phoneNumbers.set(i, newPhoneNumber);
        return oldPhoneNumber;
    }

    /**
     * Get a phone number at a specified position in the model.
     *
     * @param i The position to retrieve.
     * @return The phone number at the specified position or null if the index is out of range.
     */
    @Override
    public String getPhoneNumber(int i) {
        if (i >= 0 && i < phoneNumbers.size()) {
            return phoneNumbers.get(i);
        }
        return null;
    }

    /**
     * Deletes a phone number at a specified position in the model.
     *
     * @param i The position to delete.
     * @return The deleted phone number if successful, or null if the index is out of range.
     */
    @Override
    public String deleteNumber(int i) {
        if (i < 0 || i >= phoneNumbers.size()) {
            return null;
        }

        return phoneNumbers.remove(i);
    }

    /**
     * Returns the maximum number of phone numbers this model can hold.
     *
     * @return The maximum number of phone numbers, or -1 if there is no maximum.
     */
    @Override
    public int getMaxNumPhoneNumbers() {
        return maxPhoneNumbers;
    }

    /**
     * Checks if the model has reached the maximum number of phone numbers it can hold.
     *
     * @return True if the model is full, false otherwise.
     */
    @Override
    public boolean isFull() {
        return maxPhoneNumbers != -1 && phoneNumbers.size() >= maxPhoneNumbers;
    }

    /**
     * Returns an array of the phone numbers in the model.
     *
     * @return An array of phone numbers.
     */
    @Override
    public String[] getPhoneNumbers() {
        return phoneNumbers.toArray(new String[0]);
    }

    /**
     * Returns the number of phone numbers in the model.
     *
     * @return The number of phone numbers in the model.
     */
    @Override
    public int getNumPhoneNumbers() {
        return phoneNumbers.size();
    }

    /**
     * @return the message in the model
     */
    @Override
    public String getMessage() {
        return null;
    }

    /**
     * Sets the message in the model to the one provided, mMessage
     *
     * @param mMessage The new text message for this model
     */
    @Override
    public void setMessage(String mMessage) {

    }

    /**
     * Sorts the phone numbers in the model in natural order.
     */
    @Override
    public void sortNumbers() {
        Collections.sort(phoneNumbers);
    }
}
