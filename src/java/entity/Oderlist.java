/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.sql.Date;

/**
 *
 * @author ADA
 */
public class Oderlist {
    int OrderID;
    String  CustomerName;
    Date OrderDate;
    Date ShippedDate;
    int status;

    public Oderlist(int OrderID, String CustomerName, Date OrderDate, Date ShippedDate, int status) {
        this.OrderID = OrderID;
        this.CustomerName = CustomerName;
        this.OrderDate = OrderDate;
        this.ShippedDate = ShippedDate;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Oderlist{" + "OrderID=" + OrderID + ", CustomerName=" + CustomerName + ", OrderDate=" + OrderDate + ", ShippedDate=" + ShippedDate + ", status=" + status + '}';
    }

    public int getOrderID() {
        return OrderID;
    }

    public void setOrderID(int OrderID) {
        this.OrderID = OrderID;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public Date getOrderDate() {
        return OrderDate;
    }

    public void setOrderDate(Date OrderDate) {
        this.OrderDate = OrderDate;
    }

    public Date getShippedDate() {
        return ShippedDate;
    }

    public void setShippedDate(Date ShippedDate) {
        this.ShippedDate = ShippedDate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    
    
    public Oderlist() {
    }
     
}
