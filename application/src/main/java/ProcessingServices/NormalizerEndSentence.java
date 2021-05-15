package ProcessingServices;

import InputFile.InputFile;

import java.util.ArrayList;

public class NormalizerEndSentence {

    public static void normalize(ArrayList<InputFile>inputFiles){
        for(InputFile inputFile : inputFiles){
            normalizeFile(inputFile);
        }
    }

    private static void normalizeFile(InputFile inputFile){
        String fileText = inputFile.getFileText();
        inputFile.setFileText(fileText.replaceAll("\\.ES", "."));
    }
}
