package cat.mrtxema.crispetes.service;

public class VideoServiceException extends BaseServiceException {

    public VideoServiceException(String msg) {
        super(msg);
    }

    public VideoServiceException(String msg, Throwable parent) {
        super(msg, parent);
    }
}
