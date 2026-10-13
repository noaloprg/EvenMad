package lopez.noa.evenMad;

import lopez.noa.evenMad.DTO.creation.CreateEventDTO;
import lopez.noa.evenMad.DTO.creation.CreateVenueDTO;
import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.documents.subdocuments.Address;
import lopez.noa.evenMad.documents.subdocuments.ConcertDetails;
import lopez.noa.evenMad.services.EventService;
import lopez.noa.evenMad.services.VenueService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.time.LocalDateTime;
import java.util.List;

@Configuration
public class DataSeederConfig {

    @Bean
    public CommandLineRunner dataSeeder(EventService eventService, VenueService venueService) {
        return args -> {

            createVenues(venueService);

        };
    }

    private static void createEvents(EventService serivce, List<String> idVenues) {
        // BERNABEU EVENTS

        // concerts
        serivce.createNewEvent(
                new CreateEventDTO(
                        LocalDateTime.of(2024, 05, 29, 20, 10),
                        LocalDateTime.of(2024, 05, 29, 23, 45),
                        "imagenUrl",
                        idVenues.get(0),
                        new ConcertDetails(
                                "Taylor Swift",
                                "The eras tour"
                        )

                )
        );

        serivce.createNewEvent(
                new CreateEventDTO(
                        LocalDateTime.of(2024, 07, 21, 21, 30),
                        LocalDateTime.of(2024, 07, 22, 00, 00),
                        "imagenUrl",
                        idVenues.get(0),
                        new ConcertDetails(
                                "Karol G",
                                "Mañana será bonito tour"
                        )
                )
        );

        // matches
        serivce.createNewEvent(
                new CreateEventDTO(
                        LocalDateTime.of(2024, 07, 21, 21, 30),
                        LocalDateTime.of(2024, 07, 22, 00, 00),
                        "imagenUrl",
                        idVenues.get(0),
                        new ConcertDetails(
                                "Karol G",
                                "Mañana será bonito tour"
                        )
                )
        );

        serivce.createNewEvent(
                new CreateEventDTO(
                        LocalDateTime.of(2024, 07, 21, 21, 30),
                        LocalDateTime.of(2024, 07, 22, 00, 00),
                        "imagenUrl",
                        idVenues.get(0),
                        new ConcertDetails(
                                "Karol G",
                                "Mañana será bonito tour"
                        )
                )
        );
    }


    private static List<String> createVenues(VenueService service) {
        Venue venue1 = service.createNewVenue(
                new CreateVenueDTO(
                        "Estadio Santiago Bernabeú",
                        83186,
                        List.of("Avenida de Concha Espina", "Paseo de la Castellana",
                                "Calle de Rafael Salgado", "Calle del Profesor Waksman", "Calle del Padre Damián"),
                        new GeoJsonPoint(-3.68835, 40.45306),
                        new Address("Paseo de la Castellana", "Madrid", 28046, 138)
                )
        );

        Venue venue2 = service.createNewVenue(
                new CreateVenueDTO(
                        "Riyadh Air Metropolitano",
                        70813,
                        List.of("Avenida de Arcentales", "Avenida de Luis Aragonés",
                                "Avenida de Canillejas a Vicálvaro", "Calle de Estocolmo", "M-40"),
                        new GeoJsonPoint(-3.59944, 40.43611),
                        new Address("Avenida de Arcentales", "Madrid", 28022, 37)
                )
        );

        return List.of(venue1.getId(), venue2.getId());

    }
}
