package com.example.ep.Model;

public class Products {

    private String pname, description, Conatct, image, category, pid, date, time;

    public Products(){


    }

    public Products(String pname,  String description, String Conatct, String image, String category, String pid, String date, String time ) {
        this.pname = pname;
        this.description = description;
        this.Conatct = Conatct;
        this.image = image;
        this.category = category;
        this.pid = pid;
        this.date = date;
        this.time = time;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getConatct() {
        return Conatct;
    }

    public void setConatct(String conatct) {
        Conatct = conatct;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }
}
