package lk.ijse.medical.dao.custom.impl;

import lk.ijse.medical.dao.custom.MedicineDAO;
import lk.ijse.medical.db.DBconnection;
import lk.ijse.medical.dto.MedicineDTO;
import lk.ijse.medical.dto.MedicineDetails;
import lk.ijse.medical.entity.Appoinment;
import lk.ijse.medical.entity.Medicine;
import lk.ijse.medical.util.CrudUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MedicineDAOImpl implements MedicineDAO {
    /*public  String genarateMedicineId() {
        try {
          ResultSet  result = CrudUtil.execute("SELECT Medicine_Id   FROM Medicine ORDER BY Medicine_Id  DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }*/

    private static String generateNextOrderId(String currentOrderId) {
        if (currentOrderId != null) {
            String[] split = currentOrderId.split("M0");
            int id = Integer.parseInt(split[1]);
            id += 1;
            return "M0" + id;
        }
        return "O001";

    }
    public  boolean save(Medicine medicine)  {
        try {
            return CrudUtil.execute("INSERT INTO Medicine VALUES(?,?,?,?,?)",
                    medicine.getId(),
                    medicine.getDescription(),
                    medicine.getType(),
                    medicine.getQty(),
                    medicine.getPrice()


            );
        } catch (SQLException e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }




    public  ArrayList<Medicine> getAll() {
        try {
          ResultSet  rst = CrudUtil.execute("SELECT * FROM Medicine");
            ArrayList<Medicine>customerList=new ArrayList<>();

            while(rst.next()){
                Medicine medicine = new Medicine(rst.getString(1),rst.getString(2),
                        rst.getString(3),rst.getInt(4), rst.getDouble(5));
                customerList.add(medicine);
            }
            return customerList;
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }
    public  ArrayList<String> loadMedicineIds() {
        try {
          ResultSet  result = CrudUtil.execute("SELECT Medicine_Id FROM Medicine");
            ArrayList<String> idList = new ArrayList<>();

            while (result.next()) {
                idList.add(result.getString(1));
            }
            return idList;
        } catch (SQLException  e) {
            throw new RuntimeException(e);
        }
    }


    public  boolean updateStock(ArrayList<MedicineDetails> medicineDetailList)  {
        for (MedicineDetails medicineDetails : medicineDetailList) {
            if(!updateStock(medicineDetails)){
                return false;
            }
        }
        return true;
    }
    private static boolean updateStock(MedicineDetails medicineDetails)  {
        try {
            return  CrudUtil.execute("Update Medicine set Medicine_qty=Medicine_qty-? where Medicine_Id=?",
                    medicineDetails.getQty(),medicineDetails.getM_id());

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }


    public  boolean deleteMedicine(String id) {
        try {
            return  CrudUtil.execute("Delete From MedicineDTO where Medicine_Id ='"+id+"'");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String generateNextMeddicineId() {
        try {
            ResultSet  result = CrudUtil.execute("SELECT Medicine_Id   FROM Medicine ORDER BY Medicine_Id  DESC LIMIT 1");
            if (result.next()) {
                return generateNextOrderId(result.getString(1));
            }
            return generateNextOrderId(result.getString(null));
        } catch (SQLException  e) {
            throw new RuntimeException("Failed to save the Delete");
        }
    }

}
