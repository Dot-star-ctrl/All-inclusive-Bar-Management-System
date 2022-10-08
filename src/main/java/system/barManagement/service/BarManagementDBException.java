package system.barManagement.service;

public class BarManagementDBException extends Exception{
    public BarManagementDBException(String message) {
        super(message);
    }

    public BarManagementDBException(String message, Throwable cause) {
        super(message, cause);
    }
}
