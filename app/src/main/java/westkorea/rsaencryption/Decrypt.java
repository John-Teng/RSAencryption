package westkorea.rsaencryption;

import android.util.Log;
import android.widget.EditText;

import java.math.BigInteger;
import java.util.Random;

/**
 * Created by Teng on 04/07/2016.
 */
public class Decrypt {
    private EditText fieldN;
    private EditText fieldD;
    private EditText fieldE;
    private EditText fieldC;
    private EditText fieldM;
    private Random rnd = new Random();
    private BigInteger p;
    private BigInteger q;
    private BigInteger n;
    private BigInteger d;
    private BigInteger e;
    private BigInteger pn;
    private BigInteger c;
    private BigInteger asciiM;
    private String m;

    public Decrypt (EditText fieldE,EditText fieldN, EditText fieldD , EditText fieldM, EditText fieldC ) {
        this.fieldN = fieldN;
        this.fieldD = fieldD;
        this.fieldE = fieldE;
        this.fieldC = fieldC;
        this.fieldM = fieldM;
    }

    public void generateN () {
        p = new BigInteger(996,99,rnd);
        q = new BigInteger(996,99,rnd);
        n = p.multiply(q);
        Log.d("generateN", "n has "+n.toString().length() + " digits");
        fieldN.setText(n.toString());
    }

    public void generatePN () {
        BigInteger a = p.subtract(BigInteger.valueOf(1));
        BigInteger b = q.subtract(BigInteger.valueOf(1));
        pn = a.multiply(b);
    }

    public void generateE () {
        long d = 70001;
        while (true){
            e = BigInteger.valueOf(d);
            d++;
            if (pn.gcd(e).intValue()==1)
                break;
        }
        fieldE.setText(e.toString());
    }

    public void generateD () {
        d = e.modInverse(pn);
        fieldD.setText(d.toString());
    }

    public void generateKeys () {
                generateN ();
                generatePN();
                generateE();
                generateD();
    }

public void getInfo () {
    d = new BigInteger(fieldD.getText().toString());
    n = new BigInteger(fieldN.getText().toString());
    c = new BigInteger(fieldC.getText().toString());

}
    public void decryptCipher () {
        getInfo();
        asciiM = c.modPow(d,n);
        String s = asciiM.toString();
        //String s = fieldC.getText().toString();
        if (s.length()%3!=0) {
            s = "0" + s.substring(0, s.length());
        }
        m = asciiToString(s);
        fieldM.setText(m);
    }

    public String asciiToString (String s) {
        int x = 0;
        int y =0;
        char[] c = new char[s.length()/3];
        while (y<s.length()/3){
            c[y] = (char)Integer.parseInt(s.substring(x,x+3));
            x+=3;
            y++;
        }
        return new String(c);
    }

}
