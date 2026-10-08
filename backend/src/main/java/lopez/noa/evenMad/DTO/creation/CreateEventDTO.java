package lopez.noa.evenMad.DTO.creation;

import java.time.LocalDateTime;


public record CreateEventDTO(
        LocalDateTime startTime,
        LocalDateTime endTime,
        String imageUrl,
        String venueID
) {
}
