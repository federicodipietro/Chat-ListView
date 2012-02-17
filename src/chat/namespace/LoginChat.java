package chat.namespace;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class LoginChat extends Activity{

	Intent intentLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loginchat);
		String usrname,passwrd;
		final EditText eTextUsr = (EditText)findViewById(R.id.editTextusern);
		final EditText eTextPass = (EditText)findViewById(R.id.editTextpass);
		eTextUsr.setHint("username");
		Button btnLogin = (Button)findViewById(R.id.btnJoin);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				intentLogin = new Intent(LoginChat.this, SelectUser.class);
				intentLogin.putExtra("username", eTextUsr.getText().toString());
				intentLogin.putExtra("password", eTextPass.getText().toString());
				startActivity(intentLogin);
			}
		});
	}
}
