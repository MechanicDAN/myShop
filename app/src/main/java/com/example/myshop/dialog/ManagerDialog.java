package com.example.myshop.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import com.example.myshop.R;

public class ManagerDialog extends AppCompatDialogFragment {
    private int flag;
    private String title;
    private String name;
    private EditText nameEditText;
    private ExampleDialogListener listener;

    public void setFlag(int flag){
        this.flag = flag;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setName(String name){
        this.name = name;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        final View view = inflater.inflate(R.layout.manager_dialog_layout, null);

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
                        if(flag == 0) listener.applyCreateTexts(name);
                        if(flag == 1) listener.applyEditTexts(name);
                    }
                });
        nameEditText = view.findViewById(R.id.listEditName);
        nameEditText.setText(name);

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
        void applyCreateTexts(String name);
        void applyEditTexts(String name);
    }

}
