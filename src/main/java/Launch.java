import Handler.Handler;
import NumberService.NumberHandler;
import NumberService.NumberService;
import ProcessingServices.DateServices.DateHandler;
import ProcessingServices.DateServices.StringToData;

import java.io.IOException;

public class Launch {


    public static void main(String[] args) throws IOException {
        String propertyFilePath = args[0];
        Long startTime = System.currentTimeMillis();
        Handler analyzer = new Handler(propertyFilePath);
//        Handler analyzer = new Handler("C:\\Users\\ivan\\Desktop\\Presentation_CROC\\Property.json");

        analyzer.createOutputFiles();
//        ExcelParser excelParser = new ExcelParser("C:\\Users\\ivan\\Desktop\\CROCLab\\Files\\txt");
//        excelParser.createFilesForProcess("C:\\Users\\ivan\\Desktop\\CROCLab\\Files\\AllMonth");
        Long endTime = System.currentTimeMillis();
        System.out.println("Time to process the files = " + (float)(endTime - startTime)/1000 + " с.");



    }

}
