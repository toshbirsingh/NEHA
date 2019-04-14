package app.papr.instalogin;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class SignUpActivity extends AppCompatActivity {


    private Firebase mfirebase;
    private FusedLocationProviderClient client;
    private String latitude;
    private String longitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }


    public void newSignUp(View e){

        Firebase.setAndroidContext(this);
        mfirebase = new Firebase("https://neha-22b3c.firebaseio.com/user");

        //System.out.println("new sign up");
        EditText name=findViewById(R.id.name);
        EditText email=findViewById(R.id.email_id);
        EditText phno=findViewById(R.id.phone);
        EditText age=findViewById(R.id.age);
        EditText gender=findViewById(R.id.gender);
        EditText username=findViewById(R.id.username);
        EditText password =findViewById(R.id.password1);
        EditText confirmpass=findViewById(R.id.password2);
        //Toast.makeText(SignUpActivity.this,"Password not matching",Toast.LENGTH_LONG).show();
        if(!name.getText().toString().isEmpty() && !email.getText().toString().isEmpty() && !phno.getText().toString().isEmpty() && !age.getText().toString().isEmpty() && !gender.getText().toString().isEmpty() && !username.getText().toString().isEmpty() && !password.getText().toString().isEmpty() && !confirmpass.getText().toString().isEmpty())
        {
            if(password.getText().toString().equals(confirmpass.getText().toString()))
            {
                //Intent intent = new Intent("android.intent.action.MainScreenActivity");
                //startActivity(intent);
                Firebase childOfFireBase = mfirebase.child(phno.getText().toString());

                Firebase fname = childOfFireBase.child("name");
                fname.setValue(name.getText().toString());

                Firebase femail = childOfFireBase.child("email");
                femail.setValue(email.getText().toString());

                Firebase fage = childOfFireBase.child("age");
                fage.setValue(age.getText().toString());

                Firebase fgender = childOfFireBase.child("gender");
                fgender.setValue(gender.getText().toString());

                Firebase fusername = childOfFireBase.child("username");
                fusername.setValue(username.getText().toString());

                Firebase fpassword = childOfFireBase.child("password");
                fpassword.setValue(password.getText().toString());

                Firebase flat = childOfFireBase.child("lat");
                flat.setValue("12.931970");

                Firebase flon = childOfFireBase.child("lon");
                flon.setValue("77.692100");

           /*
                requestPermission();
                client = LocationServices.getFusedLocationProviderClient(this);

                if (ActivityCompat.checkSelfPermission(SignUpActivity.this, ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED ) {


                    return;
                }

                client.getLastLocation().addOnSuccessListener(SignUpActivity.this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        if(location != null){
                             latitude = Double.toString(location.getLatitude());
                            longitude = Double.toString(location.getLongitude());

                            Log.d("pos",latitude);
                            Log.d("pos",longitude);

                        }

                    }
                }); */



                Intent intent = new Intent("android.intent.action.MainActivity");
                startActivity(intent);
                Toast.makeText(SignUpActivity.this,"Signup successful",Toast.LENGTH_LONG);


            }
            else{
                Toast.makeText(SignUpActivity.this,"Password not matching",Toast.LENGTH_LONG).show();
            }
        }
        else{
            Toast.makeText(SignUpActivity.this,"Unsuccessfull signup",Toast.LENGTH_LONG).show();
        }

    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{ACCESS_FINE_LOCATION},1);
    }
}

