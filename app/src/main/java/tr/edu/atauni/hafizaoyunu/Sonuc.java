package tr.edu.atauni.hafizaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class Sonuc extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sonuc);
        Intent i = getIntent();
        String sonuc;
        sonuc = i.getStringExtra("kazandiMi");
        TextView sonucTv = findViewById(R.id.sonucText);

        if(sonuc.equals("evet")) {
            sonucTv.setText("Tebrikler Kazandınız");
        } else {
            sonucTv.setText("Üzgünüm Kazanamadınız");
        }

    }
}
