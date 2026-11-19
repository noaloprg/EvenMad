package lopez.noa.evenMad.mappers;

import lopez.noa.evenMad.DTO.creation.CreateEventDTO;
import lopez.noa.evenMad.DTO.response.ResponseEventDTO;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.utils.SlugHelper;


public class EventMapper {


    /**
     * Creates an object Event from the basic CreateDTO
     * <p> <br>
     * {@code event.setTitle(event.getDetails().getTitle())}; -> Creates title from the details of the event
     * <br> {@code .getTitle()}  is an abstract method from {@link lopez.noa.evenMad.documents.subdocuments.Details} that returns
     * formated text from detailed atributes
     * </p>
     */

    // Creates an object Event from the basic CreateDTO
    public static Event fromCreateDtoToEvent(CreateEventDTO dto) {
        Event event = new Event();

        event.setDetails(dto.details());
        event.setEndTime(dto.endTime());
        event.setStartTime(dto.startTime());
        event.setImageUrl(dto.imageUrl());
        event.setVenue(dto.venueID());

        // look JavaDoc of general method
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
                event.getVenueID(),
                event.getDetails()
        );
    }
}
