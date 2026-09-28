package lopez.noa.evenMad.documents.subdocuments;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY
)

@JsonSubTypes({
        @JsonSubTypes.Type(value = ConcertDetails.class, name = "CONCERT"),
        @JsonSubTypes.Type(value = MatchDetails.class, name = "MATCH")
})
public abstract class Details {
}

