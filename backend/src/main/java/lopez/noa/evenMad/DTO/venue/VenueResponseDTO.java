package lopez.noa.evenMad.DTO.venue;

import lopez.noa.evenMad.DTO.subdocuments.AddressResponseDTO;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public record VenueResponseDTO(
        String id,
        String name,
        String slug,
        int capacity,
        List<String> affectedStreets,
        GeoJsonPoint location,
        AddressResponseDTO addressDTO
) {
}
