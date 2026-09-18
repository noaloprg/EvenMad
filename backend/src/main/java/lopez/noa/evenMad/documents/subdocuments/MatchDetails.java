package lopez.noa.evenMad.documents.subdocuments;

public class MatchDetails extends Details {

    private String competition;
    private int matchDay;
    private String homeTeam;
    private String awayTeam;


    public MatchDetails() {
    }

    public MatchDetails(String competition, int matchDay, String homeTeam, String awayTeam) {
        this.competition = competition;
        this.matchDay = matchDay;
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
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
