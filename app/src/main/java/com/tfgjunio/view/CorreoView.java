package com.tfgjunio.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import com.tfgjunio.utils.EnvioCorreos;
import com.tfgjunio.R;
import com.tfgjunio.view.Crianza.AddCrianzaView;

public class CorreoView extends AppCompatActivity {

    private TextView tvToEmail;
    private EditText etSubject, etMessage;
    private Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correo);

        tvToEmail = findViewById(R.id.tvToEmail);
        etSubject = findViewById(R.id.etSubject);
        etMessage = findViewById(R.id.etMessage);
        btnSend = findViewById(R.id.btnSend);

        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String toEmail = tvToEmail.getText().toString();
                String subject = etSubject.getText().toString();
                String message = etMessage.getText().toString();

                if (!subject.isEmpty() && !message.isEmpty()) {
                    new SendMailTask().execute(toEmail, subject, message);
                } else {
                    Toast.makeText(CorreoView.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private class SendMailTask extends AsyncTask<String, Void, Boolean> {
        @Override
        protected Boolean doInBackground(String... params) {
            String toEmail = params[0];
            String subject = params[1];
            String message = params[2];

            try {
                EnvioCorreos mailSender = new EnvioCorreos();
                mailSender.sendMail(toEmail, subject, message);
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onPostExecute(Boolean result) {
            if (result) {
                showSuccessDialog();
            } else {
                Toast.makeText(CorreoView.this, "Failed to send email", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void showSuccessDialog() {
        new AlertDialog.Builder(CorreoView.this)
                .setTitle("Email Sent")
                .setMessage("The email has been sent successfully. Thank you for contacting us.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        goToMainMenu();
                    }
                })
                .show();
    }

    private void goToMainMenu() {
        Intent intent = new Intent(CorreoView.this, AddCrianzaView.class);
        startActivity(intent);
        finish(); // Optionally finish the current activity if you don't want the user to return to it
    }
}
