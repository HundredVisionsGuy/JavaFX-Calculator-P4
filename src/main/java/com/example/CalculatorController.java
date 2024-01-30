package com.example;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;

public class CalculatorController {
    @FXML
    private Spinner<Double> windSpinner;
    SpinnerValueFactory<Double> windValueFactory = //
        new SpinnerValueFactory.DoubleSpinnerValueFactory(0.0, 200.0, 0.0);
    
    @FXML
    private Spinner<Double> tempSpinner;
    SpinnerValueFactory<Double> tempValueFactory = //
        new SpinnerValueFactory.DoubleSpinnerValueFactory(-100.0, 50.0, 0.0);
    
    @FXML
    private Button calculateButton;

    @FXML
    private Button clearButton;

    @FXML
    private Label outputLabel;

    @FXML
    private Label windLabel;

    @FXML
    private Label tempLabel;

    @FXML
    private ComboBox<String> unitsComboBox;

    @FXML
    void initialize() {
        windSpinner.setValueFactory(windValueFactory);
        tempSpinner.setValueFactory(tempValueFactory);

        ObservableList<String> options = 
            FXCollections.observableArrayList(
                "Fahrenheit",
                "Celsius"
            );
        unitsComboBox.getItems().addAll(options);
        unitsComboBox.setPromptText("Fahrenheit");
    }

    @FXML 
    void clearScreen() {
        outputLabel.setText("Hey, you just clicked the clear button.");
    }

    @FXML
    void calculateWindChill() {
        double wind = (double) windSpinner.getValue();
        double temp = (double) tempSpinner.getValue();
        double windchill = getWindchill(wind, temp);
        String output = "With a wind of " + wind + ", and a temperature of " + temp;
        output += " degrees would feel like " + windchill + ".";
        outputLabel.setText(output);
    }

    private double getWindchill(double w, double t) {
        double windchill;
        if (t == 0 && w == 0) {
            windchill = 0.0;
        }
        else {
            windchill = 35.74 + 0.6215 * t - 35.75 * Math.pow(w, 0.16) + 0.4275 * t * Math.pow(w, 0.16);
        }
        windchill = Math.round(windchill);
        return windchill;
    }

    @FXML
    void changeUnits() {
        if (unitsComboBox.getValue() == "Celsius") {
            windLabel.setText("kph");
            tempLabel.setText("°C");
        } else if (unitsComboBox.getValue() == "Fahrenheit") {
            windLabel.setText("mph");
            tempLabel.setText("°F");
        }
    }
}
