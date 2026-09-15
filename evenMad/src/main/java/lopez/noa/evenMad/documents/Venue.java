package lopez.noa.evenMad.documents;

import lopez.noa.evenMad.documents.subdocuments.Adress;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "venues")
public class Venue {

    @Id
    private String id;

    private String name;

    private String slug;

    private int capacity;

    // List of street names
    private List<String> affectedStreets;

    private GeoJsonPoint location;

    private Adress adress;

    public Venue() {
    }

    public Venue(String id, String name, String slug, int capacity, List<String> affectedStreets, GeoJsonPoint location, Adress adress) {
        this.id = id;
        this.name = name;
        this.slug = slug;
        this.capacity = capacity;
        this.affectedStreets = affectedStreets;
        this.location = location;
        this.adress = adress;
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

    public Adress getAdress() {
        return adress;
    }

    public void setAdress(Adress adress) {
        this.adress = adress;
    }
}
