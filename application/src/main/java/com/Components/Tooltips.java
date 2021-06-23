package com.Components;

import com.Controller.MainController;
import javafx.scene.control.Tooltip;

public class Tooltips {

    private MainController controller;
    private Tooltip propertyTooltip;
    private Tooltip inFilesTooltip;
    private Tooltip dictionariesTooltip;
    private Tooltip userStatisticTooltip;
    private Tooltip outDirTooltip;
    private Tooltip protectWordsTooltip;
    private Tooltip deleteWordsTooltip;

    public Tooltips(MainController controller){
        propertyTooltip = new Tooltip();
        inFilesTooltip = new Tooltip();
        dictionariesTooltip = new Tooltip();
        userStatisticTooltip = new Tooltip();
        outDirTooltip = new Tooltip();
        protectWordsTooltip = new Tooltip();
        deleteWordsTooltip = new Tooltip();
    }

    public void updatePropertyTooltip() {
        propertyTooltip.setText(controller.getTxtFieldPropertyPath().getText());
    }

    public void updateInFilesTooltip() {
        inFilesTooltip.setText(controller.getTxtFieldInputFiles().getText());
    }

    public void updateDictionariesTooltip() {
        dictionariesTooltip.setText(controller.getTxtFieldDictionaries().getText());
    }

    public void updateUserStatisticTooltip() {
       userStatisticTooltip.setText(controller.getTxtFieldUserStatistic().getText());

    }

    public void updateOutDirTooltip() {
        outDirTooltip.setText(controller.getTxtFieldOutFiles().getText());
    }

    public void updateProtectWordsTooltip() {
        protectWordsTooltip.setText(controller.getTxtFieldProtectedWords().getText());

    }

    public Tooltip updateDeleteWordsTooltip() {
        deleteWordsTooltip.setText(controller.getTxtFieldDeleteWords().getText());
        return deleteWordsTooltip;
    }
}
