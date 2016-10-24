package com.example.a21753725a.magiccardlist;

/**
 * Created by 21753725a on 24/10/16.
 */

public class Card {
    String name;
    String type;
    String rarity;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRarity() {
        return rarity;
    }

    public void setRarity(String rarity) {
        this.rarity = rarity;
    }

    @Override
    public String toString() {
        return "card{" +
                "name='" + name + '\'' +
                ", type=" + type +
                ", rarity='" + rarity +
                '}';
    }

}
