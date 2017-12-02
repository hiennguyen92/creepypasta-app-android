package startfirst.truyen.creepypasta;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class AboutActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		
		TextView Email = (TextView)findViewById(R.id.txtEmail);
		
		Email.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent email = new Intent(Intent.ACTION_SEND);
				email.putExtra(Intent.EXTRA_EMAIL, new String[]{"start.first.group@gmail.com"});		  
				email.putExtra(Intent.EXTRA_SUBJECT, "Creepypasta");
				email.putExtra(Intent.EXTRA_TEXT, "message");
				email.setType("message/rfc822");
				startActivity(Intent.createChooser(email, "Choose an Email client :"));
			}
		});
		
	}


}
