package com.Components;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class LabelsChooseFiles {

    private Label lblProperty;
    private Label lblSizeProcessedFile;
    private Label lblCountProcessedString;
    private Label lblInputFiles;
    private Label lblDictionaries;
    private Label lblUserStatistic;
    private Label lblOutDir;
    private Label lblProtectedWords;
    private Label lblDeleteWords;

    public LabelsChooseFiles(Label lblProperty, Label lblSizeProcessedFile, Label lblCountProcessedString, Label lblInputFiles,
                             Label lblDictionaries, Label lblUserStatistic, Label lblOutDir, Label lblProtectedWords,
                             Label lblDeleteWords) {
        this.lblProperty = lblProperty;
        this.lblSizeProcessedFile = lblSizeProcessedFile;
        this.lblCountProcessedString = lblCountProcessedString;
        this.lblInputFiles = lblInputFiles;
        this.lblDictionaries = lblDictionaries;
        this.lblUserStatistic = lblUserStatistic;
        this.lblOutDir = lblOutDir;
        this.lblProtectedWords = lblProtectedWords;
        this.lblDeleteWords = lblDeleteWords;
        fillInHints();
        addUnderline();
    }

    private void fillInHints(){
        Tooltip propertyTooltip = new Tooltip("Файл Property.json - хранит настройку всех путей и модулей,\n" +
                "необходимых для запуска приложения. В этом файле сохраняются\nпараметры предыдущего запуска приложения.");
        propertyTooltip.setShowDuration(Duration.minutes(5));
        lblProperty.setTooltip(propertyTooltip);

        Tooltip szProcFileTooltip = new Tooltip("Число, определяющее размер выходного файла.\nЕсли обработанный файл " +
                "превышает заданный размер,\nто он разбивается на части установленной величины.\nЕдиницы измерения - Мегабайты.");
        szProcFileTooltip.setShowDuration(Duration.minutes(5));
        lblSizeProcessedFile.setTooltip(szProcFileTooltip);

        Tooltip countStringProcFileTooltip = new Tooltip("Число, определяющее количество строк в обработанном файле.");
        countStringProcFileTooltip.setShowDuration(Duration.minutes(5));
        lblCountProcessedString.setTooltip(countStringProcFileTooltip);

        Tooltip inputFilesTooltip = new Tooltip("Путь к директории, в которой находятся файлы,\nпредназначенные для обработки");
        inputFilesTooltip.setShowDuration(Duration.minutes(5));
        lblInputFiles.setTooltip(inputFilesTooltip);

        Tooltip dictionariesTooltip = new Tooltip("Путь к директории, в которой хранятся словари. Словарь - файл,\n" +
                "в который пользователь может добавить сокращение\nи правила его раскрытия(расшифровку). При работе приложение\nбудет искать " +
                "указанные сокращения во входных файлах и заменять,\nв соответствии с указанной пользователем расшифровкой.");
        dictionariesTooltip.setShowDuration(Duration.minutes(5));
        lblDictionaries.setTooltip(dictionariesTooltip);

        Tooltip userStatisticTooltip = new Tooltip("Путь к папке, в которую пользователь помещает файлы\nсо словами," +
                " по которым он хочет получит дополнительную\nстатистику, собранную по обработанным файлам.");
        userStatisticTooltip.setShowDuration(Duration.minutes(5));
        lblUserStatistic.setTooltip(userStatisticTooltip);

        Tooltip outDirectoryTooltip = new Tooltip("Путь к директории, в которой приложение создаст " +
                "папку out\n Out - директория со всеми сгенерированными и обработанными файлами.");
        outDirectoryTooltip.setShowDuration(Duration.minutes(5));
        lblOutDir.setTooltip(outDirectoryTooltip);

        Tooltip protectedWordsTooltip = new Tooltip("В папке, расположенной по этому пути, находятся файлы \n" +
                " со словами, которые в процессе работы приложения\n не будут изменены/ удалены.");
        protectedWordsTooltip.setShowDuration(Duration.minutes(5));
        lblProtectedWords.setTooltip(protectedWordsTooltip);

        Tooltip deleteWordsTooltip = new Tooltip("В эту директорию пользователь помещает файлы со словами,\n " +
                "которые должны быть удалены из входных данных.");
        deleteWordsTooltip.setShowDuration(Duration.minutes(5));
        lblDeleteWords.setTooltip(deleteWordsTooltip);
    }

    private void addUnderline() {
        lblProperty.setOnMouseEntered(event -> {
            lblProperty.setStyle("-fx-underline : true");
        });
        lblProperty.setOnMouseExited(event -> {
            lblProperty.setStyle("-fx-underline : false");
        });

        lblSizeProcessedFile.setOnMouseEntered(event -> {
            lblSizeProcessedFile.setStyle("-fx-underline : true");
        });
        lblSizeProcessedFile.setOnMouseExited(event -> {
            lblSizeProcessedFile.setStyle("-fx-underline : false");
        });

        lblCountProcessedString.setOnMouseEntered(event -> {
            lblCountProcessedString.setStyle("-fx-underline : true");
        });
        lblCountProcessedString.setOnMouseExited(event -> {
            lblCountProcessedString.setStyle("-fx-underline : false");
        });

        lblInputFiles.setOnMouseEntered(event -> {
            lblInputFiles.setStyle("-fx-underline : true");
        });
        lblInputFiles.setOnMouseExited(event -> {
            lblInputFiles.setStyle("-fx-underline : false");
        });

        lblDictionaries.setOnMouseEntered(event -> {
            lblDictionaries.setStyle("-fx-underline : true");
        });
        lblDictionaries.setOnMouseExited(event -> {
            lblDictionaries.setStyle("-fx-underline : false");
        });

        lblUserStatistic.setOnMouseEntered(event -> {
            lblUserStatistic.setStyle("-fx-underline : true");
        });
        lblUserStatistic.setOnMouseExited(event -> {
            lblUserStatistic.setStyle("-fx-underline : false");
        });

        lblOutDir.setOnMouseEntered(event -> {
            lblOutDir.setStyle("-fx-underline : true");
        });
        lblOutDir.setOnMouseExited(event -> {
            lblOutDir.setStyle("-fx-underline : false");
        });

        lblProtectedWords.setOnMouseEntered(event -> {
            lblProtectedWords.setStyle("-fx-underline : true");
        });
        lblProtectedWords.setOnMouseExited(event -> {
            lblProtectedWords.setStyle("-fx-underline : false");
        });

        lblDeleteWords.setOnMouseEntered(event -> {
            lblDeleteWords.setStyle("-fx-underline : true");
        });
        lblDeleteWords.setOnMouseExited(event -> {
            lblDeleteWords.setStyle("-fx-underline : false");
        });

    }
    
}
