package org.example.Model;

import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;

@Component
@Entity
public class Employee {

    @Id
    private int employeeId;
    private String employeeName;
    private int quantity;
    private int price;

    public Employee(int employeeId, String employeeName, int quantity, int price) {
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.quantity = quantity;
        this.price = price;
    }

    public Employee() {
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", quantity=" + quantity +
                ", price=" + price +
                '}';
    }
}