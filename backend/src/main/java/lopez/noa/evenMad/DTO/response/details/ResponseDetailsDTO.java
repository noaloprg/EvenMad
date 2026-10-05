package lopez.noa.evenMad.DTO.response.details;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lopez.noa.evenMad.documents.Category;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = ResponseConcertDetailsDTO.class, name = Category.CONCERT_TYPE),
        @JsonSubTypes.Type(value = ResponseMatchDetailsDTO.class, name = Category.MATCH_TYPE)
})
public abstract class ResponseDetailsDTO {
}
