package vn.edu.tdmu.lethanhhiep.bcck;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class tintuc extends AppCompatActivity {

    private TextView txtnd,txttdg,txtda,txtkk,txtbui,txtten,txttt;
    private ImageView img,imgtt;
    private Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tintuc);
        addControl();
        addEvent();
        doc();

    }
    private void addControl()
    {
        txtnd = findViewById(R.id.txtndtt);
        txtda = findViewById(R.id.txtdamtt);
        txtten =  findViewById(R.id.txtvt);
        txttt =  findViewById(R.id.txtthongtin);
        txttdg =  findViewById(R.id.txtgio);
        txtbui =  findViewById(R.id.txtpmtt);
        txtkk =   findViewById(R.id.txtaqi);
        img =  findViewById(R.id.imgtt);
        imgtt =  findViewById(R.id.imgttt);
        btn =  findViewById(R.id.btnl);
    }
    private void addEvent()
    {
        btn.setOnClickListener(new Myevent());
    }
    private void doc()
    {
        String ss = "http://api.airvisual.com/v2/city?city=Thu%20Dau%20Mot&state=Tinh%20Binh%20Duong&country=Vietnam&key=02e0b493-ba7c-45aa-a896-d210bc2c4e8d";
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, ss, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response)
            {

                try {
                    JSONObject jsonObject = response.getJSONObject("data");
                    JSONObject jsonObject1 = jsonObject.getJSONObject("current");
                    JSONObject jsonObject2 = jsonObject1.getJSONObject("weather");
                    JSONObject jsonObject3 = jsonObject1.getJSONObject("pollution");
                    String Temperature = jsonObject2.getString("tp");
                    String Humidity = jsonObject2.getString("hu");
                    String wind_speed = jsonObject2.getString("ws");
                    String ic = jsonObject2.getString("ic");
                    String dt = jsonObject2.getString("ts");
                    String city = jsonObject.getString("city");
                    String state = jsonObject.getString("state");
                    String country = jsonObject.getString("country");
                    String Aqi = jsonObject3.getString("aqius");
                    String aqicn = jsonObject3.getString("aqicn");
                    double kk = Double.parseDouble(Aqi);
                    double pm = Double.parseDouble(aqicn);
                    int kq2 = kk > 300 ? R.drawable.rattt: kk >=201 ? R.drawable.rattt:kk >= 101 ? R.drawable.rattoite :kk >= 51 ? R.drawable.boy:R.drawable.userfuny;
                    int kq3 = kk > 300 ? 0xffcc9900:kk >= 201 ? 0xfffe0000:kk >= 101 ? 0xffffbe00:kk >= 51 ? 0xffffff01:0xff01b0f1;
                    int kq4 = pm >= 350.5 ? 0xffa60331:pm >= 250.5 ? 0xffff0000:pm >= 150.5 ? 0xffcc9900:pm >=65.5 ? 0xfffe0000:pm >=40.5 ? 0xfffe0000:pm >= 15.5 ? 0xffffff01:0xff01b0f1;
                    String a = kk > 300 ? "Harmful Everyone should stay indoors"
                            :kk >= 201 ? "Bad Sensitive groups should limit their time outside"
                            :kk >= 101 ? "Poor Sensitive group should limit time outside"
                            :kk >= 51 ? "Sensitive groups should limit time spent outdoors"
                            :"Good Does not affect health";
                    String b= pm >= 350.5 ? "Very dangerous":pm >= 250.5 ? "Danger":pm >= 150.5 ? "Very bad impact on health":pm >=65.5 ? "Bad effects on health":pm >=40.5 ? "Affect sensitive groups":pm >= 15.5 ? "Medium":"Good";
                    ic = ic.trim();
                    int kq = ic.equals("01d") ? R.drawable.dmot
                            : ic.equals("01n") ? R.drawable.nmot
                            :ic.equals("02d") ? R.drawable.dhai
                            :ic.equals("02n") ? R.drawable.nhai
                            :ic.equals("03d") ? R.drawable.dba
                            :ic.equals("03n") ? R.drawable.ban
                            :ic.equals("04d") ? R.drawable.dbon
                            :ic.equals("04n") ? R.drawable.bonnn
                            :ic.equals("09d") ? R.drawable.dchin
                            :ic.equals("09n") ? R.drawable.chinn
                            :ic.equals("10d")? R.drawable.dmuoi
                            :ic.equals("10n") ? R.drawable.nmuoi
                            :ic.equals("11n") ? R.drawable.muoimotn
                            :ic.equals("11d") ? R.drawable.muoimotd
                            :ic.equals("13n") ? R.drawable.muoiban
                            :ic.equals("13d") ? R.drawable.muoibad
                            :ic.equals("50n") ? R.drawable.nammuoinpng
                            :R.drawable.nammuoid;

                    txtda.setText("Humidity :" + Humidity + "%");
                    txtda.setBackgroundColor(0xff333333);
                    txtda.setTextColor(0xffffff01);
                    txtnd.setText("Temperature :"+Temperature + "C");
                    txtnd.setBackgroundColor(0xff333333);
                    txtnd.setTextColor(0xffffff01);
                    txttdg.setText("Wind speed : "+wind_speed + "Km/h");
                    txttdg.setBackgroundColor(0xff333333);
                    txttdg.setTextColor(0xffffff01);
                    txtkk.setText("AQI : "+Aqi);
                    txtkk.setTextColor(kq3);
                    txtkk.setBackgroundColor(0xff333333);
                    txtbui.setText("PM " + aqicn);
                    txtbui.setTextColor(kq4);
                    txtbui.setBackgroundColor(0xff333333);
                    img.setImageResource(kq);
                    imgtt.setBackgroundResource(kq2);
                    txtten.setText("Country : " + country + "\nState : " + state + "\nCity : " + city);
                    txtten.setBackgroundColor(0xff333333);
                    txtten.setTextColor(0xffffff01);
                    txttt.setText("AQI :"+ a +"\nPM 2.5 :" + b);
                } catch (JSONException e)
                {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(tintuc.this,"ko",Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }
    private class Myevent implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            doc();
        }
    }
}