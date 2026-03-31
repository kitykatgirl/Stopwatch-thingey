package me.docs.stopwachthgh;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private int sekundy = 0;

    private TextView textView;
    private Button startButton;
    private Button stopButton;
    private Button resetButton;
    private Button zapiszButton;

    private boolean czyDziala = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        textView = findViewById(R.id.textViewCzas);
        startButton = findViewById(R.id.btnStart);
        stopButton = findViewById(R.id.btnStop);
        resetButton = findViewById(R.id.btnReset);
        zapiszButton = findViewById(R.id.btnZapisz);

        Handler handler = new Handler();
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(czyDziala){
                    sekundy++;
                    textView.setText(wyswietlCzas(sekundy));
                }
                handler.postDelayed(this,1000);
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                czyDziala = true;
            }
        });
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                czyDziala = false;
            }
        });
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                czyDziala = false;
                sekundy = 0;
                textView.setText(wyswietlCzas(0));
            }
        });
    }
    private String wyswietlCzas(int ile){
        int sekundy = ile%60;
        int minuty = (ile/60)%60;
        int godziny = ile/3600;
        return String.format("%02d:%02d:%02d",godziny,minuty,sekundy);
    }
}