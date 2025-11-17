package co.com.poli.tienda.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensajesEnum {
    ERROR_GENERAL("Error realizando la operación, contacte al administrador del sistema"),
    CERRAR_SESION_EXITO("La sesión fue cerrada con exito"),
    SIN_DATOS("No se encontraron datos para la busqueda."),
    USUARIO_NO_ENCONTRADO("Usuario no encontrado."),
    CONTRASENA_ERRONEA("Contraseña incorrecta"),
    PERSONA_NO_ENCONTRADA("Persona no encontrada"),
    ERROR_GUARDANDO_ACTUALIZANDO("Error guardando o actualizando el registro"),
    PERSONA_REGISTRADA_ERROR("La persona ya se encuentra registrada"),
    USUARIO_REGISTRADO("El usuario ya se encuentra registrado.")
    ;

    private String valor;
}
