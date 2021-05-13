import Handler.Handler;


import java.io.IOException;

public class Launch {


    public static void main(String[] args) throws IOException {
//        String propertyFilePath = args[0];
        Long startTime = System.currentTimeMillis();
//        Handler analyzer = new Handler(propertyFilePath);
        Handler analyzer = new Handler("C:\\Users\\ivan\\Desktop\\CROCLab\\Files\\Property.json");

        analyzer.createOutputFiles();
//        ExcelParser excelParser = new ExcelParser("C:\\Users\\ivan\\Desktop\\input\\ExcelInputFiles");
//        excelParser.createFilesForProcess("C:\\Users\\ivan\\Desktop\\input\\FilesFromExcel");
        Long endTime = System.currentTimeMillis();
        System.out.println("Time to process the files = " + (float)(endTime - startTime)/1000 + " s.");



    }

}
