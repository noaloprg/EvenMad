package lopez.noa.evenMad.scrappers;

import lopez.noa.evenMad.dto.creation.CreateEventDTO;
import lopez.noa.evenMad.constants.AppConstants;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.documents.subdocuments.MatchDetails;
import lopez.noa.evenMad.services.EventService;
import lopez.noa.evenMad.services.VenueService;
import lopez.noa.evenMad.utils.DataHelper;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;

/**
 * Label names and anidation of content explained on file:
 * ./docs/scrapping-sources.md
 */
public class MetropolitanoScrapper extends BaseScrapper implements EventScrapper {

    @Override
    public String getScrapperSourceId() {
        return "metropolitano";
    }

    @Override
    public List<Event> scraperEvents() {
        return List.of();
    }

    private final String BASIC_URL = "https://www.atleticodemadrid.com";

    private final String FEM_TEAM = BASIC_URL + "/calendario-primer-equipo-femenino";

    private final String MASC_TEAM = BASIC_URL + "/calendario-completo-primer-equipo";

    private int currentYear = LocalDateTime.now().getYear();

    private final VenueService venueService;
    private final EventService eventService;

    public MetropolitanoScrapper(VenueService venueService, EventService eventService) {
        this.venueService = venueService;
        this.eventService = eventService;
    }

    // Manages matches from the venue
    public void manageMatchDocuments() {
        // looks in the class calendar-v2 only for the objects from class .calendar-item (for security)
        String mainSearch = ".calendar-v2 .calendar-item";

        // REMEMBER gestionar la excepcion, mirar si se personaliza
        try {
            // documents for matches
            Document femaleMathcesDoc = ScraperClient.basicConnection(FEM_TEAM);
            Document maleMatchesDoc = ScraperClient.basicConnection(MASC_TEAM);

            // label of each element in the list of matches
            Elements femaleMatches = femaleMathcesDoc.select(mainSearch);
            Elements maleMatches = maleMatchesDoc.select(mainSearch);

            // saves the matches
            saveExtractedMatchEvents(femaleMatches);
            saveExtractedMatchEvents(maleMatches);

        } catch (IOException e) {
            System.err.println("Error obtaining the documents from scrapper");
        }
    }

    // Normalizes data from extraction of matches
    private void saveExtractedMatchEvents(Elements matches) {

        // each element is the object inside -> <div class="calendar-item toggler-todos active">
        for (Element el : matches) {
            // 'span' elements inside a class called 'info-calendario'
            Elements calendarInfo = el.select(".info-calendario span");

            // info of image, that contians info about the competition
            Element competitionInfo = el.selectFirst(".competition img");

            // dateTime info
            String dateScr = DataHelper.stringCleaner(calendarInfo.get(1).text());
            String timeScr = DataHelper.stringCleaner(calendarInfo.get(2).text());

            // venue info
            String venueScr = DataHelper.stringCleaner(calendarInfo.get(3).text());

            // players info
            // gets the text from the label <dt> inside the class container local and visitor
            String homeTeamScr = DataHelper.stringCleaner(el.select(".local dt").text());
            String awayTeamScr = DataHelper.stringCleaner(el.select(".visitante dt").text());

            // checks if the info is not empty and if the venue is the one that has to be
            if (competitionInfo != null && isThisVenue(venueScr, AppConstants.ESTADIO_METROPOLITANO)) {

                String competitionScr = DataHelper.stringCleaner(competitionInfo.attr("title"));
                LocalDateTime timeMatch = getDateTimesFromMatches(dateScr, timeScr);

                try {
                    String idVenue = getVenueId(venueScr);

                    CreateEventDTO dto = new CreateEventDTO(
                            timeMatch,
                            timeMatch.plusMinutes(120),
                            //REMEMBER mirar tema imagenes de donde sacarlas y donde alamcenarlas
                            "",
                            idVenue,
                            new MatchDetails(competitionScr, homeTeamScr, awayTeamScr)
                    );
                    // creates and adds event to local list
                    eventService.createNewEvent(dto);
                }
                // REMEMBER CAMBIAR
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
    }

    /**
     * returns de date and time in the correct data format from matcher
     */
    private LocalDateTime getDateTimesFromMatches(String date, String hour) {
        // date example -> 14 de Marzo
        // hour example -> 11:00
        String webPattern = "d 'de' MMMM 'de' yyyy HH:mm";

        // completes the scraping date with current year
        String fullScrDate = String.format("%s de %d %s", date, currentYear, hour);

        return LocalDateTime.parse(fullScrDate,
                // indicates language for month that comes in spanish
                DateTimeFormatter.ofPattern(webPattern, new Locale("es", "ES")));
    }

    // gets ID from venue through venuService for security
    private String getVenueId(String name) throws Exception {
        Venue venue = venueService.getByName(name);
        if (venue == null) throw new Exception();
        return venue.getId();
    }
}
