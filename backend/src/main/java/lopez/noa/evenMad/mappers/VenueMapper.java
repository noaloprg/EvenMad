package lopez.noa.evenMad.mappers;

import lopez.noa.evenMad.DTO.subdocuments.ResponseAddressDTO;
import lopez.noa.evenMad.DTO.venue.CreateVenueDTO;
import lopez.noa.evenMad.DTO.venue.ResponseVenueDTO;
import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.documents.subdocuments.Address;
import lopez.noa.evenMad.helpers.SlugHelper;

public class VenueMapper {
    // Tranforms Venue into the Response DTO
    public static ResponseVenueDTO toResponseDTO(Venue venue) {
        return new ResponseVenueDTO(
                venue.getId(), venue.getName(), venue.getSlug(), venue.getCapacity(),
                venue.getAffectedStreets(), venue.getLocation(), getAddressResponseDTO(venue.getAddress()));

    }

    // Creates Venue from DTO
    public static Venue fromCreateDtoToVenue(CreateVenueDTO dto) {
        Venue venue = new Venue();

        venue.setName(dto.name());
        venue.setSlug(SlugHelper.toSlug(dto.name()));
        venue.setCapacity(dto.capacity());
        venue.setLocation(dto.location());
        venue.setAddress(dto.address());
        venue.setAffectedStreets(dto.affectedAreas());

        return venue;
    }

    // Transforms an Address into its response DTO
    private static ResponseAddressDTO getAddressResponseDTO(Address address) {
        return new ResponseAddressDTO(address.getStreet(), address.getCity(), address.getPostalCode());
    }

}
