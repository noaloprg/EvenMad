package lopez.noa.evenMad.services;

import lopez.noa.evenMad.dto.creation.CreateVenueDTO;
import lopez.noa.evenMad.dto.response.ResponseVenueDTO;
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
    public List<ResponseVenueDTO> getAllVenues() {
        List<Venue> listVenues = repository.findAll();
        return listVenues.stream().map(v -> VenueMapper.toResponseDTO(v)).toList();
    }

    /**
     *
     * @param dto of creation for venue
     * @return entity because creations are not shown
     */
    public Venue createNewVenue(CreateVenueDTO dto) throws Exception {
        boolean exists = this.repository.existsByName(dto.name());

        // REMEMBER CAMBIAR
        if (exists) throw new Exception();
        return this.repository.insert(VenueMapper.fromCreateDtoToVenue(dto));
    }

    // looks for a venue through his name
    public Venue getByName(String name) {
        // it doesn't manage exceptions because we want to reuse in the future for bar searchers, etc...
        // its managed when it very important (Scrappers)
        return repository.findByNameIgnoreCase(name.trim());
    }
}
