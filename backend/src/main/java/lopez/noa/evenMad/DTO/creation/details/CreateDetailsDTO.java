package lopez.noa.evenMad.DTO.creation.details;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lopez.noa.evenMad.documents.Category;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include =  JsonTypeInfo.As.PROPERTY
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = CreateConcertDetailsDTO.class, name = Category.CONCERT_TYPE),
        @JsonSubTypes.Type(value = CreateMatchDetailsDTO.class, name = Category.MATCH_TYPE)
})
public class CreateDetailsDTO {
}
