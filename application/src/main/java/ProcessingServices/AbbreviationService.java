package ProcessingServices;

import Handler.Handler;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AbbreviationService {

    private static final String DESCRIPTION_REPLACEMENT = " Сокращение";

    private static Map<String, String> daysOfWeek = new LinkedHashMap<>();
    static{
        daysOfWeek.put("пн", "понедельник");
        daysOfWeek.put("вт", "вторник");
        daysOfWeek.put("ср", "среда");
        daysOfWeek.put("чт", "четверг");
        daysOfWeek.put("пт", "пятница");
        daysOfWeek.put("сб", "суббота");
        daysOfWeek.put("вс", "воскресенье");
    }

    //Принимает строку, в которую записан файл. Возвращает строку с обработанными сокращениями.
    public static String handle(String fileText){
        String handledText = handleAbbreviation(fileText);
        handledText = handleDaysOfWeek(handledText);
        handledText = handleInitials(handledText);
        return handledText;
    }


    public static String handleAbbreviation(String fileText){
        StringBuffer result = new StringBuffer();
        //regex, который находит аббревиатуры
        Pattern pattern = Pattern.compile("(\\b[а-яА-ЯёЁ]+?\\.)|([А-Яа-яёЁ]{1,4}-[А-Яа-яёЁ]+)");
        Matcher matcher = pattern.matcher(fileText);
        while(matcher.find()){
            //аббревиатура найденная в тексте
            String abbreviation = fileText.substring(matcher.start(), matcher.end()).trim();
            //ищем для неё замену в словаре
            String replacement = Handler.getDictionary().abbreviationExplanation(abbreviation);
            if (replacement != null) {
                matcher.appendReplacement(result, " " + replacement + " ");
                Handler.replacementFile.addReplacement(abbreviation, replacement, DESCRIPTION_REPLACEMENT);
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }

    //Раскрывает найденные в тексте сокращения дней недели
    private static String handleDaysOfWeek(String fileText){
        StringBuffer result = new StringBuffer();
        Pattern pattern = Pattern.compile("(\\b[а-яА-Я]{2}[;:,.!?\\s\b])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(fileText);
        while(matcher.find()){
            String foundWord = fileText.substring(matcher.start(), matcher.end()).toLowerCase();
            String punctuationMark = foundWord.substring(2);
            foundWord = foundWord.substring(0,2);
            if (daysOfWeek.containsKey(foundWord)){
                String replacement = daysOfWeek.get(foundWord);
                matcher.appendReplacement(result, " " + replacement + punctuationMark);
            }
        }
        matcher.appendTail(result);
        return result.toString();
    }

    //Удаление инициалов
    private static String handleInitials(String fileText){
        fileText = fileText.replaceAll("\\s+[А-ЯЁ]\\.\\s*[А-ЯЁ]\\.", " ");
        return fileText;
    }
}
