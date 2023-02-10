package lk.ijse.medical.control.reciption;

import animatefx.animation.SlideInUp;
import animatefx.animation.ZoomIn;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


import javax.naming.Binding;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class SecondDashBoardFormController {


    public AnchorPane context4;
    public Label lblcout;
    public Label lblcount2;
    public LineChart linechart;
    public PieChart piechart;
    public Label lblcount3;
    public AnchorPane pane1;


    public void initialize() throws SQLException, ClassNotFoundException {
       //loadcounts();
      // EmployeeCount();
       inlinechart();
      inpiechart();
      // DoctorCount();
    }
  /*  private void loadcounts() {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            int count=0;
            ArrayList<Integer> idList = AppoinmentModel.countorder();

            for (int type : idList) {
                count=type;
            }
            System.out.println(observableList);
            lblcout.setText(String.valueOf(count));
            new ZoomIn(lblcout).play();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
    /*private void EmployeeCount() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            int count=0;
            ArrayList<Integer> idList = EmployeeModel.countEmployee();

            for (int type : idList) {
                count=type;
            }
            lblcount2.setText(String.valueOf(count));
            new ZoomIn(lblcount2).play();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void DoctorCount() throws SQLException, ClassNotFoundException {
        try {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            int count=0;
            ArrayList<Integer> idList = DoctorModel.countDocors();

            for (int type : idList) {
                count=type;
            }
            lblcount3.setText(String.valueOf(count));
            new ZoomIn(lblcount2).play();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }*/
    
    private void inlinechart(){
        XYChart.Series series=new XYChart.Series();
        series.setName("Appoinments");
        series.getData().add(new XYChart.Data("Monday",9));
        series.getData().add(new XYChart.Data("Tuesday",12));
        series.getData().add(new XYChart.Data("Wednesday",15));
        series.getData().add(new XYChart.Data("Thuesday",19));
        series.getData().add(new XYChart.Data("Friday",20));
        series.getData().add(new XYChart.Data("Saturday",22));
        series.getData().add(new XYChart.Data("Sunday",45));
        linechart.getData().addAll(series);
        linechart.lookup(".chart-plot-background").setStyle("-fx-border-color: transparent;");
        series.getNode().setStyle("-fx-stroke: #FFD6DC");
       new ZoomIn(linechart).play();



    }
   private void inpiechart(){
        ObservableList<PieChart.Data> piechart1=FXCollections.observableArrayList(
                new PieChart.Data("Asprin",50),
                new PieChart.Data("Clopidogrel",70),
                new PieChart.Data("Azithromycin",40),
                new PieChart.Data("Betonovate",80)
                );
        piechart1.forEach(data ->
                data.nameProperty().bind(
                        Bindings.concat(
                                data.getName(),"stock",data.pieValueProperty()
                        )
                )
        );
        piechart.getData().addAll(piechart1);
        new ZoomIn(piechart).play();
    }

    public void AppoinDetailOnAction(ActionEvent actionEvent) {
    }

    public void Active1OnAction(ActionEvent actionEvent) throws IOException {
        Parent parent= FXMLLoader.load(getClass().getResource("/lk/ijse/medical/view/ActiveAppoinmentForm.fxml"));
        pane1.getChildren().clear();
        pane1.getChildren().add(parent);
        new ZoomIn(parent).play();
        pane1.setVisible(true);

    }
    private void setUi(String ui) throws IOException {
        Stage stage = (Stage) pane1.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/"+ui+".fxml"))));

    }
}
