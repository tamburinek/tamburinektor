package fel.cvut.cz.tamburinektor.exception;

public class TamburinektorException extends RuntimeException {

    public TamburinektorException() {
    }

    public TamburinektorException(String message) {
        super(message);
    }

    public TamburinektorException(String message, Throwable cause) {
        super(message, cause);
    }

    public TamburinektorException(Throwable cause) {
        super(cause);
    }
}

