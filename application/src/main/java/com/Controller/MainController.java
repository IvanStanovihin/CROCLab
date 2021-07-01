package com.Controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import com.Logic.Properties.PropertyLoader;

import com.Threads.ThreadLaunchButton;
import com.Threads.ThreadLaunchHandler;
import com.Utility.Alerts;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.*;

public class MainController {

    @FXML
    public volatile ProgressBar progressBar;
    @FXML
    public volatile Button btnOpenLog;
    @FXML
    public Button btnCloseApp;

    @FXML
    public ToggleButton btnSectionUpdate;

    @FXML
    public Label lblLaunchUnavailable;

    volatile public static PropertyLoader property;

    @FXML
    volatile public TextArea txtAreaLog;

    @FXML
    volatile private ResourceBundle resources;

    @FXML
    volatile private URL location;

    @FXML
    volatile private TextField txtFieldInputFiles;

    @FXML
    volatile private Label lblCountStringProcFile;

    @FXML
    volatile private TextField txtFieldPropertyPath;

    @FXML
    volatile private Button btnOpenOutDir;

    @FXML
    volatile private Pane pnlChooseFiles;

    @FXML
    volatile private TextField txtFieldDictionaries;

    @FXML
    volatile private Label lblMdlInitials;

    @FXML
    volatile private StackPane pnlAbbreviationsSwitch;

    @FXML
    volatile private StackPane pnlInitialsSwitch;

    @FXML
    volatile private StackPane pnlDeleteWordsSwitch;

    @FXML
    volatile private Button btnChooseProperty;

    @FXML
    volatile private StackPane pnlDatesSwitch;

    @FXML
    volatile private Button btnChooseUserStatistic;

    @FXML
    volatile private Pane pnlModules;

    @FXML
    volatile private Pane pnlLaunch;

    @FXML
    volatile private StackPane pnlDictionariesSwitch;

    @FXML
    volatile private TextField txtFieldOutFiles;

    @FXML
    volatile private Label lblMdlLinks;

    @FXML
    volatile private ToggleButton btnSectionModules;

    @FXML
    volatile private Label lblOutDir;

    @FXML
    volatile private Label lblMdlDeleteWords;

    @FXML
    volatile private StackPane pnlLinksSwitch;

    @FXML
    volatile private Label lblMdlDaysWeek;

    @FXML
    volatile private Label lblProtectedWords;

    @FXML
    volatile private Label lblMdlNumbers;

    @FXML
    volatile private Label lblMdlDictionaries;

    @FXML
    volatile private Label lblMdlMonths;

    @FXML
    volatile private TextField txtFieldUserStatistic;

    @FXML
    volatile private Label lblDeletWords;

    @FXML
    volatile private Button btnChooseProtectedWords;

    @FXML
    volatile private Button btnChooseDictionaries;

    @FXML
    volatile private TextField txtFieldProtectedWords;

    @FXML
    volatile private Label lblMdlRemoveEnglish;

    @FXML
    volatile private TextField txtFieldDeleteWords;

    @FXML
    volatile private Button btnChooseOutDirectory;

    @FXML
    volatile private ToggleButton btnSectionLaunch;

    @FXML
    volatile private TextField txtFieldSizeFile;

    @FXML
    volatile private StackPane pnlNumbersSwitch;

    @FXML
    volatile private StackPane pnlFractionsSwitch;

    @FXML
    volatile private Label lblMdlPunctuation;

    @FXML
    volatile private Button btnLaunch;

    @FXML
    volatile private StackPane pnlTimeSwitch;

    @FXML
    volatile private Button btnChooseDeleteWords;

    @FXML
    volatile private TextField txtFieldCountString;

    @FXML
    volatile private Label lblMdlFindEnglish;

    @FXML
    volatile private StackPane pnlAcronymsSwitch;

    @FXML
    volatile private StackPane pnlMonthsSwitch;


    @FXML
    volatile private ToggleButton btnSectionSettingFiles;

    @FXML
    volatile private Label lblMdlTimes;

    @FXML
    volatile private Label lblDictionaties;

    @FXML
    volatile private Label lblMdlAcronyms;

    @FXML
    volatile private StackPane pnlMoneySwitch;

    @FXML
    volatile private Label lblMdlAbbreviations;

    @FXML
    volatile private ScrollPane scrlPnChooseFile;

    @FXML
    volatile private Label lblMdlMoneys;

    @FXML
    volatile private Label lblMdlFractions;

    @FXML
    volatile private Label lblSizeProcFile;

    @FXML
    volatile private Label lblPropertyPath;

    @FXML
    volatile private StackPane pnlDeleteEnglishSwitch;

    @FXML
    volatile private Label lblInputFiles;

    @FXML
    volatile private StackPane pnlDaysWeekSwitch;

    @FXML
    volatile private Button btnChooseInputFiles;

    @FXML
    volatile private Label lblUserStat;

    @FXML
    volatile private StackPane pnlFindEnglishSwitch;

    @FXML
    volatile private StackPane pnlPunctuationSwitch;

    @FXML
    volatile private Label lblMdlDates;

    @FXML
    volatile private Label lblEmptyPaths;


