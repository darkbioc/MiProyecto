package ml.darkbioc.miproyecto;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity
{

	Button signUp;
	private FirebaseAuth mAuth;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mAuth=FirebaseAuth.getInstance();

		signUp=findViewById(R.id.bSignUpLogin);

		signUp.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent i=new Intent(getApplicationContext(), SignUpActivity.class);
				startActivityForResult(i, 1);
			}
		});
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data)
	{

		if(requestCode == 1)
		{
			if(resultCode == Activity.RESULT_OK)
			{
				finish();
			}
		}
	}
}
