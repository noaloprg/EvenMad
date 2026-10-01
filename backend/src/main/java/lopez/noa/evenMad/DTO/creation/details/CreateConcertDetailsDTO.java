package lopez.noa.evenMad.DTO.creation.details;


public class CreateConcertDetailsDTO extends CreateDetailsDTO {
    private String artist;
    private String tour;

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
