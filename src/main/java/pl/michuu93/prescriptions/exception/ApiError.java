package pl.michuu93.prescriptions.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ApiError {
    @Builder.Default
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss.SSS")
    private LocalDateTime timestamp = LocalDateTime.now();
    @JsonIgnore
    @Builder.Default
    private HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
    @Getter(AccessLevel.NONE)
    private String status;
    @Builder.Default
    private ApiErrorCode errorCode = ApiErrorCode.UNKNOWN_ERROR;

    public String getStatus() {
        return String.valueOf(httpStatus.value()) + ' ' + httpStatus.getReasonPhrase();
    }
}