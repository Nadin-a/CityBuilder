package com.nadina.citymaker;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;


public class CityActivity extends AppCompatActivity {

    /**
     * Список с городами
     */
    List<City> cities;

    /**
     * Индекс текущего города
     */
    public static int index;

    /**
     * Список с купленными постройками
     */
    List<Construction> myBuylist;

    /**
     * Список с купленными клетками
     */
    List<Title> myTitlelist;

    /**
     * константа для получения индекса из другой активности
     */
    public final static String MY_INDEX = "my_city_current_index";
    public final static String CITY = "open_my_city";

    /**
     * Переменная для определения что было открыто
     */
    public int what_open = 0;


    /**
     * Массивы из strings
     */
    String[] object_atributes;
    String[] all_types;
    String[] all_types_city;
    String[] city_attributes;
    Resources res;

    /**
     * Объекты View
     */
    EditText new_city_name;
    View main_screen;
    /**
     * Город
     */
    Button nextStep;
    TextView city_name;
    TextView current_city_count_of_money;
    TextView current_city_ecology;
    TextView current_city_count_of_citiens;
    TextView current_city_count_of_tourists;
    TextView current_science;
    TextView current_mood;
    TextView current_level;
    TextView current_step;
    TextView current_city_type;
    TextView count_of_money;
    TextView labelConstructions;
    TextView information;
    TextView effect;
    TextView effect_info;
    LinearLayout mainLL;
    /**
     * Настройки
     */
    LinearLayout settingsLL;
    SwitchCompat musicSwitch;
    SwitchCompat soundsSwitch;
    /**
     * Магазин и меню
     */
    LayoutInflater inflater;
    View view;
    View shop_view;
    View set_view;
    GridLayout city_grid;
    LinearLayout LL_with_rc;
    LinearLayout generLL;
    LinearLayout shop_back;
    LinearLayout shop_LL;
    LinearLayout settings_back;
    RelativeLayout city_view_rl;
    LinearLayout noConstructions;
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    LinearLayout city_view;
    Button shop_back_btn;
    Button get_title_button;
    /**
     * Диалоговые окна
     */
    private AlertDialog.Builder builder;
    private AlertDialog.Builder builder_del;
    AlertDialog dialog;
    AlertDialog dialog_del;
    private View dialogView;
    private View dialogView_del;
    RVAdapter adapter;
    Button settings;
    /**
     * Мои постройки
     */
    LinearLayout myConstr_back;
    LinearLayout myConstr_LL;
    TextView quest;
    View myConstr_view;
    ListView myConstructions_listView;
    ArrayAdapter<Construction> constr_adapter;
    ExpandableListView ELV;
    Snackbar snackbar;
    /**
     * Выбор направдения
     */
    LinearLayout choiseLL;
    View view_choise;
    LinearLayout choise_back;
    TextView lab_choise;
    Button one;
    Button two;
    Button three;
    /**
     * Уведомление
     */
    LinearLayout message_informationLL;
    LinearLayout info_back;
    LinearLayout infoLL;
    TextView inform_messageTV;
    View message_view;
    /**
     * Событие шторм
     */
    TextView damageTV;
    TextView weather_eventTV;
    LinearLayout event_back_storm;
    LinearLayout event_storm;
    View stormView;
    TextView damageLabel;
    /**
     * Конец игры и победа
     */
    LinearLayout end_game_back;
    LinearLayout end_game;
    View end_game_view;
    TextView what_happendTV;
    TextView reasonTV;
    TextView countConstrTV;
    TextView countStepsTV;
    TextView message_about_end_gameTV;


    /**
     * Список родительских узлов (групп)
     */
    private ArrayList<Map<String, String>> parentNodes = new ArrayList<>();
    /**
     * Список дочерних узлов (элементов)
     */
    private ArrayList<ArrayList<Map<String, String>>> chindNodes = new ArrayList<>();

    //-----------Константы
    private final static String ELV_PARENT_KEY = "parentKey"; //ключ для родительских узлов
    private final static String ELV_CHILD_KEY_NAME = "constructionKeyName";
    private final static String ELV_CHILD_KEY_MONEY = "constructionKeyMakesMoney";
    private final static String ELV_CHILD_KEY_CITIZENS = "constructionKeyCitizens";
    private final static String ELV_CHILD_KEY_TOURISTS = "constructionKeyTourists";
    private final static String ELV_CHILD_KEY_ECOLOGY = "constructionKeyEcology";
    private final static String ELV_CHILD_KEY_SCIENCE = "constructionKeyScience";
    private final static String ELV_CHILD_KEY_MOOD = "constructionKeyMOOD";
    private final static String ELV_CHILD_KEY_COST = "constructionKeyCOST";

    private ArrayList<View> allViews = new ArrayList<>();
    private int curGroupItem = -1;
    private int curChildItem = -1;

    //-----------------------------------------------------------------------------------

    /**
     * Константы
     */

    final static int VILLAGE = 1;
    final static int SMALL_TOWN = 2;
    final static int AVERAGE_CITY = 3;
    final static int BIG_CITY = 4;
    final static int MEGAPOLIS = 5;
    final static int CAPITAL = 6;

    /**
     * Эта активность.
     */
    private static CityActivity singleton;

    /**
     * Класс, что назначает названия постройкам
     */
    Find_Names find;


    /**
     * Флаг для отображения интерфейса.
     */
    boolean interface_visible = true;

    /**
     * Массив кнопок.
     */
    Button[] construction_buttons = new Button[28];


    /**
     * Массив id для кнопок.
     */
    int[] btnID = {R.id.btn1, R.id.btn2, R.id.btn3, R.id.btn4, R.id.btn5, R.id.btn6, R.id.btn7, R.id.btn8, R.id.btn9,
            R.id.btn10, R.id.btn11, R.id.btn12,
            R.id.btn13, R.id.btn14, R.id.btn15, R.id.btn16, R.id.btn17, R.id.btn18, R.id.btn19, R.id.btn20, R.id.btn21,
            R.id.btn22, R.id.btn23, R.id.btn24, R.id.btn25, R.id.btn26, R.id.btn27, R.id.btn28
    };

    /**
     * Флаги
     */


    /**
     * Была ли покупка здания.
     */
    boolean buy = false;

    /**
     * Была ли покупка клетки.
     */
    boolean buy_title = false;


    /**
     * Нажимали ли на город
     */
    static boolean isCityClick = false;

    /**
     * Заходили ли в настройки
     */
    static boolean isGenerSettings = false;

    /**
     * Ссылки.
     */
    ArrayList<Map<String, String>> my_array_list_for_purchase;
    Map<String, String> my_hash_map_for_purchase;
    double money_purcahse;
    Construction link_purchase;


    /**
     * Открыт ли вид города
     */
    public static boolean is_city_view = false;

    /**
     * Для музыки
     */
    static MediaPlayer mediaPlayer;

    /**
     * Переключатели
     */
    static boolean wasSwitchMusic;
    static boolean wasSwitchSound;


    /**
     * onCreate.
     *
     * @param savedInstanceState bundle
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        singleton = this;
        find = new Find_Names();
        res = getResources();
        object_atributes = res.getStringArray(R.array.object_attributes);
        all_types = res.getStringArray(R.array.all_types);
        all_types_city = res.getStringArray(R.array.all_types_city);
        city_attributes = res.getStringArray(R.array.city_attributes);

        cities = new ArrayList<>();
        myBuylist = new ArrayList<>();
        myTitlelist = new ArrayList<>();


        ReadFile();
        get_strings_recources();


        lockScreenOrientation();

        back_layout_initialization();
        menu_initialization();
        current_city_item_initialization();
        add_new_city_initialization();
        settings_initialization();
        messages_initialization();
        storm_event_initialization();
        game_end_initialization();


        SetAdapter();


        if (!is_city_view) {
            setContentView(generLL);
        } else {
            InitializationShopViews(); //обновили магазин
            fill_buy_constructions_list();
            check_bought_titles();
            myConstr_List_initialization();
            create_info_message();
            String strInfo = getString(R.string.constructions) +
                    getString(R.string.leftBracket) + myBuylist.size() + getString(R.string.rightBracket);
            labelConstructions.setText(strInfo);
            Initialization_Information_About_City(index);
        }

        try {
            what_open = getIntent().getExtras().getInt(CITY);
        } catch (NullPointerException npe) {

        }
        if (what_open != 0) {
            index = getIntent().getExtras().getInt(MY_INDEX);
            InitializationShopViews(); //обновили магазин
            fill_buy_constructions_list();
            check_bought_titles();
            myConstr_List_initialization();
            create_info_message();
            setContentView(shop_back);
            what_open = 0;
        }


    }


    @Override
    public void onBackPressed() {
        if (buy) {
            setContentView(shop_back);
        }
        if (buy_title) {
            buy_title = false;
            information.setText("");
            show_interface();
            nextStep.setVisibility(View.VISIBLE);
            settings.setVisibility(View.VISIBLE);
            get_title_button.setBackgroundResource(R.drawable.samosval_pic);
            clear_cost_of_title();
        }
        if (isCityClick) {
            Initialization_Information_About_City(index);
        }
        if (isGenerSettings) {
            setContentView(generLL);
        }

    }


    /**
     * Для получения активности
     *
     * @return CityActivity
     */
    public static CityActivity getInstance() {
        return singleton;
    }

