package lk.ijse.medical.dao;

import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.SuperEntity;

import java.io.Serializable;
import java.util.ArrayList;

public interface CrudDAO<T extends SuperEntity,ID extends Serializable> extends SuperDAO {

    boolean save(T entity);

    ArrayList<T> getAll();
}
