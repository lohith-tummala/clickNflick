package com.clicknflick.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.clicknflick.Network.MySingleton;
import com.clicknflick.R;
import org.json.JSONException;
import org.json.JSONObject;

public class ActivityLogin extends AppCompatActivity {

    TextInputEditText mUsername, mPassword;
    private static final String USER_NAME_KEY = "mobile";
    private static final String PASSWORD_KEY = "password";
    private String URL = "http://192.168.100.19:8080/signup/login";
    MySingleton mySingleton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mUsername = findViewById(R.id.login_mobile);
        mPassword = findViewById(R.id.login_password);

        mySingleton = new MySingleton(this);
    }


    public void loginUser(View view) throws JSONException {
        String userName, password;

        userName = mUsername.getText().toString().trim();
        password = mPassword.getText().toString().trim();

        JSONObject userLoginObject = new JSONObject();
        userLoginObject.put(USER_NAME_KEY, userName);
        userLoginObject.put(PASSWORD_KEY, password);


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, URL, userLoginObject, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(ActivityLogin.this, response.toString(), Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(ActivityLogin.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });
        mySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);

    }

    public void loginCancel(View view) {
        mUsername.setText("");
        mPassword.setText("");
    }


    public void goToSignUp(View view) {
        startActivity(new Intent(ActivityLogin.this,ActivitySignup.class));
    }

}
