package com.nadina.citymaker;

import android.content.res.Resources;

/**
 * Класс, что назначает названия постройкам
 *
 * @author Nadina
 *         Created on 19.10.2016.
 */
public class Find_Names {

    Resources res = CityActivity.getInstance().getResources();

    /**
     * Найти постройку по названию и призначить ей имя
     *
     * @param id     ID постройки
     * @param constr постройка
     */
    public void find_name(int id, Construction constr) {
        String house = res.getString(R.string.House);
        String hospital = res.getString(R.string.Hospital);
        String store = res.getString(R.string.Store);

        String sanatorium = res.getString(R.string.Sanatorium);
        String seafront = res.getString(R.string.Seafront);
        String square = res.getString(R.string.Town_Square);

        //--------------------------------------------------------------------------------------------

        String gas = res.getString(R.string.Gas_station);
        String stock = res.getString(R.string.Stock);
        String factory = res.getString(R.string.Factory);

        String mine = res.getString(R.string.Mine);
        String incineraptor = res.getString(R.string.Incinerator);
        String solar = res.getString(R.string.Solar_panels);

        String oil_tower = res.getString(R.string.Oiltower);
        String auto_centre = res.getString(R.string.Automotive_Center);
        String engin_plant = res.getString(R.string.Engineering_plant);

        String transp_inter = res.getString(R.string.Transport_Interchange);
        String concreteMixinPlant = res.getString(R.string.ConcreteMixinPlant);
        String hydroelectric = res.getString(R.string.Hydroelectric);

        String gas_power_plant = res.getString(R.string.GasPowerPlant);
        String misilles_factory = res.getString(R.string.MissilesFactory);
        String recycling_factory = res.getString(R.string.RecyclingFactory);

        String nuclear = res.getString(R.string.Nuclear);
        String rob_factory = res.getString(R.string.Robot_Factory);
        String cosmodrom = res.getString(R.string.Cosmodrome);

        //-----------------------------------------------------------------------------------------------

        String park = res.getString(R.string.Park);
        String cafe = res.getString(R.string.Café);
        String motel = res.getString(R.string.Motel);

        String train = res.getString(R.string.TrainStation);
        String restaurant = res.getString(R.string.Restaurant);
        String theater = res.getString(R.string.Theater);

        String hotel = res.getString(R.string.Hotel);
        String circ = res.getString(R.string.Circus);
        String port = res.getString(R.string.Port);

        String aquapark = res.getString(R.string.Water);
        String zoo = res.getString(R.string.Zoo);
        String dolphin = res.getString(R.string.Dolphinarium);

        String amusement = res.getString(R.string.Amusement);
        String airport = res.getString(R.string.Airport);
        String casino = res.getString(R.string.Casino);

        String stadium = res.getString(R.string.Stadium);
        String castle = res.getString(R.string.Castle);
        String monument = res.getString(R.string.Monument);

        //----------------------------------------------------------------------------------------------


        String school = res.getString(R.string.School);
        String planet = res.getString(R.string.Planetarium);
        String exhibition = res.getString(R.string.Exhibition);

        String art_center = res.getString(R.string.ArtCenter);
        String mus_school = res.getString(R.string.MusSchool);
        String lit_mus = res.getString(R.string.LitMuseum);


        String educational_center = res.getString(R.string.EducationCen);
        String historical_mus = res.getString(R.string.HistoricalMuseum);
        String art_school = res.getString(R.string.ArtSchool);

        String human_un = res.getString(R.string.HumanitiesUn);
        String tech_mus = res.getString(R.string.TechMuseum);
        String tech_un = res.getString(R.string.TechnicalUn);

        String science_center = res.getString(R.string.ScienceCen);
        String biological_un = res.getString(R.string.BiologicalUn);
        String lab = res.getString(R.string.Lab);

        String hadron_coll = res.getString(R.string.HadronCol);
        String int_lin_col = res.getString(R.string.InternationalLinearCollider);
        String portal = res.getString(R.string.Portal);

        switch (id) {
            case 11:
                constr.setName_of_object(house);
                break;
            case 12:
                constr.setName_of_object(hospital);
                break;
            case 13:
                constr.setName_of_object(store);
                break;
            case 14:
                constr.setName_of_object(sanatorium);
                break;
            case 15:
                constr.setName_of_object(seafront);
                break;
            case 16:
                constr.setName_of_object(square);
                break;

            case 21:
                constr.setName_of_object(gas);
                break;
            case 22:
                constr.setName_of_object(stock);
                break;
            case 23:
                constr.setName_of_object(factory);
                break;
            case 24:
                constr.setName_of_object(mine);
                break;
            case 25:
                constr.setName_of_object(incineraptor);
                break;
            case 26:
                constr.setName_of_object(solar);
                break;
            case 27:
                constr.setName_of_object(oil_tower);
                break;
            case 28:
                constr.setName_of_object(auto_centre);
                break;
            case 29:
                constr.setName_of_object(engin_plant);
                break;
            case 210:
                constr.setName_of_object(transp_inter);
                break;
            case 211:
                constr.setName_of_object(concreteMixinPlant);
                break;
            case 212:
                constr.setName_of_object(hydroelectric);
                break;
            case 213:
                constr.setName_of_object(gas_power_plant);
                break;
            case 214:
                constr.setName_of_object(misilles_factory);
                break;
            case 215:
                constr.setName_of_object(recycling_factory);
                break;
            case 216:
                constr.setName_of_object(nuclear);
                break;
            case 217:
                constr.setName_of_object(rob_factory);
                break;
            case 218:
                constr.setName_of_object(cosmodrom);
                break;



            case 31:
                constr.setName_of_object(park);
                break;
            case 32:
                constr.setName_of_object(cafe);
                break;
            case 33:
                constr.setName_of_object(motel);
                break;
            case 34:
                constr.setName_of_object(train);
                break;
            case 35:
                constr.setName_of_object(restaurant);
                break;
            case 36:
                constr.setName_of_object(theater);
                break;
            case 37:
                constr.setName_of_object(hotel);
                break;
            case 38:
                constr.setName_of_object(circ);
                break;
            case 39:
                constr.setName_of_object(port);
                break;
            case 310:
                constr.setName_of_object(aquapark);
                break;
            case 311:
                constr.setName_of_object(zoo);
                break;
            case 312:
                constr.setName_of_object(dolphin);
                break;
            case 313:
                constr.setName_of_object(amusement);
                break;
            case 314:
                constr.setName_of_object(airport);
                break;
            case 315:
                constr.setName_of_object(casino);
                break;
            case 316:
                constr.setName_of_object(stadium);
                break;
            case 317:
                constr.setName_of_object(castle);
                break;
            case 318:
                constr.setName_of_object(monument);
                break;




            case 41:
                constr.setName_of_object(school);
                break;
            case 42:
                constr.setName_of_object(planet);
                break;
            case 43:
                constr.setName_of_object(exhibition);
                break;
            case 44:
                constr.setName_of_object(art_center);
                break;
            case 45:
                constr.setName_of_object(mus_school);
                break;
            case 46:
                constr.setName_of_object(lit_mus);
                break;
            case 47:
                constr.setName_of_object(educational_center);
                break;
            case 48:
                constr.setName_of_object(historical_mus);
                break;
            case 49:
                constr.setName_of_object(art_school);
                break;
            case 410:
                constr.setName_of_object(human_un);
                break;
            case 411:
                constr.setName_of_object(tech_mus);
                break;
            case 412:
                constr.setName_of_object(tech_un);
                break;
            case 413:
                constr.setName_of_object(science_center);
                break;
            case 414:
                constr.setName_of_object(biological_un);
                break;
            case 415:
                constr.setName_of_object(lab);
                break;
            case 416:
                constr.setName_of_object(hadron_coll);
                break;
            case 417:
                constr.setName_of_object(int_lin_col);
                break;
            case 418:
                constr.setName_of_object(portal);
                break;

        }
    }


}
