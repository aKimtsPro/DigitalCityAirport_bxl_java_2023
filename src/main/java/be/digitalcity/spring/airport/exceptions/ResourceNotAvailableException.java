package be.digitalcity.spring.airport.exceptions;

import lombok.Getter;

@Getter
public class ResourceNotAvailableException extends RuntimeException {

    private final Class<?> resourceClass;
    private final Object id;
    private final String reason;

    public ResourceNotAvailableException(Class<?> resourceClass, Object id, String reason) {
        super(
                "Resource %s with id {%s} is not available because: %s".formatted(
                        resourceClass.getSimpleName(),
                        id.toString(),
                        reason
                )
        );
        this.resourceClass = resourceClass;
        this.id = id;
        this.reason = reason;
    }

}
