package com.example.up;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.prefs.Preferences;

public class MainActivity extends AppCompatActivity {
    private TextView textResultado;
    private TextInputEditText editNome;
    private Button buttonSalvar;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textResultado = findViewById(R.id.textResultado);
        editNome = findViewById(R.id.editNome);
        buttonSalvar = findViewById(R.id.buttonSalvar);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);
                SharedPreferences.Editor editor = preferences.edit();

                if (editNome.getText().toString().equals("")) {
                    Toast.makeText(getApplicationContext(), "Digite um nome", Toast.LENGTH_SHORT).show();
                }

                String nome = editNome.getText().toString();
                editor.putString("nome", nome);
                editor.commit();
                textResultado.setText("Ola, " + nome);
            }
        });
        SharedPreferences preferences = getSharedPreferences(ARQUIVO_PREFERENCIA, 0);

        if (preferences.contains("nome")) {
            String nome = preferences.getString("nome", "\"usuário não definido\"");
            textResultado.setText("Olá, " + nome );

        } else {
            textResultado.setText("Olá, usuário não definido");
        }
    }
}