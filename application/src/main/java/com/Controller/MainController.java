package com.Controller;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.ResourceBundle;

import com.Components.LabelsChooseFiles;
import com.Components.LabelsModules;
import com.Components.SwitchButton;
import com.Logic.Properties.PropertyLoader;

import com.Threads.ThreadLaunchButton;
import com.Threads.ThreadLaunchHandler;
import com.Utility.Alerts;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Pos;


import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

    @FXML
    public ProgressBar progressBar;
    @FXML
    public Button btnOpenLog;
    @FXML
    public Button btnCloseApp;

    volatile private PropertyLoader property;

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

    private SwitchButton switchRemoveEnglish;
    private SwitchButton switchDictionaryWords;
    private SwitchButton switchFindEnglish;
    private SwitchButton switchDates;
    private SwitchButton switchTimes;
    private SwitchButton switchMoneys;
    private SwitchButton switchFractions;
    private SwitchButton switchNumbers;
    private SwitchButton switchPunctuation;
    private SwitchButton switchDeleteWords;
    private SwitchButton switchDaysOfWeek;
    private SwitchButton switchInitials;
    private SwitchButton switchAbbreviations;
    private SwitchButton switchMonths;
    private SwitchButton switchAcronyms;
    private SwitchButton switchLinks;

    @FXML
    volatile private Label lblEmptyPaths;

    private static volatile Boolean processingFiles = true;

    private ThreadLaunchHandler taskThread;

    private boolean firstLaunch = true;

    private static final String PROPERTY_HISTORY_PATH = "..\\propertyHistory\\history.json";





    public MainController() {
    }

    @FXML
    void initialize() {
        createModuleSwitches();
        loadPropertyHistory();
        btnCloseApp.setOnAction(event -> Platform.exit());
        pnlChooseFiles.toFront();
        btnSectionSettingFiles.setSelected(true);

        btnOpenLog.setDisable(true);
        btnOpenOutDir.setDisable(true);

        lblEmptyPaths.setVisible(false);
        createTooltipsForField();

        LabelsChooseFiles hintsForChooseFiles = new LabelsChooseFiles(lblPropertyPath, lblSizeProcFile, lblCountStringProcFile,
                lblInputFiles, lblDictionaties, lblUserStat, lblOutDir, lblProtectedWords, lblDeletWords);
        LabelsModules labelsModules = new LabelsModules(lblMdlRemoveEnglish, lblMdlFindEnglish, lblMdlLinks, lblMdlInitials,
                lblMdlTimes, lblMdlDates, lblMdlFractions, lblMdlNumbers, lblMdlMoneys, lblMdlPunctuation, lblMdlAcronyms,
                lblMdlDaysWeek, lblMdlDeleteWords, lblMdlAbbreviations, lblMdlMonths, lblMdlDictionaries);

        ThreadLaunchButton controlButtonLaunchThread = new ThreadLaunchButton("ControlLaunchBtnThread", btnLaunch, lblEmptyPaths,
                txtFieldPropertyPath, txtFieldSizeFile, txtFieldCountString, txtFieldInputFiles, txtFieldUserStatistic,
                txtFieldDictionaries, txtFieldOutFiles, txtFieldProtectedWords, txtFieldDeleteWords);
        controlButtonLaunchThread.setDaemon(true);
        controlButtonLaunchThread.start();



        btnLaunch.setOnAction(event -> launchProcess());

        btnOpenLog.setOnAction(event -> openLogDir());
        btnOpenOutDir.setOnAction(event -> openOutDir());

        btnChooseInputFiles.setOnAction(event -> {
        File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldInputFiles.setText(chosenDirectory.getAbsolutePath());
            }
        });

        btnChooseDictionaries.setOnAction(event -> {
            File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldDictionaries.setText(chosenDirectory.getAbsolutePath());
            }
        });

        btnChooseUserStatistic.setOnAction(event -> {
            File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldUserStatistic.setText(chosenDirectory.getAbsolutePath());
            }
        });

        btnChooseOutDirectory.setOnAction(event -> {
            File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldOutFiles.setText(chosenDirectory.getAbsolutePath());
            }
        });

        btnChooseProtectedWords.setOnAction(event -> {
            File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldProtectedWords.setText(chosenDirectory.getAbsolutePath());
            }
        });

        btnChooseDeleteWords.setOnAction(event -> {
            File chosenDirectory = chooseDirectory();
            if (chosenDirectory != null) {
                txtFieldDeleteWords.setText(chosenDirectory.getAbsolutePath());
            }
        });
        btnChooseProperty.setOnAction(event -> {
            File chosenFile = chooseFile();
            if (chosenFile != null) {
                txtFieldPropertyPath.setText(chosenFile.getAbsolutePath());
                String propertyPath = txtFieldPropertyPath.getText().trim();
                //TODO: Make error handling for failed property.json read attempt. Create alerts.
                property = new PropertyLoader(propertyPath);
                fillInPropertyPaths();
                fillInModulesSwitch();
            }
        });

        //Select section
        btnSectionModules.setOnAction(event -> {
            pnlModules.toFront();
            btnSectionLaunch.setSelected(false);
            btnSectionSettingFiles.setSelected(false);
        });

        btnSectionSettingFiles.setOnAction(event -> {
            pnlChooseFiles.toFront();
            btnSectionLaunch.setSelected(false);
            btnSectionModules.setSelected(false);
        });

        btnSectionLaunch.setOnAction(event -> {
            pnlLaunch.toFront();
            btnSectionModules.setSelected(false);
            btnSectionSettingFiles.setSelected(false);

        });


    }

    private void loadPropertyHistory() {
        if (Files.exists(Paths.get(PROPERTY_HISTORY_PATH))) {
            property = new PropertyLoader(PROPERTY_HISTORY_PATH);
            fillInPropertyPaths();
            fillInModulesSwitch();
        }
    }


    private void openLogDir() {
        Desktop desktop = null;
        if (Desktop.isDesktopSupported()){
            desktop = Desktop.getDesktop();
        }
        try{
            Path logDirPath = Paths.get("././out/Report");
            if (Files.exists(logDirPath)) {
                desktop.open(new File(logDirPath.toString()));
            }else{

            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    private void openOutDir() {
        Desktop desktop = null;
        if (desktop.isDesktopSupported()){
            desktop = Desktop.getDesktop();
        }
        try{
            Path outDirPath = Paths.get(property.getOutDirectory());
            if (Files.exists(outDirPath)) {
                File outDir = new File(property.getOutDirectory());
                desktop.open(outDir);
            }else {
                Alerts.wrongOutDirPath();
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
    }


    private void createTooltipsForField() {
        Tooltip propertyTooltip = new Tooltip();
        Tooltip inFilesTooltip = new Tooltip();
        Tooltip dictionariesTooltip = new Tooltip();
        Tooltip userStatisticTooltip = new Tooltip();
        Tooltip outDirTooltip = new Tooltip();
        Tooltip protectWordsTooltip = new Tooltip();
        Tooltip deleteWordsTooltip = new Tooltip();

        txtFieldPropertyPath.textProperty().addListener( (ov,oldV,newV) -> {
            propertyTooltip.setShowDuration(Duration.minutes(5));
            propertyTooltip.setText(txtFieldPropertyPath.getText());
            txtFieldPropertyPath.setTooltip(propertyTooltip);
        } );

        txtFieldInputFiles.textProperty().addListener( (ov,oldV,newV) -> {
            inFilesTooltip.setShowDuration(Duration.minutes(5));
            inFilesTooltip.setText(txtFieldInputFiles.getText());
            txtFieldInputFiles.setTooltip(inFilesTooltip);
        } );

        txtFieldDictionaries.textProperty().addListener( (ov,oldV,newV) -> {
            dictionariesTooltip.setShowDuration(Duration.minutes(5));
            dictionariesTooltip.setText(txtFieldDictionaries.getText());
            txtFieldDictionaries.setTooltip(dictionariesTooltip);
        } );

        txtFieldUserStatistic.textProperty().addListener( (ov,oldV,newV) -> {
            userStatisticTooltip.setShowDuration(Duration.minutes(5));
            userStatisticTooltip.setText(txtFieldUserStatistic.getText());
            txtFieldUserStatistic.setTooltip(userStatisticTooltip);
        } );

        txtFieldOutFiles.textProperty().addListener( (ov,oldV,newV) -> {
            outDirTooltip.setShowDuration(Duration.minutes(5));
            outDirTooltip.setText(txtFieldOutFiles.getText());
            txtFieldOutFiles.setTooltip(outDirTooltip);
        } );

        txtFieldProtectedWords.textProperty().addListener( (ov,oldV,newV) -> {
            protectWordsTooltip.setShowDuration(Duration.minutes(5));
            protectWordsTooltip.setText(txtFieldProtectedWords.getText());
            txtFieldProtectedWords.setTooltip(protectWordsTooltip);
        } );

        txtFieldDeleteWords.textProperty().addListener( (ov,oldV,newV) -> {
            deleteWordsTooltip.setShowDuration(Duration.minutes(5));
            deleteWordsTooltip.setText(txtFieldDeleteWords.getText());
            txtFieldDeleteWords.setTooltip(deleteWordsTooltip);
        } );
    }

    private void launchProcess() {
        if (!firstLaunch) {
            taskThread = null;
            System.gc();
        }
            overwriteProperty();
            txtAreaLog.setText("Обработка файлов!\n");

            taskThread = new ThreadLaunchHandler("Thread launch handler", property, txtAreaLog, btnOpenOutDir, btnOpenLog);
            taskThread.setDaemon(true);
            taskThread.start();
            firstLaunch = false;
    }



    private void fillInPropertyPaths() {
        txtFieldSizeFile.setText(property.getOutFileSize().toString());
        txtFieldCountString.setText(property.getOutFileCountStrings().toString());
        txtFieldInputFiles.setText(property.getInputFilesDirectory());
        txtFieldDictionaries.setText(property.getDictionariesDirectory());
        txtFieldUserStatistic.setText(property.getFilesForStatisticDirectory());
        txtFieldOutFiles.setText(property.getOutDirectory());
        txtFieldProtectedWords.setText(property.getProtectedWordsDir());
        txtFieldDeleteWords.setText(property.getWordsToDeleteDir());
    }

    private void fillInModulesSwitch(){
        switchRemoveEnglish.setState(property.isEnableRemoveEnglishTextModule());
        System.out.println(switchRemoveEnglish.getState());
        switchFindEnglish.setState(property.isEnableFindEnglishModule());
        System.out.println(switchFindEnglish.getState());
        switchLinks.setState(property.isEnableLinksModule());
        switchInitials.setState(property.isEnableInitialsModule());
        switchTimes.setState(property.isEnableTimesModule());
        switchDates.setState(property.isEnableDatesModule());
        switchFractions.setState(property.isEnableFractionsModule());
        switchMoneys.setState(property.isEnableMoneyModule());
        switchPunctuation.setState(property.isEnablePunctuationMarkModule());
        switchAcronyms.setState(property.isEnableAcronymsModule());
        switchDaysOfWeek.setState(property.isEnableDaysOfWeekModule());
        switchDeleteWords.setState(property.isEnableRemoveWordsModule());
        switchAbbreviations.setState(property.isEnableAbbreviationsFindModule());
        switchMonths.setState(property.isEnableMonthsModule());
        switchDictionaryWords.setState(property.isEnableDictionaryWordsModule());
        switchNumbers.setState(property.isEnableNumbersModule());
    }

    private void createModuleSwitches(){

        switchRemoveEnglish = new SwitchButton();
        switchRemoveEnglish.setMaxWidth(20);
        pnlDeleteEnglishSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlDeleteEnglishSwitch.getChildren().add(switchRemoveEnglish);

        switchDictionaryWords = new SwitchButton();
        switchDictionaryWords.setMaxWidth(20);
        pnlDictionariesSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlDictionariesSwitch.getChildren().add(switchDictionaryWords);

        switchFindEnglish = new SwitchButton();
        switchFindEnglish.setMaxWidth(20);
        pnlFindEnglishSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlFindEnglishSwitch.getChildren().add(switchFindEnglish);

        switchDates = new SwitchButton();
        switchDates.setMaxWidth(20);
        pnlDatesSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlDatesSwitch.getChildren().add(switchDates);

        switchTimes = new SwitchButton();
        switchTimes.setMaxWidth(20);
        pnlTimeSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlTimeSwitch.getChildren().add(switchTimes);

        switchMoneys = new SwitchButton();
        switchMoneys.setMaxWidth(20);
        pnlMoneySwitch.setAlignment(Pos.CENTER_LEFT);
        pnlMoneySwitch.getChildren().add(switchMoneys);

        switchFractions = new SwitchButton();
        switchFractions.setMaxWidth(20);
        pnlFractionsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlFractionsSwitch.getChildren().add(switchFractions);

        switchNumbers = new SwitchButton();
        switchNumbers.setMaxWidth(20);
        pnlNumbersSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlNumbersSwitch.getChildren().add(switchNumbers);

        switchPunctuation = new SwitchButton();
        switchPunctuation.setMaxWidth(20);
        pnlPunctuationSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlPunctuationSwitch.getChildren().add(switchPunctuation);

        switchDeleteWords = new SwitchButton();
        switchDeleteWords.setMaxWidth(20);
        pnlDeleteWordsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlDeleteWordsSwitch.getChildren().add(switchDeleteWords);

        switchDaysOfWeek = new SwitchButton();
        switchDaysOfWeek.setMaxWidth(20);
        pnlDaysWeekSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlDaysWeekSwitch.getChildren().add(switchDaysOfWeek);

        switchInitials = new SwitchButton();
        switchInitials.setMaxWidth(20);
        pnlInitialsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlInitialsSwitch.getChildren().add(switchInitials);

        switchAbbreviations = new SwitchButton();
        switchAbbreviations.setMaxWidth(20);
        pnlAbbreviationsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlAbbreviationsSwitch.getChildren().add(switchAbbreviations);

        switchMonths = new SwitchButton();
        switchMonths.setMaxWidth(20);
        pnlMonthsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlMonthsSwitch.getChildren().add(switchMonths);

        switchAcronyms = new SwitchButton();
        switchAcronyms.setMaxWidth(20);
        pnlAcronymsSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlAcronymsSwitch.getChildren().add(switchAcronyms);

        switchLinks = new SwitchButton();
        switchLinks.setMaxWidth(20);
        pnlLinksSwitch.setAlignment(Pos.CENTER_LEFT);
        pnlLinksSwitch.getChildren().add(switchLinks);

    }

    @FXML
    private void handleClick(ActionEvent event){

    }




    private File chooseDirectory(){
        File chosenDirectory;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбор директории");
        chosenDirectory = directoryChooser.showDialog(btnChooseInputFiles.getScene().getWindow());
        return chosenDirectory;
    }

    private File chooseFile(){
        Stage stage = (Stage)btnChooseProperty.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(stage);
        return chosenFile;
    }

    private String getFilesPaths(List<File>files){
        StringBuilder chosenFilesPaths = new StringBuilder();
        for (File chosenFile : files) {
            chosenFilesPaths.append(chosenFile.getPath()).append("\n");
        }
        return chosenFilesPaths.toString();
    }




    private void overwriteProperty(){
        //Setting paths
        property.setOutFileSize(Integer.parseInt(txtFieldSizeFile.getText().trim()));
        property.setOutFileCountStrings(Integer.parseInt(txtFieldCountString.getText().trim()));
        property.setInputFilesDirectory(txtFieldInputFiles.getText().trim());
        property.setDictionariesDirectory(txtFieldDictionaries.getText().trim());
        property.setFilesForStatisticDirectory(txtFieldUserStatistic.getText().trim());
        property.setOutDirectory(txtFieldOutFiles.getText().trim());
        property.setProtectedWordsDir(txtFieldProtectedWords.getText().trim());
        property.setWordsToDeleteDir(txtFieldDeleteWords.getText().trim());
        //Setting modules
        property.setEnableRemoveEnglishTextModule(switchRemoveEnglish.getState());
        property.setEnableDictionaryWordsModule(switchDictionaryWords.getState());
        property.setEnableFindEnglishModule(switchFindEnglish.getState());
        //TODO : add phone numbers processing module
        property.setEnableDatesModule(switchDates.getState());
        property.setEnableTimesModule(switchTimes.getState());
        property.setEnableMoneyModule(switchMoneys.getState());
        property.setEnableFractionsModule(switchFractions.getState());
        property.setEnableNumbersModule(switchNumbers.getState());
        property.setEnableLinksModule(switchLinks.getState());
        property.setEnablePunctuationMarkModule(switchPunctuation.getState());
        property.setEnableRemoveWordsModule(switchDeleteWords.getState());
        property.setEnableDaysOfWeekModule(switchDaysOfWeek.getState());
        property.setEnableInitialsModule(switchInitials.getState());
        property.setEnableAbbreviationsFindModule(switchAbbreviations.getState());
        //TODO : add camelCase processing module
        property.setEnableMonthsModule(switchMonths.getState());
        property.setEnableAcronymsModule(switchAcronyms.getState());
        File propertyHistoryDir = new File("..\\propertyHistory");
        propertyHistoryDir.mkdir();
        property.overwritePropertyHistory(PROPERTY_HISTORY_PATH);
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
