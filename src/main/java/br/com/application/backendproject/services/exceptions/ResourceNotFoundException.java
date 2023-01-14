package br.com.application.backendproject.services.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public ResourceNotFoundException(String classname, Object id){
        super(classname+"-"+id);
    }
}
