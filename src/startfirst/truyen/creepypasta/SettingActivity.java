package startfirst.truyen.creepypasta;


import startfirst.truyen.creepypasta.utis.Utis;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;

public class SettingActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setting);
		
		final SeekBar seekbarFont =(SeekBar)findViewById(R.id.seekBarFont);
		
		seekbarFont.setMax(50);
		
		
		
		final TextView FontSize = (TextView)findViewById(R.id.txtSize);
		
		
		
		final RadioGroup radioBG = (RadioGroup) findViewById(R.id.radioBG);
		Button btnSave = (Button)findViewById(R.id.btnSave);
		final SharedPreferences settings = getSharedPreferences("UserInfo",
				MODE_PRIVATE);
		final SharedPreferences.Editor editor = settings.edit();

		RadioButton radioButton = (RadioButton) findViewById(settings.getInt(
				"ID", R.id.radio5));

		radioButton.setChecked(true);

		final RelativeLayout ll = (RelativeLayout) findViewById(R.id.RelativeLayout_Setting);

		ll.setBackgroundResource(Utis.RandomBackground(settings.getInt("BG", 1)));
		
		
		seekbarFont.setProgress(settings.getInt("FONT", 16));
		
		FontSize.setText(settings.getInt("FONT", 16) +"");
		
		seekbarFont.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				FontSize.setText(progress +"");
				if (progress < 10) {
					FontSize.setText(10 +"");
				}
				
				
			}
		});
		
		radioBG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                switch(checkedId)
                {
                case R.id.radio0:
					ll.setBackgroundResource(Utis.RandomBackground(0));
					editor.putInt("BG", 0);
                    break;
                case R.id.radio1:
					ll.setBackgroundResource(Utis.RandomBackground(1));
					editor.putInt("BG", 1);
                    break;
                case R.id.radio2:
					ll.setBackgroundResource(Utis.RandomBackground(2));
					editor.putInt("BG", 2);
                    break;
                case R.id.radio3:
					ll.setBackgroundResource(Utis.RandomBackground(3));
					editor.putInt("BG", 3);
                    break;
                case R.id.radio4:
					ll.setBackgroundResource(Utis.RandomBackground(4));
					editor.putInt("BG", 4);
                    break;
                case R.id.radio5:
					ll.setBackgroundResource(Utis.RandomBackground(-1));
					editor.putInt("BG", -1);
                    break;
                }
            }
        });
		
		
		btnSave.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				int selectedId = radioBG.getCheckedRadioButtonId();
				editor.putInt("ID", selectedId);
				if (seekbarFont.getProgress()< 10) {
					editor.putInt("FONT", 10);
				}else {
					editor.putInt("FONT", seekbarFont.getProgress());
				}
				editor.commit();
				startActivityAfterCleanup(MainActivity.class);
			}
		});
		
		
	}
	
	
	
	private void startActivityAfterCleanup(Class<?> cls) {
		  Intent intent = new Intent(getApplicationContext(), cls);
		  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  startActivity(intent);
	}
	
	


}
