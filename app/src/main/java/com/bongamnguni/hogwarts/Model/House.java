package com.bongamnguni.hogwarts.Model;

import java.util.List;

public class House {

    private String _id;

    private String name;

    private String mascot;

    private String headOfHouse;

    private String houseGhost;

    private String founder;

    private int __v;

    private String school;

    private List<String> members;

    private List<String> values;

    private List<String> colors;

    public House(String name){}

    public void set_id(String _id){
        this._id = _id;
    }
    public String get_id(){
        return this._id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setMascot(String mascot){
        this.mascot = mascot;
    }
    public String getMascot(){
        return this.mascot;
    }
    public void setHeadOfHouse(String headOfHouse){
        this.headOfHouse = headOfHouse;
    }
    public String getHeadOfHouse(){
        return this.headOfHouse;
    }
    public void setHouseGhost(String houseGhost){
        this.houseGhost = houseGhost;
    }
    public String getHouseGhost(){
        return this.houseGhost;
    }
    public void setFounder(String founder){
        this.founder = founder;
    }
    public String getFounder(){
        return this.founder;
    }
    public void set__v(int __v){
        this.__v = __v;
    }
    public int get__v(){
        return this.__v;
    }
    public void setSchool(String school){
        this.school = school;
    }
    public String getSchool(){
        return this.school;
    }
    public void setMembers(List<String> members){
        this.members = members;
    }
    public List<String> getMembers(){
        return this.members;
    }
    public void setValues(List<String> values){
        this.values = values;
    }
    public List<String> getValues(){
        return this.values;
    }
    public void setColors(List<String> colors){
        this.colors = colors;
    }
    public List<String> getColors(){
        return this.colors;
    }
}
