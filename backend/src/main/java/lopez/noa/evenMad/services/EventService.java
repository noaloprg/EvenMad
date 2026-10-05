package lopez.noa.evenMad.services;

import lopez.noa.evenMad.DTO.response.ResponseEventDTO;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.mappers.EventMapper;
import lopez.noa.evenMad.repositories.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    // Injection through constructor
    private final EventRepository repository;

    public EventService(EventRepository repository) {
        this.repository = repository;
    }

    public List<ResponseEventDTO> getAllEvents() {
        List<Event> dbEvents = repository.findAll();
        return dbEvents.stream().map(ev -> EventMapper.toResponseDTO(ev)).toList();
    }
}
