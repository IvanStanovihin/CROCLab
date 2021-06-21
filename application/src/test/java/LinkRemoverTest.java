import TAaC.Logic.ProcessingServices.LinksService.LinksRemover;
import org.junit.Assert;
import org.junit.Test;


public class LinkRemoverTest {

    @Test
    public void urlType1Link(){
        String textWithLink = "http://site/ssylki Нашиhttp://site/ssylki онлайн-сервисы работают, чтобы сэкономить ваше время и ресурсы.";
        String actualProcessedText = LinksRemover.removeLinks(textWithLink);

        String expectedProcessedText = " Наши онлайн-сервисы работают, чтобы сэкономить ваше время и ресурсы.";

        Assert.assertEquals(expectedProcessedText, actualProcessedText);
    }

    @Test
    public void urlType2Link(){
        String textWithLink = "https://ru.wikipedia.org/wiki/Википедия Наши онлайн-сервисы работают, " +
                "чтобы сэкономить ваше время и ресурсы.";
        String actualProcessedText = LinksRemover.removeLinks(textWithLink);

        String expectedProcessedText = "  Наши онлайн-сервисы работают, чтобы сэкономить ваше время и ресурсы.";

        Assert.assertEquals(expectedProcessedText, actualProcessedText);
    }
}
