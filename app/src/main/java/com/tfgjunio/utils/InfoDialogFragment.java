package com.tfgjunio.utils;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class InfoDialogFragment extends DialogFragment {

    private static final String ARG_MESSAGE = "message";
    private DialogDismissListener dismissListener;

    public static InfoDialogFragment newInstance(String message, DialogDismissListener listener) {
        InfoDialogFragment fragment = new InfoDialogFragment();
        fragment.dismissListener = listener;
        Bundle args = new Bundle();
        args.putString(ARG_MESSAGE, message);
        fragment.setArguments(args);
        return fragment;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        assert getArguments() != null;
        String message = getArguments().getString(ARG_MESSAGE);
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setMessage(message)
                .setPositiveButton("Siguiente", (dialog, id) -> {
                    dialog.dismiss();
                    if (dismissListener != null) {
                        dismissListener.onDialogDismiss();
                    }
                })
                .setCancelable(false);
        return builder.create();
    }
}
