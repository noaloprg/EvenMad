package lopez.noa.evenMad.services;

import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.repositories.VenueRepository;
import org.springframework.stereotype.Service;

@Service
public class VenueService {
    private final VenueRepository repository;

    //Injection via constructor for more security
    public VenueService(VenueRepository repository) {
        this.repository = repository;
    }

}
