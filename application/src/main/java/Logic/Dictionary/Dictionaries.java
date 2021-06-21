package TAaC.Logic.Dictionary;

import TAaC.Logic.Handler.Handler;
import TAaC.Logic.ReportLog.*;
import com.google.gson.Gson;


import java.io.*;
import java.util.ArrayList;

/**
 * Loads all kinds of dictionaries and stores them
 */
public class Dictionaries {

    /**List of dictionaries in json format*/
    private ArrayList<JsonDictionary>jsonDictionaries = new ArrayList<>();
    /**A dictionary that contains single dictionary words*/
    private DictionarySingleWords dictionarySingleWords;
    /**A dictionary that contains dictionary words with whitespace*/
    private DictionaryWhitespaceWords dictionaryWhitespaceWords;

    public Dictionaries(String dictionariesDirectoryPath){
        Handler.reportLog.startCurrentOperation(LogOperation.DICTIONARIES_LOAD);
        loadDictionaries(dictionariesDirectoryPath);
        separateDictionary();
    }

    /**Loading dictionaries from a directory*/
    private void loadDictionaries(String dictionariesDirectoryPath){
        File currentFolder = new File(dictionariesDirectoryPath);
        for (File file : currentFolder.listFiles()){
            if (file.isDirectory()){
             loadDictionaries(file.getPath());
            }else {
                readDictionary(file.getPath());
            }
        }
    }

    /**Reading dictionary files*/
    private void readDictionary(String dictionaryPath){
        StringBuilder fileData = new StringBuilder();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(dictionaryPath), "UTF-8"))){
            String readLine = "";
            while((readLine = br.readLine()) != null){
                fileData.append(readLine);
            }
        }catch(IOException ex){
            ex.printStackTrace();
        }
        Gson gson = new Gson();
        jsonDictionaries.add(gson.fromJson(fileData.toString(), JsonDictionary.class));
    }

    /**Separation of dictionary words. Words containing spaces and single dictionary words*/
    private void separateDictionary(){
        ArrayList<WordReplacements>whitespaceDictionaryWords = new ArrayList<>();
        ArrayList<WordReplacements>singleDictionaryWords = new ArrayList<>();
        for (JsonDictionary jsonDictionary : jsonDictionaries){
            ArrayList<WordReplacements>replacementsFromDictionary = jsonDictionary.getDictionaryWords();
            for (WordReplacements wordReplacement : replacementsFromDictionary){
                String unreadableWord = wordReplacement.getWord();
                if (unreadableWord.contains(" ")){
                    whitespaceDictionaryWords.add(wordReplacement);
                }else{
                    singleDictionaryWords.add(wordReplacement);
                }
            }
        }
        dictionarySingleWords = new DictionarySingleWords(singleDictionaryWords);
        dictionaryWhitespaceWords = new DictionaryWhitespaceWords(whitespaceDictionaryWords);
    }

    public DictionarySingleWords getDictionarySingleWords() {
        return dictionarySingleWords;
    }

    public DictionaryWhitespaceWords getDictionaryWhitespaceWords() {
        return dictionaryWhitespaceWords;
    }
}
