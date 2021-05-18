package ProcessingServices.DateServices;

import java.util.regex.Matcher;

/**
 * Functional interface to define action under dates
 */
public interface Action {
    abstract String exec(Matcher s);
}
