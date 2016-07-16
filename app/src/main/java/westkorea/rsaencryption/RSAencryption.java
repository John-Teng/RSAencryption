package westkorea.rsaencryption;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class RSAencryption extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsaencryption);
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);


        final EditText fieldE = (EditText) findViewById(R.id.editTextE);
        final EditText fieldN = (EditText) findViewById(R.id.editTextN);
        final EditText fieldM = (EditText) findViewById(R.id.editTextM);
        final EditText fieldC = (EditText) findViewById(R.id.editTextC);

        Button buttonC = (Button) findViewById(R.id.buttonC);
        Button buttonCE = (Button) findViewById(R.id.buttonCE);

        final Encrypt encrypt = new Encrypt(fieldE, fieldN, fieldC, fieldM);


        buttonC.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        encrypt.calculateC();
                        ClipboardManager clipboard = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                        ClipData clip = ClipData.newPlainText("Cipher", encrypt.c.toString());
                        clipboard.setPrimaryClip(clip);
                        Toast toast = Toast.makeText(getApplicationContext(), "Cipher automatically copied to clipboard", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                }
        );


        buttonCE.setOnClickListener(
                new Button.OnClickListener() {
                    public void onClick(View v) {
                        fieldC.setText("");
                        fieldE.setText("");
                        fieldM.setText("");
                        fieldN.setText("");
                    }
                }
        );
    }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {
            // Inflate the menu; this adds items to the action bar if it is present.
            getMenuInflater().inflate(R.menu.encrypt, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {
            // Handle action bar item clicks here. The action bar will
            // automatically handle clicks on the Home/Up button, so long
            // as you specify a parent activity in AndroidManifest.xml.
            int id = item.getItemId();

            //noinspection SimplifiableIfStatement
            if (id == R.id.decrypt) {
                return true;
            }

            return super.onOptionsItemSelected(item);
        }

}
