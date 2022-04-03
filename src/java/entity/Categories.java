package entity;

/**
 *
 * @author MT Bac Ninh
 */
public class Categories {

    private int CategoryID;//auto number
    private String CategoryName,
            Description, Picture;

    public Categories(String CategoryName, String Description) {
        this.CategoryName = CategoryName;
        this.Description = Description;
    }

    public Categories(String CategoryName, String Description, String Picture) {
        this.CategoryName = CategoryName;
        this.Description = Description;
        this.Picture = Picture;
    }
    
    public Categories(int CategoryID, String CategoryName, String Description) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
    }
    
    public Categories() {
    }
    
    public Categories(int CategoryID, String CategoryName, String Description, String Picture) {
        this.CategoryID = CategoryID;
        this.CategoryName = CategoryName;
        this.Description = Description;
        this.Picture = Picture;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int CategoryID) {
        this.CategoryID = CategoryID;
    }

    public String getCategoryName() {
        return CategoryName;
    }

    public void setCategoryName(String CategoryName) {
        this.CategoryName = CategoryName;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String Description) {
        this.Description = Description;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String Picture) {
        this.Picture = Picture;
    }

    @Override
    public String toString() {
        return "Categories{" + "CategoryID=" + CategoryID + ", CategoryName=" + CategoryName + ", Description=" + Description + ", Picture=" + Picture + '}';
    }
    
    
}
