package ml.darkbioc.miproyecto;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;

public class LoginActivity extends AppCompatActivity
{

	private static final String TAG="MyDebug";
	private static final int RC_SIGN_IN= 1234;
	Button signUp;
	Button login;
	com.google.android.gms.common.SignInButton gSignIn;

	EditText mail;
	EditText pass;
	private FirebaseAuth mAuth;


	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mAuth=FirebaseAuth.getInstance();

		signUp=findViewById(R.id.bSignUpLogin);
		login=findViewById(R.id.bLogin);
		gSignIn = findViewById(R.id.gSignIn);
		mail=findViewById(R.id.etMailLogin);
		pass=findViewById(R.id.etPassLogin);

		signUp.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
				startActivityForResult(i, 1);
			}
		});

		if(!mail.getText().toString().equals("") && !pass.getText().toString().equals(""))
		{
			login.setOnClickListener(new View.OnClickListener()
			{
				@Override public void onClick(View v)
				{
					mAuth.signInWithEmailAndPassword(mail.getText().toString(), pass.getText().toString()).addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>()
					{
						@Override public void onComplete(@NonNull Task<AuthResult> task)
						{
							if(task.isSuccessful())
							{
								FirebaseUser user=mAuth.getCurrentUser();
								if(user.isEmailVerified())
								{
									// Sign in success, update UI with the signed-in user's information
									Log.d(TAG, "signInWithEmail:success");
									//FirebaseUser user = mAuth.getCurrentUser();
									Intent i=new Intent(getApplicationContext(), MainActivity.class);
									startActivity(i);
									finish();
								}
								else
								{
									Toast.makeText(getApplicationContext(), R.string.notVerified, Toast.LENGTH_LONG).show();
								}
							}
							else
							{
								// If sign in fails, display a message to the user.
								Log.w(TAG, "signInWithEmail:failure", task.getException());
								Toast.makeText(LoginActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
							}
						}
					});
				}
			});
		}
		else
		{
			Toast.makeText(getApplicationContext(), R.string.toast_empty_fields, Toast.LENGTH_SHORT).show();
		}

		gSignIn.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				GoogleSignInOptions gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken("177072883292-ia4oastdvpeb2imjol3cfgeo2evc3c64.apps.googleusercontent.com").requestEmail().build();
				// Build a GoogleSignInClient with the options specified by gso.
				GoogleSignInClient mGoogleSignInClient=GoogleSignIn.getClient(LoginActivity.this, gso);
				Intent signInIntent=mGoogleSignInClient.getSignInIntent();
				startActivityForResult(signInIntent, RC_SIGN_IN);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		super.onActivityResult(requestCode, resultCode, data);

		if(requestCode == 1)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				Intent i=new Intent(getApplicationContext(), MainActivity.class);
				startActivity(i);
				finish();
			}
		}

		if(requestCode == RC_SIGN_IN)
		{
			Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
			try
			{
				// Google Sign In was successful, authenticate with Firebase
				GoogleSignInAccount account=task.getResult(ApiException.class);
				firebaseAuthWithGoogle(account);
			}
			catch(ApiException e)
			{
				// Google Sign In failed, update UI appropriately
				Log.w(TAG, "Google sign in failed", e);
				// ...
			}
		}
	}


	private void firebaseAuthWithGoogle(GoogleSignInAccount acct)
	{
		Log.d(TAG, "firebaseAuthWithGoogle:" + acct.getId());

		AuthCredential credential=GoogleAuthProvider.getCredential(acct.getIdToken(), null);
		mAuth.signInWithCredential(credential).addOnCompleteListener(this, new OnCompleteListener<AuthResult>()
		{
			@Override public void onComplete(@NonNull Task<AuthResult> task)
			{
				if(task.isSuccessful())
				{
					// Sign in success, update UI with the signed-in user's information
					Log.d(TAG, "signInWithCredential:success");
					FirebaseUser user=mAuth.getCurrentUser();
					Intent i = new Intent(getApplicationContext(), MainActivity.class);
					startActivity(i);
					finish();
				}
				else
				{
					// If sign in fails, display a message to the user.
					Log.w(TAG, "signInWithCredential:failure", task.getException());
					//Snackbar.make(findViewById(R.id.main_layout), "Authentication Failed.", Snackbar.LENGTH_SHORT).show();
				}

				// ...
			}
		});
	}
}
