package lk.ijse.medical.servise.util;

import lk.ijse.medical.dto.*;
import lk.ijse.medical.entity.*;

public class Conveter {
    public static AppoinmentDTO toAppoinmentDTO(Appoinment appoinment){
        return new AppoinmentDTO(
                appoinment.getId(),
                appoinment.getType(),
                appoinment.getDate(),
                appoinment.getTime(),
                appoinment.getPatientname(),
                appoinment.getAddress(),
                appoinment.getTel(),
                appoinment.getDoctorname(),
                appoinment.getTotal()
        );
    }
    public static Appoinment toAppoinment(AppoinmentDTO appoinmentDTO){
        return  new Appoinment(
                appoinmentDTO.getId(),
                appoinmentDTO.getType(),
                appoinmentDTO.getDate(),
                appoinmentDTO.getTime(),
                appoinmentDTO.getPatientname(),
                appoinmentDTO.getAddress(),
                appoinmentDTO.getTel(),
                appoinmentDTO.getDoctorname(),
                appoinmentDTO.getPrice()

        );
    }
    public static DoctorDTO todoctorDTO(Doctor doctor){
        return new DoctorDTO(
             doctor.getId(),
             doctor.getName(),
             doctor.getType(),
             doctor.getAlive(),
             doctor.getContact(),
             doctor.getTime()
        );
    }
    public static Doctor toDoctor(DoctorDTO doctorDTO){
        return new Doctor(
                doctorDTO.getId(),
                doctorDTO.getName(),
                doctorDTO.getType(),
                doctorDTO.getAlive(),
                doctorDTO.getContact(),
                doctorDTO.getTime()
        );
    }
    public static EmployeeDTO toemployeeDTO(Employee employee){
        return new EmployeeDTO(
             employee.getId(),
             employee.getName(),
             employee.getDob(),
             employee.getExp(),
             employee.getContact(),
             employee.getNic(),
             employee.getType()
        );
    }
    public static Employee toEmployee(EmployeeDTO employeeDTO){
        return new Employee(
                employeeDTO.getId(),
                employeeDTO.getName(),
                employeeDTO.getDob(),
                employeeDTO.getExp(),
                employeeDTO.getContact(),
                employeeDTO.getNic(),
                employeeDTO.getType()
        );
    }
    public static MedicineDTO tomedicineDTO(Medicine medicine){
        return new MedicineDTO(
           medicine.getId(),
           medicine.getDescription(),
           medicine.getType(),
           medicine.getQty(),
           medicine.getPrice()
        );
    }
    public static Medicine toMedicine(MedicineDTO medicineDTO){
        return new Medicine(
                medicineDTO.getId(),
                medicineDTO.getDescription(),
                medicineDTO.getType(),
                medicineDTO.getQty(),
                medicineDTO.getPrice()
        );
    }
   public static PaymentDTO toPaymentDTO(PaymentDTO payment){
        return new PaymentDTO(
              payment.getPl_id(),
              payment.getA_id(),
              payment.getDate(),
              payment.getP_name(),
              payment.getD_name(),
              payment.getM_Total(),
              payment.getA_Total(),
              payment.getF_Total()
        );
   }
   public static Payment topayment(PaymentDTO paymentDTO){
       return new Payment(
               paymentDTO.getPl_id(),
               paymentDTO.getA_id(),
               paymentDTO.getDate(),
               paymentDTO.getP_name(),
               paymentDTO.getD_name(),
               paymentDTO.getM_Total(),
               paymentDTO.getA_Total(),
               paymentDTO.getF_Total()
       );
   }

   public static OrderDTO toOrderDTO(Order order){
        return new OrderDTO(
             order.getO_Id(),
             order.getDate(),
             order.getAppoinment_id(),
             order.getTotal()
        );
   }
   public static Order toOrder(OrderDTO orderDTO){
        return new Order(
                orderDTO.getO_Id(),
                orderDTO.getDate(),
                orderDTO.getAppoinment_id(),
                orderDTO.getTotal()
        );
   }

}
