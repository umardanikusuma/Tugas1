package com.example.tugas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Button btnLogin;
    private Button btnRegis;
    private EditText etEmail;
    private EditText etPassword;
    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etEmail = findViewById(R.id.user);
        etPassword = findViewById(R.id.password);

        text = findViewById(R.id.text);
        text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, register.class);
                startActivity(myIntent);
            }
        });

        btnRegis = findViewById(R.id.tambah);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, register.class);
                startActivity(myIntent);
            }
        });
        btnLogin = findViewById(R.id.login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etEmail.getText().toString();
                String passeord = etPassword.getText().toString();

                if (user.isEmpty()) {
                    Toast.makeText(MainActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();

                }
                if (passeord.isEmpty()) {
                    Toast.makeText(MainActivity.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                AndroidNetworking.post(BaseUrl.url + "username.php")
                        .addBodyParameter("Email", user)
                        .addBodyParameter("Password", passeord)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("sukses");
                                    Log.d("sukses", "onResponse: " + hasil.getBoolean("sukses"));
                                    if (sukses) {
                                        Toast.makeText(MainActivity.this, "Login Suskses", Toast.LENGTH_SHORT).show();
                                        Intent myIntent = new Intent(MainActivity.this, Main_menu.class);
                                        startActivity(myIntent);
//                                        finish();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                    System.out.println("ttttt " + e.getMessage());
                                    Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                System.out.println("ttttt" + anError);
                                System.out.println("ttttt" + anError.getErrorBody());
                                System.out.println("ttttt" + anError.getErrorDetail());
                                System.out.println("ttttt" + anError.getResponse());
                                System.out.println("ttttt" + anError.getErrorCode());

                                Toast.makeText(MainActivity.this, "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}