package com.Threads;

import javafx.scene.control.Button;

public class ThreadBtnOpenOutDir extends Thread{

    private Button btnOpenOutDir;
    private Boolean processingFiles;

    public ThreadBtnOpenOutDir(String threadName, Boolean processingFiles, Button btnOpenOutDir){
        super(threadName);
        this.btnOpenOutDir = btnOpenOutDir;
        this.processingFiles = processingFiles;
    }

    @Override
    public void run() {
        while(true){
            if (btnOpenOutDir.isDisable() && !processingFiles){
                btnOpenOutDir.setDisable(false);
            }
        }
    }
}
