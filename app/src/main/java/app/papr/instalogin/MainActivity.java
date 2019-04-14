package app.papr.instalogin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private Firebase mFirebase;
    private EditText user;
    private  EditText pass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void fun(View e){
         user=findViewById(R.id.username);
        pass=findViewById(R.id.password);


        Firebase.setAndroidContext(this);

        mFirebase= new Firebase("https://neha-22b3c.firebaseio.com/user");
        mFirebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                int f=0;
                for(DataSnapshot dsp : dataSnapshot.getChildren()){

                    Map<String,String> map = dsp.getValue(Map.class);

                   String emailEnter = user.getText().toString();
                   String passEnter = pass.getText().toString();


                    String email = map.get("username");
                    String password = map.get("password");

                    //Log.d("Varify","original email:"+email);
                    if(email.equals(emailEnter) && password.equals(passEnter)){
                        f=1;
                    }
                   // Log.v("Evalue","email:" + email);

                }
                if(f==1)
                {
                    Toast toast=Toast.makeText(getApplicationContext(),"Login Successfull",Toast.LENGTH_LONG);
                    toast.show();
                    Intent intent = new Intent("android.intent.action.TabActivity");
                    startActivity(intent);

                }
                else {
                    Toast toast=Toast.makeText(getApplicationContext(),"Login Failure",Toast.LENGTH_LONG);
                    toast.show();
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

    }
        /*if(user.getText().toString().equals("shubham") && pass.getText().toString().equals("1234")) {
            Intent intent = new Intent("android.intent.action.TabActivity");
            startActivity(intent);
            Toast.makeText(MainActivity.this,"Login Successful",Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this,"Sorry...invalid username/password",Toast.LENGTH_LONG).show();
        }*/


    public void newUser(View e){

        System.out.println("in sign up");
        Intent intent = new Intent("android.intent.action.SignUpActivity");
        startActivity(intent);
    }

}
