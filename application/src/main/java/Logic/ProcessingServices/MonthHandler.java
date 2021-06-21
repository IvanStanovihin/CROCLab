package TAaC.Logic.ProcessingServices;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.InputFile.InputFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for disclosure of months
 */
public class MonthHandler {

    /**
     * Map that stores the options for the disclosure of months
     */
    private static Map<Pattern, String>monthsRegexps = new HashMap<>();
    static{
        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])янв(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "январь");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])фев(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "февраль");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])апр(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "апрель");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])авг(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "август");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])сен(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "сентябрь");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])окт(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "октябрь");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])ноя(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "ноябрь");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])нояб(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "ноябрь");

        monthsRegexps.put(Pattern.compile("(?<![А-Яа-яёЁ])дек(?![А-Яа-яёЁ])",
                (Pattern.UNICODE_CASE | Pattern.CASE_INSENSITIVE) ), "декабрь");
    }


    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableMonthsModule();
    }

    /**
     * Method of processing months
     * @param inputFiles ArrayList with inputFiles
     */
    public static void processMonths(ArrayList<InputFile>inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                processFile(inputFile);
            }
            Handler.reportLog.endModule("Months ");
        }
    }

    /**
     * Find and disclosure month in a single input file
     * @param inputFile input file for processing
     */
    private static void processFile(InputFile inputFile){
        for (Map.Entry<Pattern, String> entries : monthsRegexps.entrySet()){
            StringBuilder processedFileText = new StringBuilder();
            String fileText = inputFile.getFileText();
            Pattern monthPattern = entries.getKey();
            String monthReplacement = " " + entries.getValue() + " ";
            Matcher matcher = monthPattern.matcher(fileText);
            while(matcher.find()){
                matcher.appendReplacement(processedFileText, monthReplacement);
            }
            matcher.appendTail(processedFileText);
            inputFile.setFileText(processedFileText.toString());
        }
    }
}
