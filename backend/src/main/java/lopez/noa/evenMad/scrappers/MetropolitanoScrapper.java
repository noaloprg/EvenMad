package lopez.noa.evenMad.scrappers;

import lopez.noa.evenMad.DTO.creation.CreateEventDTO;
import lopez.noa.evenMad.constants.AppConstants;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.documents.Venue;
import lopez.noa.evenMad.documents.subdocuments.MatchDetails;
import lopez.noa.evenMad.services.EventService;
import lopez.noa.evenMad.services.VenueService;
import lopez.noa.evenMad.utils.DataHelper;
import lopez.noa.evenMad.utils.ScrapperClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    private VenueService venueService;
    private EventService eventService;

    public MetropolitanoScrapper(VenueService venueService) {
        this.venueService = venueService;
    }

    public void manageDocuments() {
        // REMEMBER gestionar la excepcion, mirar si se personaliza
        try {
            Document htmlDoc = ScrapperClient.basicConnection(FEM_TEAM);

            // label of each element in the list of matches
            Elements events = htmlDoc.select("header-calendar");


        } catch (IOException e) {
            System.err.println("Error obtaining the documents from scrapper");
        }
    }

    // Normalizes data from extraction of matches
    private List<Event> normalizeMatchesData(Elements matches) {
        List<Event> eventsList = new ArrayList<>();


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
                    Event newEvent = eventService.createNewEvent(dto);
                    eventsList.add(newEvent);
                }
                // REMEMBER CAMBIAR
                catch (Exception e) {
                    e.getStackTrace();
                }
            }
        }
        return eventsList;
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
