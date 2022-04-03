/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import entity.Account;
import entity.Customers;
import entity.Employees;
import java.util.Vector;

/**
 *
 * @author ADA
 */
public class DAOlogin  extends ConnectDB{
    public Vector<Account> listAllAccount() {
        DAOCustomers dao= new DAOCustomers();
        DAOEmployees dao1= new DAOEmployees();
        Vector<Customers> vectorcus = dao.listAllCustommer("select * from Customers");
        Vector<Employees> vectoremp = dao1.listAllEmployees("select * from Employees ");
        Vector<Account> vector = new Vector<>();
        for (Customers vectorcu : vectorcus) {
            Account e= new Account(vectorcu.getUser(), vectorcu.getPass(), 0);
            if(vectorcu.getUser()!=null)
                vector.add(e);
            }
        for (Employees emp : vectoremp) {
            Account e= new Account(emp.getUser(), emp.getPass(), 1);
            if(emp.getUser()!=null)
                vector.add(e);
            }
        return vector;
        }
       public static void main(String[] args) {
        DAOlogin dao = new DAOlogin();
        Vector<Account> vector=dao.listAllAccount();
           for (Account account : vector) {
               System.out.println(account);
           }
    }
    }
 

