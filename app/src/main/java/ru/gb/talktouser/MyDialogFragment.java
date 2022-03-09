package ru.gb.talktouser;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        return new AlertDialog.Builder(requireContext())
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

    void showToast(String message) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show();
    }
}
