package vn.edu.tdmu.lethanhhiep.bcck;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

import vn.edu.tdmu.lethanhhiep.bcck.Service.ConnectionReceiver;

public class Main extends TabActivity {

    private TabHost tabHost;
    private TabHost.TabSpec tabSpec;
    private Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        dangkynut();
        ax();
    }
    private void dangkynut()
    {
        tabHost = (TabHost)findViewById(android.R.id.tabhost);
    }
    private void check()
    {
        boolean ret = ConnectionReceiver.isConnected();
        String ms;
        if(ret)
        {
            ms = "The device has an Internet connection and can be done online";
        }
        else
        {
            ms = "The device does not have an Internet connection and can be performed offline";
        }
        Toast.makeText(Main.this,ms,Toast.LENGTH_SHORT).show();
    }

    private void ax()
    {
        tabSpec = tabHost.newTabSpec ("Info");
        tabSpec.setIndicator ("Info");
        intent = new Intent (this, tranghienthi.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);

        tabSpec = tabHost.newTabSpec ("Account");
        tabSpec.setIndicator ("Account");
        intent = new Intent (this, trangcanhan.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);


        tabSpec = tabHost.newTabSpec ("News");
        tabSpec.setIndicator ("News");
        intent = new Intent (this, tintuc.class);
        tabSpec.setContent (intent);
        tabHost.addTab (tabSpec);
        tabHost.setCurrentTab(1);
    }

    @Override
    protected void onStart()
    {
        check();
        super.onStart();
    }

    private class sukiencuatoi implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {

        }
    }
}