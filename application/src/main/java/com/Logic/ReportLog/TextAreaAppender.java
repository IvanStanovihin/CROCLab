package com.Logic.ReportLog;

import javafx.scene.control.TextArea;
import org.apache.log4j.AppenderSkeleton;
import org.apache.log4j.spi.LoggingEvent;

public class TextAreaAppender extends AppenderSkeleton {

    TextArea logArea;

    public TextAreaAppender(TextArea logArea){
        this.logArea = logArea;
    }

    @Override
    protected void append(LoggingEvent loggingEvent) {
        logArea.appendText(loggingEvent.getMessage().toString() + "\n");
    }

    @Override
    public void close() {

    }

    @Override
    public boolean requiresLayout() {
        return false;
    }
}
