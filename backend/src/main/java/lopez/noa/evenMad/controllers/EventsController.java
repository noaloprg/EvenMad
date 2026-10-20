package lopez.noa.evenMad.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lopez.noa.evenMad.DTO.response.ApiResponseDTO;
import lopez.noa.evenMad.DTO.response.ResponseEventDTO;
import lopez.noa.evenMad.helpers.MessagesHelper;
import lopez.noa.evenMad.services.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Events", description = "Endpoints to access and operate to the DB Events")
@RestController
@RequestMapping("/events")
public class EventsController {

    private final EventService service;

    public EventsController(EventService service) {
        this.service = service;
    }


    @Operation(summary = "Gets all events from DB", description = "All events with their respective venue_id")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseDTO<List<ResponseEventDTO>>> getAll() {
        return ResponseEntity.ok(new ApiResponseDTO<>(200,
                MessagesHelper.getOkMessage("List of events", "obtained"),
                service.getAllEvents()));
    }
}
