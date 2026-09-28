package lopez.noa.evenMad.repositories;

import lopez.noa.evenMad.documents.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VenueRepository extends MongoRepository<Venue, String> {
}
