package lopez.noa.evenMad.documents.subdocuments;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lopez.noa.evenMad.documents.Category;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcertDetails.class, name = Category.CONCERT_TYPE),
        @JsonSubTypes.Type(value = MatchDetails.class, name = Category.MATCH_TYPE)
})
public abstract class Details {
}

