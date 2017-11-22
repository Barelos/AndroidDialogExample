package com.example.barel.dialogtraining;

import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView title;
    private int title_index = 0;
    private String[] titles = {"Click buttons to show dialogs",
                               "Try another one",
                               "This is the last one"};
    private int[] colors = {Color.BLACK, Color.RED, Color.GREEN, Color.BLUE};
    private int side = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = (TextView) findViewById(R.id.title);
        title.setText(titles[title_index]);
        title_index = (title_index + 1) & titles.length;
    }

    public void basic(View view){
        AlertDialog.Builder basicBuilder = new AlertDialog.Builder(this);
        basicBuilder
        .setTitle("Basic Dialog")
        .setMessage("Would you like to change the title?")
        .setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                title.setText(titles[title_index]);
                title_index= (title_index + 1) % titles.length;
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        })
        .show();
    }

    public void list(View view){
        AlertDialog.Builder basicBuilder = new AlertDialog.Builder(this);
        basicBuilder
                .setTitle("Pick title color")
                .setItems(R.array.colors, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        title.setTextColor(colors[i]);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    public void checkBox(View view){
        final ArrayList cur_styles = new ArrayList();
        AlertDialog.Builder basicBuilder = new AlertDialog.Builder(this);
        basicBuilder
                .setTitle("Change title style")
                .setMultiChoiceItems(R.array.styles, null, new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i, boolean b) {
                        if (b){
                            cur_styles.add(i);
                        }else{
                            cur_styles.remove(i);
                        }
                    }
                })
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        title.setTypeface(null, Typeface.NORMAL);
                        if (cur_styles.contains(0) && cur_styles.contains(1)){
                            Log.d("DEBUG", "BOTH");
                            title.setTypeface(null, Typeface.BOLD_ITALIC);
                        } else if (cur_styles.contains(0)){
                            Log.d("DEBUG", "BOLD");
                            title.setTypeface(null, Typeface.BOLD);
                        } else if (cur_styles.contains(1)){
                            Log.d("DEBUG", "ITALIC");
                            title.setTypeface(null, Typeface.ITALIC);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    public void singleChoice(View view){
        AlertDialog.Builder basicBuilder = new AlertDialog.Builder(this);
        basicBuilder
                .setTitle("Change title side")
                .setSingleChoiceItems(R.array.sides, side, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        side = i;
                    }
                })
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d("DEBUG", Integer.toString(side));
                        if (side == 0){
                            title.setGravity(Gravity.START);
                        }else if (side == 1){
                            title.setGravity(Gravity.END);
                        }else if (side == 2){
                            title.setGravity(Gravity.CENTER);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    public void spinner(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.spinner_dialog, null);
        builder
        .setView(v)
        .setPositiveButton("Change", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Spinner spinner =  v.findViewById(R.id.spinner);
                title.setTextSize(TypedValue.COMPLEX_UNIT_SP, (float) Math.pow(2, spinner.getSelectedItemPosition()));
            }
        })
        .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        })
        .show();
    }

    public void editText(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        final View v = inflater.inflate(R.layout.edit_text_layout, null);
        builder
                .setView(v)
                .setPositiveButton("Change", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        EditText text =  v.findViewById(R.id.editText);
                        title.setText(text.getText());
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                })
                .show();
    }
}
