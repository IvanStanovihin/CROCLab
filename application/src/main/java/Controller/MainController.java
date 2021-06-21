package Controller;

import java.awt.event.ActionEvent;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import TAaC.Components.SwitchButton;
import TAaC.Logic.Properties.PropertyLoader;
import Utility.Alerts;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

public class MainController {

    private PropertyLoader property;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnMdNumbers;

    @FXML
    private TextField txtFieldInputFiles;

    @FXML
    private Label lblCountStringProcFile;

    @FXML
    private TextField txtFieldPropertyPath;

    @FXML
    private Button btnMdDates;

    @FXML
    private Button btnMdWordsRemove;

    @FXML
    private Button btnOpenOutDir;

    @FXML
    private Pane pnlChooseFiles;

    @FXML
    private TextField txtFieldDictionaries;

    @FXML
    private Button btnChooseProperty;

    @FXML
    private Button btnChooseUserStatistic;

    @FXML
    private Button btnMdFractions;

    @FXML
    private Button btnMdAcronyms;

    @FXML
    private Pane pnlModules;

    @FXML
    private Pane pnlLaunch;

    @FXML
    private Button btnMdWeeks;

    @FXML
    private TextField txtFieldOutFiles;

    @FXML
    private ToggleButton btnSectionModules;

    @FXML
    private Label lblOutDir;

    @FXML
    private StackPane pnlSwitch30;

    @FXML
    private StackPane pnlSwitch31;

    @FXML
    private StackPane pnlSwitch32;

    @FXML
    private StackPane pnlSwitch33;

    @FXML
    private StackPane pnlSwitch34;

    @FXML
    private Label lblProtectedWords;

    @FXML
    private StackPane pnlSwitch35;

    @FXML
    private StackPane pnlSwitch36;

    @FXML
    private StackPane pnlSwitch37;

    @FXML
    private Button btnMdEnglishFind;

    @FXML
    private TextField txtFieldUserStatistic;

    @FXML
    private Label lblDeletWords;

    @FXML
    private Button btnChooseProtectedWords;

    @FXML
    private Button btnMdPunctuation;

    @FXML
    private Button btnChooseDictionaries;

    @FXML
    private TextField txtFieldProtectedWords;

    @FXML
    private Button btnMdInitials;

    @FXML
    private TextField txtFieldDeleteWords;

    @FXML
    private Button btnChooseOutDirectory;

    @FXML
    private Button btnMdLinks;

    @FXML
    private ToggleButton btnSectionLaunch;

    @FXML
    private TextField txtFieldSizeFile;

    @FXML
    private Button btnMdDictionaries;

    @FXML
    private Button btnLaunch;

    @FXML
    private Button btnChooseDeleteWords;

    @FXML
    private TextField txtFieldCountString;

    @FXML
    private Button btnChooseProperty1;

    @FXML
    private ToggleButton btnSectionSettingFiles;

    @FXML
    private Button btnMdMoney;

    @FXML
    private Label lblDictionaties;

    @FXML
    private StackPane pnlSwitch10;

    @FXML
    private StackPane pnlSwitch11;

    @FXML
    private StackPane pnlSwitch12;

    @FXML
    private StackPane pnlSwitch13;

    @FXML
    private StackPane pnlSwitch14;

    @FXML
    private Button btnMdTimes;

    @FXML
    private StackPane pnlSwitch15;

    @FXML
    private StackPane pnlSwitch16;

    @FXML
    private StackPane pnlSwitch17;

    @FXML
    private Button btnMdAbbreviations;

    @FXML
    private Label lblSizeProcFile;

    @FXML
    private Label lblPropertyPath;

    @FXML
    private Label lblInputFiles;

    @FXML
    private Button btnMdEnglishRemove;

    @FXML
    private Button btnChooseInputFiles;

    @FXML
    ScrollPane scrlPnChooseFile;

    @FXML
    private Label lblUserStat;

    @FXML
    private Button btnMdMonths;

    @FXML
    void initialize() {

        scrlPnChooseFile.setFitToWidth(true);
        pnlChooseFiles.toFront();
        btnSectionSettingFiles.setSelected(true);
        btnOpenOutDir.setDisable(true);
        createModuleSwitches();
        settingLabelsUnderline();

        lblPropertyPath.setOnMouseClicked(event -> Alerts.propertyPathInfo());




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
                fillInPropertyPaths();
                createTooltipForPath();
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

    private void settingLabelsUnderline() {
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });

