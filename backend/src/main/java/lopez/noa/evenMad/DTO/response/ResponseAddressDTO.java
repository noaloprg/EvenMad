package lopez.noa.evenMad.DTO.response;

public record ResponseAddressDTO(
        String street,
        String city,
        int postalCode
) {
}
