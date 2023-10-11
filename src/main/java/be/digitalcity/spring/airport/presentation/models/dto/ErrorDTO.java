package be.digitalcity.spring.airport.presentation.models.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class ErrorDTO {

    private final String uri;
    private final LocalDateTime receivedAt;
    private final int status;
    private final Map<String, Object> errors;

    public ErrorDTO(String uri, LocalDateTime receivedAt, int status, Map<String, Object> errors) {
        this.uri = uri;
        this.receivedAt = receivedAt;
        this.status = status;
        this.errors = errors;
    }

    public static Builder builder(String uri, LocalDateTime receivedAt, int status){
        return new Builder(uri, receivedAt, status);
    }

    public static class Builder {
        private final String uri;
        private final LocalDateTime receivedAt;
        private final int status;
        private final Map<String, Object> errors = new HashMap<>();

        public Builder(String uri, LocalDateTime receivedAt, int status) {
            this.uri = uri;
            this.receivedAt = receivedAt;
            this.status = status;
        }

        public Builder putError(String key, Object data){
            errors.put(key, data);
            return this;
        }

        public ErrorDTO build(){
            return new ErrorDTO(this.uri, this.receivedAt, this.status, this.errors);
        }
    }

}
