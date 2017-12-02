package startfirst.truyen.creepypasta.fragment;

import startfirst.truyen.creepypasta.R;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentGioiThieu extends SherlockFragment{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_gioi_thieu, null);
		
		SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", getActivity().MODE_PRIVATE);
		
		TextView gioithieu = (TextView)view.findViewById(R.id.fragment_gioi_thieu_text);
		
		gioithieu.setTextSize(settings.getInt("FONT", 16));
		
			
		return view;
	}
	
	
}
