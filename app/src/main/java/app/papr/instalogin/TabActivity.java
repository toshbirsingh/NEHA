package app.papr.instalogin;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;


public class TabActivity extends AppCompatActivity
{
    TextView camera,chat,status,call;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;
    String lon,lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        camera = findViewById(R.id.camera);
        chat = findViewById(R.id.chat);
        status = findViewById(R.id.status);
        call = findViewById(R.id.call);

        viewPager = findViewById(R.id.fragment_container);

        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());

        viewPager.setAdapter(pagerViewAdapter);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(0);
            }
        });
        chat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(1);
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(2);
            }
        });
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(3);
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                onChangeTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });

        //try
        Firebase mref;
        Firebase.setAndroidContext(this);
        mref = new Firebase("https://neha-22b3c.firebaseio.com/Notification/1234/msg");
        mref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String val=dataSnapshot.getValue(String.class);
                System.out.println(val);
                TextView tv=findViewById(R.id.textView2);
                tv.setText(val);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        //try end

    }
    public void sendNotifications(View v){

        EditText et = findViewById(R.id.request_msg);

        String msg = et.getText().toString();

        Firebase mref;
        Firebase.setAndroidContext(this);
        mref = new Firebase("https://neha-22b3c.firebaseio.com/Notification/");

        String pno="1234";
        lon="12.931970";
        lat="77.692100";

        Firebase childd=mref.child(pno);

        Firebase fname=childd.child("lon");
        fname.setValue(lon);

        Firebase femail=childd.child("lat");
        femail.setValue(lat);

        Firebase fpass=childd.child("msg");
        fpass.setValue(msg);


        //random value for lon and lat
       /* Double randomvalue = 12.5 + Math.random();
        lat = randomvalue.toString();

        randomvalue = 77.25 + Math.random();
        lon = randomvalue.toString();*/
    }

    public void acceptRequest(View v){
        String url="https://www.google.com/maps?q="+lat+","+lon+"";
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
        startActivity(intent);
    }
    public void rejectRequest(View v){
        TextView tv=findViewById(R.id.textView2);
        tv.setText("");
    }

    @RequiresApi(api= Build.VERSION_CODES.M)
    private void onChangeTab(int i) {
        if(i == 0)
        {
            camera.setTextSize(16);
            camera.setTextColor(getColor(R.color.bright_color));

            chat.setTextSize(14);
            chat.setTextColor(getColor(R.color.light_color));

            status.setTextSize(14);
            status.setTextColor(getColor(R.color.light_color));

            call.setTextSize(14);
            call.setTextColor(getColor(R.color.light_color));
        }
        if(i == 1)
        {
            camera.setTextSize(14);
            camera.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(16);
            chat.setTextColor(getColor(R.color.bright_color));

            status.setTextSize(14);
            status.setTextColor(getColor(R.color.light_color));

            call.setTextSize(14);
            call.setTextColor(getColor(R.color.light_color));
        }
        if(i == 2)
        {
            camera.setTextSize(14);
            camera.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(14);
            chat.setTextColor(getColor(R.color.light_color));

            status.setTextSize(16);
            status.setTextColor(getColor(R.color.bright_color));

            call.setTextSize(14);
            call.setTextColor(getColor(R.color.light_color));
        }
        if(i == 3)
        {
            camera.setTextSize(14);
            camera.setTextColor(getColor(R.color.light_color));

            chat.setTextSize(14);
            chat.setTextColor(getColor(R.color.light_color));

            status.setTextSize(14);
            status.setTextColor(getColor(R.color.light_color));

            call.setTextSize(16);
            call.setTextColor(getColor(R.color.bright_color));
        }
    }

}
