package tr.edu.atauni.hafizaoyunu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button basla = findViewById(R.id.baslaBtn);
        basla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText edt = findViewById(R.id.isimText);
                String isim = edt.getText().toString();
                Intent i = new Intent(getApplicationContext(),oyunEkrani.class);
                i.putExtra("ism",isim);
                startActivity(i);
            }
        });
    }
}
