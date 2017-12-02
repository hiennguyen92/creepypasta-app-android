package startfirst.truyen.creepypasta;

import java.io.IOException;
import java.util.ArrayList;



import startfirst.truyen.creepypasta.adapter.FragmentViewPagerAdapter;
import startfirst.truyen.creepypasta.database.DataBaseHelper;
import startfirst.truyen.creepypasta.dto.Truyen;
import startfirst.truyen.creepypasta.utis.Utis;



import com.actionbarsherlock.app.ActionBar;
import com.actionbarsherlock.app.SherlockFragmentActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;
import com.actionbarsherlock.view.MenuItem.OnMenuItemClickListener;
import com.actionbarsherlock.widget.SearchView;
import com.viewpagerindicator.PageIndicator;
import com.viewpagerindicator.TitlePageIndicator;


import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends SherlockFragmentActivity implements
		SearchView.OnQueryTextListener, SearchView.OnSuggestionListener {

	DataBaseHelper DBHelper;
	SearchView searchView;
	ArrayList<Truyen> _data;
	
	
	FragmentViewPagerAdapter mAdapter;
	ViewPager mPager;
    PageIndicator mIndicator;
    
    int BG;
	

	private static final String[] COLUMNS = { BaseColumns._ID,
			SearchManager.SUGGEST_COLUMN_TEXT_1,
			SearchManager.SUGGEST_COLUMN_TEXT_2 };

	private SuggestionsAdapter mSuggestionsAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ActionBar ab = getSupportActionBar();
		ab.setHomeButtonEnabled(true);	
		
		
		SharedPreferences settings = getSharedPreferences("UserInfo", MODE_PRIVATE);
		BG = settings.getInt("BG", 1);
		
		
		
		RelativeLayout ll = (RelativeLayout)findViewById(R.id.RelativeLayout_Main);
		ll.setBackgroundResource(Utis.RandomBackground(BG));
		
		
		DBHelper = new DataBaseHelper(this);
		try {
			
			DBHelper.createDataBase();
			DBHelper.openDataBase();	
			_data = DBHelper.GetAllTruyen();
			
			
			
			
			Button btnBack = (Button)findViewById(R.id.btnBackList);
			btnBack.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					mPager.setCurrentItem(0);
				}
			});
			
			
			
			mAdapter = new FragmentViewPagerAdapter(getSupportFragmentManager(),_data);
			
			
			 mPager = (ViewPager)findViewById(R.id.pager);
		        mPager.setAdapter(mAdapter);
		        mPager.setCurrentItem(0);
		        
		        mIndicator = (TitlePageIndicator)findViewById(R.id.indicator);
		        mIndicator.setViewPager(mPager);
			
			
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}
	
	
	public  ViewPager getViewPager ()  { 
		if (null == mPager) {
			mPager = (ViewPager) findViewById(R.id.pager);
		}
		return mPager;
	}
	
	

	
	
	
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
	}
	
	
	
	
	


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		// return super.onCreateOptionsMenu(menu);
		searchView = new SearchView(getSupportActionBar().getThemedContext());
		searchView.setQueryHint("Keyword...");
		searchView.setOnQueryTextListener(this);
		searchView.setOnSuggestionListener(this);
		
		MatrixCursor cursor = new MatrixCursor(COLUMNS);
		if (mSuggestionsAdapter == null) {
			ArrayList<Truyen> mdata = DBHelper.GetAllTruyen();
			for (int i = 0; i < mdata.size(); i++) {
				cursor.addRow(new String[] { String.valueOf(i),
						mdata.get(i).get_TenTruyen(),
						mdata.get(i).get_NoiDung() });
			}
			mSuggestionsAdapter = new SuggestionsAdapter(
					getSupportActionBar().getThemedContext(), cursor);

		}
		searchView.setSuggestionsAdapter(mSuggestionsAdapter);
		
		

		menu.add("Search")
				.setIcon(R.drawable.abs__ic_search)
				.setActionView(searchView)
				.setShowAsAction(
						MenuItem.SHOW_AS_ACTION_IF_ROOM
								| MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		

		menu.add("Cài Đặt").setIcon(R.drawable.ic_setting).setOnMenuItemClickListener(SettingsOnclick)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);

		menu.add("Liên Hệ").setIcon(R.drawable.ic_top100).setOnMenuItemClickListener(AboutOnclick)
				.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		
		
		menu.add("Các Ứng Dụng Khác").setIcon(R.drawable.ic_signin).setOnMenuItemClickListener(TacGiaOnClick)
		.setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW);
		return super.onCreateOptionsMenu(menu);
		
		
		
	}
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			startActivityAfterCleanup(MainActivity.class);
			return true;

		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void startActivityAfterCleanup(Class<?> cls) {
		  Intent intent = new Intent(getApplicationContext(), cls);
		  intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		  startActivity(intent);
	}
	
	
	OnMenuItemClickListener SettingsOnclick = new OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			Intent Settings = new Intent(MainActivity.this,SettingActivity.class);
			startActivity(Settings);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			return true;
		}
	};
	
	OnMenuItemClickListener AboutOnclick = new OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			Intent about = new Intent(MainActivity.this,AboutActivity.class);
			startActivity(about);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			return true;
		}
	};
	
	
	OnMenuItemClickListener TacGiaOnClick = new OnMenuItemClickListener() {
		
		@Override
		public boolean onMenuItemClick(MenuItem item) {
			Intent about = new Intent(MainActivity.this,ThongTinActivity.class);
			startActivity(about);
			overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
			return false;
		}
	};
	
	
	


	@Override
	public boolean onQueryTextSubmit(String query) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onQueryTextChange(String newText) {
		if (newText.length() >= 2 && !newText.equals("  ")) {
			MatrixCursor cursor = new MatrixCursor(COLUMNS);
			ArrayList<Truyen> data = DBHelper.GetThoByName(newText);
			for (int i = 0; i < data.size(); i++) {
				cursor.addRow(new String[] { String.valueOf(i),
						data.get(i).get_TenTruyen(),
						data.get(i).get_NoiDung() });
			}
			mSuggestionsAdapter.changeCursor(cursor);
		}
		return true;
	}

	@Override
	public boolean onSuggestionSelect(int position) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean onSuggestionClick(int position) {

		Cursor c = (Cursor) mSuggestionsAdapter.getItem(position);

		Truyen temp = new Truyen();
		temp.set_SoThuTu(-1);
		temp.set_TenTruyen(c.getString(c
				.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1)));
		temp.set_NoiDung(c.getString(c
				.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_2)));
		Intent isearch = new Intent(MainActivity.this,SearchActivity.class);
		searchView.clearFocus();
		isearch.putExtra("SEARCH", temp);
		startActivity(isearch);
		overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
		return true;
	}
	
	
	@Override
	protected void onDestroy() {
		DBHelper.close();
		super.onDestroy();
		System.gc();
	}

	private class SuggestionsAdapter extends CursorAdapter {

		public SuggestionsAdapter(Context context, Cursor c) {
			super(context, c, 0);
		}

		@Override
		public View newView(Context context, Cursor cursor, ViewGroup parent) {
			LayoutInflater inflater = LayoutInflater.from(context);
			View v = inflater.inflate(R.layout.item_search, parent, false);
			return v;
		}

		@Override
		public void bindView(View view, Context context, Cursor cursor) {
			TextView search_title = (TextView) view
					.findViewById(R.id.txt_search);
			final int titleIndex = cursor
					.getColumnIndex(SearchManager.SUGGEST_COLUMN_TEXT_1);
			search_title.setText(cursor.getString(titleIndex));
		}

	}
	
	
		

}
