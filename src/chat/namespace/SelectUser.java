package chat.namespace;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

public class SelectUser extends Activity {
	Intent intentUser;
	ArrayAdapter<String> adapteruser;
	String selectedString;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		final String username;
		final String password;
		setContentView(R.layout.selectuser);
		
		
		Button btnAdd = (Button)findViewById(R.id.btnAdd);
		Button btnDel = (Button)findViewById(R.id.btnDel);
		Button btnStart = (Button)findViewById(R.id.btnStart);
		final EditText etextUsr = (EditText)findViewById(R.id.editTextUser);
		username=getIntent().getExtras().getString("username");
		password=getIntent().getExtras().getString("password");
		adapteruser = new ArrayAdapter<String>(SelectUser.this, android.R.layout.simple_list_item_single_choice);
		adapteruser.add("loreti@ppl.eln.uniroma2.it");
		
		final ListView listVwuser = (ListView)findViewById(R.id.lWiewuser);
		listVwuser.setAdapter(adapteruser);
        listVwuser.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
       
        
        btnStart.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
					
					selectedString=listVwuser.getItemAtPosition(listVwuser.getCheckedItemPosition()).toString();
					intentUser = new Intent(SelectUser.this, ChatActivity.class );
					intentUser.putExtra("username", username);
					intentUser.putExtra("password", password);
					intentUser.putExtra("destination", selectedString);
					startActivity(intentUser);
			}
		});
        
        btnAdd.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				adapteruser.add(etextUsr.getText().toString()+"@ppl.eln.uniroma2.it");
			}
		});
        
        btnDel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
			
			selectedString=listVwuser.getItemAtPosition(listVwuser.getCheckedItemPosition()).toString();
			for(int i=0;i<adapteruser.getCount();i++)
				{
				if (selectedString==adapteruser.getItem(i))
					adapteruser.remove(adapteruser.getItem(i));
				}
			}
		});
	}
}
