package co.com.poli.tienda.adapter.port;


import co.com.poli.tienda.domain.exception.NoAutorizadoException;
import co.com.poli.tienda.domain.requester.LoginRequester;
import co.com.poli.tienda.domain.response.LoginResponse;

public interface LoginPort {

    LoginResponse login(LoginRequester loginRequester) throws NoAutorizadoException;
    Boolean cerrarSesion(String token);
}
