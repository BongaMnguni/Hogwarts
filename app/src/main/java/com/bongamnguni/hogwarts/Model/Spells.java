package com.bongamnguni.hogwarts.Model;

public class Spells {
    private String _id;

    private String spell;

    private String type;

    private String effect;
public Spells (){}
    public void set_id(String _id){
        this._id = _id;
    }
    public String get_id(){
        return this._id;
    }
    public void setSpell(String spell){
        this.spell = spell;
    }
    public String getSpell(){
        return this.spell;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }
    public void setEffect(String effect){
        this.effect = effect;
    }
    public String getEffect(){
        return this.effect;
    }
}
