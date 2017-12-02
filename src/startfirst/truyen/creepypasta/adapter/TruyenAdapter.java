package startfirst.truyen.creepypasta.adapter;

import java.util.ArrayList;

import startfirst.truyen.creepypasta.R;
import startfirst.truyen.creepypasta.dto.Truyen;



import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class TruyenAdapter extends BaseAdapter{

	
	private Context _context;
	private ArrayList<Truyen> _data;
	
	
	public TruyenAdapter(Context context,ArrayList<Truyen> data) {
		_context = context;
		_data = data;
	}
	
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return _data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return _data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View view, ViewGroup patent) {
		
		
		LayoutInflater inflater = ((Activity) _context).getLayoutInflater();
		View rowView;
		rowView= inflater.inflate(R.layout.item_truyen, null, true);
		TextView txtTitle = (TextView) rowView.findViewById(R.id.txt_title_tho);
		TextView txtSubTitle = (TextView) rowView.findViewById(R.id.txt_subtitle_tho);
		txtTitle.setText(_data.get(position).get_TenTruyen());
		txtSubTitle.setText(_data.get(position).get_NoiDung().split("\n")[0]);

		return rowView;
	}

	
	
}
