package com.Components;

import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.util.Duration;

public class LabelsModules {

    private Label lblRemoveEnglish;
    private Label lblFindEnglish;
    private Label lblLinks;
    private Label lblRemoveInitials;
    private Label lblTimes;
    private Label lblDates;
    private Label lblFractions;
    private Label lblNumbers;
    private Label lblMoney;
    private Label lblPunctuation;
    private Label lblAcronyms;
    private Label lblDaysOfWeek;
    private Label lblDeleteWords;
    private Label lblAbbreviations;
    private Label lblMonths;
    private Label lblDictionaries;


    public LabelsModules(Label lblRemoveEnglish, Label lblFindEnglish, Label lblLinks, Label lblRemoveInitials,
                         Label lblTimes, Label lblDates, Label lblFractions, Label lblNumbers, Label lblMoney,
                         Label lblPunctuation, Label lblAcronyms, Label lblDaysOfWeek, Label lblDeleteWords,
                         Label lblAbbreviations, Label lblMonths, Label lblDictionaries) {
        this.lblRemoveEnglish = lblRemoveEnglish;
        this.lblFindEnglish = lblFindEnglish;
        this.lblLinks = lblLinks;
        this.lblRemoveInitials = lblRemoveInitials;
        this.lblTimes = lblTimes;
        this.lblDates = lblDates;
        this.lblFractions = lblFractions;
        this.lblNumbers = lblNumbers;
        this.lblMoney = lblMoney;
        this.lblPunctuation = lblPunctuation;
        this.lblAcronyms = lblAcronyms;
        this.lblDaysOfWeek = lblDaysOfWeek;
        this.lblDeleteWords = lblDeleteWords;
        this.lblAbbreviations = lblAbbreviations;
        this.lblMonths = lblMonths;
        this.lblDictionaries = lblDictionaries;
        createTooltips();
        createUnderlines();
    }

