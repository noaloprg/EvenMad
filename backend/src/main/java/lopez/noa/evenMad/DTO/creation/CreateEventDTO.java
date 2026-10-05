package lopez.noa.evenMad.DTO.creation;

import lopez.noa.evenMad.DTO.creation.details.CreateDetailsDTO;

import java.time.LocalDateTime;


public record CreateEventDTO(
        LocalDateTime startTime,
        LocalDateTime endTime,
        String imageUrl,
        String venueID,
        CreateDetailsDTO createDetails
) {
}
