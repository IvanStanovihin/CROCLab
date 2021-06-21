package TAaC.Logic.Statistic.GeneralStatistic;

import TAaC.Logic.Statistic.ProcessedFileStatistic;
import java.util.ArrayList;

/**A class that generates general statistics for all processed files*/
public class GeneralProcessedFilesStatistic {

    /**List of statistics for each processed file*/
    private ArrayList<ProcessedFileStatistic>processedFileStatistics;


    public GeneralProcessedFilesStatistic(ArrayList<ProcessedFileStatistic>processedFiles){
        this.processedFileStatistics = processedFiles;
    }

    /**Creates general statistics for sentences and words*/
    public void createGeneralStatisticFile(String outDir){
        GeneralWordsStatistic generalWordsStatistic = new GeneralWordsStatistic(processedFileStatistics);
        GeneralSentencesStatistic generalSentencesStatistic = new GeneralSentencesStatistic(processedFileStatistics);
        generalWordsStatistic.createFile(outDir);
        generalSentencesStatistic.createFile(outDir);
    }


}