    public static final String PROPERTY_HISTORY_PATH = ".\\propertyHistory\\history.json";
    private static volatile Boolean processingFiles = true;

    private ThreadLaunchHandler taskThread;

    private boolean firstLaunch = true;

    private ThreadLaunchButton threadLaunchButton;

    private SettingsController settingsController;

    private Stage settingsStage;

    public static boolean btnLaunchActive = false;


    public MainController() {
    }

    @FXML
    void initialize() {
        btnCloseApp.setOnAction(event -> Platform.exit());



        lblEmptyPaths.setVisible(false);
        lblLaunchUnavailable.setVisible(false);

        btnOpenLog.setDisable(true);
        btnOpenOutDir.setDisable(true);



//        threadLaunchButton = new ThreadLaunchButton("Thread control launch button", property,
//                btnLaunch, lblEmptyPaths);
//        threadLaunchButton.setDaemon(true);
//        threadLaunchButton.start();



//        controlButtonLaunchThread = new ThreadLaunchButton("ControlLaunchBtnThread", btnLaunch, lblEmptyPaths,
//                txtFieldPropertyPath, txtFieldSizeFile, txtFieldCountString, txtFieldInputFiles, txtFieldUserStatistic,
//                txtFieldDictionaries, txtFieldOutFiles, txtFieldProtectedWords, txtFieldDeleteWords);
//        controlButtonLaunchThread.setDaemon(true);
//        controlButtonLaunchThread.start();


        btnLaunch.setOnAction(event -> launchProcess());

        btnOpenLog.setOnAction(event -> openLogDir());
        btnOpenOutDir.setOnAction(event -> openOutDir());



        //Select section

        btnSectionSettingFiles.setOnAction(event -> {
            createSettingsStage();
            btnSectionSettingFiles.setSelected(false);
        });



//        btnSectionUpdate.setOnAction(event -> {
//            btnSectionUpdate.setSelected(false);
//            btnSectionLaunch.setSelected(false);
//            btnSectionModules.setSelected(false);
//            btnSectionSettingFiles.setSelected(false);
//            String jarPath = "C:/Users/ivan/Desktop/CROCLab/application/target/TAaC-jar-with-dependencies.jar";
//            Runtime re = Runtime.getRuntime();
//            try {
//                re.exec("java -jar " + jarPath);
//            } catch (IOException ioe) {
//                ioe.printStackTrace();
//            }
//            System.exit(0);
//        });


    }




    private void createSettingsStage() {
        try {
            settingsStage = new Stage();
            settingsStage.initModality(Modality.WINDOW_MODAL);
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Objects.requireNonNull(getClass().getResource("/fxml/settingsView.fxml")));
            Parent root = loader.load();
            settingsController = loader.getController();
            settingsController.setProperty(property);
            settingsStage.setTitle("Settings");
            settingsStage.initStyle(StageStyle.UNDECORATED);
            settingsStage.setScene(new Scene(root, 1100, 650));
            settingsStage.show();
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    private void openLogDir() {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            Path logDirPath = Paths.get("././out/Report");
            if (Files.exists(logDirPath)) {
                desktop.open(new File(logDirPath.toString()));
            } else {

            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    private void openOutDir() {
        Desktop desktop = null;
        if (desktop.isDesktopSupported()) {
            desktop = Desktop.getDesktop();
        }
        try {
            Path outDirPath = Paths.get(property.getOutDirectory());
            if (Files.exists(outDirPath)) {
                File outDir = new File(property.getOutDirectory());
                desktop.open(outDir);
            } else {
                Alerts.wrongOutDirPath();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }




    private void launchProcess() {
        if (!btnLaunchActive){
            Alerts.hasEmptyPath();
        }else {
            txtAreaLog.setText("Обработка файлов!\n");
            property = settingsController.getProperty();
            taskThread = new ThreadLaunchHandler("Thread launch handler", property, txtAreaLog,
                    btnOpenOutDir, btnOpenLog, progressBar);
            taskThread.setDaemon(true);
            taskThread.start();
            firstLaunch = false;
        }
    }






    @FXML
    private void handleClick(ActionEvent event) {

    }




    private String getFilesPaths(List<File> files) {
        StringBuilder chosenFilesPaths = new StringBuilder();
        for (File chosenFile : files) {
            chosenFilesPaths.append(chosenFile.getPath()).append("\n");
        }
        return chosenFilesPaths.toString();
    }





    public TextField getTxtFieldInputFiles() {
        return txtFieldInputFiles;
    }

    public TextField getTxtFieldPropertyPath() {
        return txtFieldPropertyPath;
    }

    public TextField getTxtFieldDictionaries() {
        return txtFieldDictionaries;
    }

    public TextField getTxtFieldOutFiles() {
        return txtFieldOutFiles;
    }

    public TextField getTxtFieldUserStatistic() {
        return txtFieldUserStatistic;
    }

    public TextField getTxtFieldProtectedWords() {
        return txtFieldProtectedWords;
    }

    public TextField getTxtFieldDeleteWords() {
        return txtFieldDeleteWords;
    }

    public TextField getTxtFieldSizeFile() {
        return txtFieldSizeFile;
    }

    public TextField getTxtFieldCountString() {
        return txtFieldCountString;
    }


}