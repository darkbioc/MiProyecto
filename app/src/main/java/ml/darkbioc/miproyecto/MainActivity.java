package ml.darkbioc.miproyecto;

import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity
{

	private FirebaseAuth mAuth;

	@Override protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mAuth = FirebaseAuth.getInstance();

		Toolbar toolbar;
		toolbar = findViewById(R.id.toolbar);
		setSupportActionBar(toolbar);

		FragmentManager fragmentManager = getSupportFragmentManager( );
		FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction( );

		MainFragment fragmentMain = new MainFragment( );
		fragmentTransaction.replace(android.R.id.content, fragmentMain);
	}

	@Override protected void onStart()
	{
		super.onStart();
		// Check if user is signed in (non-null) and update UI accordingly.
		FirebaseUser currentUser = mAuth.getCurrentUser();
		if(currentUser == null)
		{
			Intent i=new Intent(getApplicationContext(), LoginActivity.class);
			startActivity(i);
			finish();
		}
		else
		{

		}
	}
}
