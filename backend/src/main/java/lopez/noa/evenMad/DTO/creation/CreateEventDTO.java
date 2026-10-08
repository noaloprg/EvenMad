package lopez.noa.evenMad.DTO.creation;

import lopez.noa.evenMad.documents.subdocuments.Details;

import java.time.LocalDateTime;


public record CreateEventDTO(
        LocalDateTime startTime,
        LocalDateTime endTime,
        String imageUrl,
        String venueID,
        Details details
) {
}
