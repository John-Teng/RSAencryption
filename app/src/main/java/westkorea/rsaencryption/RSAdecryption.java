package westkorea.rsaencryption;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Random;

public class RSAdecryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsadecryption);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        final EditText fieldE = (EditText) findViewById(R.id.editTextE);
        final EditText fieldN = (EditText) findViewById(R.id.editTextN);
        final EditText fieldM = (EditText) findViewById(R.id.editTextM);
        final EditText fieldC = (EditText) findViewById(R.id.editTextC);
        final EditText fieldD = (EditText) findViewById(R.id.editTextD);


        Button buttonD = (Button) findViewById(R.id.buttonD);
        Button buttonCE = (Button) findViewById(R.id.buttonCE);
        Button buttonSK = (Button) findViewById(R.id.buttonSK);

        final Decrypt decrypt = new Decrypt( fieldE, fieldN, fieldD, fieldM, fieldC);


        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                decrypt.decryptCipher();
            }
        });

        buttonCE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fieldE.setText("");
                fieldN.setText("");
                fieldM.setText("");
                fieldC.setText("");
                fieldD.setText("");
            }
        });


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                decrypt.generateKeys();
                Snackbar.make(view, "New private key generated", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

}
