package com.example.camelia;

public class Plant {

    int id;
    String verName;
    String latName;
    int pictureId;

    public Plant(int id, String verName, String latName){
        this.id=id;
        this.verName=verName;
        this.latName=latName;
    }

    public String getName() {
        return verName;
    }
    
    public String getLatName(){
        return latName;
    }

    public int getId() { return id; }

    public int getPictureId() {
        return R.drawable.great_plant;
    }
}
