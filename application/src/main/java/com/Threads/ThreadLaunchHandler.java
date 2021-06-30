package com.Threads;

import com.Logic.Handler.Handler;
import com.Logic.Properties.PropertyLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;

public class ThreadLaunchHandler extends Thread{

    private Handler handler;
    private PropertyLoader property;
    private TextArea logArea;
    private Button btnOpenOutDir;
    private Button btnOpenLog;
    public static Boolean handlerIsWork = true;
    private ProgressBar progressBar;


    public ThreadLaunchHandler(String threadName, PropertyLoader property, TextArea logArea, Button btnOpenOutDir,
                               Button btnOpenLog, ProgressBar progressBar){
        super(threadName);
        this.progressBar = progressBar;
        this.btnOpenLog = btnOpenLog;
        this.btnOpenOutDir = btnOpenOutDir;
        this.logArea = logArea;
        this.property = property;
    }

    @Override
    public void run() {
        handler = new Handler(property, logArea, btnOpenOutDir, btnOpenLog, progressBar);
    }


}
