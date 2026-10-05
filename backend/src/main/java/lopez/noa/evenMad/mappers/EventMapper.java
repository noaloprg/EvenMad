package lopez.noa.evenMad.mappers;

import lopez.noa.evenMad.DTO.creation.CreateEventDTO;
import lopez.noa.evenMad.DTO.response.ResponseEventDTO;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.helpers.SlugHelper;


public class EventMapper {


    // Creates an object Event from the basic CreateDTO
    public static Event fromCreateDtoToEvent(CreateEventDTO dto) {
        Event event = new Event();

        event.setDetails(DetailsMapper.fromCreateDTOToDetails(dto.createDetails()));
        event.setEndTime(dto.endTime());
        event.setStartTime(dto.startTime());
        event.setImageUrl(dto.imageUrl());

        // Creates title from the details of the event
        event.setTitle(event.getDetails().getTitle());
        // Creates URL slug from the event title using the SlugHelper
        event.setSlug(SlugHelper.toSlug(event.getTitle()));

        return event;
    }

    // Returns a ResponseDTO from an object event
    public static ResponseEventDTO toResponseDTO(Event event) {
        return new ResponseEventDTO(
                event.getId(),
                event.getTitle(),
                event.getSlug(),
                event.getStartTime(),
                event.getEndTime(),
                event.getImageUrl(),
                event.getVenue().getId(),
                DetailsMapper.toResponseDTO(event.getDetails())
        );
    }
}
