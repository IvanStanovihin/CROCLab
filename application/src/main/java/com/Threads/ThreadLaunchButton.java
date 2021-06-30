package com.Threads;


import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class ThreadLaunchButton extends Thread{

    private Button launchButton;
    private Label lblEmptyPaths;
    private TextField txtFieldPropertyPath;
    private TextField txtFieldSizeFile;
    private TextField txtFieldCountString;
    private TextField txtFieldInputFiles;
    private TextField txtFieldUserStatistic;
    private TextField txtFieldDictionaries;
    private TextField txtFieldOutFiles;
    private TextField txtFieldProtectedWords;
    private TextField txtFieldDeleteWords;

    public ThreadLaunchButton(String name, Button launchButton, Label lblEmptyPaths, TextField txtFieldPropertyPath, TextField txtFieldSizeFile,
                              TextField txtFieldCountString, TextField txtFieldInputFiles, TextField txtFieldUserStatistic,
                              TextField txtFieldDictionaries, TextField txtFieldOutFiles, TextField txtFieldProtectedWords,
                              TextField txtFieldDeleteWords) {
        super(name);
        this.lblEmptyPaths = lblEmptyPaths;
        this.launchButton = launchButton;
        this.txtFieldPropertyPath = txtFieldPropertyPath;
        this.txtFieldSizeFile = txtFieldSizeFile;
        this.txtFieldCountString = txtFieldCountString;
        this.txtFieldInputFiles = txtFieldInputFiles;
        this.txtFieldUserStatistic = txtFieldUserStatistic;
        this.txtFieldDictionaries = txtFieldDictionaries;
        this.txtFieldOutFiles = txtFieldOutFiles;
        this.txtFieldProtectedWords = txtFieldProtectedWords;
        this.txtFieldDeleteWords = txtFieldDeleteWords;

    }

    @Override
    public void run() {
        int counter = 0;
        while(!interrupted()){
            if (areFieldsFilled() && launchButton.isDisable()){
                launchButton.setDisable(false);
                lblEmptyPaths.setVisible(false);
            }else if (!areFieldsFilled() && !launchButton.isDisable()){
                launchButton.setDisable(true);
                lblEmptyPaths.setVisible(true);
            }
//            System.out.println(counter++);
        }
        launchButton.setDisable(true);
    }


    private boolean areFieldsFilled(){
        boolean result = (!txtFieldSizeFile.getText().isEmpty() &&
                !txtFieldCountString.getText().isEmpty() && !txtFieldInputFiles.getText().isEmpty() &&
                !txtFieldDictionaries.getText().isEmpty() && !txtFieldUserStatistic.getText().isEmpty() &&
                !txtFieldOutFiles.getText().isEmpty() && !txtFieldProtectedWords.getText().isEmpty() &&
                !txtFieldDeleteWords.getText().isEmpty());
        return result;
    }
}
