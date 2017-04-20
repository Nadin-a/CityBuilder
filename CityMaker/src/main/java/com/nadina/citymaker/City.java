package com.nadina.citymaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Класс для города
 *
 * @author Nadina
 *         Created on 10.09.2016.
 */
public class City implements Serializable {

    /**
     * Название города
     */
    String name_of_city;
    /**
     * Уровень города
     */
    int city_level;
    /**
     * Количество денег
     */
    double count_of_money;
    /**
     * Количество горожан
     */
    int count_of_citiens;
    /**
     * Количество туристов
     */
    int count_of_tourists;
    /**
     * Экология
     */
    int state_of_ecology;
    /**
     * Наука
     */
    int state_of_science;
    /**
     * Настроение
     */
    int mood;
    /**
     * На каком шаге
     */
    int step;
    /**
     * Тип города
     */
    int type_of_city;
    /**
     * Список его конструкций
     */
    List<Construction> constructions;

    /**
     * Прогресс.
     */
    CityProgress progress;

    /**
     * Массив клеток.
     */
    Title[] titles;


    public City() {

        this.name_of_city = "NewCity";
        this.city_level = 1;
        this.count_of_money = 50;
        this.count_of_citiens = 0;
        this.count_of_tourists = 0;
        this.state_of_ecology = 0; //от 100 до 1000
        this.state_of_science = 0;
        this.mood = 0;
        this.step = 1;
        this.type_of_city = 0; //default
        this.constructions = new ArrayList<>();
        add_constructions();
        this.progress = new CityProgress();

        titles = new Title[28];

        draw_city_view();


    }


    /**
     * Уровни города
     */
    public static String LEVELS[];

    /**
     * Типы города
     */
    public static String TYPES[];

    /**
     * Определить уровень города
     *
     * @return уровень
     */
    public String getLevels() {
        if (this.city_level >= City.LEVELS.length) {
            return City.LEVELS[0];
        } else {
            return City.LEVELS[this.city_level];
        }
    }

    /**
     * Определить тип города
     *
     * @return тип
     */
    public String getTypes() {
        if (this.type_of_city >= City.TYPES.length) {
            return City.TYPES[0];
        } else {
            return City.TYPES[this.type_of_city];
        }
    }

    public String getName_of_city() {
        return name_of_city;
    }

    public void setName_of_city(String name_of_city) {
        this.name_of_city = name_of_city;
    }

    public int getCity_level() {
        return city_level;
    }

    public void setCity_level(int city_level) {
        this.city_level = city_level;
    }

    public double getCount_of_money() {
        return count_of_money;
    }

    public void setCount_of_money(double count_of_money) {
        this.count_of_money = count_of_money;
    }

    public int getCount_of_citiens() {
        return count_of_citiens;
    }

    public void setCount_of_citiens(int count_of_citiens) {
        this.count_of_citiens = count_of_citiens;
    }

    public int getCount_of_tourists() {
        return count_of_tourists;
    }

    public void setCount_of_tourists(int count_of_tourists) {
        this.count_of_tourists = count_of_tourists;
    }

    public int getState_of_ecology() {
        return state_of_ecology;
    }

    public void setState_of_ecology(int state_of_ecology) {
        this.state_of_ecology = state_of_ecology;
    }

    public int getState_of_science() {
        return state_of_science;
    }

    public void setState_of_science(int state_of_science) {
        this.state_of_science = state_of_science;
    }

    public int getMood() {
        return mood;
    }

    public void setMood(int mood) {
        this.mood = mood;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }


    public int getType_of_city() {
        return type_of_city;
    }

    public void setType_of_city(int type_of_city) {
        this.type_of_city = type_of_city;
    }


    public List<Construction> getConstructions() {
        return constructions;
    }

    public void setConstructions(List<Construction> constructions) {
        this.constructions = constructions;
    }

    public CityProgress getProgress() {
        return progress;
    }

    public void setProgress(CityProgress progress) {
        this.progress = progress;
    }

    public void setTYPES(String[] TYPES) {
        City.TYPES = TYPES;
    }

    public void setLEVELS(String[] LEVELS) {
        City.LEVELS = LEVELS;
    }

    public Title[] getTitles() {
        return titles;
    }


