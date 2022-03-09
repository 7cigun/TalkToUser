package ru.gb.talktouser;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;


public class MessagesFragment extends Fragment {

    public static MessagesFragment newInstance() {
        MessagesFragment fragment = new MessagesFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_messages, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
    }

    void initView(View view) {
        view.findViewById(R.id.btnToast).setOnClickListener(view1 -> showToast());
        view.findViewById(R.id.btnSnackBar).setOnClickListener(view1 -> showSnackBar(view));
        view.findViewById(R.id.btnSnackBarWithAction).setOnClickListener(view1 -> showSnackBarWithAction(view));
        view.findViewById(R.id.AlertDialog).setOnClickListener(view1 -> showAlertDialog());
        view.findViewById(R.id.AlertDialogCustom).setOnClickListener(view1 -> showAlertDialogCustom());
    }

    void showToast() {
        Toast.makeText(requireContext(), "Отображание Toast", Toast.LENGTH_LONG).show();
    }

    void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }

    void showSnackBar(View view) {
        Snackbar.make(view, "Отображение SnackBar", Snackbar.LENGTH_LONG).show();
    }

    void showSnackBarWithAction(View view) {
        Snackbar.make(view, "Отображение SnackBarWithAction", Snackbar.LENGTH_LONG).setAction("Еще раз!", view1 -> {
            showToast();
        }).show();
    }

    void showAlertDialog() {
        new AlertDialog.Builder(requireContext())
                .setTitle("Диалог")
                .setMessage("Поговорим?")
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    showToast("Нажали OK");
                })
                .setNegativeButton("NO", (dialogInterface, i) -> {
                    showToast("Нажали NO");
                })
                .setNeutralButton("Надо подумать", (dialogInterface, i) -> {
                    showToast("Не уверен");
                })
                .show();
    }

    void showAlertDialogCustom() {

        View view = getLayoutInflater().inflate(R.layout.dialog_custom, null);

        AlertDialog alertDialog = new AlertDialog.Builder(requireContext())
                .setTitle("Диалог")
                .setMessage("Поговорим?")
                .setView(view)
                .show();

        view.findViewById(R.id.buttonCustomView).setOnClickListener(v -> {
            EditText editText = view.findViewById(R.id.editTextCustomView);
            showToast(editText.getText().toString());
            alertDialog.dismiss();
        });
    }
}