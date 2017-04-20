package com.nadina.citymaker;

import java.io.Serializable;
import java.util.Random;

/**
 * @author Nadina
 *         Created on 28.10.2016.
 */
public class Title implements Serializable {

    int id;
    /**
     * Расположение клетки.
     */
    int location;
    /**
     * Доступна ли.
     */
    boolean available;

    /**
     * Вода.
     */
    boolean isWater;

    /**
     * Лес.
     */
    boolean isOccupied;

    /**
     * Цена.
     */
    int cost;


    int title_pic;




    public Title() {
        this.id = 0;
        this.location = -1;
        this.available = false; //не доступна!
        this.isWater = false;
        this.isOccupied = true; // лес


        set_nature();

    }

    public void set_nature()
    {
        Random rn = new Random();

        //(max-min+1)+min
        int rand = rn.nextInt(3 - 1 + 1) + 1;

        switch (rand) {
            case 1: {
                this.title_pic = R.drawable.full_of_trees;
                this.cost = 30;
                break;
            }
            case 2: {
                this.title_pic = R.drawable.no_trees;
                this.cost = 10;
                break;
            }
            case 3: {
                this.title_pic = R.drawable.a_little_trees;
                this.cost = 20;
                break;
            }
        }



    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public boolean isWater() {
        return isWater;
    }

    public void setWater(boolean water) {
        isWater = water;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getTitle_pic() {
        return title_pic;
    }

    public void setTitle_pic(int title_pic) {
        this.title_pic = title_pic;
    }

    @Override
    public String toString() {
        return "Title{" +
                ", available=" + available +
                ", isWater=" + isWater +
                '}';
    }
}
