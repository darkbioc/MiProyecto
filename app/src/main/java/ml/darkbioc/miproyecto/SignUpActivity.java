package ml.darkbioc.miproyecto;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpActivity extends AppCompatActivity
{

	Button goBack;
	Button signUp;

	EditText etPass;
	EditText etRepPass;
	EditText etUserName;
	EditText etMail;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);

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
				//if( )
			}
		});
	}
}
