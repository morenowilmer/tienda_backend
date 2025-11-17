package co.com.poli.tienda.domain.exception;

public class NoAutorizadoException extends Exception {

    public NoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}
