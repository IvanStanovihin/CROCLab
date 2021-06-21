import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

/**
 * TAaC application for processing and collecting statistics on files
 * @Version 1.0
 */
public class Launch extends Application {


    public static void main(String[] args) throws IOException {
        launch(args);
//        Long startTime = System.currentTimeMillis();
////        String propertyFilePath = args[0];
////        TAaC.Logic.Handler analyzer = new TAaC.Logic.Handler(propertyFilePath);
//        Handler analyzer = new Handler("C:\\Users\\ivan\\Desktop\\CROCLab\\Files\\Property.json");
//
//        analyzer.createOutputFiles();
////        TAaC.Logic.ExcelParser excelParser = new TAaC.Logic.ExcelParser("C:\\Users\\ivan\\Desktop\\input\\ExcelInputFiles");
////        excelParser.createFilesForProcess("C:\\Users\\ivan\\Desktop\\input\\FilesFromExcel");
//        Long endTime = System.currentTimeMillis();
//        System.out.println("Time to process the files = " + (float)(endTime - startTime)/1000 + " s.");
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/view.fxml")));
        primaryStage.setTitle("TAaC");
        primaryStage.setScene(new Scene(root, 900, 600));
        primaryStage.show();
    }

}
