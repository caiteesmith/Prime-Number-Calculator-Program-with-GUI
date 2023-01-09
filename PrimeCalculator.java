/* Program Information:
Author: Caitlyn Smith
Course: CISS 111-300
Email: c-smith54@hvcc.edu

Program Objective:
Create a program and JavaFX GUI for a Prime Number Calculator

Program Description:
This program consists of a JavaFX GUI which presents to the user
an opportunity to input a number. The user then finds out if that number
is prime or not prime, and/or what the factors of that number are.
It also provides a button for the user to quit the program.
 */

package com.example.smithcaitlyn_program07;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;

public class PrimeCalculator extends Application {
    //Accessible TextField and Labels
    private TextField inputField;
    private TextArea outputField;
    private RadioButton numberRadio;
    private RadioButton factorRadio;

    @Override
    public void start(Stage stage) {
        //Calculator label, input, output
        Label inputLabel = new Label("Number: ");
        Label outputLabel = new Label("Output: ");
        inputField = new TextField();
        outputField = new TextArea();
        outputField.setWrapText(true);

        //Radio Buttons and Toggle Group
        numberRadio = new RadioButton("Prime Number?");
        factorRadio = new RadioButton("Prime Factors?");
        ToggleGroup radioGroup = new ToggleGroup();
        numberRadio.setToggleGroup(radioGroup);
        factorRadio.setToggleGroup(radioGroup);

        //Calculate button
        Button calculate = new Button("Calculate");
        calculate.setOnAction(new CalcButtonHandler());

        //Cancel button
        Button cancel = new Button("Cancel");

        //Handler for Cancel button – terminate program
        cancel.setOnAction(event -> stage.close());

        //HBoxes and VBoxes
        HBox inputHbox = new HBox(5,inputLabel,inputField);
        VBox radioVbox = new VBox(5,numberRadio,factorRadio);
        VBox outputVbox = new VBox(5,outputLabel,outputField);
        HBox buttonHbox = new HBox(10,calculate,cancel);
        VBox vbox = new VBox(10,inputHbox,radioVbox,outputVbox,buttonHbox);

        //Setting scene, size, alignment, padding
        Scene scene = new Scene(vbox, 350, 250); //width,height
        vbox.setAlignment(Pos.TOP_LEFT);
        inputHbox.setAlignment(Pos.TOP_LEFT);
        radioVbox.setAlignment(Pos.CENTER_LEFT);
        outputVbox.setAlignment(Pos.BOTTOM_LEFT);
        buttonHbox.setAlignment(Pos.BOTTOM_CENTER);
        vbox.setPadding(new Insets(25));

        //Scene set and call
        stage.setTitle("Prime Number Calculator");
        stage.setScene(scene);
        stage.show();
    }

    //Handler for Calculate button
    class CalcButtonHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent actionEvent) {
            //Get user input
            int number = Integer.parseInt(inputField.getText());
            int prime = 0;

            //Check if radio button is selected
            if (!numberRadio.isSelected() && !factorRadio.isSelected()) {
                outputField.setText("Please select Prime Number option or Prime Factor option.");
            }

            //Check for and print prime, composite, or invalid number
            for (int n = 1; n <= number; n++) {
                if (number % n == 0) {
                    prime++;
                }
            }

            if (numberRadio.isSelected()) {
                if (number < 0) {
                    inputField.setText("Number must be >= 0");
                    outputField.setText("Please enter a number greater than or equal to 0.");
                } else if (prime == 2) {
                    outputField.setText(number + " is prime.");
                } else {
                    outputField.setText(number + " is not prime.");
                }
            }

            //Check for and store prime factors
            ArrayList<Integer> primeFactors = new ArrayList<>();
            for (int n = 2; n <= number; n++) {
                while (number % n == 0) {
                    primeFactors.add(n);
                    number /= n;
                }
            }

            //Print prime factors if selected
            if (factorRadio.isSelected()) {
                if (number < 0 || primeFactors.isEmpty()) {
                    outputField.setText("No factors.");
                } else {
                    outputField.setText("Factors of " + Integer.parseInt(inputField.getText()) + ": " + primeFactors);
                }
            }
        }
    }

    //Call FX event
    public static void main(String[] args) {
        launch();
    }
}