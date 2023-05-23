package com.example.test;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class HelloController implements Initializable {
    public void initialize(URL url, ResourceBundle resourceBundle) {
        name.setCellValueFactory(new PropertyValueFactory<Pays, String>("name"));
        File file = new File("students.txt");

        List<List<String>> csv = getCsv();
        for (int i = 1; i < csv.size(); i++) {
            Pays pays = new Pays(csv.get(i).get(3));
            tableau.getItems().add(pays);
        }
    }

        public List<List<String>> getCsv() {
            List<List<String>> records = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader("pays.csv"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(",");
                    records.add(Arrays.asList(values));
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            return records;
        }

        @FXML
        private TableView tableau;

        @FXML
        private TableColumn name;
}
