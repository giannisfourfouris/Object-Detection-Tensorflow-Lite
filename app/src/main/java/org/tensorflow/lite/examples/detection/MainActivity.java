package org.tensorflow.lite.examples.detection;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    Button buttonClose;
    Button buttonSearch;
    ImageButton buttonList;
    TextView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonSearch = (Button) findViewById(R.id.search_button);
        buttonClose = (Button) findViewById(R.id.close_button);
        buttonList = (ImageButton) findViewById(R.id.list_image_button);
        listView = (TextView) findViewById(R.id.listTextView);

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(v.getContext(), DetectorActivity.class);
                startActivityForResult(myIntent, 0);
            }
        });

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });

        buttonList.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View v) {
                ArrayList<String> list = new ArrayList<String>();
                try {
                    list = readLabelMap(DetectorActivity.TF_OD_API_LABELS_FILE);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("This detector can recognize:");
                String listForView=list.toString();
                listForView = listForView.replace("???,","");
                listForView = listForView.replace("[","");
                listForView = listForView.replace("]","");
                listForView = listForView.replace(" ","");
                listForView = listForView.replace(",","\n");
                builder.setMessage(listForView);


                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        listView.setVisibility(View.VISIBLE);
                    }
                });
                builder.show();
            }
        });

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public ArrayList<String> readLabelMap(String labelMap) throws IOException {
        ArrayList<String> labels = new ArrayList<String>();
        InputStream labelsInput = null;
        String actualFilename = labelMap.split("file:///android_asset/")[1];
        labelsInput =  getAssets().open(actualFilename);
        BufferedReader br = null;
        br = new BufferedReader(new InputStreamReader(labelsInput));
        String line;
        while ((line = br.readLine()) != null) {

            labels.add(line);
        }
        br.close();
        return labels;

    }
}