    /**
     * Вид города
     */
    public void draw_city_view() {
        for (int i = 0; i < titles.length; i++) {
            titles[i] = new Title();
            titles[i].setId(i);

            if (titles[i].getId() == 0 || titles[i].getId() == 1 || titles[i].getId() == 2) {
                titles[i].setOccupied(false);
                titles[i].setAvailable(true);
                titles[i].setTitle_pic(R.drawable.default_pic);
            }

            if (titles[i].getId() == 21) {
                Random rn = new Random();
                int rand = rn.nextInt(2 - 1 + 1) + 1;
                switch (rand) {
                    case 1: {
                        titles[i].setTitle_pic(R.drawable.water_nature1);
                        titles[i].setCost(50);
                        titles[i].setWater(true);
                        titles[i].setOccupied(true);
                        titles[i].setAvailable(false);
                        break;
                    }
                    case 2: {
                        titles[i].setTitle_pic(R.drawable.water_nature2);
                        titles[i].setCost(30);
                        titles[i].setWater(true);
                        titles[i].setOccupied(true);
                        titles[i].setAvailable(false);
                        break;
                    }
                }
            }
            if (titles[i].getId() == 22) {
                titles[i].setTitle_pic(R.drawable.water_nature_edge);
                titles[i].setWater(true);
                titles[i].setCost(40);
                titles[i].setOccupied(true);
                titles[i].setAvailable(false);
            }

        }
    }


    @Override
    public String toString() {
        return "City{" +
                "name_of_city='" + name_of_city + '\'' +
                ", city_level=" + city_level +
                ", count_of_money=" + count_of_money +
                ", count_of_citiens=" + count_of_citiens +
                ", count_of_tourists=" + count_of_tourists +
                ", state_of_ecology=" + state_of_ecology +
                ", state_of_science=" + state_of_science +
                ", mood=" + mood +
                ", step=" + step +
                ", type_of_city=" + type_of_city +
                //", constructions=" + constructions +
                // ", progress=" + progress +
                ", titles=" + Arrays.toString(titles) +
                '}';
    }

