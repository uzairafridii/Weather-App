package com.example.weatherapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.pavlospt.CircleView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    private CircleView circleView;
    private EditText searchCity;
    private TextView city_name, date, humidityValue, windValue;
    private ImageView weatherIcon;
    private Button searchButton;
    private ProgressBar progressBar;
    private String weatherUrl = "http://api.openweathermap.org/data/2.5/weather?q=Lachi&appid=d23c66ea0f5b306ca7ca393069eeacc0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        circleView = (CircleView) findViewById(R.id.circleView);
        searchCity = (EditText) findViewById(R.id.searchCity);
        city_name = (TextView) findViewById(R.id.cityName);
        date = (TextView) findViewById(R.id.date);
        humidityValue = (TextView) findViewById(R.id.humidityValue);
        windValue = (TextView) findViewById(R.id.windValue);
        weatherIcon = (ImageView) findViewById(R.id.weatherIcon);
        searchButton = (Button) findViewById(R.id.searchbtn);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        final RequestQueue btnRequestQueue = Volley.newRequestQueue(this);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressBar.setVisibility(View.VISIBLE);
                String city = searchCity.getText().toString().trim();
                String urlWithCityName = "http://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=d23c66ea0f5b306ca7ca393069eeacc0";


                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlWithCityName,
                        new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {
                                try {

                                    JSONObject jsonObject = new JSONObject(response);
                                    JSONArray jsonArray = jsonObject.getJSONArray("weather");

                                    for (int i = 0; i < jsonArray.length(); i++) {
                                        JSONObject object = jsonArray.getJSONObject(i);
                                        JSONObject mainObject = jsonObject.getJSONObject("main");
                                        JSONObject windObject = jsonObject.getJSONObject("wind");

                                        String description = object.getString("description");
                                        String icon = object.getString("icon");

                                        //mainObject
                                        String humidity = mainObject.getString("humidity");
                                        String temp = mainObject.getString("temp");
                                        //windObject
                                        String windDegree = windObject.getString("deg");



                                        String cityName = jsonObject.getString("name");

                                        Calendar c = Calendar.getInstance();
                                        SimpleDateFormat df = new SimpleDateFormat("E, d MMMM", Locale.getDefault());
                                        String dateOfDay = df.format(c.getTime());



                                        if(icon.equals("01d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day1);
                                        }
                                        else if(icon.equals("02d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day2);
                                        }
                                        else if(icon.equals("03d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day3);
                                        }
                                        else if(icon.equals("04d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day4);
                                        }
                                        else if(icon.equals("09d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day9);
                                        }

                                        else if(icon.equals("10d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day10);
                                        }
                                        else if(icon.equals("11d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day11);
                                        }
                                        else if(icon.equals("13d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day13);
                                        }
                                        else if(icon.equals("50d"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.day50);
                                        }


                                        // for night

                                        if(icon.equals("01n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night1);
                                        }
                                        else if(icon.equals("02n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night2);
                                        }
                                        else if(icon.equals("03n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night3);
                                        }
                                        else if(icon.equals("04n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night4);
                                        }
                                        else if(icon.equals("09n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night9);
                                        }

                                        else if(icon.equals("10n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night10);
                                        }
                                        else if(icon.equals("11n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night11);
                                        }
                                        else if(icon.equals("13n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night13);
                                        }
                                        else if(icon.equals("50n"))
                                        {
                                            weatherIcon.setImageResource(R.drawable.night50);
                                        }

                                        city_name.setText(cityName);
                                        date.setText(dateOfDay);
                                        circleView.setTitleText(temp+"°");
                                        circleView.setSubtitleText(description);
                                        humidityValue.setText(humidity+"%");
                                        windValue.setText(windDegree+"km/h");
                                        progressBar.setVisibility(View.INVISIBLE);

                                    }


                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_LONG).show();
                                    Log.d("Error", "" + e.toString());
                                    progressBar.setVisibility(View.INVISIBLE);
                                }


                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
                        Log.d("Error", "" + error.toString());
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                });
                btnRequestQueue.add(stringRequest);




            }
        });


       RequestQueue requestQueue = Volley.newRequestQueue(this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, weatherUrl,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("weather");

                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                JSONObject mainObject = jsonObject.getJSONObject("main");
                                JSONObject windObject = jsonObject.getJSONObject("wind");

                                String description = object.getString("description");
                                String icon = object.getString("icon");

                                //mainObject
                                String humidity = mainObject.getString("humidity");
                                String temp = mainObject.getString("temp");
                                //windObject
                                String windDegree = windObject.getString("deg");



                                String cityName = jsonObject.getString("name");

                                Calendar c = Calendar.getInstance();
                                SimpleDateFormat df = new SimpleDateFormat("E, d MMMM", Locale.getDefault());
                                String dateOfDay = df.format(c.getTime());



                                if(icon.equals("01d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day1);
                                }
                                else if(icon.equals("02d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day2);
                                }
                                else if(icon.equals("03d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day3);
                                }
                                else if(icon.equals("04d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day4);
                                }
                                else if(icon.equals("09d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day9);
                                }

                                else if(icon.equals("10d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day10);
                                }
                                else if(icon.equals("11d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day11);
                                }
                                else if(icon.equals("13d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day13);
                                }
                                else if(icon.equals("50d"))
                                {
                                    weatherIcon.setImageResource(R.drawable.day50);
                                }


                                // for night

                                if(icon.equals("01n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night1);
                                }
                                else if(icon.equals("02n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night2);
                                }
                                else if(icon.equals("03n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night3);
                                }
                                else if(icon.equals("04n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night4);
                                }
                                else if(icon.equals("09n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night9);
                                }

                                else if(icon.equals("10n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night10);
                                }
                                else if(icon.equals("11n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night11);
                                }
                                else if(icon.equals("13n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night13);
                                }
                                else if(icon.equals("50n"))
                                {
                                    weatherIcon.setImageResource(R.drawable.night50);
                                }

                                city_name.setText(cityName);
                                date.setText(dateOfDay);
                                circleView.setTitleText(temp+"°");
                                circleView.setSubtitleText(description);
                                humidityValue.setText(humidity+"%");
                                windValue.setText(windDegree+"km/h");

                            }


                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MainActivity.this, "" + e.toString(), Toast.LENGTH_LONG).show();
                            Log.d("Error", "" + e.toString());
                        }


                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(MainActivity.this, "" + error.toString(), Toast.LENGTH_LONG).show();
                Log.d("Error", "" + error.toString());
            }
        });
        requestQueue.add(stringRequest);




    }
}
