package w1t1.uebung.w2t1_uebung_zahlenraten_intents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SpielActivity extends AppCompatActivity
{
    private EditText edTipp;
    private Button btnTippen;
    private TextView lblTippErgebnis, lblAnrede;
    private String name;
    private int zuErratendeZahl, anzahlVersuche;


    // ==============================================================
    private class MyOCL implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            int tipp = Integer.valueOf(edTipp.getText().toString());
            String ergebnis = "";

            if ((tipp < 1) || (tipp > 100))
                ergebnis = "nur zwischen 1 und 100";
            else
            {
                anzahlVersuche++;
                if (tipp < zuErratendeZahl)
                    ergebnis = "Größer!";
                else if (tipp > zuErratendeZahl)
                    ergebnis = "Kleiner";
                else
                {
                    ergebnis = "Richtig!";
                    Intent intent = new Intent();
                    intent.putExtra("anzahl", anzahlVersuche);
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }

            lblTippErgebnis.setText(ergebnis);
        }
    }

    // ==============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spiel);

        lblAnrede = (TextView) findViewById(R.id.lblAnrede);
        lblTippErgebnis = (TextView) findViewById(R.id.lblTippErgebnis);
        edTipp = (EditText) findViewById(R.id.edTipp);
        btnTippen = (Button) findViewById(R.id.btnTippen);
        btnTippen.setOnClickListener(new MyOCL());

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        name = bundle.getString("name");

        zuErratendeZahl = (int) (Math.random() * 100) + 1;
        Log.d("##############", ""+zuErratendeZahl);
        anzahlVersuche = 0;

        lblAnrede.setText("Dein Tipp " + name + ":");
    }
}