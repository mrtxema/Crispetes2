package cat.mrtxema.crispetes.api;

public class ExternalServiceException extends Exception {

    public ExternalServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
