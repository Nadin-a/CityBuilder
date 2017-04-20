package com.nadina.citymaker;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

/**
 * Адаптер данных
 * @author Nadina
 * Createdon 10.09.2016.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.CityViewHolder> {

    /** список с городами */
    List<City> cities;

    RVAdapter(List<City> cities
    ) {
        this.cities = cities;
    }


    /**
     * Класс для отображения городов в начальном меню
     * @author Nadina
     */
    public class CityViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {

        CardView cv;
        TextView cityName;
        TextView cityCountOfMoney;
        TextView levelCity;
        TextView typeCity;
        ImageView cityPicture;
        int position;


        CityViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(oclBtnLong);
            cv = (CardView) itemView.findViewById(R.id.cv);
            cityName = (TextView) itemView.findViewById(R.id.city_name_textview);
            cityCountOfMoney = (TextView) itemView.findViewById(R.id.count_of_money_text_view);
            cityPicture = (ImageView) itemView.findViewById(R.id.city_picture);
            levelCity = (TextView) itemView.findViewById(R.id.level_of_city_text_view);
            typeCity = (TextView) itemView.findViewById(R.id.type_of_city_text_view);
        }



        @Override
        public void onClick(View v) {

            this.position = getAdapterPosition();
            ((CityActivity) v.getContext()).OpenCity(position);
        }

        final View.OnLongClickListener oclBtnLong = new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                position = getAdapterPosition();
                ((CityActivity) v.getContext()).startDeleting(position);
                return true;
            }
            };

    }


    @Override
    public int getItemCount() {
        return cities.size();
    }


    @Override
    public CityViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_of_menu, viewGroup, false);
        CityViewHolder pvh = new CityViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(CityViewHolder personViewHolder, int i) {
        personViewHolder.cityName.setText(cities.get(i).name_of_city);
        personViewHolder.cityCountOfMoney.setText("M: " + new BigDecimal(cities.get(i).getCount_of_money()).setScale(1, RoundingMode.UP).doubleValue());
        personViewHolder.levelCity.setText(cities.get(i).getLevels() + "");
        personViewHolder.typeCity.setText(cities.get(i).getTypes() + "");


        switch(cities.get(i).getType_of_city())
        {
            case 0:
            {
                personViewHolder.cityPicture.setImageResource(R.drawable.city_pic);
                break;
            }
            case 1:
            {
                personViewHolder.cityPicture.setImageResource(R.drawable.city_ind_pic);
                break;
            }
            case 2:
            {
                personViewHolder.cityPicture.setImageResource(R.drawable.city_res_pic);
                break;
            }
            case 3:
            {
                personViewHolder.cityPicture.setImageResource(R.drawable.city_sci_pic);
                break;
            }
        }

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) personViewHolder.cityPicture.getLayoutParams(); // получаем параметры
        int MyHeight = 250; // желаемая высота, будет меняться по условиям
        int MyWeight = 350;
        params.height = MyHeight; // меняем высоту. Если уползёт выравнивание, то imageView.getLayoutParams().width = MyHeight;
        personViewHolder.cityPicture.getLayoutParams().width = MyWeight;
        personViewHolder.cityPicture.setLayoutParams(params); // меняем параметр
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}