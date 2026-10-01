package lopez.noa.evenMad.DTO.response;

import lopez.noa.evenMad.DTO.response.details.ResponseDetailsDTO;

import java.time.LocalDateTime;

public record  ResponseEventDTO (
        String id,
        String title,
        String slug,
        LocalDateTime startTime,
        LocalDateTime endTime,
        String imageUrl,
        String venueId,
        ResponseDetailsDTO responseDetails
){
}
