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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity
{

	private static final String TAG= "MyDebug";
	Button goBack;
	Button signUp;

	private FirebaseAuth mAuth;

	EditText etPass;
	EditText etRepPass;
	EditText etUserName;
	EditText etMail;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

		mAuth = FirebaseAuth.getInstance();

		goBack = findViewById(R.id.bBackSignUp);
		signUp = findViewById(R.id.bSignUp);

		etPass = findViewById(R.id.etPassSignUp);
		etRepPass = findViewById(R.id.etRePass);
		etUserName = findViewById(R.id.etAliasSignUp);
		etMail = findViewById(R.id.etMailSignUp);

		goBack.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				finish();
			}
		});

		signUp.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				String password = etPass.getText().toString();
				String email = etMail.getText().toString();

				if(etUserName.getText().toString().length() == 0 || password.length() == 0 || etRepPass.getText().toString().length() == 0 || email.length() == 0)
				{
					Toast.makeText(getApplicationContext(),R.string.ToastVoidFields,Toast.LENGTH_LONG).show();
				}
				else if(etUserName.getText().toString().length() < 4)
				{
					Toast.makeText(getApplicationContext(),R.string.ToastUserShort,Toast.LENGTH_LONG).show();
				}
				else if(!password.equals(etRepPass.getText().toString()))
				{
					Toast.makeText(getApplicationContext(),R.string.ToastRePassFail,Toast.LENGTH_LONG).show();
				}
				else
				{
					mAuth.createUserWithEmailAndPassword(email, password)
							.addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(@NonNull Task<AuthResult> task) {
									if (task.isSuccessful()) {
										// Sign in success, update UI with the signed-in user's information
										Log.d(TAG, "createUserWithEmail:success");
										FirebaseUser user = mAuth.getCurrentUser();
										Intent returnIntent = new Intent();
										setResult(Activity.RESULT_OK,returnIntent);
										finish();
										//updateUI(user);
									} else {
										// If sign in fails, display a message to the user.
										Log.w(TAG, "createUserWithEmail:failure", task.getException());
										Toast.makeText(getApplicationContext(), task.getException().getLocalizedMessage(), Toast.LENGTH_SHORT).show();
										//updateUI(null);
									}

									// ...
								}
							});
				}
			}
		});
	}
}
