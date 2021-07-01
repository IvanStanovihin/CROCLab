package com.Controller;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ResourceBundle;

import com.Components.LabelsChooseFiles;
import com.Components.LabelsModules;
import com.Components.SwitchButton;
import com.Logic.Properties.PropertyLoader;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class SettingsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtFieldInputFiles;

    @FXML
    private Label lblCountStringProcFile;

    @FXML
    private TextField txtFieldPropertyPath;

    @FXML
    private Pane pnlChooseFiles;

    @FXML
    private TextField txtFieldDictionaries;

    @FXML
    private Label lblMdlInitials;

    @FXML
    private StackPane pnlAbbreviationsSwitch;

    @FXML
    private StackPane pnlDeleteWordsSwitch;

    @FXML
    private Button btnChooseProperty;

    @FXML
    private StackPane pnlDatesSwitch;

    @FXML
    private Button btnChooseUserStatistic;

    @FXML
    private Pane pnlModules;

    @FXML
    private StackPane pnlDictionariesSwitch;

    @FXML
    private TextField txtFieldOutFiles;

    @FXML
    private Label lblMdlLinks;

    @FXML
    private ToggleButton btnSectionModules;

    @FXML
    private Button btnOK;

    @FXML
    private Button btnCancel;

    @FXML
    private Label lblOutDir;

    @FXML
    private Label lblMdlDeleteWords;

    @FXML
    private StackPane pnlLinksSwitch;

    @FXML
    private Label lblMdlDaysWeek;

    @FXML
    private Label lblProtectedWords;

    @FXML
    private Label lblMdlNumbers;

    @FXML
    private Label lblMdlDictionaries;

    @FXML
    private Label lblMdlMonths;

    @FXML
    private TextField txtFieldUserStatistic;

    @FXML
    private Label lblDeletWords;

    @FXML
    private Button btnChooseProtectedWords;

    @FXML
    private Button btnChooseDictionaries;

    @FXML
    private TextField txtFieldProtectedWords;

    @FXML
    private Label lblMdlRemoveEnglish;

    @FXML
    private TextField txtFieldDeleteWords;

    @FXML
    private Button btnChooseOutDirectory;

    @FXML
    private TextField txtFieldSizeFile;

    @FXML
    private StackPane pnlNumbersSwitch;

    @FXML
    private StackPane pnlFractionsSwitch;

    @FXML
    private Label lblMdlPunctuation;

    @FXML
    private StackPane pnlTimeSwitch;

    @FXML
    private Button btnChooseDeleteWords;

    @FXML
    private TextField txtFieldCountString;

    @FXML
    private Label lblMdlFindEnglish;

    @FXML
    private StackPane pnlAcronymsSwitch;

    @FXML
    private StackPane pnlMonthsSwitch;

    @FXML
    private ToggleButton btnSectionSettingFiles;

    @FXML
    private Label lblMdlTimes;

    @FXML
    private Label lblDictionaties;

    @FXML
    private Label lblMdlAcronyms;

    @FXML
    private StackPane pnlMoneySwitch;

    @FXML
    private StackPane pnlInitialsSwitch;

    @FXML
    private Label lblMdlAbbreviations;

    @FXML
    private Label lblMdlMoneys;

    @FXML
    private Label lblMdlFractions;

    @FXML
    private Label lblSizeProcFile;

    @FXML
    private Label lblPropertyPath;

    @FXML
    private StackPane pnlDeleteEnglishSwitch;

    @FXML
    private Label lblInputFiles;

    @FXML
    private StackPane pnlDaysWeekSwitch;

    @FXML
    private Button btnChooseInputFiles;

    @FXML
    private Label lblUserStat;

    @FXML
    private StackPane pnlFindEnglishSwitch;

    @FXML
    private StackPane pnlPunctuationSwitch;

    @FXML
    private Label lblMdlDates;

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

    public PropertyLoader property;




    @FXML
    void initialize() {

        createModuleSwitches();
        createTooltipsForField();
        loadPropertyHistory();
        pnlChooseFiles.toFront();
        btnSectionSettingFiles.setSelected(true);

        LabelsChooseFiles hintsForChooseFiles = new LabelsChooseFiles(lblPropertyPath, lblSizeProcFile, lblCountStringProcFile,
                lblInputFiles, lblDictionaties, lblUserStat, lblOutDir, lblProtectedWords, lblDeletWords);
        LabelsModules labelsModules = new LabelsModules(lblMdlRemoveEnglish, lblMdlFindEnglish, lblMdlLinks, lblMdlInitials,
                lblMdlTimes, lblMdlDates, lblMdlFractions, lblMdlNumbers, lblMdlMoneys, lblMdlPunctuation, lblMdlAcronyms,
                lblMdlDaysWeek, lblMdlDeleteWords, lblMdlAbbreviations, lblMdlMonths, lblMdlDictionaries);

        btnOK.setOnAction(event -> applySettings());
        btnCancel.setOnAction(event -> closeView());
        btnSectionSettingFiles.setOnAction(event -> choseSectionFiles());
        btnSectionModules.setOnAction(event -> choseSectionModules());

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
                property = new PropertyLoader();
                property.load(propertyPath);
                fillInPropertyPaths();
                fillInModulesSwitch();
            }
        });
    }

    private void readPropertyHistory() {
        if (property.isPropertyFill()){
            fillInPropertyPaths();
            fillInModulesSwitch();
        }
    }

    private void applySettings() {
        overwritePropertyHistory();
        if (isSettingsFill()){
            MainController.btnLaunchActive = true;
        }else{
            MainController.btnLaunchActive = false;
        }
        Stage currentStage = (Stage)btnOK.getScene().getWindow();
        currentStage.close();
    }

    private void choseSectionFiles() {
        pnlChooseFiles.toFront();
        btnSectionSettingFiles.setSelected(true);
        btnSectionModules.setSelected(false);
    }

    private void choseSectionModules(){
        pnlModules.toFront();
        btnSectionModules.setSelected(true);
        btnSectionSettingFiles.setSelected(false);
    }

    private void closeView() {
        Stage currentStage = (Stage)btnCancel.getScene().getWindow();
        txtFieldPropertyPath.setText("");
        txtFieldSizeFile.setText("");
        txtFieldCountString.setText("");
        txtFieldInputFiles.setText("");
        txtFieldDictionaries.setText("");
        txtFieldUserStatistic.setText("");
        txtFieldOutFiles.setText("");
        txtFieldProtectedWords.setText("");
        txtFieldDeleteWords.setText("");
        if (isSettingsFill()){
            MainController.btnLaunchActive = true;
        }else{
            MainController.btnLaunchActive = false;
        }
        currentStage.close();
    }


    private File chooseDirectory() {
        File chosenDirectory;
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Выбор директории");
        chosenDirectory = directoryChooser.showDialog(btnChooseInputFiles.getScene().getWindow());
        return chosenDirectory;
    }

    private File chooseFile() {
        Stage stage = (Stage) btnChooseProperty.getScene().getWindow();
        FileChooser fileChooser = new FileChooser();
        File chosenFile = fileChooser.showOpenDialog(stage);
        return chosenFile;
    }

    private void fillInPropertyPaths() {
        String sizeFileData = property.getOutFileSize() == null ? "" : property.getOutFileSize().toString();
        String countStringData = property.getOutFileCountStrings() == null ? "" : property.getOutFileCountStrings().toString();
        String inputFilesData = property.getInputFilesDirectory() == null ? "" : property.getInputFilesDirectory();
        String dictionariesData = property.getDictionariesDirectory() == null ? "" : property.getDictionariesDirectory();
        String userStatisticData = property.getFilesForStatisticDirectory() == null ? "" : property.getFilesForStatisticDirectory();
        String outDirData =  property.getOutDirectory() == null ? "" : property.getOutDirectory();
        String protectedWordsData = property.getProtectedWordsDir() == null ? "" : property.getProtectedWordsDir();
        String deleteWordsData = property.getWordsToDeleteDir() == null ? "" : property.getWordsToDeleteDir();


        txtFieldSizeFile.setText(sizeFileData);
        txtFieldCountString.setText(countStringData);
        txtFieldInputFiles.setText(inputFilesData);
        txtFieldDictionaries.setText(dictionariesData);
        txtFieldUserStatistic.setText(userStatisticData);
        txtFieldOutFiles.setText(outDirData);
        txtFieldProtectedWords.setText(protectedWordsData);
        txtFieldDeleteWords.setText(deleteWordsData);
    }

    private void fillInModulesSwitch() {
        switchRemoveEnglish.setState(property.isEnableRemoveEnglishTextModule());
        switchFindEnglish.setState(property.isEnableFindEnglishModule());
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

    private void createModuleSwitches() {

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


    private void loadPropertyHistory() {
        if (Files.exists(Paths.get(MainController.PROPERTY_HISTORY_PATH))) {
            property = new PropertyLoader();
            property.load(MainController.PROPERTY_HISTORY_PATH);
            fillInPropertyPaths();
            fillInModulesSwitch();
        }
    }


    private void overwritePropertyHistory() {
        property = new PropertyLoader();
        setPropertyData();
        File propertyHistoryDir = new File(".\\propertyHistory");
        propertyHistoryDir.mkdir();
        property.writeFile(MainController.PROPERTY_HISTORY_PATH);
    }

    public void setPropertyData(){
        String outFileSizeData = txtFieldSizeFile.getText().trim();
        String outFileCountStringsData = txtFieldCountString.getText().trim();
        String inputFilesDirData = txtFieldInputFiles.getText().trim();
        String dictionariesData = txtFieldDictionaries.getText().trim();
        String userStatisticData = txtFieldUserStatistic.getText().trim();
        String outDirData = txtFieldOutFiles.getText().trim();
        String protectedWordDir = txtFieldProtectedWords.getText().trim();
        String wordsToDelete = txtFieldDeleteWords.getText().trim();

        property.setOutFileSize(outFileSizeData.isEmpty() ? null :Integer.parseInt(outFileSizeData));
        property.setOutFileCountStrings(outFileCountStringsData.isEmpty() ? null : Integer.parseInt(outFileCountStringsData));
        property.setInputFilesDirectory(inputFilesDirData.isEmpty() ? null : inputFilesDirData);
        property.setDictionariesDirectory(dictionariesData.isEmpty() ? null : dictionariesData);
        property.setFilesForStatisticDirectory(userStatisticData.isEmpty() ? null : userStatisticData);
        property.setOutDirectory(outDirData.isEmpty() ? null : outDirData);
        property.setProtectedWordsDir(protectedWordDir.isEmpty() ? null : protectedWordDir);
        property.setWordsToDeleteDir(wordsToDelete.isEmpty() ? null : wordsToDelete);

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
    }


    private void createTooltipsForField() {
        Tooltip propertyTooltip = new Tooltip();
        Tooltip inFilesTooltip = new Tooltip();
        Tooltip dictionariesTooltip = new Tooltip();
        Tooltip userStatisticTooltip = new Tooltip();
        Tooltip outDirTooltip = new Tooltip();
        Tooltip protectWordsTooltip = new Tooltip();
        Tooltip deleteWordsTooltip = new Tooltip();

        txtFieldPropertyPath.textProperty().addListener((ov, oldV, newV) -> {
            propertyTooltip.setShowDuration(Duration.minutes(5));
            propertyTooltip.setText(txtFieldPropertyPath.getText());
            txtFieldPropertyPath.setTooltip(propertyTooltip);
        });

        txtFieldInputFiles.textProperty().addListener((ov, oldV, newV) -> {
            inFilesTooltip.setShowDuration(Duration.minutes(5));
            inFilesTooltip.setText(txtFieldInputFiles.getText());
            txtFieldInputFiles.setTooltip(inFilesTooltip);
        });

        txtFieldDictionaries.textProperty().addListener((ov, oldV, newV) -> {
            dictionariesTooltip.setShowDuration(Duration.minutes(5));
            dictionariesTooltip.setText(txtFieldDictionaries.getText());
            txtFieldDictionaries.setTooltip(dictionariesTooltip);
        });

        txtFieldUserStatistic.textProperty().addListener((ov, oldV, newV) -> {
            userStatisticTooltip.setShowDuration(Duration.minutes(5));
            userStatisticTooltip.setText(txtFieldUserStatistic.getText());
            txtFieldUserStatistic.setTooltip(userStatisticTooltip);
        });

        txtFieldOutFiles.textProperty().addListener((ov, oldV, newV) -> {
            outDirTooltip.setShowDuration(Duration.minutes(5));
            outDirTooltip.setText(txtFieldOutFiles.getText());
            txtFieldOutFiles.setTooltip(outDirTooltip);
        });

        txtFieldProtectedWords.textProperty().addListener((ov, oldV, newV) -> {
            protectWordsTooltip.setShowDuration(Duration.minutes(5));
            protectWordsTooltip.setText(txtFieldProtectedWords.getText());
            txtFieldProtectedWords.setTooltip(protectWordsTooltip);
        });

        txtFieldDeleteWords.textProperty().addListener((ov, oldV, newV) -> {
            deleteWordsTooltip.setShowDuration(Duration.minutes(5));
            deleteWordsTooltip.setText(txtFieldDeleteWords.getText());
            txtFieldDeleteWords.setTooltip(deleteWordsTooltip);
        });
    }

    public boolean isSettingsFill(){
        return (!txtFieldSizeFile.getText().isEmpty() && !txtFieldCountString.getText().isEmpty() &&
                !txtFieldInputFiles.getText().isEmpty() && !txtFieldDictionaries.getText().isEmpty() &&
                !txtFieldUserStatistic.getText().isEmpty() && !txtFieldOutFiles.getText().isEmpty() &&
                !txtFieldProtectedWords.getText().isEmpty() && !txtFieldDeleteWords.getText().isEmpty());

    }

    public PropertyLoader getProperty(){
        return property;
    }

    public void setProperty(PropertyLoader property){
        this.property = property;
    }

}
