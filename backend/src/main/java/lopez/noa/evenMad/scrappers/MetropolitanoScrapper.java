package lopez.noa.evenMad.scrappers;

import lopez.noa.evenMad.DTO.creation.CreateEventDTO;
import lopez.noa.evenMad.documents.Event;
import lopez.noa.evenMad.utils.DataHelper;
import lopez.noa.evenMad.utils.ScrapperClient;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Label names and anidation of content explained on file:
 * ./docs/scrapping-sources.md
 */
public class MetropolitanoScrapper implements EventScrapper {

    @Override
    public String getScrapperSourceId() {
        return "metropolitano";
    }

    @Override
    public List<Event> scrapeEvents() {
        return List.of();
    }


    private static final String BASIC_URL = "https://www.atleticodemadrid.com";

    private static final String FEM_TEAM = BASIC_URL + "/calendario-primer-equipo-femenino";

    private static final String MASC_TEAM = BASIC_URL + "/calendario-completo-primer-equipo";

    private static final String ACTUAL_VENUE_NAME = "Riyadh Air Metropolitano";

    public static void manageDocuments() {
        // REMEMBER gestionar la excepcion, mirar si se personaliza
        try {
            Document htmlDoc = ScrapperClient.basicConnection(FEM_TEAM);

            // label of each elemen in the list of matches
            Elements events = htmlDoc.select("header-calendar");


        } catch (IOException e) {
            System.err.println("Error obtaining the documents from scrapper");
        }
    }

    // Normalizes data from extraction of matches
    private static void normalizeMatchesData(Elements matches) {
        List<Event> eventsList = new ArrayList<>();


        for (Element el : matches) {
            // 'span' elements inside a class called 'info-calendario'
            Elements calendarInfo = el.select(".info-calendario span");

            // info of image, that contians info about the competition
            Element competicionInfo = el.selectFirst(".competition img");

            // dateTime info
            String dateScr = DataHelper.stringCleaner(calendarInfo.get(1).text());
            String timeScr = DataHelper.stringCleaner(calendarInfo.get(2).text());

            // venue info
            String venueScr =  DataHelper.stringCleaner(calendarInfo.get(3).text());

            // players info
            // gets the text from the label <dt> inside the class container local and visitor
            String localScr = DataHelper.stringCleaner(el.select(".local dt").text());
            String visitorScr = DataHelper.stringCleaner(el.select(".visitante dt").text());

            if(competicionInfo != null){
                String competitionScr = DataHelper.stringCleaner(competicionInfo.attr("title"));

//                CreateEventDTO dto = new CreateEventDTO(
//
//                ) ;
            }
        }
    }

    // validates that the stadium is the one desired
    private static boolean isThisVenue(String venue) {
        return venue.toLowerCase().contains(ACTUAL_VENUE_NAME.toLowerCase());
    }

    /**
     * returns de date and time in the correct data format from matcher
     * @param date format from Scrapper ->
      */
    private static LocalDateTime getDateTimesFromMatches(String date, String hour){
        return null;
    }
}
