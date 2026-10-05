package lopez.noa.evenMad.DTO.response.details;

public class ResponseConcertDetailsDTO extends ResponseDetailsDTO {
    private String artist;
    private String tour;

    public ResponseConcertDetailsDTO(String artist, String tour) {
        this.artist = artist;
        this.tour = tour;
    }

    public ResponseConcertDetailsDTO() {
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
};
