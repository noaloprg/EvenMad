package lopez.noa.evenMad.documents.subdocuments;

public class ConcertDetails extends Details {
    private String artist;
    private String tour;

    public ConcertDetails() {
    }

    public ConcertDetails(String artist, String tour) {
        this.artist = artist;
        this.tour = tour;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getTour() {
        return tour;
    }

    public void setTour(String tour) {
        this.tour = tour;
    }
}