    public static void setIs_city_view(boolean is_city_view) {
        CityActivity.is_city_view = is_city_view;
    }

    /**
     * Инициализация пустых фонов
     */
    public void back_layout_initialization() {
        inflater = this.getLayoutInflater();
        this.mainLL = (LinearLayout) inflater.inflate(R.layout.city_back, null, false);
        this.generLL = (LinearLayout) inflater.inflate(R.layout.menu_back, null, false);
        this.shop_back = (LinearLayout) inflater.inflate(R.layout.shop_back, null, false);
        this.myConstr_back = (LinearLayout) inflater.inflate(R.layout.my_contructions_back, null, false);
       this.noConstructions = (LinearLayout) inflater.inflate(R.layout.no_constructions, null, false);
        this.settings_back = (LinearLayout) inflater.inflate(R.layout.settings_back, null, false);
        this.choise_back = (LinearLayout) inflater.inflate(R.layout.choise_back, null, false);

        this.info_back = (LinearLayout) inflater.inflate(R.layout.message_back, null, false);
        this.event_back_storm = (LinearLayout) inflater.inflate(R.layout.event_back_storm, null, false);
        this.end_game_back = (LinearLayout) inflater.inflate(R.layout.end_game_back, null, false);
    }


    /**
     * Инициализация меню
     */
    public void menu_initialization() {
        this.LL_with_rc = (LinearLayout) inflater.inflate(R.layout.main_menu, null, false);
        main_screen = inflater.inflate(R.layout.main_menu, LL_with_rc, false);
        mRecyclerView = (RecyclerView) main_screen.findViewById(R.id.rv);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        generLL.addView(main_screen);
    }


    public void create_music() {
        mediaPlayer = MediaPlayer.create(this, R.raw.city_music);
        mediaPlayer.setLooping(true);
    }


