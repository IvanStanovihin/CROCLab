package TAaC.Logic.Handler;

import TAaC.Logic.Dictionary.Dictionaries;
import TAaC.Logic.ProcessingServices.DateServices.DateHandler;
import TAaC.Logic.ProcessingServices.LinksService.LinkService;
import TAaC.Logic.ProtectWords.ProtectedWordsStorage;
import TAaC.Logic.InputFile.InputFilesLoader;
import TAaC.Logic.InputFile.InputFile;
import TAaC.Logic.NumberService.NumberService;
import TAaC.Logic.ProcessingServices.*;
import TAaC.Logic.Properties.PropertyLoader;
import TAaC.Logic.Quarantine.QuarantineCreator;
import TAaC.Logic.ReplacementFile.CreatorReplacementFile;
import TAaC.Logic.ReplacingWords.ReplacerSingleWords;
import TAaC.Logic.ReplacingWords.ReplacerSpaceWords;
import TAaC.Logic.ReportLog.*;
import TAaC.Logic.Statistic.*;
import TAaC.Logic.WordsToDelete.WordsRemover;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;

/**The class in which all file processing modules are called.*/
public class Handler {

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

    /**
     **
     * @param propertyFile - The path to the property.json file. This file stores the file paths for running the program.
     */
    public Handler(String propertyFile) {
        property = new PropertyLoader(propertyFile);
        protectedWordsStorage = new ProtectedWordsStorage(property);
        handle();
    }


    private void handle() {
        Cleaner.deleteOldOutDirectory(property);
        inputFiles = InputFilesLoader.loadInputFiles(property.getInputFilesDirectory());
        Statistic.generateRowFilesStatistic(inputFiles, property);
        reportLog = new ReportLog(inputFiles.size());
        System.out.println(Calendar.getInstance().getTime().toString());
        dictionaries = new Dictionaries(property.getDictionariesDirectory());
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
}
