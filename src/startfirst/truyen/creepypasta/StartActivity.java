package startfirst.truyen.creepypasta;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.Menu;
import android.view.Window;
import android.widget.TextView;

public class StartActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_start);
		
		
		TextView tacgia = (TextView)findViewById(R.id.txt_start_tacgia);
		Typeface typeFace=Typeface.createFromAsset(getAssets(),"fonts/UVNBanhMi.TTF");
		tacgia.setTypeface(typeFace);
	
		
		
		
		new Handler().postDelayed(new Runnable() {
			
			@Override
			public void run() {
	            Intent i = new Intent(StartActivity.this, MainActivity.class);
	            startActivity(i);
	            finish();
	            System.gc();
			}
		}, 1000);
	}
		


}
