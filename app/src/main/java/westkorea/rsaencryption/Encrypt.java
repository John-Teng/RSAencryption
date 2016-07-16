package westkorea.rsaencryption;


import android.widget.EditText;


import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;


/**
 * Created by Teng on 04/07/2016.
 */
public class Encrypt {
    private BigInteger m;
    private BigInteger n;
    private BigInteger e;
    public  BigInteger c;
    EditText fieldE;
    EditText fieldN;
    EditText outputC;
    EditText fieldM;

    public Encrypt (EditText fieldE, EditText fieldN, EditText outputC, EditText fieldM){
        this.fieldE = fieldE;
        this.fieldN = fieldN;
        this.outputC = outputC;
        this.fieldM = fieldM;
    }

    public void saveM (String M) {
        m = new BigInteger(M);
    }

    public void getE () {
        e = new BigInteger(fieldE.getText().toString());
    }

    public void getN () {
        n = new BigInteger(fieldN.getText().toString());
    }

    public void calculateC () {
        decode();
        getE();
        getN();
        c = m.modPow(e,n);
        outputC.setText(c.toString());
    }

    public void decode () {
        String s = fieldM.getText().toString();
        char[] ball = new char[s.length() * 3];
        int y =0;
        for (char c : s.toCharArray()) {
            int x =(int)c;
            String z=Integer.toString(x);
            if (x < 100) {
                ball[y] = '0';
                ball[y + 1] = z.charAt(0);
                ball[y+2] = z.charAt(1);
            }
            else {
                ball[y] = z.charAt(0);
                ball[y + 1] = z.charAt(1);
                ball[y + 2] = z.charAt(2);
            }
            y+=3;
        }
        String M = new String (ball);
        saveM(M);
    }


}
