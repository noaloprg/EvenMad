package lopez.noa.evenMad.helpers;

/**
 * Class for refactoring HTTP messages that the controller is going to send
 */
public class MessagesHelper {

    /**
     *
     * @param resource - name of the resource affected
     * @return
     */
    public static String getOkMessage(String resource, String operation) {
        return String.format("%s %s correctly", resource, operation);
    }
}