    private void createTooltips(){
        Tooltip tooltipLblRemoveEnglish = new Tooltip("Отвечает за удаление английского текста из входных\nфайлов." +
                " При отключении модуля – английский текст\nне удаляется в процессе обработки данных.");
        Tooltip tooltipLblFindEnglish = new Tooltip("При включении модуля в папке FilesWithEnglish\nсоздастся файл, " +
                "в который будет помещён весь\nанглийский текст, найденный во входных файлах.");
        Tooltip tooltipLblLinks = new Tooltip("Удаляет ссылки, найденные в тексте. При включении\nмодуля будут " +
                "обработаны ссылки любой длины,\nначинающиеся на: http, https, ftp, ftps.");
        Tooltip tooltipLblRemoveInitials = new Tooltip("Отвечает за удаление инициалов из входных фалов .");
        Tooltip tooltipLblTimes = new Tooltip("Отвечает за работу модуля по обработке времени.\nПри значении true, " +
                "комбинации цифр подходящие под\nшаблоны времени будут раскрываться в текст.");
        Tooltip tooltipLblDates = new Tooltip("Включение обработки дат. При включении, даты представленные в\nчисловом виде, " +
                "будут раскрыты в текст.");
        Tooltip tooltipLblFractions = new Tooltip("Отвечает за обработку десятичных дробей – чисел,\nкоторые " +
                "записаны через запятую.");
        Tooltip tooltipLblNumbers = new Tooltip("Отвечает за раскрытие цифр и чисел в текст. Раскрытие\nпроисходит" +
                " в именительном падеже.");
        Tooltip tooltipLblMoney = new Tooltip("Включение этого модуля добавляет дополнительные правила\nобработки" +
                " денежных сокращений. При включении будут\nобработанны числа, после которых написан один из\nспец. " +
                "символов руб. или ₽");
        Tooltip tooltipLblPunctuation = new Tooltip("Включенный модуль удаляет из входных файлов все знаки\n" +
                "пунктуации и спец. символы, кроме “.” и “-”.");
        Tooltip tooltipLblAcronyms = new Tooltip("Ищет во входных файлах акронимы, и удаляет их.\nУдалённые " +
                "акронимы помещаются в карантинный файл");
        Tooltip tooltipLblDaysOfWeek = new Tooltip("Находит в текстах дни недели, записанные в сокращённой\nформе," +
                " и раскрывает в полное слово.");
        Tooltip tooltipLblDeleteWords = new Tooltip("Отвечает за удаление слов, которые пользователь указал\nв файле," +
                " находящемся в директории WordsToDelete");
        Tooltip tooltipLblAbbreviations = new Tooltip("Модуль находит слова, по написанию похожие на\nсокращения, " +
                "и добавляет их в справочный файл,\nкоторый генерируестя в директории FilesWithAbbreviations.\nНайденные" +
                " слова не удаляются из обрабатываемых файлов.\nПопавшие в справочный файл слова могу не являтся\nаббревиатурами.");
        Tooltip tooltipLblMonths = new Tooltip("Раскрывает месяцы, написанные сокращённо в их\nполную форму.");
        Tooltip tooltipLblDictionaries = new Tooltip("Модуль, отвечающий за раскрытие слов по правилам,\nуказанным пользователем в файлах, находящихся в папке\ndictionaries.");

        tooltipLblRemoveEnglish.setShowDuration(Duration.minutes(5));
        tooltipLblFindEnglish.setShowDuration(Duration.minutes(5));
        tooltipLblLinks.setShowDuration(Duration.minutes(5));
        tooltipLblRemoveInitials.setShowDuration(Duration.minutes(5));
        tooltipLblTimes.setShowDuration(Duration.minutes(5));
        tooltipLblDates.setShowDuration(Duration.minutes(5));
        tooltipLblFractions.setShowDuration(Duration.minutes(5));
        tooltipLblNumbers.setShowDuration(Duration.minutes(5));
        tooltipLblMoney.setShowDuration(Duration.minutes(5));
        tooltipLblPunctuation.setShowDuration(Duration.minutes(5));
        tooltipLblAcronyms.setShowDuration(Duration.minutes(5));
        tooltipLblDaysOfWeek.setShowDuration(Duration.minutes(5));
        tooltipLblDeleteWords.setShowDuration(Duration.minutes(5));
        tooltipLblAbbreviations.setShowDuration(Duration.minutes(5));
        tooltipLblMonths.setShowDuration(Duration.minutes(5));
        tooltipLblDictionaries.setShowDuration(Duration.minutes(5));

        lblRemoveEnglish.setTooltip(tooltipLblRemoveEnglish);
        lblFindEnglish.setTooltip(tooltipLblFindEnglish);
        lblLinks.setTooltip(tooltipLblLinks);
        lblRemoveInitials.setTooltip(tooltipLblRemoveInitials);
        lblTimes.setTooltip(tooltipLblTimes);
        lblDates.setTooltip(tooltipLblDates);
        lblFractions.setTooltip(tooltipLblFractions);
        lblNumbers.setTooltip(tooltipLblNumbers);
        lblMoney.setTooltip(tooltipLblMoney);
        lblPunctuation.setTooltip(tooltipLblPunctuation);
        lblAcronyms.setTooltip(tooltipLblAcronyms);
        lblDaysOfWeek.setTooltip(tooltipLblDaysOfWeek);
        lblDeleteWords.setTooltip(tooltipLblDeleteWords);
        lblAbbreviations.setTooltip(tooltipLblAbbreviations);
        lblMonths.setTooltip(tooltipLblMonths);
        lblDictionaries.setTooltip(tooltipLblDictionaries);
    }

