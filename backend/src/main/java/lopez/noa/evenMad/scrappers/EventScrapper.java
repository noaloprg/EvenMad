package lopez.noa.evenMad.scrappers;

import lopez.noa.evenMad.documents.Event;

import java.util.List;

/**
 * Interface used in every event scrapper 
 */
public interface EventScrapper {
    // returns name of the scrapper using the interface
    String getScrapperSourceId();

    // list of events obtained from the scrapper
    List<Event> scraperEvents();
}
