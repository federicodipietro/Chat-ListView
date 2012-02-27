package chat.namespace;

import org.jivesoftware.smack.Connection;
import org.jivesoftware.smack.ConnectionConfiguration;
import org.jivesoftware.smack.PacketListener;
import org.jivesoftware.smack.XMPPConnection;
import org.jivesoftware.smack.XMPPException;
import org.jivesoftware.smack.filter.MessageTypeFilter;
import org.jivesoftware.smack.packet.Message;
import org.jivesoftware.smack.packet.Packet;



import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import android.widget.Toast;

public class ChatActivity extends Activity {
   ListView lv;
   EditText etext;
   Connection connection;
   ArrayAdapter<String> adapter;
   
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        etext=(EditText)findViewById(R.id.editText1);
        lv=(ListView)findViewById(R.id.listViewchat);
        LinearLayout lDest = (LinearLayout)findViewById(R.id.linearLayoutDest);
        TextView eTextDest = (TextView)findViewById(R.id.textViewDest);
        Button btnSend = (Button)findViewById(R.id.btnSend);
        
        eTextDest.setText("Chat avviata con "+getIntent().getExtras().getString("destination").toString());
        adapter = new ArrayAdapter<String>(ChatActivity.this, android.R.layout.simple_list_item_1);lv.setAdapter(adapter);
        adapter.add("Chat");
        
        
       
        try{
        	ConnectionConfiguration config = new ConnectionConfiguration("ppl.eln.uniroma2.it",5222);
        	config.setSecurityMode(ConnectionConfiguration.SecurityMode.disabled);
        	connection = new XMPPConnection(config);
        	connection.connect();
        	connection.login(getIntent().getExtras().getString("username").toString(),
        						getIntent().getExtras().getString("password").toString());
        	
        	connection.addPacketListener(new PacketListener() {
    			
    			@Override
    			public void processPacket(Packet pkt) {
    				Message msg = (Message) pkt;
    				String from = msg.getFrom();
    				String body = msg.getBody();
    				adapter.add(from+" : "+body+"\n");
    				lv.setSelection(adapter.getCount()-1);
    				Log.e("RIC",from+"  "+body);
    			}
    		}, new MessageTypeFilter(Message.Type.normal));
        	
        }catch (XMPPException e){
        	e.printStackTrace();
        	Toast.makeText(ChatActivity.this, "Nessuna connessione", Toast.LENGTH_SHORT);
        }
        
      
        btnSend.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//adapter = new ArrayAdapter<String>(ChatActivity.this,R.layout.row, R.id.rowText);
				adapter.add("ME : "+etext.getText().toString());
				lv.setSelection(adapter.getCount()-1);
				//spedizione messaggio
				Message msg = new Message();
				//al posto di loreti inserire username del destinatario
				msg.setTo(getIntent().getExtras().getString("destination").toString());
				msg.setBody(etext.getText().toString());
				lv.setSelection(adapter.getCount()-1);
				connection.sendPacket(msg);
				Log.d("DEST",getIntent().getExtras().getString("destination").toString());
				Log.d("MSG", msg.getBody().toString());
			}
		});
    }
}