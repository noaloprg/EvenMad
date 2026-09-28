package lopez.noa.evenMad.services;

import lopez.noa.evenMad.DTO.venue.ResponseVenueDTO;
import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.mappers.VenueMapper;
import lopez.noa.evenMad.repositories.VenueRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VenueService {

    private final VenueRepository repository;

    //Injection via constructor for more security
    public VenueService(VenueRepository repository) {
        this.repository = repository;
    }

    // Gets all venues from DB
    private List<ResponseVenueDTO> getAllVenues() {
        List<Venue> listVenues = repository.findAll();
        return listVenues.stream().map(v -> VenueMapper.toResponseDTO(v)).toList();
    }
}
