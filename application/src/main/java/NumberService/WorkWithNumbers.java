package NumberService;

/**
 * Additional class with constants to use in processing handler.
 */
public class WorkWithNumbers {
    /**
     * Number <= 10
     */
    public static final String edinitsi[] = {
         "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять", "десять"
    };
    /**
     * Number > 10 but < 20
     */
    public static final String nadsat[] = {
            "один", "две", "три", "четыр", "пят", "шест", "сем", "восем", "девят"
    };
    /**
     * Number >= 20 but < 40
     */
    public static final String dsat[] = {
      "два", "три"
    };
    /**
     * Number >= 50 but < 90
     */
    public static final String desyat[] = {
            "пять", "шесть", "cемь", "восемь"
    };
    /**
     * Number >= 300 but <= 90
     * ! 90, 100, 200 - exceptions
     */
    public static final String sta[] = {
            "три", "четыре"
    };
    /**
     * Number >=500 but <1000
     */
    public static final String sot[] = {
            "пять", "шесть", "семь", "восемь", "девять"
    };
    /**
     * Number >= 2000 but < 5000
     * ! 1000 - exception
     */
    public static final String tasyachi[] = {
      "две", "три", "четыре"
    };
    /**
     * Others
     */
    public static final String others[] = {
            "миллион", "миллиард", "триллион", "квадриллион"
    };
}