    public void add_constructions() {

        // id -- name -- money -- citiziens - tourists -- ecology -- science - mood

        constructions.add(new Construction(11, "", -3, 3, 1, 2, 0, 1, Construction.CATEGORY_DEFAULT, 1, false, 10, true, R.drawable.houses, -1)); // house

        constructions.add(new Construction(12, "", 10, 9, 1, 10, 3, 10, Construction.CATEGORY_DEFAULT, 3, false, 50, false, R.drawable.hospital, -1)); // hospital

        constructions.add(new Construction(13, "", 2, 1, 1, 0, 0, 2, Construction.CATEGORY_DEFAULT, 2, false, 30, false, R.drawable.store, -1)); // store


        //--------Инустриальные
        //1
        constructions.add(new Construction(21, "", 2, 1, 0, -2, 0, 0, Construction.CATEGORY_INDUSTRIAL, 1, false, 10, true, R.drawable.gas_station, -1)); //Gas Station

        constructions.add(new Construction(22, "", 1, 1, 0, -1, 0, 0, Construction.CATEGORY_INDUSTRIAL, 1, false, 15, true, R.drawable.house_pic, -1)); //Stock

        constructions.add(new Construction(23, "", 3, 2, 0, -5, 0, 0, Construction.CATEGORY_INDUSTRIAL, 1, false, 20, true, R.drawable.factory_pic, -1)); //Factory


        //3
        constructions.add(new Construction(25, "", -10, 1, 0, 7, 0, 0, Construction.CATEGORY_INDUSTRIAL, 3, false, 50, false, R.drawable.incener, -1)); //Incineraptor

        constructions.add(new Construction(27, "", 7, 3, 0, -8, 0, 0, Construction.CATEGORY_INDUSTRIAL, 3, false, 60, false, R.drawable.oil_tower, -1)); // Oil Tower

        constructions.add(new Construction(28, "", 4, 1, 0, -9, 0, 0, Construction.CATEGORY_INDUSTRIAL, 3, false, 60, false, R.drawable.auto_center, -1));  //Auto Center

        constructions.add(new Construction(29, "", 6, 5, 0, -11, 0, 0, Construction.CATEGORY_INDUSTRIAL, 3, false, 70, false, R.drawable.eng_plant, -1));  //Engineering Plant


        //4


        constructions.add(new Construction(26, "", -15, 2, 0, 12, 0, 0, Construction.CATEGORY_INDUSTRIAL, 4, false, 70, false, R.drawable.solar_panels, -1)); //Solar Panels

        constructions.add(new Construction(210, "", 5, 4, 0, -12, 0, 0, Construction.CATEGORY_INDUSTRIAL, 4, false, 80, false, R.drawable.transport_int, -1)); // Transport_Interchange

        constructions.add(new Construction(211, "", 7, 5, 0, -11, 0, 0, Construction.CATEGORY_INDUSTRIAL, 4, false, 90, false, R.drawable.concrete_mix_plant, -1));  //Concrete Mixin Plant

        constructions.add(new Construction(212, "", 8, 5, 0, -9, 0, 0, Construction.CATEGORY_INDUSTRIAL, 4, false, 100, false, R.drawable.ges, -1)); //Hydroelectric Power Station

        //5

        constructions.add(new Construction(24, "", 7, 3, 0, -9, 0, 0, Construction.CATEGORY_INDUSTRIAL, 5, false, 90, false, R.drawable.mine, -1)); //Mine

        constructions.add(new Construction(213, "", 10, 6, 0, -11, 0, 0, Construction.CATEGORY_INDUSTRIAL, 5, false, 110, false, R.drawable.gas, -1));  //Gas Power Plant

        constructions.add(new Construction(214, "", 9, 5, 0, -12, 0, 0, Construction.CATEGORY_INDUSTRIAL, 5, false, 105, false, R.drawable.miss_fact, -1));  //Missiles Factory

        constructions.add(new Construction(215, "", -20, 3, 0, 17, 0, 0, Construction.CATEGORY_INDUSTRIAL, 5, false, 100, false, R.drawable.rec_factory, -1));  //Recycling Factory

        //6

        constructions.add(new Construction(216, "", 30, 7, 0, 0, 0, 0, Construction.CATEGORY_INDUSTRIAL, 6, false, 150, false, R.drawable.as, -1));  //Nuclear Power  Station

        constructions.add(new Construction(217, "", 15, 4, 0, 0, 30, 0, Construction.CATEGORY_INDUSTRIAL, 6, false, 250, false, R.drawable.robot, -1));  //Robot Factory

        constructions.add(new Construction(218, "", 15, 10, 0, 0, 10, 0, Construction.CATEGORY_INDUSTRIAL, 6, false, 350, false, R.drawable.cosm, -1));  //Cosmodrome


        //--------Курортные

        // 1

        constructions.add(new Construction(31, "", -5, 1, 2, 5, 0, 5, Construction.CATEGORY_RESORT, 1, false, 10, true, R.drawable.park, -1)); //Park

        constructions.add(new Construction(32, "", 3, 1, 3, 0, 0, 4, Construction.CATEGORY_RESORT, 1, false, 15, true, R.drawable.cafe, -1)); //Cafe

        constructions.add(new Construction(33, "", 2, 1, 5, 0, 0, 2, Construction.CATEGORY_RESORT, 1, false, 20, true, R.drawable.motel, -1)); //Motel


        //3

        constructions.add(new Construction(34, "", -10, 3, 5, 0, 0, 1, Construction.CATEGORY_RESORT, 3, false, 50, false, R.drawable.train_station, -1)); //Train Station

        constructions.add(new Construction(37, "", 2, 2, 10, 0, 0, 3, Construction.CATEGORY_RESORT, 3, false, 60, false, R.drawable.hotel, -1)); //Hotel

        constructions.add(new Construction(38, "", 2, 2, 7, 0, 0, 7, Construction.CATEGORY_RESORT, 3, false, 70, false, R.drawable.circus, -1)); //Circus

        constructions.add(new Construction(39, "", -9, 3, 5, 0, 0, 3, Construction.CATEGORY_RESORT, 3, false, 50, false, R.drawable.port, -1)); //Port

        //4

        constructions.add(new Construction(35, "", 4, 2, 6, 0, 0, 7, Construction.CATEGORY_RESORT, 4, false, 70, false, R.drawable.restoran, -1)); //Restaurant

        constructions.add(new Construction(310, "", 5, 1, 7, 0, 0, 10, Construction.CATEGORY_RESORT, 4, false, 100, false, R.drawable.aquapark, -1)); //Water

        constructions.add(new Construction(311, "", 3, 2, 11, 0, 0, 12, Construction.CATEGORY_RESORT, 4, false, 90, false, R.drawable.zoo, -1)); //Zoo

        constructions.add(new Construction(312, "", 4, 1, 9, 0, 0, 10, Construction.CATEGORY_RESORT, 4, false, 80, false, R.drawable.dolphins, -1)); //Dolphinarium

        //5

        constructions.add(new Construction(36, "", -15, 3, 7, 0, 0, 5, Construction.CATEGORY_RESORT, 5, false, 100, false, R.drawable.theatre, -1)); //Theater

        constructions.add(new Construction(313, "", 6, 3, 5, 0, 0, 11, Construction.CATEGORY_RESORT, 5, false, 120, false, R.drawable.ampark, -1)); //Amusement Park

        constructions.add(new Construction(314, "", -20, 4, 7, 0, 0, 3, Construction.CATEGORY_RESORT, 5, false, 90, false, R.drawable.airport, -1)); //Airport

        constructions.add(new Construction(315, "", 7, 1, 10, 0, 0, 12, Construction.CATEGORY_RESORT, 5, false, 105, false, R.drawable.casino, -1)); //Casino

        //6

        constructions.add(new Construction(316, "", 15, 4, 15, 0, 0, 20, Construction.CATEGORY_RESORT, 6, false, 250, false, R.drawable.stadium, -1)); //Stadium

        constructions.add(new Construction(317, "", 12, 2, 20, 0, 0, 15, Construction.CATEGORY_RESORT, 6, false, 350, false, R.drawable.castle, -1)); //Castle

        constructions.add(new Construction(318, "", 10, 5, 10, 0, 0, 7, Construction.CATEGORY_RESORT, 6, false, 150, false, R.drawable.monument, -1)); //Monument

        //--------Научные

        //1

        constructions.add(new Construction(41, "", -5, 2, 0, 2, 4, 0, Construction.CATEGORY_SCIENTIFIC, 1, false, 20, true, R.drawable.school, -1)); //Школа

        constructions.add(new Construction(42, "", 2, 1, 0, 2, 3, 0, Construction.CATEGORY_SCIENTIFIC, 1, false, 15, true, R.drawable.plnt, -1)); //Планетарий

        constructions.add(new Construction(43, "", 3, 1, 0, 2, 2, 0, Construction.CATEGORY_SCIENTIFIC, 1, false, 10, true, R.drawable.exb, -1)); //Выставка


        //3

        constructions.add(new Construction(44, "", -10, 1, 0, 0, 7, 0, Construction.CATEGORY_SCIENTIFIC, 3, false, 50, false, R.drawable.art_centre, -1)); //Art Center

        constructions.add(new Construction(47, "", -8, 3, 0, 0, 10, 0, Construction.CATEGORY_SCIENTIFIC, 3, false, 60, false, R.drawable.educational_center, -1)); //Educational Center

        constructions.add(new Construction(48, "", -7, 2, 0, 0, 8, 0, Construction.CATEGORY_SCIENTIFIC, 3, false, 70, false, R.drawable.hist_museum, -1)); //Historical Museum

        constructions.add(new Construction(49, "", -5, 2, 0, 0, 4, 0, Construction.CATEGORY_SCIENTIFIC, 3, false, 50, false, R.drawable.art_school, -1)); //Art School

        //4

        constructions.add(new Construction(45, "", -5, 2, 0, 0, 6, 0, Construction.CATEGORY_SCIENTIFIC, 4, false, 70, false, R.drawable.music_school, -1)); //Music School

        constructions.add(new Construction(410, "", -15, 8, 0, 0, 12, 0, Construction.CATEGORY_SCIENTIFIC, 4, false, 90, false, R.drawable.hum_university, -1)); //Humanities University

        constructions.add(new Construction(411, "", -10, 3, 0, 0, 8, 0, Construction.CATEGORY_SCIENTIFIC, 4, false, 80, false, R.drawable.tech_museum, -1)); //Techical Museum

        constructions.add(new Construction(412, "", -17, 8, 0, 0, 12, 0, Construction.CATEGORY_SCIENTIFIC, 4, false, 100, false, R.drawable.tech_univercity, -1)); //Techical University


        //5

        constructions.add(new Construction(46, "", -7, 5, 0, 0, 8, 0, Construction.CATEGORY_SCIENTIFIC, 5, false, 90, false, R.drawable.library, -1)); //Literature Museum

        constructions.add(new Construction(413, "", -25, 6, 0, 0, 15, 0, Construction.CATEGORY_SCIENTIFIC, 5, false, 105, false, R.drawable.scsc, -1)); //Science center

        constructions.add(new Construction(414, "", -20, 15, 0, 0, 13, 0, Construction.CATEGORY_SCIENTIFIC, 5, false, 100, false, R.drawable.bio_univ, -1)); //Biological university

        constructions.add(new Construction(415, "", -15, 7, 0, 0, 17, 0, Construction.CATEGORY_SCIENTIFIC, 5, false, 120, false, R.drawable.lab, -1)); //Lab

        //6

        constructions.add(new Construction(416, "", 15, 10, 0, 0, 20, 0, Construction.CATEGORY_SCIENTIFIC, 6, false, 250, false, R.drawable.clldr, -1)); //Hadron collider

        constructions.add(new Construction(417, "", 15, 10, 0, 0, 20, 0, Construction.CATEGORY_SCIENTIFIC, 6, false, 150, false, R.drawable.lincoll, -1)); //Linear collider

        constructions.add(new Construction(418, "", 15, 17, 0, 0, 25, 0, Construction.CATEGORY_SCIENTIFIC, 6, false, 350, false, R.drawable.portal, -1)); //Portal


    }

}
