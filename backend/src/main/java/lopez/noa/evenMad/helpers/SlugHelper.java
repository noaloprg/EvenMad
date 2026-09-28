package lopez.noa.evenMad.helpers;

import java.text.Normalizer;

public class SlugHelper {

    public static String toSlug(String text) {
        if (text == null || text.isBlank()) return "";

        return Normalizer.normalize(text, Normalizer.Form.NFD)
                // removes accents
                .replaceAll("\\p{M}", "")
                .toLowerCase()
                // removes everything that it's not a letter, a number, a space or a dash
                .replaceAll("[^a-z0-9\\s-]", "")
                .trim()
                // collapses multiple spaces or dashes into only one dash
                .replaceAll("[\\s-]+", "-")
                // no dash at the start or end of slug
                .replaceAll("^-|-$", "");

    }
}
