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

public class PurchasesDialog extends AppCompatDialogFragment {
    int flag;
    private String title;
    private String name;
    private String cost;
    private String quantity;
    private EditText nameEditText;
    private EditText costEditText;
    private EditText quantityEditText;
    private ExampleDialogListener listener;
    private int color = 0;

    public void setFlag(int flag) {
        this.flag = flag;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.purchases_dialog_layout, null);

        builder.setView(view)
                .setTitle(title)
                .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String name = nameEditText.getText().toString();
                        String cost = costEditText.getText().toString();
                        String quantity = quantityEditText.getText().toString();
                        if (flag == 0) listener.applyCreateTexts(name, cost, quantity, color);
                        if (flag == 1) listener.applyEditTexts(name, cost, quantity, color);
                    }
                });
        nameEditText = view.findViewById(R.id.editName);
        costEditText = view.findViewById(R.id.editCost);
        quantityEditText = view.findViewById(R.id.editQuantity);
        nameEditText.setText(name);
        costEditText.setText(cost);
        quantityEditText.setText(quantity);

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

    public interface ExampleDialogListener {
        void applyCreateTexts(String name, String cost, String quantity, int color);
        void applyEditTexts(String name, String cost, String quantity, int color);
    }
}
