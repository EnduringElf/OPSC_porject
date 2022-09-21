package com.example.opsc_porject;

public class User {

    public String name,age,email;

    public User(){

    }

    public User(String Name, String Age, String Email ){

        this.name = Name;
        this.age = Age;
        this.email = Email;

    }

    public boolean metric = false;

    public boolean ToggleMetric(){
        if (metric == false){
            return   true;

        }else {
            return  false;

        }

    }






}




