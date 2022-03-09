package ru.gb.talktouser;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.DialogFragment;
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
        view.findViewById(R.id.btnAlertDialog).setOnClickListener(view1 -> showAlertDialog());
        view.findViewById(R.id.btnAlertDialogCustom).setOnClickListener(view1 -> showAlertDialogCustom());
        view.findViewById(R.id.btnDialogFragment).setOnClickListener(view1 -> showDialogFragment());
        view.findViewById(R.id.btnDialogFragmentCustom).setOnClickListener(view1 -> showDialogFragmentCustom());
        view.findViewById(R.id.btnBottomSheetDialogFragment).setOnClickListener(view1 -> showBottomSheetDialogFragment());
        view.findViewById(R.id.btnPushNotification).setOnClickListener(view1 -> showPushNotification());
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

    void showDialogFragment() {
        new MyDialogFragment().show(getActivity().getSupportFragmentManager(), "SupportFM");
    }

    void showDialogFragmentCustom() {
        new MyDialogFragmentCustom().show(getActivity().getSupportFragmentManager(), "SupportFM");
    }

    void showBottomSheetDialogFragment() {
        new MyBottomSheetDialogFragment().show(getActivity().getSupportFragmentManager(), "SupportFM");
    }

    void showPushNotification() {
        NotificationManager notificationManager = (NotificationManager) requireActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O) {
            NotificationChannel notificationChannel = new NotificationChannel("1", "CHANNEL1", NotificationManager.IMPORTANCE_HIGH);
            notificationChannel.setDescription("Канал для Push-уведомлений");
            notificationManager.createNotificationChannel(notificationChannel);
        }

        Notification notification = new NotificationCompat.Builder(requireContext(), "1")
                .setContentTitle("Заголовок Push")
                .setContentText("Текст Push")
                .setPriority((Notification.PRIORITY_HIGH))
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .build();

        notificationManager.notify(1, notification);
    }
}