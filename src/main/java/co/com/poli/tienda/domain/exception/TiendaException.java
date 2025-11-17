package co.com.poli.tienda.domain.exception;

public class TiendaException extends Exception {

    public TiendaException(String message) {
        super(message);
    }

    public TiendaException(String message, Throwable cause) {
        super(message, cause);
    }
}
