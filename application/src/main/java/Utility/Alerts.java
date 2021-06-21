package Utility;

import javafx.scene.control.Alert;

public class Alerts {

    public static void propertyPathInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Property.json");
        alert.setHeaderText(null);
        alert.setContentText("Property.json предназначен для ...");
        alert.showAndWait();
    }
}
