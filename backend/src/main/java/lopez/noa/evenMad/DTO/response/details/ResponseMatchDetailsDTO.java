package lopez.noa.evenMad.DTO.response.details;

public class ResponseMatchDetailsDTO extends ResponseDetailsDTO {
    private String competition;
    private int matchDay;
    private String homeTeam;
    private String awayTeam;

    public ResponseMatchDetailsDTO(String competition, int matchDay, String homeTeam, String awayTeam) {
        this.competition = competition;
        this.matchDay = matchDay;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
    }

    public ResponseMatchDetailsDTO() {
    }

    public String getCompetition() {
        return competition;
    }

    public void setCompetition(String competition) {
        this.competition = competition;
    }

    public int getMatchDay() {
        return matchDay;
    }

    public void setMatchDay(int matchDay) {
        this.matchDay = matchDay;
    }

    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }
}
