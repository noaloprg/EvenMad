package lopez.noa.evenMad.documents;

import com.fasterxml.jackson.annotation.JsonFormat;
import lopez.noa.evenMad.constants.AppConstants;
import lopez.noa.evenMad.documents.subdocuments.Details;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.IndexDirection;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.time.LocalDateTime;

@Document(collection = "events")
// Index for each category and times
// Descending to have first the events happening nearer the actual day
@CompoundIndex(def = "{´category´: 1, 'startTime': -1}", name = "category_starttime_idx")
public class Event {

    @Id
    private String id;

    // For possible text search
    @TextIndexed
    private String title;

    @Indexed(direction = IndexDirection.DESCENDING)
    @JsonFormat(pattern = AppConstants.DATE_TIME_JSON_PATTERN)
    private LocalDateTime startTime;

    private LocalDateTime endTime;

    private String imageUrl;

    @DocumentReference
    @Indexed
    private Venue venue;

    /**
     * Any instance inherited from abstract class {@link Details}
     */
    private Details details;

    private Category category;

    public Event() {
    }

    public Event(String id, String title, LocalDateTime startTime, LocalDateTime endTime, String imageUrl, Venue venue, Details details, Category category) {
        this.id = id;
        this.title = title;
        this.startTime = startTime;
        this.endTime = endTime;
        this.imageUrl = imageUrl;
        this.venue = venue;
        this.details = details;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Venue getVenue() {
        return venue;
    }

    public void setVenue(Venue venue) {
        this.venue = venue;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
