package be.digitalcity.spring.airport.bl.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends RuntimeException {

    private final Class<?> resourceClass;
    private final Object id;

    public ResourceNotFoundException(Class<?> resourceClass, Object id) {
        super("%s could not be found with ID {%s}".formatted(resourceClass.getSimpleName(), id.toString()));
        this.resourceClass = resourceClass;
        this.id = id;
    }


}
