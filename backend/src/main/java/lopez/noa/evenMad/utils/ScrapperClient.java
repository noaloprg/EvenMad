package lopez.noa.evenMad.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

/**
 * Utility class that centralizes and abstracts HTTP connections logic for scrappers
 */
public class ScrapperClient {

    // creates a basic connection with a web
    public static Document basicConnection(String url) throws IOException {
        return Jsoup.connect(url)
                // 1.5 minutes
                .timeout(5400)
                // windows
                .userAgent("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/157.0.0.0 Safari/537.36")
                .get();
    }
}
