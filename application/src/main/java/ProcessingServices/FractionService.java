package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import NumberService.NumberHandler;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for disclosure fractions into text
 */
public class FractionService {

    /**
     * Regular expression for finding fractions
     */
    private static final String regexFraction = "\\d+,\\d+";

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableFractionsModule();
    }


    /**
     * Method of processing fractions
     * @param inputFiles ArrayList with inputFiles
     */
    public static void handle(ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESSING_FRACTIONS, inputFile.getFileName());
                handleFraction(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Fractions ");
        }
    }

    /**
     * Find and expand fractions in a single input file
     * @param inputFile input file for processing
     */
    private static void handleFraction(InputFile inputFile) {
        String fileText = inputFile.getFileText();
        Pattern fractionPattern = Pattern.compile(regexFraction);
        Matcher matcher = fractionPattern.matcher(fileText);
        StringBuilder processedText = new StringBuilder();
        while(matcher.find()){
            StringBuilder replacement = new StringBuilder();
            String  foundFraction = fileText.substring(matcher.start(), matcher.end());

            String[] strings = foundFraction.split(",");

            String whole = "";
            String fractional = "";
            String end = "";

            switch (strings[0]){
                case "1":
                    whole = "одна целая ";
                    break;
                case "2":
                    whole = "две целых ";
                    break;
                default:
                    if(strings[0].matches("\\d*2") && !strings[0].equals("12")){
                        String number = NumberHandler.numberToString(strings[0]);
                        whole = number.substring(0 , number.length() - 2) + "е" +  " целых ";
                    }else if(strings[0].matches("\\d*1")){
                        String number = NumberHandler.numberToString(strings[0]);
                        whole = number.substring(0 , number.length() - 3) + "на" +  " целая ";
                    }
                    else {
                        whole = NumberHandler.numberToString(strings[0]) + " целых ";
                        break;
                    }

            }


            switch (strings[1]){
                case "1":
                    fractional = "одна десятая";
                    break;
                case "2":
                    fractional = "две десятых";
                    break;
                default:
                    fractional = NumberHandler.numberToString(strings[1]);
                    switch (strings[1].length()){
                        case 1:
                            end = " десятых";
                            break;
                        case 2:
                            end = " сотых";
                            break;
                        case 3:
                            end = " тысячных";
                            break;
                        case 4:
                            end = " десятитысячных";
                            break;
                    }
                    break;

            }
            replacement.append(whole).append(fractional).append(end);
            matcher.appendReplacement(processedText, replacement.toString());
        }

        matcher.appendTail(processedText);
        inputFile.setFileText(processedText.toString());
    }
}
