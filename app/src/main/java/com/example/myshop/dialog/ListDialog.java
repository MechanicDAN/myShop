package com.example.myshop.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myshop.R;

public class ListDialog extends AppCompatDialogFragment {
    private int flag;
    private String title;
    private String name;
    private EditText nameEditText;
    private ExampleDialogListener listener;
    private int color = 0;

    public void setFlag(int flag){
        this.flag = flag;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setName(String name){
        this.name = name;
    }
    public void setColor(int color){this.color = color;}

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.list_dialog_layout, null);

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEditText.getText().toString();
                        if(flag == 0) listener.applyCreateTexts(name, color);
                        if(flag == 1) listener.applyEditTexts(name, color);
                    }
                });
        nameEditText = view.findViewById(R.id.listEditName);
        nameEditText.setText(name);

        final Button button0 = view.findViewById(R.id.button0);
        final Button button1 = view.findViewById(R.id.button1);
        final Button button2 = view.findViewById(R.id.button2);
        final Button button3 = view.findViewById(R.id.button3);
        final Button button4 = view.findViewById(R.id.button4);
        final Button button5 = view.findViewById(R.id.button5);
        final Button button6 = view.findViewById(R.id.button6);
        final Button button7 = view.findViewById(R.id.button7);
        button0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                button0.setText("+");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                color = 0;
            }
        });
        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("+");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                color = 1;
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("+");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                color = 2;
            }
        });
        button3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("+");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                color = 3;
            }
        });
        button4.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("+");
                button5.setText("");
                button6.setText("");
                button7.setText("");
                color = 4;
            }
        });
        button5.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("+");
                button6.setText("");
                button7.setText("");
                color = 5;
            }
        });
        button6.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("+");
                button7.setText("");
                color = 6;
            }
        });
        button7.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                button0.setText("");
                button1.setText("");
                button2.setText("");
                button3.setText("");
                button4.setText("");
                button5.setText("");
                button6.setText("");
                button7.setText("+");
                color = 7;
            }
        });


        return builder.create();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (ExampleDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implements ExampleDialogListener");
        }
    }

    public interface ExampleDialogListener{
        void applyCreateTexts(String name, int color);
        void applyEditTexts(String name, int color);
    }

}
