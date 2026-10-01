package lopez.noa.evenMad.DTO.creation.details;

public class CreateMatchDetailsDTO  extends  CreateDetailsDTO{
    private String competition;
    private int matchDay;
    private String homeTeam;
    private String awayTeam;

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
