package com.Utility;

import javafx.scene.control.Alert;

public class Alerts {

    public static void wrongPropertyPath(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Некорректный property.json");
        alert.setHeaderText(null);
        alert.setContentText("Произошла ошибка при выборе файла Property.json!");
        alert.showAndWait();
    }

    public static void propertyPathInfo(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Property.json");
        alert.setHeaderText(null);
        alert.setContentText("Property.json предназначен для ...");
        alert.showAndWait();
    }

    public static void wrongOutDirPath(){
        System.out.println("error!!!");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText("Ошибка при открытии директории. Проверьте путь к выходной папке в разделе " +
                "настройка.");
        alert.showAndWait();
    }

    public static void wrongLogDirPath(){
        System.out.println("error!!!");
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(null);
        alert.setContentText("Ошибка при открытии директории с отчётом.");
        alert.showAndWait();
    }
}
