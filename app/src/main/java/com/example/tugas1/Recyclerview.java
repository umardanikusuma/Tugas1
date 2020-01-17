package com.example.tugas1;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Recyclerview extends AppCompatActivity {
    RecyclerView recyclerView;
    private Adapter adapter;
    private ArrayList<siswa> rvdata = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recyclerview);

        initDataset();

        recyclerView = findViewById(R.id.recyclerview);
        adapter = new Adapter(rvdata);

        Log.e("","onCreate:"+rvdata.size());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this,RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }
    private void initDataset(){

        siswa s1 = new siswa();
        s1.setNama("REYKA");
        s1.setKelas("XII RPL");
        s1.setAlamat("GEBOG");
        rvdata.add(s1);

        siswa s2 = new siswa();
        s2.setNama("BADROL");
        s2.setKelas("XII RPL");
        s2.setAlamat("BESITO");
        rvdata.add(s2);

        siswa s3 = new siswa();
        s3.setNama("BAGAS");
        s3.setKelas("XII RPL");
        s3.setAlamat("BESITO");
        rvdata.add(s3);

        siswa s4 = new siswa();
        s4.setNama("RIFKI");
        s4.setKelas("XII RPL");
        s4.setAlamat("BESITO");
        rvdata.add(s4);

        siswa s5 = new siswa();
        s5.setNama("FREDI");
        s5.setKelas("XII RPL");
        s5.setAlamat("NALUMSARI");
        rvdata.add(s5);

        siswa s6 = new siswa();
        s6.setNama("Sutopo");
        s6.setKelas("XII RPL");
        s6.setAlamat("Samirejo");
        rvdata.add(s5);

        siswa s7 = new siswa();
        s7.setNama("Sutopo");
        s7.setKelas("XII RPL");
        s7.setAlamat("Samirejo");
        rvdata.add(s5);

        siswa s8 = new siswa();
        s8.setNama("Sutopo");
        s8.setKelas("XII RPL");
        s8.setAlamat("Samirejo");
        rvdata.add(s5);

        siswa s9 = new siswa();
        s9.setNama("FREDI");
        s9.setKelas("XII RPL");
        s9.setAlamat("NALUMSARI");
        rvdata.add(s5);

        siswa s10 = new siswa();
        s10.setNama("FREDI");
        s10.setKelas("XII RPL");
        s10.setAlamat("NALUMSARI");
        rvdata.add(s5);
    }
}