package lopez.noa.evenMad.DTO.creation.details;


public class CreateConcertDetailsDTO extends CreateDetailsDTO {
    private String artist;
    private String tour;

    public CreateConcertDetailsDTO(String artist, String tour) {
        this.artist = artist;
        this.tour = tour;
    }

    public CreateConcertDetailsDTO() {
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
