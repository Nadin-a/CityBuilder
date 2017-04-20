package com.nadina.citymaker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Класс отвечает за прогресс города. Получение эффектов от построек.
 *
 * @author Nadina
 *         Create on 02.10.2016.
 */
public class CityProgress implements Serializable {


    /**
     * Список с текущими постройками
     */
    List<Construction> currentConstruction;

    double money;
    int effect_on_citiziens;
    int effect_on_tourists;
    int effect_on_ecology;
    int effect_on_science;
    int effect_on_mood;

    /**
     * Налоги
     */
    double all_taxes;

    double constr_effect;

    double tourists_effect;

    double science_effect;

    public double tax = 0.05;



    public double tourist_money = 0.01;

    public double science_eco = 0.02;
    public double constr_money = 0.4;


    /**
     * Счетчики, для того чтобы проверить были ли новые постройки за посление n шагов.
     */
    int old_count_of_constructions = 0;
    int new_count_of_constructions = 0;




    boolean not_new_constr = false;

    /** Конец игры. */
    boolean gameOver = false;
    /** Победа */
    boolean victory = false;

    boolean first_open = true;

    public boolean isVictory() {
        return victory;
    }

    public void setVictory(boolean victory) {
        this.victory = victory;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public double getTax() {
        return tax;
    }

    public double getTourist_money() {
        return tourist_money;
    }

    public double getScience_eco() {
        return science_eco;
    }

    public double getConstr_money() {
        return constr_money;
    }

    public boolean isFirst_open() {
        return first_open;
    }

    public void setFirst_open(boolean first_open) {
        this.first_open = first_open;
    }

    /**
     * События на каждом шаге
     *
     * @param current_city текущий город
     */
    public void Step(City current_city) {

        switch (current_city.getType_of_city()) {
            case 1: {

                calculate_money(current_city); //постройки приносят деньги

                break;
            }
            case 2: {
                calculate_tourist_effect(current_city);//туристы приносят деньги
                break;
            }
            case 3: {
                calculate_science_effect(current_city); //у научных городов экология растёт за счет науки
                checkTitlesAndEcology(current_city);
            }
            case 0: {
                calculate_taxes(current_city);
                break;
            }
        }
        formListConstruction(current_city);


        if (!currentConstruction.isEmpty()) {
            calculation();
            obtain_city_indicators(current_city);
        }
    }


    public void checkTitlesAndEcology(City current_city)
    {

        for(Title t: current_city.getTitles())
        {
            if(t.isOccupied)
            {
                if(current_city.getState_of_ecology() > 800)
                {
                    if(t.getCost() == 10)
                    {
                        t.setCost(0);
                    }
                    if(t.getCost() == 20)
                    {
                        t.setCost(5);
                    }
                    if(t.getCost() == 30)
                    {
                        t.setCost(12);
                    }
                }

            }
        }
    }

    /**
     * Формируется список с имеющимися постройками
     * Инициализация листа, должен быть первым в Step
     *
     * @param current_city текущий город
     */
    public void formListConstruction(City current_city) {
        currentConstruction = new ArrayList<>();
        for (Construction constr : current_city.getConstructions()) {
            if (constr.was_bought) {
                currentConstruction.add(constr);
            }
        }
    }


    public void calculate_money(City current_city)
    {

        List<Construction> myBuylist = new ArrayList<>();

        myBuylist.clear();

        for (Construction c : current_city.getConstructions()) {
            if (c.isWas_bought()) {
                myBuylist.add(c);
            }
        }

        double old_count_money = current_city.getCount_of_money();
        constr_effect = constr_money * myBuylist.size();
        current_city.setCount_of_money(old_count_money + constr_effect);
        constr_effect = 0;

    }
    /**
     * Подсчёт налогов с горожан.
     *
     * @param current_city Текущий город
     */
    public void calculate_taxes(City current_city) {
        double old_count_money = current_city.getCount_of_money();
        all_taxes = current_city.getCount_of_citiens() * tax;
        current_city.setCount_of_money(old_count_money + all_taxes);
        all_taxes = 0;

    }


    public void calculate_tourist_effect(City current_city) {
        double old_count_money = current_city.getCount_of_money();
        tourists_effect = current_city.getCount_of_tourists() * tourist_money;
        current_city.setCount_of_money(old_count_money + tourists_effect);
        tourists_effect = 0;

    }

    public void calculate_science_effect(City current_city) {
        int old_count_eco = current_city.getState_of_ecology();
        science_effect = current_city.getState_of_science() * science_eco;
        current_city.setState_of_ecology(old_count_eco + (int)science_effect);
        science_effect = 0;

    }


    /**
     * Чтобы пользователь покупал постройки.
     *
     * @param step         шаг. На каждом 10-м шагу, есди не было новых построек падает настроение
     * @param current_city текущний город
     */
    public void calculate_mood(int step, City current_city) {
        new_count_of_constructions = currentConstruction.size();
        if (not_new_constr) {
            low_mood(current_city);
        }

        if (new_count_of_constructions != old_count_of_constructions) {
            not_new_constr = false;
        }

        if (step == 2) {
            old_count_of_constructions = currentConstruction.size();
        }
        if (step % 10 == 0) {
            if (new_count_of_constructions == old_count_of_constructions) {
                low_mood(current_city);
            }
            old_count_of_constructions = currentConstruction.size();
        }

    }

    public void low_mood(City current_city) {
        int old_mood = current_city.getMood();
        if (current_city.getMood() > 1) {
            current_city.setMood(0);
            not_new_constr = true;
        } else if(current_city.getMood() <= 0) {
            current_city.setMood(old_mood - 100);
            not_new_constr = true;
        }
    }

    /**
     * Эффекты от построек
     */
    public void calculation() {
        for (Construction c : currentConstruction) {
            money += c.getMakes_money();
            effect_on_citiziens += c.getEffect_on_citizens();
            effect_on_tourists += c.getEffect_on_tourists();
            effect_on_ecology += c.getEffect_on_ecology();
            effect_on_science += c.getEffect_on_science();
            effect_on_mood += c.getEffect_on_mood();
        }

    }


    /**
     * @param current_city текущий город
     */
    public void obtain_city_indicators(City current_city) {


        double old_count_money = current_city.getCount_of_money();
        current_city.setCount_of_money(old_count_money + money);
        money = 0;

        int old_count_citiziens = current_city.getCount_of_citiens();
        current_city.setCount_of_citiens(old_count_citiziens + effect_on_citiziens);
        effect_on_citiziens = 0;

        int old_count_tourists = current_city.getCount_of_tourists();
        current_city.setCount_of_tourists(old_count_tourists + effect_on_tourists);
        effect_on_tourists = 0;

        int old_state_of_ecology = current_city.getState_of_ecology();
        current_city.setState_of_ecology(old_state_of_ecology + effect_on_ecology);
        effect_on_ecology = 0;

        int old_state_of_science = current_city.getState_of_science();
        current_city.setState_of_science(old_state_of_science + effect_on_science);
        effect_on_science = 0;

        int old_mood = current_city.getMood();
        current_city.setMood(old_mood + effect_on_mood);
        effect_on_mood = 0;
    }


}
