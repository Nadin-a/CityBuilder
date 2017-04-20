package com.nadina.citymaker;

import java.io.Serializable;

/**
 * Created by Nadina on 10.09.2016.
 */
public class Construction implements Serializable {


    /**
     * Типы построек
     */
    public final static int CATEGORY_DEFAULT = 0;
    public final static int CATEGORY_INDUSTRIAL = 1;
    public final static int CATEGORY_RESORT = 2;
    public final static int CATEGORY_SCIENTIFIC = 3;

    int idObject;

    /**
     * Название постройки
     */
    String name_of_object;
    /**
     * Сколько приносит денег
     */
    double makes_money;
    /**
     * Эффект на горожан
     */
    int effect_on_citizens;
    /**
     * Эффект на туристов
     */
    int effect_on_tourists;
    /**
     * Эффект на экологию
     */
    int effect_on_ecology;
    /**
     * Эффект на науку
     */
    int effect_on_science;
    /**
     * Эффект на настроение
     */
    int effect_on_mood;
    /**
     * Категория постройки
     */
    Integer idCategory;

    /**
     * Была ли куплена
     */
    boolean was_bought = false;

    /**
     * Доступна ли в магазине
     */
    boolean available = false;

    /**
     * Цена постройки
     */

    double cost;


    int location;

    int construction_pic;

    int construction_level;




    public Construction(int idObject,String name_of_object, double makes_money, int effect_on_citizens, int effect_on_tourists,
                        int effect_on_ecology, int effect_on_science, int effect_on_mood, int idCategory, int level, boolean was_bought,
                        double cost, boolean available, int construction_pic, int location) {
        this.idObject = idObject;
        this.name_of_object = name_of_object;
        this.makes_money = makes_money;
        this.effect_on_citizens = effect_on_citizens;
        this.effect_on_tourists = effect_on_tourists;
        this.effect_on_ecology = effect_on_ecology;
        this.effect_on_science = effect_on_science;
        this.effect_on_mood = effect_on_mood;
        this.idCategory = idCategory;
        this.construction_level = level;
        this.was_bought = was_bought;
        this.cost = cost;
        this.available = available;
        this.construction_pic = construction_pic;
        this.location = location;

    }

    public int getIdObject() {
        return idObject;
    }

    public String getName_of_object() {
        return name_of_object;
    }


    public void setName_of_object(String name_of_object) {
        this.name_of_object = name_of_object;
    }

    public double getMakes_money() {
        return makes_money;
    }

    public void setMakes_money(double makes_money) {
        this.makes_money = makes_money;
    }

    public int getEffect_on_tourists() {
        return effect_on_tourists;
    }

    public void setEffect_on_tourists(int effect_on_tourists) {
        this.effect_on_tourists = effect_on_tourists;
    }

    public int getEffect_on_citizens() {
        return effect_on_citizens;
    }

    public void setEffect_on_citizens(int effect_on_citizens) {
        this.effect_on_citizens = effect_on_citizens;
    }

    public int getEffect_on_ecology() {
        return effect_on_ecology;
    }

    public void setEffect_on_ecology(int effect_on_ecology) {
        this.effect_on_ecology = effect_on_ecology;
    }

    public int getEffect_on_science() {
        return effect_on_science;
    }

    public void setEffect_on_science(int effect_on_science) {
        this.effect_on_science = effect_on_science;
    }

    public int getEffect_on_mood() {
        return effect_on_mood;
    }

    public void setEffect_on_mood(int effect_on_mood) {
        this.effect_on_mood = effect_on_mood;
    }

    public boolean isWas_bought() {
        return was_bought;
    }

    public void setWas_bought(boolean was_bought) {
        this.was_bought = was_bought;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public int getConstruction_pic() {
        return construction_pic;
    }

    public void setConstruction_pic(int construction_pic) {
        this.construction_pic = construction_pic;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public int getConstruction_level() {
        return construction_level;
    }

    @Override
    public String toString() {
        return "Construction{" +
                "name_of_object='" + name_of_object + '\'' +
                ", makes_money=" + makes_money +
                ", effect_on_citizens=" + effect_on_citizens +
                ", effect_on_tourists=" + effect_on_tourists +
                ", effect_on_ecology=" + effect_on_ecology +
                ", effect_on_science=" + effect_on_science +
                ", effect_on_mood=" + effect_on_mood +
                ", idCategory=" + idCategory +
                ", was_bought=" + was_bought +
                ", available=" + available +
                ", cost=" + cost +
                '}';
    }

    /**
     * Для ExpandibleListView(магазин)
     *
     * @return постройку
     */
    public Construction cloneConstruction() {
        return new Construction(this.idObject, this.name_of_object, this.makes_money,
                this.effect_on_citizens, this.effect_on_tourists, this.effect_on_ecology, this.effect_on_science,
                this.effect_on_mood,
                this.idCategory, this.construction_level, this.was_bought, this.cost,this.available, this.construction_pic, this.location);
    }


}
