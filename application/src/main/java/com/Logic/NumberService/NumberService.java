package com.Logic.NumberService;

import com.Logic.Handler.Handler;
import com.Logic.InputFile.InputFile;
import com.Logic.ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberService {

    private static String MODULE_NAME = "Numbers ";

    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableNumbersModule();
    }


    public static void handleNumbers(ArrayList<InputFile> inputFiles) {
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESS_NUMBERS, inputFile.getFileName());
                handleFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule(MODULE_NAME);
        }else{
            Handler.reportLog.moduleIsDisable(MODULE_NAME);
        }
    }

    //Принимает список предложений из файла. Возвращает список предложений с числами, раскрытыми в текст.
    private static void handleFile(InputFile inputFile) {
        String fileText = inputFile.getFileText();
        StringBuilder cleanText = new StringBuilder();
        Pattern numbersPattern = Pattern.compile("[\\d]+(-ый|-ого|-его|-ому|-ым|-ом|-ой|-ий|-й|-го|-ему|-ем|ый|ого|его|ому|ым|ом|ой|ий|й|го|ему|ем)?");
        Matcher matcher = numbersPattern.matcher(fileText);
        while (matcher.find()) {
            String foundNumber = fileText.substring(matcher.start(), matcher.end()).trim();
            String replacement = "";
            try {
                matcher.group(0);
                replacement = NumberHandler.numberToString(foundNumber);
            }
            catch(Exception e) {
                if (foundNumber.length() > 4) {
                    replacement = processLongNumber(foundNumber);
                } else {
                    replacement = NumberHandler.numberToString(foundNumber);
                }
            }

            matcher.appendReplacement(cleanText, " " + replacement + " ");
        }
        matcher.appendTail(cleanText);
        inputFile.setFileText(cleanText.toString());
    }

    private static String processLongNumber(String longNumber) {
        StringBuilder result = new StringBuilder();
        while (longNumber.length() > 0) {
            if (longNumber.length() > 3) {
                result.append(NumberHandler.numberToString(longNumber.substring(0, 3)));
                longNumber = longNumber.substring(3);
            } else {
                result.append(NumberHandler.numberToString(longNumber));
                break;
            }
        }
        return result.toString();
    }


}