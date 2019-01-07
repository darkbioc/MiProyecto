package ml.darkbioc.miproyecto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LoginActivity extends AppCompatActivity
{

	Button signUp;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		signUp= findViewById(R.id.bSignUpLogin);

		signUp.setOnClickListener(new View.OnClickListener()
		{
			@Override public void onClick(View v)
			{
				Intent i = new Intent(getApplicationContext(), SignUpActivity.class);
				startActivity(i);
			}
		});
	}
}
