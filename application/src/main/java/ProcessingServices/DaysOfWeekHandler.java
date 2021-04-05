package ProcessingServices;

import InputFile.InputFile;
import ReportLog.ReportLog;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DaysOfWeekHandler {

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


    public static void handleDaysOfWeek(ArrayList<InputFile> inputFiles){
        ReportLog.logCurrentOperation("Обработка дней недели.");
        for (InputFile inputFile : inputFiles){
            processInputFile(inputFile);
        }
    }

    private static void processInputFile(InputFile inputFile){
        for (String dayOfWeek : daysOfWeek.keySet()){
            String fileText = inputFile.getFileText();
            StringBuffer cleanText = new StringBuffer();
            Pattern dayOfWeekPattern = Pattern.compile("(?<=[^А-Яа-яЁё])" + dayOfWeek + "(?=[^А-Яа-яёЁ])", Pattern.CASE_INSENSITIVE | Pattern.UNICODE_CASE);
            Matcher matcher = dayOfWeekPattern.matcher(fileText);
            while (matcher.find()){
                String dayOfWeekReplacement = daysOfWeek.get(dayOfWeek);
                String foundWord = fileText.substring(matcher.start(), matcher.end());
                inputFile.getReplacementFile().addReplacement(foundWord, dayOfWeekReplacement, "dayOfWeek");
                matcher.appendReplacement(cleanText, dayOfWeekReplacement);
            }
            matcher.appendTail(cleanText);
            inputFile.setFileText(cleanText.toString());
        }
    }
}