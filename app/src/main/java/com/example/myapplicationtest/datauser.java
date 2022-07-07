package com.example.myapplicationtest;

public class datauser {
    private String name,mobileno,address;

    public datauser(String name, String mobileno, String address) {
        this.name = name;
        this.mobileno = mobileno;
        this.address = address;
    }

    public datauser() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
