package mx.edu.ittepic.proyectounidad2;


import android.content.Intent;

import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AutoCompleteTextView;

import android.widget.Button;
import android.widget.EditText;

import com.airbnb.lottie.LottieAnimationView;


public class LoginActivity extends AppCompatActivity  {
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    Button botoniniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Inicio de sesion");
        animacion();

        mEmailView=findViewById(R.id.email);
        mPasswordView=findViewById(R.id.contra);
        botoniniciar=findViewById(R.id.inicio);
        botoniniciar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio= new Intent(LoginActivity.this,MainActivity.class);
                startActivity(inicio);
            }
        });

    }

    private void attemptLogin() {

        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            //showProgress(true);
          //  mAuthTask = new UserLoginTask(email, password);
           // mAuthTask.execute((Void) null);
        }
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 4;
    }




    public void animacion(){
        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.animation_view);
        animationView.setAnimation(R.raw.building);
        animationView.loop(true);
        animationView.playAnimation();
    }
}