        lblSizeProcFile.setOnMouseEntered(event -> {
            lblSizeProcFile.setStyle("-fx-underline : true");
        });
        lblSizeProcFile.setOnMouseExited(event -> {
            lblSizeProcFile.setStyle("-fx-underline : false");
        });

        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });
        lblPropertyPath.setOnMouseEntered(event -> {
            lblPropertyPath.setStyle("-fx-underline : true");
        });
        lblPropertyPath.setOnMouseExited(event -> {
            lblPropertyPath.setStyle("-fx-underline : false");
        });

    }

    private void createTooltipForPath() {
        Tooltip tooltip = new Tooltip(txtFieldPropertyPath.getText());
        tooltip.setShowDuration(Duration.minutes(5));
        txtFieldPropertyPath.setTooltip(tooltip);
    }

    private void fillInPropertyPaths() {
        String propertyPath = txtFieldPropertyPath.getText().trim();
        //TODO: Make error handling for failed property.json read attempt. Create alerts.
        property = new PropertyLoader(propertyPath);
        txtFieldSizeFile.setText(property.getOutFileSize().toString());
        txtFieldCountString.setText(property.getOutFileCountStrings().toString());
        txtFieldInputFiles.setText(property.getInputFilesDirectory());
        txtFieldDictionaries.setText(property.getDictionariesDirectory());
        txtFieldUserStatistic.setText(property.getFilesForStatisticDirectory());
        txtFieldOutFiles.setText(property.getOutDirectory());
        txtFieldProtectedWords.setText(property.getProtectedWordsDir());
        txtFieldDeleteWords.setText(property.getWordsToDeleteDir());

    }

    private void createModuleSwitches(){

        SwitchButton switchRemoveEnglish = new SwitchButton();
        switchRemoveEnglish.setMaxWidth(20);
        pnlSwitch10.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch10.getChildren().add(switchRemoveEnglish);

        SwitchButton switchDictionaryWords = new SwitchButton();
        switchDictionaryWords.setMaxWidth(20);
        pnlSwitch11.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch11.getChildren().add(switchDictionaryWords);

        SwitchButton switchFindEnglish = new SwitchButton();
        switchFindEnglish.setMaxWidth(20);
        pnlSwitch12.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch12.getChildren().add(switchFindEnglish);

        SwitchButton switchDates = new SwitchButton();
        switchDates.setMaxWidth(20);
        pnlSwitch13.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch13.getChildren().add(switchDates);

        SwitchButton switchTimes = new SwitchButton();
        switchTimes.setMaxWidth(20);
        pnlSwitch14.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch14.getChildren().add(switchTimes);

        SwitchButton switchMoneys = new SwitchButton();
        switchMoneys.setMaxWidth(20);
        pnlSwitch15.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch15.getChildren().add(switchMoneys);

        SwitchButton switchFractions = new SwitchButton();
        switchFractions.setMaxWidth(20);
        pnlSwitch16.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch16.getChildren().add(switchFractions);

        SwitchButton switchNumbers = new SwitchButton();
        switchNumbers.setMaxWidth(20);
        pnlSwitch17.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch17.getChildren().add(switchNumbers);

        SwitchButton switchPunctuation = new SwitchButton();
        switchPunctuation.setMaxWidth(20);
        pnlSwitch30.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch30.getChildren().add(switchPunctuation);

        SwitchButton switchDeleteWords = new SwitchButton();
        switchDeleteWords.setMaxWidth(20);
        pnlSwitch31.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch31.getChildren().add(switchDeleteWords);

        SwitchButton switchDaysOfWeek = new SwitchButton();
        switchDaysOfWeek.setMaxWidth(20);
        pnlSwitch32.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch32.getChildren().add(switchDaysOfWeek);

        SwitchButton switchInitials = new SwitchButton();
        switchInitials.setMaxWidth(20);
        pnlSwitch33.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch33.getChildren().add(switchInitials);

        SwitchButton switchAbbreviations = new SwitchButton();
        switchAbbreviations.setMaxWidth(20);
        pnlSwitch34.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch34.getChildren().add(switchAbbreviations);

        SwitchButton switchMonths = new SwitchButton();
        switchMonths.setMaxWidth(20);
        pnlSwitch35.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch35.getChildren().add(switchMonths);

        SwitchButton switchAcronyms = new SwitchButton();
        switchAcronyms.setMaxWidth(20);
        pnlSwitch36.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch36.getChildren().add(switchAcronyms);

        SwitchButton switchLinks = new SwitchButton();
        switchLinks.setMaxWidth(20);
        pnlSwitch37.setAlignment(Pos.CENTER_LEFT);
        pnlSwitch37.getChildren().add(switchLinks);

    }

    @FXML
    private void handleClick(ActionEvent event){

    }



    private List<File> chooseFiles(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Выбор директории");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Text Files", "*.txt"),
                new FileChooser.ExtensionFilter("JSON", "*.json"),
                new FileChooser.ExtensionFilter("HTML", "*.html"));
        List<File>chosenFiles;
        chosenFiles = fileChooser.showOpenMultipleDialog(btnChooseInputFiles.getScene().getWindow());
        return chosenFiles;
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

    }

    private void openProperty(String propertyPath){
        PropertyLoader property = new PropertyLoader(propertyPath);


    }


}
