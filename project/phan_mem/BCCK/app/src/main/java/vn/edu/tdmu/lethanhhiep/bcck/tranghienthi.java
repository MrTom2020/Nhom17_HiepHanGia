package vn.edu.tdmu.lethanhhiep.bcck;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class tranghienthi extends AppCompatActivity {

    TextView txttb,txtts,txtkg,txtcb,txtttco2,txtAQI,txtdam;
    ImageView imgus;
    Button btnmr;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tranghienthi);
        addcontrol();
        addevent();
        ax();
    }
    public void addcontrol()
    {
        txtcb = findViewById(R.id.txtcanhbao);
        txtttco2 = findViewById(R.id.txtttco2);
        txtkg = findViewById(R.id.txtkg);
        txttb = findViewById(R.id.txttb);
        txtts = findViewById(R.id.txtts);
        imgus = findViewById(R.id.imguser);
        txtAQI = findViewById(R.id.txtAQI);
        txtdam = findViewById(R.id.txtdam);
        btnmr = findViewById(R.id.btnmr);
    }
    public void ax()
    {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Data");
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot)
            {
               txtkg.setTextColor(Color.parseColor(canhbao(snapshot.child("Khí Gas").getValue().toString())));
               txtcb.setText(tb2(snapshot.child("Khí Gas").getValue().toString()));
                    txtkg.setText(snapshot.child("Khí Gas").getValue().toString());
                txtAQI.setText(tb(snapshot.child("AQI").getValue().toString()));
                txtttco2.setText("Nồng độ CO2 : "+snapshot.child("Co2").getValue().toString());
                String nhietdo = snapshot.child("Nhiệt độ").getValue().toString() +"\nNhiệt độ(C)";
                String doam = snapshot.child("Độ ẩm").getValue().toString() + "\nĐộ ẩm(%)";
                txtts.setText(nhietdo);
                txtdam.setText(doam);
                imgus.setBackgroundResource(td(snapshot.child("AQI").getValue().toString(),snapshot.child("Khí Gas").getValue().toString()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public String canhbao(String tt)
    {
        float kq = Float.parseFloat(tt);
        String mau = kq >=700 ? "#f30031":kq >= 600 ? "#ec5f5f":"#ffffff";
        return  mau;
    }
    public String tb(String tt)
    {
        float kq = Float.parseFloat(tt);
        String k = kq > 300 ? "Mọi người nên ở trong nhà":kq >= 201 ? "Mọi người nên hạn chế ra ngoài":kq >= 51 ? "Những người nhạy cảm nên hạn chế ở bên ngoài":"Không khí trong lành";
        String kq2 = "AQI :"+String.valueOf(kq) +"\n" + k;
        return kq2;
    }
    public int td(String aqi,String gas)
    {
        float k1 = Float.parseFloat(aqi);
        float k2 = Float.parseFloat(gas);
        int kq2 = k1 >= 600 ? 1:0;
        int kq = k2 > 201 ? 1:0;
        int kq3 = kq + kq2 >= 1 ? R.drawable.rattoite:R.drawable.userfuny;
        return  kq3;
    }
    public String tb2(String tt)
    {
        float kq = Float.parseFloat(tt);
        String k = kq > 700 ? "Phát hiện nồng độ khí Gas":kq >= 600 ? "Phát hiện nồng độ Alcohol":"An toàn";
        return k;
    }
    public String cbco2(String tt)
    {
        float kq = Float.parseFloat(tt);
        String mau = kq >=700 ? "#f30031":kq >= 600 ? "#ec5f5f":"#f8ac32";
        return  mau;
    }
    public void addevent()
    {
        btnmr.setOnClickListener(new myevent());
    }
    public class myevent implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btnmr))
            {
                Toast.makeText(tranghienthi.this,"Hiện tại tính năng đang trong quá trình phát triển",Toast.LENGTH_SHORT).show();
            }
        }
    }
}