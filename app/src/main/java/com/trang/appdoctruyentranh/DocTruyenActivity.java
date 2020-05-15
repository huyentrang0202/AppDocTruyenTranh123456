package com.trang.appdoctruyentranh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.trang.appdoctruyentranh.api.ApiLayAnh;
import com.trang.appdoctruyentranh.interfaces.LayAnhVe;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

public class DocTruyenActivity extends AppCompatActivity implements LayAnhVe {
ImageView imgAnh;
//ImageButton quaylai1;
ArrayList<String> arrUrlAnh;
int soTrang,soTrangDangDoc;
TextView txvSoTrang;
String idChap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doc_truyen);
        init();
        anhXa();
        setUp();
        setClik();
        new ApiLayAnh(this,idChap).execute();


    }

    private void init() {


        Bundle b = getIntent().getBundleExtra("data");
        idChap= b.getString("idChap");



    }

    private void anhXa() {
        txvSoTrang =findViewById(R.id.txvSoTrang);
        imgAnh = findViewById(R.id.imgAnh);
       // quaylai1 = findViewById(R.id.quaylai1);
    }

    private void setUp(){
    }

    private void setClik() {
//        quaylai1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(DocTruyenActivity.this,ChapActivity.class);
//                startActivity(intent);
//            }
//        });

    }

    public void right(View view) {
        docTheoTrang(1);
    }

    public void left(View view) {
        docTheoTrang(-1);
    }

    private void docTheoTrang(int i){
        soTrangDangDoc= soTrangDangDoc+i;
        if(soTrangDangDoc==0){
            soTrangDangDoc=1;
        }
        if(soTrangDangDoc>soTrang){
            soTrangDangDoc = soTrang;
        }
        txvSoTrang.setText(soTrangDangDoc+" / "+soTrang);
        Glide.with(this).load(arrUrlAnh.get(soTrangDangDoc-1)).into(imgAnh);
    }

    @Override
    public void batDau() {

    }

    @Override
    public void ketThuc(String data) {
        try {
            arrUrlAnh = new ArrayList<>();
            JSONArray array = new JSONArray(data);
            for(int i=0;i<array.length();i++){
                arrUrlAnh.add(array.getString(i));
            }
            soTrangDangDoc = 1;
            soTrang=arrUrlAnh.size();
            docTheoTrang(0);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void biLoi() {

    }
}
