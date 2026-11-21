package lopez.noa.evenMad.scrapers;

public abstract class BaseScrapper {
    // validates that the venue is the one desired / that has to be
    protected boolean isThisVenue(String venue, String actualVenue) {
        // contains because is more flexible
        return venue.toLowerCase().contains(actualVenue);
    }

}
