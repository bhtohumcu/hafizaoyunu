package tr.edu.atauni.hafizaoyunu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class oyunEkrani extends AppCompatActivity {
    int sonkart=0;
    boolean bekle=false;
    int kullanimHakki=10;
    int dogruTahmin=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oyun_ekrani);
        Intent intnt = getIntent();
        String isim = intnt.getStringExtra("ism");
        TextView karsilama = findViewById(R.id.karsilamaTv);
        karsilama.setText(isim + " Hoş geldiniz!");
        GridLayout gd = findViewById(R.id.grdLyt);
        int satir=2;
        int sutun=2;
        final int adet=satir*sutun;
        gd.setColumnCount(satir);
        gd.setRowCount(sutun);
        Kart[] kartlar = new Kart[adet];
        for(int i =0;i<adet;i++) {
            if(i%2 ==0) {
                kartlar[i] = new Kart(getApplicationContext(), i,i+10);
            } else {
                kartlar[i] = new Kart(getApplicationContext(), i-1,i+10);
            }
            kartlar[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(kullanimHakki<1) {
                        Intent i = new Intent(getApplicationContext(),Sonuc.class);
                        i.putExtra("kazandiMi","hayır");
                        startActivity(i);
                        return;
                    }
                    final Kart suankiKart = (Kart)v;
                    if(suankiKart.acikMi==true || bekle==true) {
                        return;
                    }
                    suankiKart.dondur();
                    if(sonkart>0) {
                        final Kart oncekiKart = findViewById(sonkart);
                        if(oncekiKart.resId == suankiKart.resId) {
                            dogruTahmin+=2;
                            oncekiKart.eslesti=true;
                            suankiKart.eslesti=true;
                            if(dogruTahmin == adet) {
                                Intent i = new Intent(getApplicationContext(),Sonuc.class);
                                i.putExtra("kazandiMi","evet");
                                startActivity(i);
                                return;
                            }
                        } else {
                            bekle = true;
                            kullanimHakki--;
                            Handler h1= new Handler();
                            h1.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    oncekiKart.dondur();
                                    suankiKart.dondur();
                                    bekle=false;
                                }
                            },2000);

                        }

                        sonkart=0;
                    } else {
                        sonkart = suankiKart.getId();
                    }
                }
            });



        }
        Collections.shuffle(Arrays.asList(kartlar));
        for(Kart k: kartlar) {
            gd.addView(k);
        }


    }
}
