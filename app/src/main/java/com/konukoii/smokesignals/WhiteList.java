package com.konukoii.smokesignals;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by bioburn on 2016/03/09.
 */
public class WhiteList extends Activity {
    //this is one way you can save and open a text file
    private final static String storeText ="storeText.txt";
    private EditText txtEditor;
    private EditText appendText;
    private TextView phoneNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.whitelist_main);

        txtEditor=(EditText)findViewById(R.id.numbers);
        appendText=(EditText)findViewById(R.id.editText4);
        phoneNumbers=(TextView)findViewById(R.id.textView);
        //puts up the list
        readFile();
    }


    public void saveNumber(View v) {

        try {

            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(storeText, 0));
            out.write(txtEditor.getText().toString());
            out.close();

            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();

        } catch (Throwable t) {

            Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
        }
    readFile();
    }

    public void append(View v){
        try {
            OutputStreamWriter out = new OutputStreamWriter(openFileOutput(storeText, Context.MODE_APPEND));
            out.write(appendText.getText().toString()+"\n");
            out.close();

            Toast.makeText(this, "The contents are saved in the file.", Toast.LENGTH_LONG).show();

        } catch (Throwable t) {
            Toast.makeText(this, "Exception: "+t.toString(), Toast.LENGTH_LONG).show();
        }
        //updates the list of numbers
        readFile();
    }

    public void readFile() {
        try {
            InputStream in = openFileInput(storeText);
            if (in != null) {
                InputStreamReader tmp=new InputStreamReader(in);
                BufferedReader reader=new BufferedReader(tmp);
                String str;
                StringBuilder buf=new StringBuilder();
                while ((str = reader.readLine()) != null) {
                    buf.append(str+"\n");
                }
                in.close();
                txtEditor.setText(buf.toString());
                phoneNumbers.setText(buf.toString());
            }
        }
        catch (java.io.FileNotFoundException e) {
// that's OK, we probably haven't created it yet
        }
        catch (Throwable t) {
            Toast.makeText(this, "Exception: " + t.toString(), Toast.LENGTH_LONG).show();
        }
    }
}
