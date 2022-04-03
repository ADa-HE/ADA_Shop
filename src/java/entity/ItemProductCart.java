/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.util.Vector;

/**
 *
 * @author ADA
 */
public class ItemProductCart {
    private int quantity;
    private Products product;   

    @Override
    public String toString() {
        return "ItemProductCart{" + "quantity=" + quantity + ", product=" + product + '}';
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Products getProduct() {
        return product;
    }

    public void setProduct(Products product) {
        this.product = product;
    }

    public ItemProductCart(int quantity, Products product) {
        this.quantity = quantity;
        this.product = product;
    }

    public ItemProductCart() {
    }
    public static void main(String[] args) {
        ItemProductCart i = new ItemProductCart();
        Vector<Products> vector = new Vector<>();
        Products p = new Products(0, "2", 0, 0, "4", 0, 0, 0, 0, 0, "image");
        Products p1 = new Products(3, "3", 0, 0, "4", 0, 0, 0, 0, 0, "image");
        i.setProduct(p);
        i.setProduct(p1);
        i.setQuantity(2);
        i.setQuantity(1);
      vector.addElement(p1);
      vector.addElement(p);
        for (Products products : vector) {
            System.out.println(products);
        }
        
    }
}
