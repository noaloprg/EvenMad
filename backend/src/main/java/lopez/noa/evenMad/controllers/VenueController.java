package lopez.noa.evenMad.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lopez.noa.evenMad.DTO.response.ApiResponseDTO;
import lopez.noa.evenMad.DTO.response.ResponseVenueDTO;
import lopez.noa.evenMad.helpers.MessagesHelper;
import lopez.noa.evenMad.services.VenueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Venues", description = "Endpoints to access and operate to the DB Venues")
@RestController
@RequestMapping("/venues")
public class VenueController {

    private final VenueService venueService;

    public VenueController(VenueService venueService) {
        this.venueService = venueService;
    }


    @Operation(summary = "Gets al venues from DB")
    @GetMapping("/getAll")
    public ResponseEntity<ApiResponseDTO<List<ResponseVenueDTO>>> getAll() {
        return ResponseEntity.ok(new ApiResponseDTO<>(
                200,
                MessagesHelper.getOkMessage("List of venues", "obtained"),
                this.venueService.getAllVenues()));
    }
}
