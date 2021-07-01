package com.Threads;


import com.Controller.SettingsController;
import com.Logic.Properties.PropertyLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class ThreadLaunchButton extends Thread{

    private Button btnLaunch;
    private Label lblEmptyPaths;
    private PropertyLoader property;

    public ThreadLaunchButton(String name, PropertyLoader property, Button btnLaunch, Label lblEmptyPath) {
        super(name);
        this.property = property;
        this.btnLaunch = btnLaunch;
        this.lblEmptyPaths = lblEmptyPath;
    }

    @Override
    public void run() {
        int counter = 0;
        while(!interrupted()){
            if (property.isPropertyFill() && lblEmptyPaths.isVisible()){
                btnLaunch.setDisable(false);//button on
                lblEmptyPaths.setVisible(false);
            }else if (!property.isPropertyFill() && !lblEmptyPaths.isVisible()){
                btnLaunch.setDisable(true);//button off
                lblEmptyPaths.setVisible(true);
            }
//            System.out.println(counter++);
        }
        btnLaunch.setDisable(true);
    }

}
