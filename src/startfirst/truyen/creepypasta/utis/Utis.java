package startfirst.truyen.creepypasta.utis;

import java.util.Random;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import startfirst.truyen.creepypasta.R;





public class Utis {
	static Integer[] data = {R.drawable.bg1,R.drawable.bg3,R.drawable.bg4,R.drawable.bg5,R.drawable.bg6};
	public static int RandomBackground(int bg) {
		if (bg==-1) {
			Random rand = new Random();
			return data[rand.nextInt(4) + 1];
		}
		else {
			return data[bg];
		}
	}
	
	
	
	public static void goToLink(Context context, String link) {
		Uri uriUrl = Uri.parse(link);
		Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl); 
		context.startActivity(launchBrowser);
	}
	
	
}
