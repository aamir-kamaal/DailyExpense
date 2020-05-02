package com.example.aamir.dailyexpense;

import android.graphics.Typeface;
import android.os.Handler;
import android.os.Bundle;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LaunchingActivity extends AppCompatActivity {

    private TextView expense;
    int SIGN_IN = 0;
    SignInButton signInButton;
    GoogleSignInClient googleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launching);
        signInButton = findViewById(R.id.signInButton);


        //Google sign in code written here
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();

        //Building signIn client
        googleSignInClient = GoogleSignIn.getClient(this,gso);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });

        expense = (TextView) findViewById(R.id.textView);
        Typeface face = Typeface.createFromAsset(getAssets(),"Caviar_Dreams_Bold.ttf");
        expense.setTypeface(face);

        ImageView logo = (ImageView) findViewById(R.id.imageView);
        //ImageButton signIn = (ImageButton) findViewById(R.id.signIn);

        signInButton.setAlpha(0f);
        expense.animate().translationYBy(170).setDuration(2000);
        logo.animate().scaleX(0.5f).setDuration(1000);
        logo.animate().scaleY(0.5f).setDuration(1000);
        logo.animate().alpha(1).translationY(-300).setDuration(1000);
        signInButton.animate().alpha(1).translationY(300).setDuration(2000);


        /*Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i  = new Intent(LaunchingActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        },4000);*/


    }

    private void signIn()
    {
        Intent signInIntent = googleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent,SIGN_IN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode == SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask)
    {
        try
        {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
            startActivity(new Intent(LaunchingActivity.this,MainActivity.class));
        }
        catch (ApiException api)
        {
            Log.d("GOOGGLE SIGN IN","Error while logging in");
            Toast.makeText(this,"Login Failed !!",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onStart() {

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
        {
            startActivity(new Intent(LaunchingActivity.this,MainActivity.class));

        }
        super.onStart();
    }
}
