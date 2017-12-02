package startfirst.truyen.creepypasta;

import java.util.ArrayList;
import java.util.List;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ThongTinActivity extends ListActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		List<item> input = new ArrayList<ThongTinActivity.item>();
		input.add(new item("Tuyển Tập Thơ Xuân Quỳnh", "market://details?id=startfirst.tho.nhathoxuanquynh", R.drawable.xq));
		input.add(new item("Tuyển Tập Thơ Hàn Mặc Tử", "market://details?id=startfirst.tho.nhathohanmactu", R.drawable.hmt));
		input.add(new item("Game Siêu Troll", "market://details?id=startfirst.troll.sieutroll", R.drawable.sieutroll));
		input.add(new item("Tất cả", "market://search?q=pub:Start.First", R.drawable.logo));
		setListAdapter(new ListAppAdapter(this, R.layout.item_app, input));
	
	}
	
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String link = ((item)l.getAdapter().getItem(position)).link;
		
		Intent marketIntent = new Intent(Intent.ACTION_VIEW,Uri.parse(link));
		startActivity(marketIntent);
	}
	
	
	
	
	
	
	private class item{
		public String name;
		public String link;
		public int resIcon;
		
		
		public item(String name, String link,int icon) {
			this.name = name;
			this.link = link;
			this.resIcon = icon;
		}
		
	}
	
	
	
	
	
	private class ListAppAdapter extends ArrayAdapter<item>{
		private Context context;
		public ListAppAdapter(Context context, int resource, List<item> objects) {
			super(context, resource, objects);
			this.context = context;
		}
		
		
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View view = convertView;
		       if (view == null) {
		            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		            view = inflater.inflate(R.layout.item_app, null);
		        }
			item data = getItem(position);
			
			if (data != null) {
				ImageView image_app = (ImageView)view.findViewById(R.id.image_app);
				image_app.setImageResource(data.resIcon);
				
				TextView name = (TextView)view.findViewById(R.id.txt_app);
				name.setText(data.name);
			}
		       
			
			return view;
		}
		
	}


}
