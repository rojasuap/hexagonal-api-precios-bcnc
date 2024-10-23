package es.bcnc.hexagonal.servicios.application.exceptions;

@SuppressWarnings("ALL")
public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException() {
        super("Recurso no encontrado en el servidor !!!");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
