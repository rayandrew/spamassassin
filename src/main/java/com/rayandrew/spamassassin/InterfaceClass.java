package com.rayandrew.spamassassin;

import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Callback;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.gui.beans.TextViewer;

import java.awt.Desktop;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;

import static com.rayandrew.spamassassin.MainClass.primaryStage;

/**
 * Created by ironlota on 6/15/17.
 */
public class InterfaceClass implements Initializable {
    private ObservableList<SpamResult> data = FXCollections.observableArrayList();

    @FXML
    private TableView tableView;

    @FXML
    private TableColumn noRow;

    @FXML
    private TableColumn text;

    @FXML
    private TableColumn<SpamResult, SpamStatus> result;

    @FXML
    private MenuItem openFile;

    //@FXML
    //private MenuItem saveFile;

    @FXML
    private MenuItem closeWindow;

    @FXML
    private MenuItem aboutWindow;

    @FXML
    private MenuItem helpItem;

    @FXML
    private MenuItem changeLog;

    @FXML
    private TextArea trainData;

    @FXML
    private TextArea classifierData;

    @FXML
    private Hyperlink urlLabel;

    private File dataFile = null;
    private static Instances train = null;
    private static Instances test = null;

    private HostServices hostServices;

    public HostServices getHostServices() {
        return hostServices ;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices ;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableView.setEditable(true);

        /**
         * KEY BINDING
         */
        openFile.setAccelerator(new KeyCodeCombination(KeyCode.O, KeyCombination.SHORTCUT_DOWN));
        //saveFile.setAccelerator(new KeyCodeCombination(KeyCode.S, KeyCombination.SHORTCUT_DOWN));
        closeWindow.setAccelerator(new KeyCodeCombination(KeyCode.F4, KeyCombination.ALT_DOWN));
        helpItem.setAccelerator(new KeyCodeCombination(KeyCode.F11));
        changeLog.setAccelerator(new KeyCodeCombination(KeyCode.F12));
        aboutWindow.setAccelerator(new KeyCodeCombination(KeyCode.F10));


        aboutWindow.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setResizable(true);
            alert.setTitle("About");
            alert.setHeaderText("SpamAssassin v.0.1-beta");
            alert.setContentText("Created by Ray Andrew");
            Hyperlink hyperlink = new Hyperlink("Fork at https://github.com/ironlota/SpamAssassin");
            hyperlink.setOnAction(eventl -> {
                goToGithub();
            });

            Label label = new Label("What can Spam Assassin do?");

            String explanationText = "Spam Assassin is a tool to classify spam messages " +
                    "or not using classifer technique in machine learning." +
                    "\nYou also can add more data to train this machine learning become better " +
                    "\nto classify spam messages or not by re-evaluate the test data, " +
                    "and save it to train data.";

            String specialCredit = "\n\nCredits :\n" +
                    "- Weka 3 - Data Mining Open Source Machine Learning Software (www.cs.waikato.ac.nz/ml/weka/)\n" +
                    "- Machine Learning Recipe with Josh Gordon (https://www.youtube.com/playlist?list=PLOU2XLYxmsIIuiBfYad6rFYQU_jL2ryal)\n" +
                    "- Weka Text Classification for First Time & Beginner Users by Brandon Weinberg (https://www.youtube.com/watch?v=IY29uC4uem8)\n" +
                    "- Text Classification with Weka using a J48 Decision Tree by S0naris (https://www.youtube.com/watch?v=HrixTPMOCD4)\n" +
                    "- JFoenix (www.jfoenix.com)";

            TextArea textArea = new TextArea(explanationText + specialCredit);
            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(hyperlink, 0, 0);
            expContent.add(label, 0, 1);
            expContent.add(textArea, 0, 2);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        });

        changeLog.setOnAction(event -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setResizable(true);
            alert.setTitle("Change Log");
            alert.setHeaderText("SpamAssassin v.0.1-beta");
            alert.setContentText("This project is still in initial version\n");

            Label label = new Label("Change Log");


            StringBuilder str = new StringBuilder();
            try {
                BufferedReader reader = new BufferedReader(new FileReader("changelog.txt"));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    str.append(line + "\n");
                }
                reader.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            TextArea textArea = new TextArea(str.toString());

            textArea.setEditable(false);
            textArea.setWrapText(true);

            textArea.setMaxWidth(Double.MAX_VALUE);
            textArea.setMaxHeight(Double.MAX_VALUE);
            GridPane.setVgrow(textArea, Priority.ALWAYS);
            GridPane.setHgrow(textArea, Priority.ALWAYS);

            GridPane expContent = new GridPane();
            expContent.setMaxWidth(Double.MAX_VALUE);
            expContent.add(label, 0, 0);
            expContent.add(textArea, 0, 1);

            // Set expandable Exception into the dialog pane.
            alert.getDialogPane().setExpandableContent(expContent);

            alert.showAndWait();
        });
    }

    @FXML
    public void openAction(ActionEvent actionEvent) {
        handleOpenClick();
    }

    @FXML
    public void quitAction(ActionEvent actionEvent) {
        System.exit(0);
    }

    private void evaluateData(String filename) {
        try {
            train = TrainingClass.readDataFile("./dataset/train.arff");
            test = TrainingClass.readDataFile(filename);
            train.setClassIndex(train.numAttributes() - 1);
            test.setClassIndex(test.numAttributes() - 1);

            trainData.setText(train.toString());


            FilteredClassifier fc = TrainingClass.trainData(train);

            int count = 0;
            for (int i = 0; i < test.numInstances(); i++) {
                double pred = fc.classifyInstance(test.instance(i));
                String actual = test.classAttribute().value((int) test.instance(i).classValue());
                String predicted = test.classAttribute().value((int) pred);
                data.add(new SpamResult(
                        (int) test.instance(i).value(0),
                        test.get(i).stringValue(test.attribute("text")),
                        predicted)
                );
                /**
                 System.out.print("ID: " + test.instance(i).value(0));
                 System.out.print(", actual: " + actual);
                 System.out.println(", predicted: " + predicted);
                 */
                if (!predicted.equals(actual)) {
                    count++;
                }
            }
            //System.out.println("False prediction count = " + count);
            //int sumData = test.numInstances();
            //double s = (sumData - count) * 100 / sumData;
            //System.out.println("Accuracy = " + String.format("%.05f", s));

            classifierData.setText(fc.toString());

            noRow.setCellValueFactory(new PropertyValueFactory<SpamResult, Integer>("no"));

            text.setCellValueFactory(new PropertyValueFactory<>("text"));

            Callback<TableColumn, TableCell> stringCellFactory =
                    p -> {
                        MyStringTableCell cell = new MyStringTableCell();
                        cell.addEventFilter(MouseEvent.MOUSE_CLICKED, event -> {
                            TableCell c = (TableCell) event.getSource();

                            int index = c.getIndex();
                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                            alert.setResizable(true);
                            alert.setTitle("Text row " + index);
                            alert.setHeaderText("Detailed Text for row " + index);

                            TextArea textArea = new TextArea(data.get(index).getText());

                            textArea.setEditable(false);
                            textArea.setWrapText(true);

                            textArea.setMaxWidth(Double.MAX_VALUE);
                            textArea.setMaxHeight(Double.MAX_VALUE);

                            GridPane.setVgrow(textArea, Priority.ALWAYS);
                            GridPane.setHgrow(textArea, Priority.ALWAYS);

                            GridPane expContent = new GridPane();
                            expContent.setMaxWidth(Double.MAX_VALUE);
                            expContent.add(textArea, 0, 0);

                            alert.getDialogPane().setExpandableContent(expContent);
                            alert.showAndWait();
                        });
                        return cell;
                    };

            text.setCellFactory(stringCellFactory);

            result.setCellValueFactory(new PropertyValueFactory<>("result"));

            ObservableList<SpamStatus> statusList = FXCollections.observableArrayList(//
                    SpamStatus.values());

            result.setCellFactory(ComboBoxTableCell.forTableColumn(statusList));

            result.setOnEditCommit((CellEditEvent<SpamResult, SpamStatus> event) -> {
                TablePosition<SpamResult, SpamStatus> pos = event.getTablePosition();

                SpamStatus newStatus = event.getNewValue();

                int row = pos.getRow();
                SpamResult spam = event.getTableView().getItems().get(row);

                spam.setResult(newStatus.getCode());
            });

            tableView.setItems(data);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    private void handleOpenClick() {

        // A fuller JavaFX app would
        // want to perform a check
        // to make sure the current
        // data has been saved.

        //creating JavaFX file chooser
        FileChooser fc = new FileChooser();
        fc.setTitle("Get Arff");
        fc.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter(
                        "Arff Files",
                        "*.arff"),
                new FileChooser.ExtensionFilter(
                        "Text Files",
                        "*.txt")
        );

        //showing the file chooser
        File phil = fc.showOpenDialog(primaryStage);

        // checking that a file was
        // chosen by the user
        if (phil != null) {

            // opening a scanner
            try (Scanner scan = new Scanner(phil)) {
                // saving the file for
                // use by the saveMI
                dataFile = phil;
                evaluateData(dataFile.getAbsolutePath());
                // enabling saveMI
                //saveMI.setDisable(false);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    class MyStringTableCell extends TableCell<SpamResult, String> {

        @Override
        public void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            setText(empty ? null : getString());
            setGraphic(null);
        }

        private String getString() {
            return getItem() == null ? "" : getItem();
        }
    }

    public void goToGithub() {
        hostServices.showDocument("https://github.com/ironlota/SpamAssassin");
    }
}
