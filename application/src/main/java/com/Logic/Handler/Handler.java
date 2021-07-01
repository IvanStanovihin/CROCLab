package com.Logic.Handler;

import com.Logic.Dictionary.Dictionaries;
import com.Logic.ProcessingServices.*;
import com.Logic.ProcessingServices.DateServices.DateHandler;
import com.Logic.ProcessingServices.LinksService.LinkService;
import com.Logic.ProtectWords.ProtectedWordsStorage;
import com.Logic.InputFile.InputFilesLoader;
import com.Logic.InputFile.InputFile;
import com.Logic.NumberService.NumberService;

import com.Logic.Properties.PropertyLoader;
import com.Logic.Quarantine.QuarantineCreator;
import com.Logic.ReplacementFile.CreatorReplacementFile;
import com.Logic.ReplacingWords.ReplacerSingleWords;
import com.Logic.ReplacingWords.ReplacerSpaceWords;
import com.Logic.ReportLog.LogOperation;
import com.Logic.ReportLog.ReportLog;
import com.Logic.Statistic.Statistic;
import com.Logic.WordsToDelete.WordsRemover;
import com.Threads.ThreadLaunchHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

/**The class in which all file processing modules are called.*/
public class Handler{

    public static Boolean processingFiles;

    /**List of files that are specified in property.json*/
    private ArrayList<InputFile> inputFiles;
    /**An object that loads and stores files with dictionary words.*/
    private static Dictionaries dictionaries;
    /**The object that stores the json file with the paths to the configuration files*/
    private static PropertyLoader property;
    /**The object stores words that should not be processed*/
    private ProtectedWordsStorage protectedWordsStorage;
    /**Stores the running time of each module and writes logs to the console*/
    public static ReportLog reportLog;

    public static volatile TextArea logArea;
    private Button btnOpenOutDir;

    private ProgressBar progressBar;


    /**
     **
     * @param property - The path to the property.json file. This file stores the file paths for running the program.
     */
    public Handler(PropertyLoader property, TextArea logArea, Button btnOpenOutDir, Button btnOpenLog, ProgressBar progressBar) {
        Handler.processingFiles = processingFiles;
        Handler.property = property;
        Handler.logArea = logArea;
        this.progressBar = progressBar;
        protectedWordsStorage = new ProtectedWordsStorage(property);
        handle();
        createOutputFiles();

        btnOpenOutDir.setDisable(false);
        btnOpenLog.setDisable(false);
        progressBar.setProgress(1);
        logArea.appendText("Обработка файлов завершена!");
        ThreadLaunchHandler.handlerIsWork = false;
    }





    private void handle() {
        Cleaner.deleteOldOutDirectory(property);
        inputFiles = InputFilesLoader.loadInputFiles(property.getInputFilesDirectory());
        Statistic.generateRowFilesStatistic(inputFiles, property);
        reportLog = new ReportLog(inputFiles.size(), progressBar);
        System.out.println(Calendar.getInstance().getTime().toString());

        //Обработка мобильных номеров телефонов
        PhoneNumberService.handle(inputFiles);
        //Обработка времени
        TimeService.handle(inputFiles);
        //Обработка дат
        DateHandler.processDate(inputFiles);
        //Обработка денежных сумм
        MoneyService.processMoney(inputFiles);
        //Обработка дробей
        FractionService.handle(inputFiles);
        //Удаляем ссылки из предложений
        LinkService.handle(inputFiles);
        //Удаление слов, которые пользователь добавил в файл
        WordsRemover.removeWords(property, inputFiles);
        //Раскрываем числа в текст.
        NumberService.handleNumbers(inputFiles);

        dictionaries = new Dictionaries(property.getDictionariesDirectory());
        //Замена слов которые содержат пробелы(# в. ч. -> войсковая часть)
        ReplacerSpaceWords.handleWhitespaceWords(dictionaries.getDictionaryWhitespaceWords(), inputFiles);
        System.gc();
        //Замена слов которые не содержат пробелы(# гор. -> город)
        ReplacerSingleWords.handleSingleWords(dictionaries.getDictionarySingleWords(), inputFiles);
        dictionaries = null;
        System.gc();
        //Обработка знаков препинания и спец. символов.
        PunctuationMarkService.handle(inputFiles);
        //Обработка дней недели
        DaysOfWeekHandler.handleDaysOfWeek(inputFiles);
        System.gc();
        //Удаление инициалов
        InitialsRemover.removeInitials(inputFiles);
        //Поиск сокращений( вида - гор./м./г./...)
        AbbreviationFinder.processAbbreviations(inputFiles);
        //Предложения содержащие CamelCase отправляются в карантин.
        CamelCaseRemover.removeCamelCase(inputFiles);
        //Обработка месяцев записанных в сокращенном виде(янв, фев, ...)
        MonthHandler.processMonths(inputFiles);
        //Разбиваем файлы на предложения.
        SentenceSeparator.splitOnSentences(inputFiles);
        System.gc();
        //Удаляем лишние пробелы из предложения.
        WhitespaceService.removeExtraWhitespace(inputFiles);
        //Находим все предложения содержащие английский текст
        EnglishTextFinder.findEnglishText(inputFiles);
        //Отправляем в карантин предложения содержащие английские буквы.
        EnglishTextRemover.removeEnglishText(inputFiles);

        //Отправляем акронимы в карантин
        AcronymService.acronymsInQuarantine(inputFiles);


    }



    //Создаёт выходной файл.
    public void createOutputFiles() {
        String processedFilesDir = property.getOutDirectory() + "/Processed";
        reportLog.create(property);
        try {
            Files.createDirectories(Paths.get(processedFilesDir));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        QuarantineCreator.createQuarantine(property.getOutDirectory(), inputFiles, processedFilesDir);
        CreatorReplacementFile.createReplacementFile(property.getOutDirectory(), inputFiles);
        Statistic statistic = new Statistic(property, inputFiles);
        statistic.createStatisticFiles();
        for (InputFile inputFile : inputFiles) {
            Handler.reportLog.startCurrentOperation(LogOperation.CREATE_PROCESSED_FILES, inputFile.getFileName());
            inputFile.createOutputFile(processedFilesDir, property);
            Handler.reportLog.endOperation();
        }
        statistic.printQuarantineSentencesInfo();
    }

    public static PropertyLoader getProperty() {
        return property;
    }

    public void cleanModel(){
        InputFilesLoader.inputFiles = new ArrayList<>();
        QuarantineCreator.countQuarantineSentences = 0;
    }
}
