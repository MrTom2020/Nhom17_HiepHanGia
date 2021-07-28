package vn.edu.tdmu.lethanhhiep.bcck;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import vn.edu.tdmu.lethanhhiep.bcck.CSDL.sqlite;
import vn.edu.tdmu.lethanhhiep.bcck.Service.ConnectionReceiver;

public class MainActivity extends Activity {

    private EditText edtName,edtmk;
    private Button btndn,btndk;
    public FirebaseAuth firebaseAuth;
    public Intent intent;
    static String tend,ten1;
    public Cursor cursor;
    public sqlite dl;
    public int numBer = 0;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dangkynut();
        dangkysukien();
    }
    private void check()
    {
        boolean ret = ConnectionReceiver.isConnected();
        String ms;
        if (ret)
        {
            ms = "The device has an Internet connection and can be done online";
            ten1 = "ok";
        }
        else
        {
            ms = "The device has no Internet connection and can be performed offline";
            ten1 = "ko";
            btndk.setEnabled (false);
        }
        Toast.makeText(MainActivity.this,ms,Toast.LENGTH_SHORT).show();
    }
    private void IsCheck()
    {
        try {
            dl = new sqlite(MainActivity.this, "dulieunguoidung.sqlite", null, 1);
            cursor = dl.truyvancoketqua("SELECT * FROM nguoidung WHERE ten='" + edtName.getText().toString().trim() + "' AND matkhau='" + edtmk.getText().toString().trim() + "'");
            if (cursor != null)
            {
                if(cursor.getCount() == 0)
                {
                    Toast.makeText(MainActivity.this, "Username or password incorrect", Toast.LENGTH_SHORT).show();
                }
                intent = new Intent(MainActivity.this, Main.class);
                while (cursor.moveToNext())
                {
                    tend = cursor.getString(0);
                    Toast.makeText(MainActivity.this, "Logged in successfully", Toast.LENGTH_SHORT).show();
                    edtName.setText("tomhumchinvn@gmail.com");
                    edtmk.setText("123456789");
                    startActivity(intent);
                }
            }
        }
        catch (Exception e)
        {
            Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event)
    {
        Log.i("key pressed", String.valueOf(event.getKeyCode()));
        return super.dispatchKeyEvent(event);
    }

    @Override
    protected void onStart()
    {
        check();
        super.onStart();
    }

    private void dangkynut()
    {
        edtName = findViewById(R.id.use);
        edtmk = findViewById(R.id.pass);
        btndn = findViewById(R.id.button);
        btndk = findViewById(R.id.btndangkytk);
        edtName.setOnKeyListener(new View.OnKeyListener()
        {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtName.getText().toString().trim().length() < 1)
                    {
                        btndn.setEnabled(false);
                    }
                    else
                    {

                    }
                }
                return false;
            }
        });
        edtmk.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_UP)
                {
                    if(edtmk.getText().toString().trim().length() < 1)
                    {
                        btndn.setEnabled(false);
                    }
                    else
                    {
                        btndn.setEnabled(true);
                    }
                }
                return false;
            }
        });

    }
    private void dangkysukien()
    {
        btndn.setOnClickListener(new sukiencuatoi());
        btndk.setOnClickListener(new sukiencuatoi());
    }
    private void ax(String eMail)
    {
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.sendPasswordResetEmail(eMail).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task)
            {
                if(task.isSuccessful())
                {
                    Toast.makeText(MainActivity.this, "Please check your email for new password update", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            if(view.equals(btndn))
            {
                if(ten1.equals("ok"))
                {
                    dangnhap();
                }
                if(ten1.equals("ko"))
                {
                    IsCheck();
                }
            }
            if(view.equals(btndk))
            {
                Intent intent = new Intent(MainActivity.this,Signup.class);
                startActivity(intent);
            }
        }

    }

    private void dangnhap()
    {
        try {

            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait a moment");
            progressDialog.show();
            String ten = "tomhumchinvn@gmail.com";
            String mk = "123456789";
            firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(ten.trim(), mk.trim()).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task)
                {
                    if (task.isSuccessful())
                    {
                        progressDialog.dismiss();
                        intent = new Intent(MainActivity.this,Main.class);
                        tend = firebaseAuth.getCurrentUser().getUid();
                        startActivity(intent);
                    } else
                    {
                        progressDialog.dismiss();
                        numBer++;
                        if(numBer == 3)
                        {
                            ax(edtName.getText().toString());
                            numBer = 0;
                        }
                        Toast.makeText(MainActivity.this, "Login failed", Toast.LENGTH_SHORT).show();

                    }
                    progressDialog.dismiss();
                }

            });
        }
        catch (Exception e)
        {
            progressDialog.dismiss();
            Toast.makeText(MainActivity.this,"Login unsuccessful",Toast.LENGTH_SHORT).show();
        }
    }
}