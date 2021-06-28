package com;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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


    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/view.fxml")));
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        root.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                primaryStage.setX(event.getScreenX() - xOffset);
                primaryStage.setY(event.getScreenY() - yOffset);
            }
        });

        primaryStage.setTitle("TAaC");
        primaryStage.setScene(new Scene(root, 1100, 650));
        primaryStage.show();
    }

}
