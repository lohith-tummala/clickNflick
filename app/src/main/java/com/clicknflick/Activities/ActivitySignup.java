package com.clicknflick.Activities;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clicknflick.Network.MySingleton;
import com.clicknflick.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivitySignup extends AppCompatActivity {


    TextInputEditText mEmail,mMobile,mPassword;
    String URL = "http://192.168.100.19:8080/signup/new";
    MySingleton mySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);



        mEmail    = findViewById(R.id.signup_email);
        mMobile   = findViewById(R.id.signup_mobile);
        mPassword = findViewById(R.id.signup_password);


        mySingleton = new MySingleton(this);
    }



    public void signupUser(View view) throws JSONException {
        String email,mobile,password;


        email = mEmail.getText().toString().trim();
        mobile = mMobile.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        JSONObject userSignupObject = new JSONObject();
        userSignupObject.put("email",email);
        userSignupObject.put("mobile",mobile);
        userSignupObject.put("password",password);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,URL,userSignupObject,new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                Toast.makeText(ActivitySignup.this, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ActivitySignup.this, error.toString(), Toast.LENGTH_SHORT).show();

            }
        });

        jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(0,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }

    public void signupCancel(View view){
        mEmail.setText("");
        mMobile.setText("");
        mPassword.setText("");
    }

    public void goToLogIn(View view){
        startActivity(new Intent(ActivitySignup.this,ActivityLogin.class));
    }
}
