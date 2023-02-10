package lk.ijse.medical.control.reciption;

import animatefx.animation.ZoomIn;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import lk.ijse.medical.dao.DaoFactory;
import lk.ijse.medical.dao.DaoTypes;
import lk.ijse.medical.servise.ServiseFactory;
import lk.ijse.medical.servise.ServiseTypes;
import lk.ijse.medical.servise.custom.AppoinmentServise;

import java.sql.SQLException;
import java.util.ArrayList;

public class ActiveAppoinmentFormController {

    public AnchorPane pane1;
    public Label lbl1;

    private final AppoinmentServise appoinmentServise= (AppoinmentServise) ServiseFactory.getServiseFactory().getServise(ServiseTypes.APPOINMENT);

    public void initialize(){
        loadcounts();
    }

        private void loadcounts()  {
            ObservableList<String> observableList = FXCollections.observableArrayList();
            int count=0;
            ArrayList<Integer> idList = appoinmentServise.countorder();

            for (int type : idList) {
                count=type;
            }
            System.out.println(observableList);
            lbl1.setText(String.valueOf(count));
            new ZoomIn(lbl1).play();
        }
}
