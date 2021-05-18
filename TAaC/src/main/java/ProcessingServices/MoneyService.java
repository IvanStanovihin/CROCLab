package ProcessingServices;

import Handler.Handler;
import InputFile.InputFile;
import NumberService.NumberHandler;
import ReportLog.LogOperation;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class for processing monetary symbols in the text
 */
public class MoneyService {

    /**
     * Method for checking the module connection
     * @return true if the module is enabled , else false
     */
    private static boolean isModuleEnable(){
        return Handler.getProperty().isEnableMoneyModule();
    }

    /**
     * Method of processing monetary symbols
     * @param inputFiles ArrayList with inputFiles
     */
    public static void processMoney(ArrayList<InputFile> inputFiles){
        if (isModuleEnable()) {
            Handler.reportLog.startModule();
            for (InputFile inputFile : inputFiles) {
                Handler.reportLog.startCurrentOperation(LogOperation.PROCESSING_MONEY, inputFile.getFileName());
                processFile(inputFile);
                Handler.reportLog.endOperation();
            }
            Handler.reportLog.endModule("Money ");
        }
    }

    /**
     * Find and disclosure monetary symbols in a single input file
     * @param inputFile input file for processing
     */
    private static void processFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        StringBuilder processedText = new StringBuilder();
        Pattern moneyPattern = Pattern.compile("((\\d{1,3})+\\s?(\\d{1,3})+\\s?\\d{1,3}\\s?)(руб|₽)");
        Matcher matcher = moneyPattern.matcher(fileText);
        while (matcher.find()){
            String foundMoney = matcher.group(1);
            String moneyReplacement = getMoneyReplacement(foundMoney);
            matcher.appendReplacement(processedText, moneyReplacement);
        }
        matcher.appendTail(processedText);
        inputFile.setFileText(processedText.toString());
    }

    /**
     * Method for expanding monetary symbols to text
     * @param foundMoney
     * @return monetary symbols in the form of text
     */
    private static String getMoneyReplacement(String foundMoney){
        String result = "";
        String[] numEndings = {"рубль", "рубля", "рублей"};
        String moneyWithoutSpace = foundMoney.replaceAll(" ", "");
        String textMoney = NumberHandler.numberToString(moneyWithoutSpace);
        Integer moneyNumber = Integer.parseInt(moneyWithoutSpace);

        int base = moneyNumber % 100;
        if (base > 19){
            base = base % 10;
        }
        switch (base){
            case 1:
                result = textMoney + numEndings[0];
                break;
            case 2:
            case 3:
            case 4:
                result = textMoney + numEndings[1];
                break;
            default:
                result = textMoney +  numEndings[2];
        }
        return result;
    }


}
