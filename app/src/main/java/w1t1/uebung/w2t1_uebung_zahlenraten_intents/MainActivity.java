package w1t1.uebung.w2t1_uebung_zahlenraten_intents;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText edName;
    private Button btnSpielen;
    private TextView lblErgebnis;
    private ActivityResultLauncher<Intent> startForResult;
    private String name;

    // ==============================================================
    private class MyOCL implements View.OnClickListener
    {
        @Override
        public void onClick(View view)
        {
            name = edName.getText().toString().trim();

            if(name.length() > 0)
            {
                Intent intent = new Intent(MainActivity.this, SpielActivity.class);
                intent.putExtra("name", name);
                startForResult.launch(intent);
            }
        }
    }

    // ==============================================================
    private class MyOAR implements ActivityResultCallback<ActivityResult>
    {
        @Override
        public void onActivityResult(ActivityResult result)
        {
            if (result.getResultCode() == Activity.RESULT_OK)
            {
                Intent intent = result.getData();
                Bundle extras = intent.getExtras();

                lblErgebnis.setText(name+", Du hast " + extras.get("anzahl").toString()
                        + " Versuche benötigt!");
            }
        }
    }

    // ==============================================================

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityResultContracts.StartActivityForResult safr = new ActivityResultContracts.StartActivityForResult();
        startForResult = registerForActivityResult(safr, new MyOAR());

        lblErgebnis = (TextView) findViewById(R.id.lblErgebnis);
        edName = (EditText) findViewById(R.id.edName);
        btnSpielen = (Button) findViewById(R.id.btnSpielen);
        btnSpielen.setOnClickListener(new MyOCL());
    }
}