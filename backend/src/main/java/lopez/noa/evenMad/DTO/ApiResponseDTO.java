package lopez.noa.evenMad.DTO;

import java.time.LocalDateTime;

public record ApiResponseDTO<T>(
        LocalDateTime timestamp,
        int status,
        boolean success,
        String message,
        T data,
        // For more flexibility
        Object errors
) {

    // Petition ok
    public static <T> ApiResponseDTO<T> ok(String message, T data) {
        return new ApiResponseDTO<>(LocalDateTime.now(), 200, true, message, data, null);
    }

    // DELETE petition Ok
    public static <T> ApiResponseDTO<T> ok(String message) {
        return ApiResponseDTO.ok(message, null);
    }

    // Error with petitions
    public static <T> ApiResponseDTO<T> error(Object errors, int status, String message) {
        return new ApiResponseDTO<>(LocalDateTime.now(), status, false, message, null, errors);
    }
}
