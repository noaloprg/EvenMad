package lopez.noa.evenMad.DTO.venue;

import lopez.noa.evenMad.DTO.subdocuments.ResponseAddressDTO;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public record ResponseVenueDTO(
        String id,
        String name,
        String slug,
        int capacity,
        List<String> affectedStreets,
        GeoJsonPoint location,
        ResponseAddressDTO addressDTO
) {
}