    private void createUnderlines(){
        lblRemoveEnglish.setOnMouseEntered(event -> {
            lblRemoveEnglish.setStyle("-fx-underline : true");
        });
        lblRemoveEnglish.setOnMouseExited(event -> {
            lblRemoveEnglish.setStyle("-fx-underline : false");
        });

        lblFindEnglish.setOnMouseEntered(event -> {
            lblFindEnglish.setStyle("-fx-underline : true");
        });
        lblFindEnglish.setOnMouseExited(event -> {
            lblFindEnglish.setStyle("-fx-underline : false");
        });

        lblLinks.setOnMouseEntered(event -> {
            lblLinks.setStyle("-fx-underline : true");
        });
        lblLinks.setOnMouseExited(event -> {
            lblLinks.setStyle("-fx-underline : false");
        });

        lblRemoveInitials.setOnMouseEntered(event -> {
            lblRemoveInitials.setStyle("-fx-underline : true");
        });
        lblRemoveInitials.setOnMouseExited(event -> {
            lblRemoveInitials.setStyle("-fx-underline : false");
        });

        lblTimes.setOnMouseEntered(event -> {
            lblTimes.setStyle("-fx-underline : true");
        });
        lblTimes.setOnMouseExited(event -> {
            lblTimes.setStyle("-fx-underline : false");
        });

        lblDates.setOnMouseEntered(event -> {
            lblDates.setStyle("-fx-underline : true");
        });
        lblDates.setOnMouseExited(event -> {
            lblDates.setStyle("-fx-underline : false");
        });

        lblFractions.setOnMouseEntered(event -> {
            lblFractions.setStyle("-fx-underline : true");
        });
        lblFractions.setOnMouseExited(event -> {
            lblFractions.setStyle("-fx-underline : false");
        });

        lblMoney.setOnMouseEntered(event -> {
            lblMoney.setStyle("-fx-underline : true");
        });
        lblMoney.setOnMouseExited(event -> {
            lblMoney.setStyle("-fx-underline : false");
        });

        lblPunctuation.setOnMouseEntered(event -> {
            lblPunctuation.setStyle("-fx-underline : true");
        });
        lblPunctuation.setOnMouseExited(event -> {
            lblPunctuation.setStyle("-fx-underline : false");
        });

        lblAcronyms.setOnMouseEntered(event -> {
            lblAcronyms.setStyle("-fx-underline : true");
        });
        lblAcronyms.setOnMouseExited(event -> {
            lblAcronyms.setStyle("-fx-underline : false");
        });

        lblDaysOfWeek.setOnMouseEntered(event -> {
            lblDaysOfWeek.setStyle("-fx-underline : true");
        });
        lblDaysOfWeek.setOnMouseExited(event -> {
            lblDaysOfWeek.setStyle("-fx-underline : false");
        });

        lblDeleteWords.setOnMouseEntered(event -> {
            lblDeleteWords.setStyle("-fx-underline : true");
        });
        lblDeleteWords.setOnMouseExited(event -> {
            lblDeleteWords.setStyle("-fx-underline : false");
        });

        lblAbbreviations.setOnMouseEntered(event -> {
            lblAbbreviations.setStyle("-fx-underline : true");
        });
        lblAbbreviations.setOnMouseExited(event -> {
            lblAbbreviations.setStyle("-fx-underline : false");
        });

        lblMonths.setOnMouseEntered(event -> {
            lblMonths.setStyle("-fx-underline : true");
        });
        lblMonths.setOnMouseExited(event -> {
            lblMonths.setStyle("-fx-underline : false");
        });

        lblDictionaries.setOnMouseEntered(event -> {
            lblDictionaries.setStyle("-fx-underline : true");
        });
        lblDictionaries.setOnMouseExited(event -> {
            lblDictionaries.setStyle("-fx-underline : false");
        });

        lblNumbers.setOnMouseEntered(event -> {
            lblNumbers.setStyle("-fx-underline : true");
        });
        lblNumbers.setOnMouseExited(event -> {
            lblNumbers.setStyle("-fx-underline : false");
        });


    }
}
