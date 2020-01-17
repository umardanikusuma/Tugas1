package com.example.tugas1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {

    private EditText etEmail;
    private EditText etPassword;
    private Button btnRegis;
    private EditText edkonfimasipassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edkonfimasipassword = findViewById(R.id.confrimpassword);
        etEmail = findViewById(R.id.user);
        etPassword = findViewById(R.id.password);

        btnRegis = findViewById(R.id.Register);
        btnRegis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = etEmail.getText().toString();
                String passeord = etPassword.getText().toString();

                if (user.isEmpty()){
                    Toast.makeText(register.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }
                if (passeord.isEmpty()){
                    Toast.makeText(register.this, "ini tidak boleh kosong", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (!passeord.equals(edkonfimasipassword.getText().toString())){
                    Toast.makeText(register.this, "Paswoord tidak sama", Toast.LENGTH_SHORT).show();
                    return;
                }
                AndroidNetworking.post(BaseUrl.url + "nambah.php")
                        .addBodyParameter("Username", user)
                        .addBodyParameter("Passwords", passeord)
                        .setPriority(Priority.LOW)
                        .build()
                        .getAsJSONObject(new JSONObjectRequestListener() {
                            @Override
                            public void onResponse(JSONObject response) {
                                try {
                                    JSONObject hasil = response.getJSONObject("hasil");
                                    boolean sukses = hasil.getBoolean("respon");
                                    if (sukses){
                                        Toast.makeText(register.this, "Register Suskses", Toast.LENGTH_SHORT).show();
                                        finish();
                                    } else {
                                        Toast.makeText(register.this, "Register gagal", Toast.LENGTH_SHORT).show();
                                    }
                                } catch (JSONException e){
                                    e.printStackTrace();
                                    System.out.println("ttttt " + e.getMessage());
                                    Toast.makeText(register.this, "Login gagal", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onError(ANError anError) {
                                System.out.println("ttttt " + anError);
                                System.out.println("ttttt "  + anError.getErrorBody());
                                System.out.println("ttttt " + anError.getErrorDetail());
                                System.out.println("ttttt " + anError.getResponse());
                                System.out.println("ttttt " + anError.getErrorCode());

                                Toast.makeText(register.this, "Login gagal", Toast.LENGTH_SHORT).show();
                            }
                        });

            }
        });
    }
}
