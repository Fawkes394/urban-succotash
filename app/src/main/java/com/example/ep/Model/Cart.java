package com.example.ep.Model;

public class Cart {

    private String pid, pname, Contact;

    public Cart() {


    }

    public Cart(String pid, String pname, String contact) {
        this.pid = pid;
        this.pname = pname;
        Contact = contact;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }
}
