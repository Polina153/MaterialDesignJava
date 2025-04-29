package com.example.materialdesignjava;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        setSplashScreenLoadingParameters();
        setErrorListener();
        setButtonLogin();

    }

    private void setButtonLogin() {
        MaterialButton button = findViewById(R.id.sign_in_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), BottomAppBarActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setErrorListener() {
        TextInputEditText textInputEditText = findViewById(R.id.password_input_edittext);
        textInputEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // ничего не делаем
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // ничего не делаем
            }

            @Override
            public void afterTextChanged(Editable editable) {
                TextInputLayout passwordLayout = findViewById(R.id.password_edittext);
                if (editable != null && editable.length() < 10) {
                    passwordLayout.setError(null); // Ошибки нет
                } else {
                    passwordLayout.setError(getResources().getString(R.string.incorrect_password_error));
                }
            }
        });
    }

    private void setSplashScreenLoadingParameters() {
        final Boolean[] isHideSplashScreen = {false};
        CountDownTimer countDownTimer = new CountDownTimer(10_000, 1_000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                isHideSplashScreen[0] = true;
            }
        }.start();


        // Set up an OnPreDrawListener to the root view.
        final View content = findViewById(android.R.id.content);
        content.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        // Check whether the initial data is ready.
                        if (isHideSplashScreen[0]) {
                            // The content is ready. Start drawing.
                            content.getViewTreeObserver().removeOnPreDrawListener(this);
                            return true;
                        } else {
                            // The content isn't ready. Suspend.
                            return false;
                        }
                    }
                });
    }

}