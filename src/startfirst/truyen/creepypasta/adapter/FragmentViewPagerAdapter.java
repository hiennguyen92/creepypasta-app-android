package startfirst.truyen.creepypasta.adapter;

import java.util.ArrayList;

import startfirst.truyen.creepypasta.R;
import startfirst.truyen.creepypasta.dto.Truyen;
import startfirst.truyen.creepypasta.fragment.FragmentGioiThieu;
import startfirst.truyen.creepypasta.fragment.FragmentTruyen;






import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.widget.Toast;

public class FragmentViewPagerAdapter extends FragmentStatePagerAdapter{

	
	ArrayList<Truyen> _data;
	
	
	public FragmentViewPagerAdapter(FragmentManager fm, ArrayList<Truyen> data) {
		super(fm);
		_data = data;

	}

	


	@Override
	public Fragment getItem(int position) {
			Log.v(""+_data.size(), position+"");
			switch (position) {
			case 0:
				return new FragmentGioiThieu();
			default:
				return FragmentTruyen.newInstance(_data.get(position-1));	
			}
		
	}

	@Override
	public CharSequence getPageTitle(int position) {

			if (position == 0) {
				return "Giới Thiệu";
			}else {
				return FragmentTruyen.newTitle(_data.get(position-1));
			}
	}
	
	
	
	@Override
	public int getCount() {
		return _data.size();
	}

	
	
	
	

}
