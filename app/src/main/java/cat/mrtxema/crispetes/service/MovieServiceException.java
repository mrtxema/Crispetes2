package cat.mrtxema.crispetes.service;

public class MovieServiceException extends BaseServiceException {

    public MovieServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
