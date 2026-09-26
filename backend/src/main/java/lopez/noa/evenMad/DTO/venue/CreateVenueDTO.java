package lopez.noa.evenMad.DTO.venue;

import lopez.noa.evenMad.documents.subdocuments.Address;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.util.List;

public record CreateVenueDTO(
        String name,
        int capacity,
        List<String> affectedAreas,
        GeoJsonPoint location,
        Address address
) {
}
