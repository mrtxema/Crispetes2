package cat.mrtxema.crispetes.service;

public class BaseServiceException extends Exception {

    public BaseServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