    /**
     * Инициализация настроек
     */
    public void settings_initialization() {
        this.settingsLL = (LinearLayout) inflater.inflate(R.layout.settings_ll, null, false);
        set_view = inflater.inflate(R.layout.settings_ll, settingsLL, false);

        musicSwitch = (SwitchCompat) set_view.findViewById(R.id.music_switch_compat);
        soundsSwitch = (SwitchCompat) set_view.findViewById(R.id.sounds_switch_compat);
        if (wasSwitchMusic) {
            musicSwitch.setChecked(true);
        } else {
            musicSwitch.setChecked(false);
        }
        if (wasSwitchSound) {
            soundsSwitch.setChecked(true);
        } else {
            soundsSwitch.setChecked(false);
        }

        musicSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    create_music();
                    mediaPlayer.start();
                    wasSwitchMusic = true;
                } else {
                    mediaPlayer.stop();
                    mediaPlayer.reset();
                    wasSwitchMusic = false;
                }

            }
        });


        soundsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    wasSwitchSound = true;

                } else {
                    wasSwitchSound = false;
                }

            }
        });

        settings_back.addView(set_view);
    }


    /**
     * Инициализация окна выбора направления, уведомлений
     */
    public void messages_initialization() {
        //choice
        this.choiseLL = (LinearLayout) inflater.inflate(R.layout.choise, null, false);
        view_choise = inflater.inflate(R.layout.choise, choiseLL, false);

        lab_choise = (TextView) view_choise.findViewById(R.id.label_about_choise);
        lab_choise.setText(R.string.choose_direction);
        one = (Button) view_choise.findViewById(R.id.choise_one);
        two = (Button) view_choise.findViewById(R.id.choise_two);
        three = (Button) view_choise.findViewById(R.id.choise_three);
        three.setVisibility(View.INVISIBLE);

        choise_back.addView(view_choise);

        // Information
        this.message_informationLL = (LinearLayout) inflater.inflate(R.layout.message_ll, null, false);
        message_view = inflater.inflate(R.layout.message_ll, message_informationLL, false);


        this.infoLL = (LinearLayout) message_view.findViewById(R.id.infoLL);
        inform_messageTV = (TextView) message_view.findViewById(R.id.information_message);


        info_back.addView(message_view);

    }

    /**
     * Инициализация погодных событий
     */
    public void storm_event_initialization() {
        this.event_storm = (LinearLayout) inflater.inflate(R.layout.event_storm, null, false);
        stormView = inflater.inflate(R.layout.event_storm, event_storm, false);


        weather_eventTV = (TextView) stormView.findViewById(R.id.weather_eventTV);
        damageLabel = (TextView) stormView.findViewById(R.id.damageLabel);
        damageTV = (TextView) stormView.findViewById(R.id.damageTV);


        event_back_storm.addView(stormView);
    }

    /**
     * Инициализация конца игры
     */
    public void game_end_initialization() {
        this.end_game = (LinearLayout) inflater.inflate(R.layout.end_game, null, false);
        end_game_view = inflater.inflate(R.layout.end_game, end_game, false);

        what_happendTV = (TextView) end_game_view.findViewById(R.id.what_happendTV);
        reasonTV = (TextView) end_game_view.findViewById(R.id.reasonTV);
        countConstrTV = (TextView) end_game_view.findViewById(R.id.countConstrTV);
        countStepsTV = (TextView) end_game_view.findViewById(R.id.countStepsTV);

        message_about_end_gameTV = (TextView) end_game_view.findViewById(R.id.message_about_end_gameTV);

        end_game_back.addView(end_game_view);
    }


    /**
     * При долгом нажатии исчезает интерфейс.
     */
    final View.OnLongClickListener long_click = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            if (interface_visible) {
                hide_interface();
            } else {
                show_interface();
            }
            interface_visible = !interface_visible;
            return true;
        }
    };


    /**
     * Показать интерфейс
     */
    public void show_interface() {
        city_name.setVisibility(View.VISIBLE);
        current_city_type.setVisibility(View.VISIBLE);
        current_level.setVisibility(View.VISIBLE);
        current_city_count_of_money.setVisibility(View.VISIBLE);
        current_city_count_of_citiens.setVisibility(View.VISIBLE);
        current_city_count_of_tourists.setVisibility(View.VISIBLE);
        current_city_ecology.setVisibility(View.VISIBLE);
        current_science.setVisibility(View.VISIBLE);
        current_mood.setVisibility(View.VISIBLE);
    }

    /**
     * Спрятать интерфейс
     */
    public void hide_interface() {
        city_name.setVisibility(View.INVISIBLE);
        current_city_type.setVisibility(View.INVISIBLE);
        current_level.setVisibility(View.INVISIBLE);
        current_city_count_of_money.setVisibility(View.INVISIBLE);
        current_city_count_of_citiens.setVisibility(View.INVISIBLE);
        current_city_count_of_tourists.setVisibility(View.INVISIBLE);
        current_city_ecology.setVisibility(View.INVISIBLE);
        current_science.setVisibility(View.INVISIBLE);
        current_mood.setVisibility(View.INVISIBLE);
    }


    /**
     * Инициализация города
     */
    public void current_city_item_initialization() { //3

        //а тельчике то одно значение!
        this.city_view = (LinearLayout) inflater.inflate(R.layout.city_view_layout, null, false);
        view = inflater.inflate(R.layout.city_view_layout, city_view, false);

        //на планше/
        //this.city_view = (LinearLayout) inflater.inflate(R.layout.city_view_size, null, false);
        // view = inflater.inflate(R.layout.city_view_size, city_view, false);


        city_view_rl = (RelativeLayout) view.findViewById(R.id.city_view_rl);
        city_name = (TextView) view.findViewById(R.id.current_city_name);
        current_city_count_of_money = (TextView) view.findViewById(R.id.current_count_of_money);
        current_city_count_of_citiens = (TextView) view.findViewById(R.id.current_count_of_citiens);
        current_city_count_of_tourists = (TextView) view.findViewById(R.id.current_count_of_tourists);
        current_city_ecology = (TextView) view.findViewById(R.id.current_ecology);
        current_science = (TextView) view.findViewById(R.id.current_science);
        current_mood = (TextView) view.findViewById(R.id.current_mood);
        current_level = (TextView) view.findViewById(R.id.current_city_level);
        current_step = (TextView) view.findViewById(R.id.current_step);
        current_city_type = (TextView) view.findViewById(R.id.current_city_type);
        city_grid = (GridLayout) view.findViewById(R.id.grid_city);

        information = (TextView) view.findViewById(R.id.information);
        nextStep = (Button) view.findViewById(R.id.next_step);

        get_title_button = (Button) view.findViewById(R.id.get_title);
        get_title_button.setOnClickListener(buy_title_click);

        settings = (Button) view.findViewById(R.id.current_settings);
        effect = (TextView) view.findViewById(R.id.taxes_int);
        effect_info = (TextView) view.findViewById(R.id.effect_info);


        for (int i = 0; i < construction_buttons.length; i++) {
            construction_buttons[i] = (Button) view.findViewById(btnID[i]);

            // construction_buttons[i].setText(String.valueOf(i));

            construction_buttons[i].setOnLongClickListener(long_click);
            construction_buttons[i].setOnClickListener(on_click);


        }


        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CityActivity.setIs_city_view(false);
                isCityClick = false;
                isGenerSettings = false;
                Intent intent = new Intent(CityActivity.this, City_Menu.class);
                intent.putExtra(MY_INDEX, index);
                startActivity(intent);
            }
        });

        mainLL.addView(view);


    }


    /**
     * Диалоговое окно добавления.
     */
    public void add_new_city_initialization() { //4

        this.builder = new AlertDialog.Builder(this);
        this.dialogView = inflater.inflate(R.layout.adding, null, false);

        this.new_city_name = (EditText) this.dialogView.findViewById(R.id.my_city_new_name);

        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                setContentView(generLL);
                ((ViewGroup) (dialogView.getParent())).removeAllViews();

            }
        });
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {

                if (!new_city_name.getText().toString().isEmpty()) {
                    City c = new City();

                    c.setTYPES(res.getStringArray(R.array.all_types_city));
                    c.setLEVELS(res.getStringArray(R.array.levels_of_city));

                    cities.add(c);

                    c.setName_of_city(new_city_name.getText().toString());
                    adapter.notifyDataSetChanged();

                    setContentView(generLL);
                    new_city_name.setText("");
                    ((ViewGroup) (dialogView.getParent())).removeAllViews();
                } else {
                    makeSnackBar(main_screen, res.getString(R.string.correct));
                    adapter.notifyDataSetChanged();

                    setContentView(generLL);
                    new_city_name.setText("");
                    ((ViewGroup) (dialogView.getParent())).removeAllViews();
                }
            }
        });

        builder.setView(dialogView);

    }


    /**
     * Поиск купленных объектов
     */
    public void fill_buy_constructions_list() {

        myBuylist.clear();

        for (Construction c : cities.get(index).getConstructions()) {
            if (c.isWas_bought()) {
                myBuylist.add(c);
            }
        }
    }

    /**
     * Поиск купленных объектов
     */
    public void check_bought_titles() {
        myTitlelist.clear();

        for (Title t : cities.get(index).getTitles()) {
            if (!t.isOccupied) {
                myTitlelist.add(t);
            }
        }
    }


    /**
     * Инициализация списка конструкций
     */
    public void myConstr_List_initialization() {
        this.myConstr_LL = (LinearLayout) inflater.inflate(R.layout.my_constructions_list, null, false);
        this.myConstr_view = inflater.inflate(R.layout.my_constructions_list, myConstr_LL, false);

        this.myConstructions_listView = (ListView) myConstr_view.findViewById(R.id.myConstructonsListView);
        this.labelConstructions = (TextView) myConstr_view.findViewById(R.id.labConstr);
        this.quest = (TextView) myConstr_view.findViewById(R.id.quest_text);


        constr_adapter =
                new ArrayAdapter<Construction>(this, R.layout.font, myBuylist) {
                    @Override
                    public View getView(int position, View convertView, ViewGroup parent)//виджет для указанного элемента данных
                    {
                        Construction c = getItem(position);

                        if (convertView == null) {
                            LayoutInflater exInflater = getLayoutInflater();
                            convertView = exInflater.inflate(R.layout.construction_item, null);
                        }
                        if (!CityActivity.this.allViews.contains(convertView)) {
                            CityActivity.this.allViews.add(convertView);
                        }


                        TextView tvName = (TextView) convertView.findViewById(R.id.tvName);
                        TextView tvMoney = (TextView) convertView.findViewById(R.id.tvMoney);
                        TextView tvCitiziens = (TextView) convertView.findViewById(R.id.tvCitizens);
                        TextView tvTourists = (TextView) convertView.findViewById(R.id.tvTourists);
                        TextView tvEcology = (TextView) convertView.findViewById(R.id.tvEcology);
                        TextView tvScience = (TextView) convertView.findViewById(R.id.tvScience);
                        TextView tvMood = (TextView) convertView.findViewById(R.id.tvMood);
                        ImageView ivImage = (ImageView) convertView.findViewById(R.id.iv_image);


                        String strName = myBuylist.get(position).getName_of_object() + object_atributes[0];
                        String strMoney = myBuylist.get(position).getMakes_money() + object_atributes[1];
                        String strCitizens = myBuylist.get(position).getEffect_on_citizens() + object_atributes[2];
                        String strTourists = myBuylist.get(position).getEffect_on_tourists() + object_atributes[3];
                        String strEcology = myBuylist.get(position).getEffect_on_ecology() + object_atributes[4];
                        String strScience = myBuylist.get(position).getEffect_on_science() + object_atributes[5];
                        String strMood = myBuylist.get(position).getEffect_on_mood() + object_atributes[6];

                        tvName.setText(strName);
                        tvMoney.setText(strMoney);
                        tvCitiziens.setText(strCitizens);
                        tvTourists.setText(strTourists);
                        tvEcology.setText(strEcology);
                        tvScience.setText(strScience);
                        tvMood.setText(strMood);
                        ivImage.setBackgroundResource(myBuylist.get(position).getConstruction_pic());


                        return convertView;


                    }
                };


        this.myConstructions_listView.setAdapter(constr_adapter);

        this.myConstructions_listView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        isCityClick = false;

                        constr_adapter.notifyDataSetChanged();
                        Initialization_Information_About_City(index);

                    }
                }
        );

        String myInfo = getString(R.string.constructions) + "(1)";
        labelConstructions.setText(myInfo);

        myConstr_back.addView(myConstr_view);
    }


    /**
     * SnackBar
     */
    public void makeSnackBar(View view, String info) {
        snackbar = Snackbar.make(view, info, Snackbar.LENGTH_SHORT)
                .setAction("Action", null);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundResource(R.color.colorStroke);
        snackbar.show();
    }

    /**
     * Открытие города, инициализация инфы о нём и магазина
     *
     * @param pos - индекс
     */
    public void OpenCity(int pos) {

        index = pos;

        check_construction();
        Initialization_Information_About_City(pos);
        InitializationShopViews();
        fill_buy_constructions_list();
        check_bought_titles();
        myConstr_List_initialization();

        for (int i = 0; i < cities.get(index).getTitles().length; i++) {
            construction_buttons[i].setTag(cities.get(index).getTitles()[i]);

        }

        setIs_city_view(true); //открыли город
        init_city_view(); // заполнить карту картинками
        create_info_message();
        isGenerSettings = false;

        if (cities.get(index).getCity_level() == 1) {
            quest.setText(R.string.lvl1);
        }

        if(cities.get(index).getProgress().isFirst_open())
        {
            setContentView(R.layout.educational);
        }

    }

    /**
     * Нажатие на кнопку
     */
    final View.OnClickListener on_click = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            if (buy) {
                button_purchase(v);
            } else if (buy_title) {
                buy_title(v);

            } else {
                cityClick();
            }
        }
    };


    /**
     * Инициализация layout-ов магазина, вызывается при открытии города
     */
    public void InitializationShopViews() {
        this.shop_LL = (LinearLayout) inflater.inflate(R.layout.shop_layout, null, false);
        shop_view = inflater.inflate(R.layout.shop_layout, shop_LL, false);

        ELV = (ExpandableListView) shop_view.findViewById(R.id.elvOne);
        count_of_money = (TextView) shop_view.findViewById(R.id.myCountMoney);
        shop_back_btn = (Button) shop_view.findViewById(R.id.back_button);


        String str_money = String.valueOf(new BigDecimal(cities.get(index).getCount_of_money()).setScale(1, RoundingMode.UP).doubleValue() + " м.");
        count_of_money.setText(str_money);
        InitializationShopList();
        Initialization_Shop(index); //получаем магазин города
    }


    /**
     * Инициализация категорий магазина
     */
    public void InitializationShopList() {
        for (int i = 0; i < all_types.length; i++) {
            HashMap<String, String> HM = new HashMap<>();
            HM.put(ELV_PARENT_KEY, all_types[i]);
            this.parentNodes.add(HM);
        }

        //---------Маршрутизация данных для родительских узлов

        String[] parentNodeFrom = {ELV_PARENT_KEY};
        int[] parentNodeTo = {android.R.id.text1};

        //----"Маршрутизация" данных для дочерних узлов

        final String[] childNodeFrom =
                {
                        ELV_CHILD_KEY_NAME,
                        ELV_CHILD_KEY_MONEY,
                        ELV_CHILD_KEY_CITIZENS,
                        ELV_CHILD_KEY_TOURISTS,
                        ELV_CHILD_KEY_ECOLOGY,
                        ELV_CHILD_KEY_SCIENCE,
                        ELV_CHILD_KEY_MOOD,
                        ELV_CHILD_KEY_COST

                };

        int[] childNodeTo =
                {
                        R.id.tvName,
                        R.id.tvMoney,
                        R.id.tvCitizens,
                        R.id.tvTourists,
                        R.id.tvEcology,
                        R.id.tvScience,
                        R.id.tvMood,
                        R.id.tvCost
                };

        final SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(
                this,
                this.parentNodes,
                android.R.layout.simple_expandable_list_item_1,
                parentNodeFrom,
                parentNodeTo,
                this.chindNodes,
                R.layout.construction_item,
                childNodeFrom,
                childNodeTo
        ) {
            @Override
            public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
                View view = super.getChildView(groupPosition, childPosition, isLastChild, convertView, parent);
                if (!allViews.contains(view)) {
                    allViews.add(view);
                }
                return view;
            }
        };

        this.ELV.setAdapter(adapter);

        this.ELV.setOnChildClickListener(
                new ExpandableListView.OnChildClickListener() {
                    @Override
                    public boolean onChildClick(
                            ExpandableListView parent,
                            View v,
                            int groupPosition,
                            int childPosition,
                            long id) {


                        //запоминаем текущий выбранный элемент
                        curGroupItem = groupPosition;
                        curChildItem = childPosition;

                        ArrayList<Map<String, String>> AL2 = chindNodes.get(curGroupItem);
                        Map<String, String> HM2 = AL2.get(curChildItem);


                        message_about_purchase(AL2, HM2);

                        return true;
                    }

                });

        shop_back.addView(shop_view);

    }


    /**
     * Инициализация магазина конкретного города
     *
     * @param pos - индекс города
     */
    public void Initialization_Shop(int pos) {

        List<Construction> cList = cities.get(pos).getConstructions();
        for (int i = 0; i < this.parentNodes.size(); i++) {
            ArrayList<Map<String, String>> childNode = new ArrayList<>();

            for (int j = 0; j < cities.get(pos).getConstructions().size(); j++) {
                if ((!cList.get(j).isWas_bought()) && (cList.get(j).isAvailable())) {
                    if (cList.get(j).idCategory != i)
                        continue;
                    Construction myConstruction = cities.get(pos).getConstructions().get(j).cloneConstruction();
                    HashMap<String, String> HM = new HashMap<>();
                    HM.put(ELV_CHILD_KEY_NAME, myConstruction.name_of_object);
                    String sign = "";
                    if (myConstruction.makes_money > 0) {
                        sign = "+";
                    }
                    HM.put(ELV_CHILD_KEY_MONEY, String.valueOf(sign + myConstruction.makes_money) + object_atributes[1]);


                    String effect_on_citiziens = myConstruction.effect_on_citizens + object_atributes[2];
                    make_shop_string(HM, myConstruction.effect_on_citizens, ELV_CHILD_KEY_CITIZENS, effect_on_citiziens);

                    String effect_on_tourists = myConstruction.effect_on_tourists + object_atributes[3];
                    make_shop_string(HM, myConstruction.effect_on_tourists, ELV_CHILD_KEY_TOURISTS, effect_on_tourists);


                    String effect_on_ecology = myConstruction.effect_on_ecology + object_atributes[4];
                    make_shop_string(HM, myConstruction.effect_on_ecology, ELV_CHILD_KEY_ECOLOGY, effect_on_ecology);

                    String effect_on_science = myConstruction.effect_on_science + object_atributes[5];
                    make_shop_string(HM, myConstruction.effect_on_science, ELV_CHILD_KEY_SCIENCE, effect_on_science);

                    String effect_on_mood = myConstruction.effect_on_mood + object_atributes[6];
                    make_shop_string(HM, myConstruction.effect_on_mood, ELV_CHILD_KEY_MOOD, effect_on_mood);

                    HM.put(ELV_CHILD_KEY_COST, String.valueOf(object_atributes[7] + myConstruction.cost));
                    childNode.add(HM);

                }

            }

            this.chindNodes.add(childNode);
        }


    }


    /**
     * Формируется строка для магазина
     *
     * @param HM      HashMap
     * @param effect  Эффект от постройки
     * @param ELV_KEY Её ключ
     * @param value   Строковое значение
     */
    public void make_shop_string(Map<String, String> HM, int effect, String ELV_KEY, String value) {
        String sign = "";
        if (effect > 0) {
            sign = "+";
        }

        if (effect != 0) {
            HM.put(ELV_KEY, String.valueOf(sign + value));
        }
    }


    /**
     * Уведомление о покупке.
     *
     * @param AL2 Список
     * @param HM2 Объект
     */
    public void message_about_purchase(ArrayList<Map<String, String>> AL2, Map<String, String> HM2) {
        double money = cities.get(index).getCount_of_money();
        for (Construction mpurchase : cities.get(index).getConstructions()) {
            if (mpurchase.getName_of_object().equals(HM2.get(ELV_CHILD_KEY_NAME))) {
                if (money >= mpurchase.getCost()) {

                    hide_interface();
                    nextStep.setVisibility(View.INVISIBLE);
                    get_title_button.setVisibility(View.INVISIBLE);
                    Initialization_Information_About_City(index);
                    information.setText(R.string.choose);


                    buy = true;

                    my_array_list_for_purchase = AL2;
                    my_hash_map_for_purchase = HM2;
                    money_purcahse = money;
                    link_purchase = mpurchase;


                } else {
                    makeSnackBar(shop_view, res.getString(R.string.nomoney));
                }
            }
        }
    }


    /**
     * Состоялась покупка конструкуии при нажатии на кнопку
     *
     * @param v кнопка
     */
    public void button_purchase(View v) {
        Title[] titles = cities.get(index).getTitles();
        for (int i = 0; i < construction_buttons.length; i++) {
            if ((Title) v.getTag() == titles[i]) {
                if ((titles[i].isAvailable() && !titles[i].isOccupied)) {

                    if (titles[i].isWater()) {
                        if (link_purchase.getIdObject() == 39
                                || link_purchase.getIdObject() == 212 || link_purchase.getIdObject() == 413) {
                            purchase_init(titles[i], i);

                        } else if (titles[i].isWater() && link_purchase.getIdObject() != 39
                                || link_purchase.getIdObject() != 212 || link_purchase.getIdObject() != 413) {
                            makeSnackBar(view, res.getString(R.string.not_water));
                        }
                    } else if ((link_purchase.getIdObject() == 39
                            || link_purchase.getIdObject() == 212 || link_purchase.getIdObject() == 413) && !titles[i].isWater()) {
                        makeSnackBar(view, res.getString(R.string.need_water));
                    }

                    if (!titles[i].isWater() && link_purchase.getIdObject() != 39
                            && link_purchase.getIdObject() != 212 && link_purchase.getIdObject() != 413) {
                        purchase_init(titles[i], i);
                    }


                } else if (!titles[i].isAvailable() && !titles[i].isOccupied()) {
                    makeSnackBar(view, res.getString(R.string.stop));
                } else if (titles[i].isOccupied() && !titles[i].isAvailable()) {
                    makeSnackBar(view, res.getString(R.string.clean));
                }

            }
        }
    }

    /**
     * Инициализация постройки и клетки
     *
     * @param t клетка
     * @param i индекс
     */
    public void purchase_init(Title t, int i) {
        was_purchase(my_array_list_for_purchase, my_hash_map_for_purchase, money_purcahse, link_purchase, i);
        t.setLocation(i); //обозначили место кнопке
        t.setAvailable(false); // сделали клетку недоступной

        construction_buttons[i].setBackgroundResource(link_purchase.getConstruction_pic());
        t.setTitle_pic(link_purchase.getConstruction_pic());
        check_water_constructions(t, i);


        buy = false;
        updateInformation(index);
    }

    /**
     * Проверим на краю ли водные
     */
    public void check_water_constructions(Title t, int i) {
        if (link_purchase.getIdObject() == 212 && t.getLocation() == 22) {
            construction_buttons[i].setBackgroundResource(R.drawable.ges_edge);
            t.setTitle_pic(R.drawable.ges_edge);
            link_purchase.setConstruction_pic(R.drawable.ges_edge);
        }
        if (link_purchase.getIdObject() == 39 && t.getLocation() == 22) {
            construction_buttons[i].setBackgroundResource(R.drawable.port_edge);
            t.setTitle_pic(R.drawable.port_edge);
            link_purchase.setConstruction_pic(R.drawable.port_edge);
        }
        if (link_purchase.getIdObject() == 413 && t.getLocation() == 22) {
            construction_buttons[i].setBackgroundResource(R.drawable.scsc_edge);
            t.setTitle_pic(R.drawable.scsc_edge);
            link_purchase.setConstruction_pic(R.drawable.scsc_edge);
        }
    }

    /**
     * Осуществление покупки
     *
     * @param AL2       список
     * @param HM2       список
     * @param money     деньги
     * @param mpurchase покупка
     * @param loc       локация
     */
    public void was_purchase(ArrayList<Map<String, String>> AL2, Map<String, String> HM2, double money, Construction mpurchase, int loc) {
        cities.get(index).setCount_of_money(money - mpurchase.getCost());
        AL2.remove(HM2);
        mpurchase.setWas_bought(true);
        mpurchase.setLocation(loc);
        myBuylist.add(mpurchase);

        if (wasSwitchSound) {
            MediaPlayer create_constr = MediaPlayer.create(this, R.raw.create_constr);
            create_constr.start();
        }

        setIs_city_view(true);

        show_interface();
        nextStep.setVisibility(View.VISIBLE);
        get_title_button.setVisibility(View.VISIBLE);
        information.setText("");

        adapter.notifyDataSetChanged();

        curGroupItem = -1;
        curChildItem = -1;


    }


    /**
     * Нажатие на кнопку с клеткой, уведомление о покупке клетки
     */
    final View.OnClickListener buy_title_click = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
            if (!buy_title) {
                buy_title = true;
                hide_interface();
                nextStep.setVisibility(View.INVISIBLE);
                settings.setVisibility(View.INVISIBLE);
                get_title_button.setVisibility(View.VISIBLE);
                Initialization_Information_About_City(index);
                get_title_button.setBackgroundResource(R.drawable.samosval_no_pic);
                information.setText(R.string.buy_title);
                for (int i = 0; i < construction_buttons.length; i++) {
                    Title t = cities.get(index).getTitles()[i];
                    if (t.isOccupied && !t.isAvailable()) {
                        String cost = String.valueOf(t.getCost());
                        construction_buttons[i].setText(cost);
                    }
                }

            } else {
                buy_title = false;
                information.setText("");
                show_interface();
                nextStep.setVisibility(View.VISIBLE);
                settings.setVisibility(View.VISIBLE);
                get_title_button.setBackgroundResource(R.drawable.samosval_pic);
                clear_cost_of_title();
            }
        }
    };

    /**
     * Убрать цену с не застроенной клетки.
     */
    public void clear_cost_of_title() {
        for (int i = 0; i < construction_buttons.length; i++) {
            Title t = cities.get(index).getTitles()[i];
            if (t.isOccupied) {
                construction_buttons[i].setText("");
            }
        }
    }

    /**
     * Покупка клетки
     *
     * @param v клетка
     */
    public void buy_title(View v) {
        double money = cities.get(index).getCount_of_money();
        Title[] titles = cities.get(index).getTitles();
        for (int i = 0; i < construction_buttons.length; i++) {
            if ((Title) v.getTag() == titles[i]) {
                if (!titles[i].isAvailable() && titles[i].isOccupied) {
                    if (cities.get(index).getCount_of_money() >= titles[i].getCost()) {

                        cities.get(index).setCount_of_money(money - titles[i].getCost());


                        titles[i].setAvailable(true);
                        titles[i].setOccupied(false);


                        if (titles[i].isWater()) {
                            if (titles[i].getId() == 21) {
                                construction_buttons[i].setBackgroundResource(R.drawable.water);
                                titles[i].setTitle_pic(R.drawable.water);
                            } else if (titles[i].id == 22) {
                                construction_buttons[i].setBackgroundResource(R.drawable.water_edge);
                                titles[i].setTitle_pic(R.drawable.water_edge);
                            }
                        } else {
                            construction_buttons[i].setBackgroundResource(R.drawable.default_pic); //default_pic
                            titles[i].setTitle_pic(R.drawable.default_pic);
                        }

                        construction_buttons[i].setText("");
                        information.setText("");
                        if (wasSwitchSound) {
                            MediaPlayer create_constr = MediaPlayer.create(this, R.raw.for_title);
                            create_constr.start();
                        }

                    } else {
                        makeSnackBar(view, res.getString(R.string.nomoney));
                        information.setText("");
                    }

                    clear_cost_of_title();

                    nextStep.setVisibility(View.VISIBLE);
                    get_title_button.setVisibility(View.VISIBLE);
                    settings.setVisibility(View.VISIBLE);
                    show_interface();

                    get_title_button.setBackgroundResource(R.drawable.samosval_pic);
                    buy_title = false;
                    Initialization_Information_About_City(index);
                } else if (!titles[i].isAvailable() && !titles[i].isOccupied) {
                    makeSnackBar(view, res.getString(R.string.yet));
                } else if (titles[i].isAvailable() && !titles[i].isOccupied) {
                    makeSnackBar(view, res.getString(R.string.yet));
                }


            }
        }
    }


    /**
     * Создание объектов для тестирования игры
     */
    public void TestObjects() {
        City city1 = new City();
        City city2 = new City();
        City city3 = new City();
        city1.setName_of_city("City");
        city2.setName_of_city("Odessa");
        city3.setName_of_city("Dnepr");


        get_strings_recources();

        cities.add(city1);
        cities.add(city2);
        cities.add(city3);
    }

    /**
     * Назначение строк
     */
    public void get_strings_recources() {
        for (City c : cities) {
            c.setTYPES(res.getStringArray(R.array.all_types_city));
            c.setLEVELS(res.getStringArray(R.array.levels_of_city));
        }
    }

    /**
     * Назначение адаптера для меню
     */
    public void SetAdapter() {
        adapter = new RVAdapter(cities);
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * Запрет поворота
     */
    private void lockScreenOrientation() {
        int currentOrientation = getResources().getConfiguration().orientation;
        if (currentOrientation == Configuration.ORIENTATION_PORTRAIT) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
    }


    /**
     * Обработчики нажатия а кнопку
     *
     * @param v - элемент
     */
    public void btnClick(View v) {
        switch (v.getId()) {
            case R.id.CreateNew: {

                dialog = builder.create();
                dialog.show();
                dialog.setCancelable(false);
                break;
            }
            case R.id.Settings: {
                setContentView(settings_back);
                isGenerSettings = true;

                break;
            }
            case R.id.back_button: {
                show_interface();
                nextStep.setVisibility(View.VISIBLE);
                get_title_button.setVisibility(View.VISIBLE);
                information.setText("");
                setIs_city_view(true);
                buy = false;
                Initialization_Information_About_City(index);
                break;
            }
            case R.id.rulesBTN:
            {
                isGenerSettings = true;
                setContentView(R.layout.rules);
                break;
            }
        }
    }

    /**
     * Обработчики нажатия на область
     *
     * @param v - элемент
     */
    public void layoutClick(View v) {
        switch (v.getId()) {
            case R.id.constructions_back_ll: {
                Initialization_Information_About_City(index);
                break;
            }
            case R.id.no_constr_ll: {
                Initialization_Information_About_City(index);
                break;
            }
            case R.id.infoLL: {
                Initialization_Information_About_City(index);
                makeSnackBar(view, res.getString(R.string.newBuildings));
                break;
            }
            case R.id.event_storm: {
                Initialization_Information_About_City(index);
                break;

            }
            case R.id.end_game: {
                setContentView(generLL);
                break;
            }
            case R.id.educ:
            {
                Initialization_Information_About_City(index);
                makeSnackBar(view, res.getString(R.string.lvl1));
                cities.get(index).getProgress().setFirst_open(false);
                break;
            }
        }
    }


    /**
     * Что происходит с каждым следующим шагом
     *
     * @param v виджет кнопки
     */
    public void nextClick(View v) {
        City currentCity = cities.get(index);
        if (v.getId() == R.id.next_step) {

            if (!currentCity.getProgress().isVictory()) {

                int step = currentCity.getStep();
                step++;
                currentCity.setStep(step);
                currentCity.getProgress().Step(currentCity);
                currentCity.getProgress().calculate_mood(step, currentCity);
            }

            Log.d("==checkLevel", " " + cities.get(index).getCity_level());
            Log.d("==checkConstrSize", " " + myBuylist.size());
            Log.d("==checkTitleSize", " " + myTitlelist.size());

            updateInformation(index);
            if (!currentCity.getProgress().isVictory()) {
                createEvent();
                checkGameOver();
            }


        }
    }


    /**
     * Удаление города
     *
     * @param pos - его индекс
     */
    public void startDeleting(int pos) {
        deleting_city();
        index = pos;
        dialog_del = builder_del.create();
        dialog_del.show();
        dialog_del.setCancelable(false);
    }

    /**
     * Создание окна для удаления города
     */
    public void deleting_city() {
        this.builder_del = new AlertDialog.Builder(this);
        this.dialogView_del = inflater.inflate(R.layout.deleting, null, false);
        builder_del.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                adapter.notifyDataSetChanged();
            }
        });
        builder_del.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                cities.remove(index);
                adapter.notifyDataSetChanged();
            }
        });
        builder_del.setView(dialogView_del);
    }

    /**
     * Инициализация полей города
     *
     * @param pos - индекс
     */
    public void Initialization_Information_About_City(int pos) {


        updateInformation(pos);

        init_city_view();
        create_info_message();


        for (int i = 0; i < cities.get(index).getTitles().length; i++) {
            construction_buttons[i].setTag(cities.get(index).getTitles()[i]);
        }


        setContentView(mainLL);
    }


    /**
     * Назначить на кнопки картинки
     */
    public void init_city_view() { //Срабатывает правильно

        for (int i = 0; i < construction_buttons.length; i++) {
            Title t = cities.get(index).getTitles()[i];
            construction_buttons[t.getId()].setBackgroundResource(t.getTitle_pic());

        }

        for (Construction c : myBuylist) {
            for (Title t : cities.get(index).getTitles()) {
                if (t.getLocation() == c.getLocation()) {
                    construction_buttons[c.getLocation()].setBackgroundResource(c.getConstruction_pic());
                }
            }
        }

    }


    /**
     * Обновляем поля с информацией
     *
     * @param pos индекс текущего города
     */
    public void updateInformation(int pos) {

        index = pos;

        check_construction();
        city_name.setText(cities.get(pos).getName_of_city());

        String strMoney = city_attributes[0] + new BigDecimal(cities.get(pos).getCount_of_money()).setScale(1, RoundingMode.UP).doubleValue();
        String strCitiziens = city_attributes[1] + cities.get(pos).getCount_of_citiens();
        String strTourists = city_attributes[2] + cities.get(pos).getCount_of_tourists();
        String strEcology = city_attributes[3] + cities.get(pos).getState_of_ecology();
        String strScience = city_attributes[4] + cities.get(pos).getState_of_science();
        String strMood = city_attributes[5] + cities.get(pos).getMood();
        double strTax;
        double newDoubleTaxes;
        fill_buy_constructions_list();
        switch (cities.get(index).getType_of_city()) {
            case 1: {
                strTax = myBuylist.size() * cities.get(pos).getProgress().getConstr_money();
                newDoubleTaxes = new BigDecimal(strTax).setScale(1, RoundingMode.UP).doubleValue();
                effect.setText(String.valueOf("+" + newDoubleTaxes));
                effect_info.setText(R.string.effect_ind);
                break;
            }
            case 2: {
                strTax = cities.get(pos).getCount_of_tourists() * cities.get(pos).getProgress().getTourist_money();
                newDoubleTaxes = new BigDecimal(strTax).setScale(1, RoundingMode.UP).doubleValue();
                effect.setText(String.valueOf("+" + newDoubleTaxes));
                effect_info.setText(R.string.effect_res);
                break;
            }
            case 3: {
                strTax = (int) (cities.get(pos).getState_of_science() * cities.get(pos).getProgress().getScience_eco());
                effect.setText(String.valueOf("+" + strTax));
                effect_info.setText(R.string.effect_sci);
                break;
            }
            case 0: {
                strTax = cities.get(pos).getCount_of_citiens() * cities.get(pos).getProgress().getTax();
                newDoubleTaxes = new BigDecimal(strTax).setScale(1, RoundingMode.UP).doubleValue();
                effect.setText(String.valueOf("+" + newDoubleTaxes));
                effect_info.setText(R.string.effect_def);
                break;
            }
        }


        current_city_count_of_money.setText(strMoney);
        current_city_count_of_citiens.setText(strCitiziens);
        current_city_count_of_tourists.setText(strTourists);
        current_city_ecology.setText(strEcology);
        current_science.setText(strScience);
        current_mood.setText(strMood);
        current_level.setText(cities.get(pos).getLevels());
        current_step.setText(String.valueOf(cities.get(pos).getStep()));
        current_city_type.setText(cities.get(pos).getTypes());


        for (Construction constr : cities.get(pos).getConstructions()) {

            find.find_name(constr.getIdObject(), constr);
        }


    }

    /**
     * Открытие имеющихся построек города
     */
    public void cityClick() {
        if (myBuylist.isEmpty()) {
            setContentView(noConstructions);
        } else {
            String strInfo = getString(R.string.constructions) +
                    getString(R.string.leftBracket) + myBuylist.size() + getString(R.string.rightBracket);
            labelConstructions.setText(strInfo);
            isCityClick = true;


            switch (cities.get(index).getCity_level()) {
                case 1: {
                    quest.setText(R.string.lvl1);
                    break;
                }
                case 2: {
                    quest.setText(R.string.lvl2);
                    break;
                }
                case 3: {
                    quest.setText(R.string.lvl3);
                    break;
                }
                case 4: {
                    quest.setText(R.string.lvl4);
                    break;
                }
                case 5: {
                    quest.setText(R.string.lvl5);
                    break;
                }
                case 6: {
                    quest.setText(R.string.lvl6);
                    break;
                }
            }


            setContentView(myConstr_back);
        }

    }

    /**
     * Чтение из файла
     */
    public void ReadFile() {

        if (this.isExtStorageAvailableForReading()) {
            File extStorageDir = Environment.getExternalStorageDirectory();
            //extStorageDir.mkdirs();
            Log.d("====", "External Storage Directory: " + extStorageDir);
            File fileName = new File(extStorageDir, "CityMaker.txt");
            if (!fileName.exists()) {
                TestObjects();//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            }


            try {
                FileInputStream FIS = new FileInputStream(fileName);
                ObjectInputStream OIS = new ObjectInputStream(FIS);
                while (FIS.available() > 0) {
                    Object obj = OIS.readObject();

                    if (obj instanceof City) {
                        cities.add((City) obj);

                    }

                }

                OIS.close();
            } catch (IOException | ClassNotFoundException e) {
                Log.d("====ERROR==", e.getMessage());
            }

        } else {
            Log.d("===", "Not Available for Reading");
        }
    }

    /**
     * запись в файл когда активность уходит с переднего плана
     */
    @Override
    public void onPause() {
        super.onPause();
        if (this.isExStorageAvailableForWriting()) {
            File extStorageDir = Environment.getExternalStorageDirectory();
            Log.d("====", "External Storage Directory: " + extStorageDir);


            File fileName = new File(extStorageDir, "CityMaker.txt");


            try {
                FileOutputStream FOS = new FileOutputStream(fileName);
                ObjectOutputStream OOS = new ObjectOutputStream(FOS);

                for (City obj : cities) {
                    OOS.writeObject(obj);
                    Log.d("==Записали", "" + obj.toString());

                }


                OOS.close();

            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        } else {
            Log.d("==========", "External Storage NOT Available for Writing");
        }
    }


    /**
     * Проверка доступно ли хранилище для записи
     *
     * @return доступно ли
     */

    private boolean isExStorageAvailableForWriting() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }

    /**
     * Проверка доступно ли хранилище для чтения
     *
     * @return доступно ли
     */
    private boolean isExtStorageAvailableForReading() {
        String state = Environment.getExternalStorageState();
        return Environment.MEDIA_MOUNTED.equals(state);
    }
//-----------------------------------------------------------------------------------------------------


    /**
     * Работа со списком всех построек. Проверяет при каждом клике
     */
    public void check_construction() {

        int current_city_level = cities.get(index).getCity_level();
        get_new_constructions(current_city_level);

        count_of_constructions();

    }


    /**
     * Проверка наличия построек в магазине
     *
     * @param level уровень
     */
    public void get_new_constructions(int level) {
        List<Construction> constr = cities.get(index).getConstructions();
        int type = cities.get(index).getType_of_city();
        for (Construction c : constr) {
            if ((!c.isAvailable()) && (!c.isWas_bought()) && (c.getConstruction_level() == level) && (type == c.getIdCategory() || c.getIdCategory() == 0)) {
                c.setAvailable(true);
            }
        }
    }

    //-------------------------------------------VICTORY AND GAME OVER---------------------------------------------------------

    public void checkGameOver() {
        what_happendTV.setText(R.string.GameOver);
        countConstrTV.setText(String.valueOf(myBuylist.size()));
        countStepsTV.setText(String.valueOf(cities.get(index).getStep()));
        message_about_end_gameTV.setText(R.string.message_about_game_over);
        end_game_back.setBackgroundResource(R.drawable.game_over);
        if (cities.get(index).getCount_of_money() <= -150) {
            cities.get(index).getProgress().setGameOver(true);
            reasonTV.setText(R.string.game_over_money);
        } else if (cities.get(index).state_of_ecology <= -500) {
            cities.get(index).getProgress().setGameOver(true);
            reasonTV.setText(R.string.game_over_ecology);

        } else if (cities.get(index).getMood() <= -500) {
            cities.get(index).getProgress().setGameOver(true);
            reasonTV.setText(R.string.game_over_mood);
        }
        else if (cities.get(index).getType_of_city() == 3 && cities.get(index).getStep() > 115) {
            cities.get(index).getProgress().setGameOver(true);
            reasonTV.setText(R.string.game_over_sci);
        }
        if(cities.get(index).getProgress().isGameOver())
        {
            if (wasSwitchSound) {
                MediaPlayer create_constr = MediaPlayer.create(this, R.raw.game_over);
                create_constr.start();
            }
            setContentView(end_game_back);
            cities.remove(index);
            adapter.notifyDataSetChanged();
        }
    }

    //----------------------------------------------------------------------------------------------------------------------------

    /**
     * Тип
     */
    public void count_of_constructions() {
        int count_industrial = 0;
        int count_resort = 0;
        int count_scientific = 0;

        for (Construction c : cities.get(index).getConstructions()) {

            if (c.isWas_bought()) {
                switch (c.getIdCategory()) {
                    case 1: {
                        count_industrial++;
                        break;
                    }
                    case 2: {
                        count_resort++;
                        break;
                    }
                    case 3: {
                        count_scientific++;
                        break;
                    }
                }
            }
        }
        check_bought_titles();
        if (myBuylist.size() == 4 && cities.get(index).getCity_level() == 1) { //Первый уровень. Посёлок - Небольшой город. Купить 4
            first_level(count_industrial, count_resort, count_scientific);
            quest.setText(R.string.lvl2);
        } else if (myBuylist.size() >= 11 && cities.get(index).getCity_level() == 2) //Небольшой город - Средний город. Нужно купить все 12
        {
            quest.setText(R.string.lvl3);
            cities.get(index).setCity_level(3);
            create_info_message();
            inform_messageTV.setText(R.string.aver);
            setContentView(info_back);
        } else if (myBuylist.size() >= 16 && cities.get(index).getCity_level() == 3) //Средний город - Большой город
        {
            quest.setText(R.string.lvl4);
            cities.get(index).setCity_level(4);
            create_info_message();
            inform_messageTV.setText(R.string.big);
            setContentView(info_back);
        } else if (myBuylist.size() >= 20 && cities.get(index).getCity_level() == 4) //Большой город - Мегаполис
        {
            quest.setText(R.string.lvl5);
            cities.get(index).setCity_level(5);
            create_info_message();
            inform_messageTV.setText(R.string.megapolis);
            setContentView(info_back);
        } else if (myBuylist.size() >= 24 && cities.get(index).getCity_level() == 5/* && myTitlelist.size() >= 28*/) //Мегаполис-столица
        {
            quest.setText(R.string.lvl6);
            cities.get(index).setCity_level(6);
            create_info_message();
            inform_messageTV.setText(R.string.capital);
            setContentView(info_back);
        } else if (myBuylist.size() >= 27 && cities.get(index).getCity_level() == 6) //победа
        {
            end_game_back.setBackgroundResource(R.drawable.victory);
            what_happendTV.setText(R.string.victory);
            reasonTV.setText(R.string.victory_capital);
            countConstrTV.setText(String.valueOf(myBuylist.size()));
            countStepsTV.setText(String.valueOf(cities.get(index).getStep()));
            message_about_end_gameTV.setText(R.string.message_about_victory);
            cities.get(index).getProgress().setVictory(true);
            setContentView(end_game_back);
            if (wasSwitchSound) {
                MediaPlayer create_constr = MediaPlayer.create(this, R.raw.victory);
                create_constr.start();
            }

        }
    }


    /**
     * Определяемся с направлением города.
     */
    public void first_level(int count_industrial, int count_resort, int count_scientific) {
        int max = SearchMax(count_industrial, count_resort, count_scientific);
        if ((count_industrial == count_resort && count_industrial == max)
                || (count_industrial == count_scientific && count_scientific == max)
                || (count_scientific == count_resort && count_resort == max)) {
            if (count_industrial == count_resort && count_industrial == count_scientific) {
                form_direction_of_city(true, true, true);
            } else if (count_industrial == count_resort) {
                form_direction_of_city(true, true, false);
            } else if (count_resort == count_scientific) {
                form_direction_of_city(false, true, true);
            } else if (count_industrial == count_scientific) {
                form_direction_of_city(true, false, true);
            }
        } else {
            if (count_industrial == max) {
                cities.get(index).setType_of_city(1);
                cities.get(index).setCity_level(2);
                inform_messageTV.setText(R.string.indInit);
                infoLL.setBackgroundResource(R.drawable.industrial_background_pic);
            } else if (count_resort == max) {
                cities.get(index).setType_of_city(2);
                cities.get(index).setCity_level(2);
                inform_messageTV.setText(R.string.resInit);
                infoLL.setBackgroundResource(R.drawable.resort_background_pic);
            } else if (count_scientific == max) {
                cities.get(index).setType_of_city(3);
                cities.get(index).setCity_level(2);
                inform_messageTV.setText(R.string.sciInit);
                infoLL.setBackgroundResource(R.drawable.science_background_pic);
            }
            setContentView(info_back);
        }
    }

    /**
     * Поиск максимального количества построек в категории
     *
     * @return максимум
     */
    public int SearchMax(int count_industrial, int count_resort, int count_scientific) {
        int max = 0;
        if (count_industrial >= count_resort) {
            max = count_industrial;
        }
        if (count_resort >= count_industrial) {
            max = count_resort;
        }
        if (count_scientific >= max) {
            max = count_scientific;
        }

        return max;
    }


    /**
     * Определяем направление города для выбора
     *
     * @param isInd индустриальный
     * @param isRes курортный
     * @param isSci научный
     */
    public void form_direction_of_city(boolean isInd, boolean isRes, boolean isSci) { //!!!

        if (isInd && isRes && !isSci) {
            one.setText(String.valueOf(all_types_city[1]));
            two.setText(String.valueOf(all_types_city[2]));

        }
        if (isRes && isSci && !isInd) {
            one.setText(String.valueOf(all_types_city[2]));
            two.setText(String.valueOf(all_types_city[3]));


        }
        if (isInd && isSci && !isRes) {
            one.setText(String.valueOf(all_types_city[1]));
            two.setText(String.valueOf(all_types_city[3]));

        }
        if (isInd && isRes && isSci) {
            one.setText(String.valueOf(all_types_city[1]));
            two.setText(String.valueOf(all_types_city[2]));
            three.setVisibility(View.VISIBLE);
            three.setText(String.valueOf(all_types_city[3]));
        }
        setContentView(choise_back);

    }


    /**
     * Выбор пользователем направления
     *
     * @param v кнопка с направлением
     */
    public void choise_click(View v) {
        switch (v.getId()) {
            case R.id.choise_one: {
                if (one.getText() == all_types_city[1]) {
                    cities.get(index).setType_of_city(1);
                } else if (one.getText() == all_types_city[2]) {
                    cities.get(index).setType_of_city(2);
                }
                cities.get(index).setCity_level(2);
                Initialization_Information_About_City(index);
                create_info_message();
                makeSnackBar(view, res.getString(R.string.newBuildings));
                break;
            }
            case R.id.choise_two: {
                if (two.getText() == all_types_city[2]) {
                    cities.get(index).setType_of_city(2);
                } else if (two.getText() == all_types_city[3]) {
                    cities.get(index).setType_of_city(3);
                }
                cities.get(index).setCity_level(2);
                create_info_message();
                Initialization_Information_About_City(index);
                makeSnackBar(view, res.getString(R.string.newBuildings));
                break;
            }
            case R.id.choise_three: {
                cities.get(index).setType_of_city(3);//потому что всегда научный
                cities.get(index).setCity_level(2);
                create_info_message();
                Initialization_Information_About_City(index);
                makeSnackBar(view, res.getString(R.string.newBuildings));
                break;
            }
        }

    }


    /**
     * Оформление информационного сообщения.
     */
    public void create_info_message() {
        switch (cities.get(index).getType_of_city()) {
            case 1: {
                infoLL.setBackgroundResource(R.drawable.industrial_background_pic);
                break;
            }
            case 2: {
                infoLL.setBackgroundResource(R.drawable.resort_background_pic);
                break;
            }
            case 3: {
                infoLL.setBackgroundResource(R.drawable.science_background_pic);
                break;
            }
            case 0: {
                infoLL.setBackgroundResource(R.drawable.trees_back);
                break;
            }

        }
    }

    //----------------------EVENTS--------------------------------------------------------------------

    /**
     * Создаём событие
     */
    public void createEvent() {
        Random rn = new Random();
        //(max-min+1)+min
        int rand = rn.nextInt(45 - 1 + 1) + 1;
        if (rand == 2) {
            double old_count_money = cities.get(index).getCount_of_money();
            int damage_value = calculate_damage();
            double damage = old_count_money - damage_value;
            cities.get(index).setCount_of_money(damage);
            weather_eventTV.setText(R.string.storm);
            damageLabel.setVisibility(View.VISIBLE);
            damageTV.setVisibility(View.VISIBLE);
            double newDoubleDamage = new BigDecimal(damage_value).setScale(1, RoundingMode.UP).doubleValue();
            damageTV.setText(String.valueOf(newDoubleDamage));

            setContentView(event_back_storm);
            if (wasSwitchSound) {
                MediaPlayer create_constr = MediaPlayer.create(this, R.raw.storm);
                create_constr.start();
            }
        }
        if (rand % 9 == 0) {
            weather_eventTV.setText(R.string.rain);

            if (cities.get(index).getState_of_ecology() <= 500) {
                int old_state_ecology = cities.get(index).getState_of_ecology();
                Random rn_eco = new Random();
                int rand_eco = rn_eco.nextInt(calculate_max_eco() - 10 + 1) + 10;
                int new_ecology = old_state_ecology + rand_eco;
                cities.get(index).setState_of_ecology(new_ecology);
            }

            damageLabel.setVisibility(View.INVISIBLE);
            damageTV.setVisibility(View.INVISIBLE);
            setContentView(event_back_storm);
            if (wasSwitchSound) {
                MediaPlayer create_constr = MediaPlayer.create(this, R.raw.rain);
                create_constr.start();
            }

        }
        if(rand == 7 && cities.get(index).getType_of_city() ==2 && calculate_damage_tourits() <= cities.get(index).getCount_of_tourists())
        {
            int old_count_tourists = cities.get(index).getCount_of_tourists();
            int damage_value = calculate_damage_tourits();
            int damage = old_count_tourists - damage_value;
            cities.get(index).setCount_of_tourists(damage);

            weather_eventTV.setText(R.string.weather_tourist);
            damageLabel.setVisibility(View.VISIBLE);
            damageTV.setVisibility(View.VISIBLE);
            damageTV.setText(String.valueOf(damage_value));

            setContentView(event_back_storm);
            if (wasSwitchSound) {
                MediaPlayer create_constr = MediaPlayer.create(this, R.raw.storm);
                create_constr.start();
            }
        }
    }


    /**
     * Считаем ущерб
     *
     * @return ущерб
     */
    public int calculate_damage() {
        switch (cities.get(index).getCity_level()) {
            case 1: {
                return myBuylist.size();
            }
            case 2: {
                return myBuylist.size() * 3;
            }
            case 3: {
                return myBuylist.size() * 5;
            }
            case 4: {
                return myBuylist.size() * 7;
            }
            case 5: {
                return myBuylist.size() * 8;
            }
            case 6: {
                return myBuylist.size() * 9;
            }
            default: {
                return 0;
            }
        }
    }

    /**
     * Считаем ущерб
     *
     * @return ущерб
     */
    public int calculate_damage_tourits() {
        switch (cities.get(index).getCity_level()) {
            case 1: {
                return myBuylist.size();
            }
            case 2: {
                return myBuylist.size() * 10;
            }
            case 3: {
                return myBuylist.size() * 15;
            }
            case 4: {
                return myBuylist.size() * 50;
            }
            case 5: {
                return myBuylist.size() *50 ;
            }
            case 6: {
                return myBuylist.size() * 60;
            }
            default: {
                return 0;
            }
        }
    }


    /**
     * Максимально возможная экология
     */
    public int calculate_max_eco() {
        switch (cities.get(index).getCity_level()) {
            case 1: {
                return 30;
            }
            case 2: {
                return 50;
            }
            case 3: {
                return 60;
            }
            case 4: {
                return 80;
            }
            case 5: {
                return 120;
            }
            case 6: {
                return 200;
            }
            default: {
                return 0;
            }
        }
    }


}


