package lopez.noa.evenMad.repositories;

import lopez.noa.evenMad.documents.Event;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventRepository extends MongoRepository<Event, String> {
}
