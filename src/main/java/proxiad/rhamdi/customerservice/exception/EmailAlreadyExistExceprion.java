package proxiad.rhamdi.customerservice.exception;

public class EmailAlreadyExistExceprion extends RuntimeException {
    public EmailAlreadyExistExceprion(String message) {
        super(message);
    }

    public EmailAlreadyExistExceprion(String message, Throwable cause) {
        super(message, cause);
    }
}
