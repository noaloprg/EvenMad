package lopez.noa.evenMad.documents;

import lopez.noa.evenMad.documents.subdocuments.Address;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexType;
import org.springframework.data.mongodb.core.index.GeoSpatialIndexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "venues")
public class Venue {

    @Id
    private String id;

    // To allow text search
    @TextIndexed
    private String name;

    private String slug;

    private int capacity;

    // List of street names
    private List<String> affectedStreets;

    @GeoSpatialIndexed(type = GeoSpatialIndexType.GEO_2DSPHERE)
    private GeoJsonPoint location;

    private Address address;

    public Venue() {
    }

    public Venue(String id, String name, String slug, int capacity, List<String> affectedStreets, GeoJsonPoint location, Address address) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.capacity = capacity;
        this.affectedStreets = affectedStreets;
        this.location = location;
        this.address = address;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<String> getAffectedStreets() {
        return affectedStreets;
    }

    public void setAffectedStreets(List<String> affectedStreets) {
        this.affectedStreets = affectedStreets;
    }

    public GeoJsonPoint getLocation() {
        return location;
    }

    public void setLocation(GeoJsonPoint location) {
        this.location = location;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
