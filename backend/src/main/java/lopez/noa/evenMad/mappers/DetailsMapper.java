package lopez.noa.evenMad.mappers;

import lopez.noa.evenMad.DTO.creation.details.CreateConcertDetailsDTO;
import lopez.noa.evenMad.DTO.creation.details.CreateDetailsDTO;
import lopez.noa.evenMad.DTO.creation.details.CreateMatchDetailsDTO;
import lopez.noa.evenMad.DTO.response.details.ResponseConcertDetailsDTO;
import lopez.noa.evenMad.DTO.response.details.ResponseDetailsDTO;
import lopez.noa.evenMad.DTO.response.details.ResponseMatchDetailsDTO;
import lopez.noa.evenMad.documents.subdocuments.ConcertDetails;
import lopez.noa.evenMad.documents.subdocuments.Details;
import lopez.noa.evenMad.documents.subdocuments.MatchDetails;

public class DetailsMapper {

    /**
     * tranforms the DTO of creation into the entity
     *
     * @param dto from abstract class to allow converting it into the inherited specific classes
     * @return instance of {@link Details} but with the specific type
     */
    public static Details fromCreateDTOToDetails(CreateDetailsDTO dto) {
        if (dto instanceof CreateConcertDetailsDTO c) {
            return toConcertDetails(c);
        } else if (dto instanceof CreateMatchDetailsDTO m) {
            return toMatchDetails(m);
        } else {
            throw new IllegalArgumentException();
        }
    }

    /**
     *
     * Tranforms the entity detail into the ResponseDTO depending on the specific class
     *
     * @param details entity with complete data
     * @return ResponseDTO depending on class type
     */
    public static ResponseDetailsDTO toResponseDTO(Details details) {
        if (details instanceof ConcertDetails c) {
            return toResponseConcertDetails(c);
        } else if (details instanceof MatchDetails m)
            return toResponseMathDetails(m);
        else throw new IllegalArgumentException();
    }

    // Creates responseDTO of concertDetails
    private static ResponseConcertDetailsDTO toResponseConcertDetails(ConcertDetails details) {
        return new ResponseConcertDetailsDTO(
                details.getArtist(),
                details.getTour()
        );
    }

    // Creates responseDTO of matchDetails
    private static ResponseMatchDetailsDTO toResponseMathDetails(MatchDetails details) {
        return new ResponseMatchDetailsDTO(
                details.getCompetition(),
                details.getMatchDay(),
                details.getHomeTeam(),
                details.getAwayTeam()
        );
    }

    // Creates match details from their corresponding DTO
    private static MatchDetails toMatchDetails(CreateMatchDetailsDTO dto) {
        return new MatchDetails(
                dto.getCompetition(),
                dto.getMatchDay(),
                dto.getHomeTeam(),
                dto.getAwayTeam()
        );
    }

    // Creates events details from ther corresponding DTO
    private static ConcertDetails toConcertDetails(CreateConcertDetailsDTO dto) {
        return new ConcertDetails(
                dto.getArtist(),
                dto.getTour()
        );
    }
}
