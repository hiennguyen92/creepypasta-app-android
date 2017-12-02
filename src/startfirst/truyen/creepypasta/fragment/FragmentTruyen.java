package startfirst.truyen.creepypasta.fragment;


import java.io.IOException;

import startfirst.truyen.creepypasta.R;
import startfirst.truyen.creepypasta.dto.Truyen;
import startfirst.truyen.creepypasta.utis.Utis;

import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockFragment;

public class FragmentTruyen extends SherlockFragment{
	private Truyen _mTruyen;
	
	
    public static FragmentTruyen newInstance(Truyen baitho) {
    	FragmentTruyen fragment = new FragmentTruyen();
        fragment._mTruyen = baitho;
        return fragment;
    }
    
    
    public static CharSequence newTitle(Truyen baitho) {
    	String[] data = baitho.get_TenTruyen().split(" ");
    	String tempTitle = data.length  > 3? data[0]+" "+data[1]+" "+data[2]+"...":baitho.get_TenTruyen() ;
        return tempTitle;
    }
    
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_truyen, null);
		SharedPreferences settings = getActivity().getSharedPreferences("UserInfo", getActivity().MODE_PRIVATE);
		TextView title = (TextView)view.findViewById(R.id.txt_title_fragment_tho);
		TextView content = (TextView)view.findViewById(R.id.txt_content_fragment_tho);
	
		
		title.setText(_mTruyen.get_TenTruyen());
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getActivity().getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		
		
		content.setText(getClickableSpan(width));
		
		content.setTextSize(settings.getInt("FONT", 16));
		
		
		content.setMovementMethod(LinkMovementMethod.getInstance());
		
		return view;
	}


	
	
	 private SpannableString getClickableSpan(int container) {
		 
		 
		View.OnClickListener l = new View.OnClickListener() {
			public void onClick(View v) {
				Utis.goToLink(getActivity(), v.getTag().toString());

			}
		};
		  
		  
		  String[] dulieu = _mTruyen.get_NoiDung().split("\n");
		  
		  SpannableString spanableInfo = new SpannableString(_mTruyen.get_NoiDung());

			for (int i = 0; i < dulieu.length; i++) {
				
				String temp = dulieu[i];
				int start = _mTruyen.get_NoiDung().indexOf(temp);
				int end = _mTruyen.get_NoiDung().indexOf(temp)+temp.length();
				
				if (temp.startsWith("<image>")) {
					try {
						
						
						
						Drawable d = Drawable.createFromStream(getActivity().getAssets().open(temp.substring(7, dulieu[i].length()-8)), null);
						
						if (d.getIntrinsicWidth() > container) {
							//man hinh nho
							float tile = d.getIntrinsicWidth()/(container-30);
							d.setBounds(10, 0, container-30, (int)(d.getIntrinsicHeight()/tile));
						}else {
							//man hinh lon
							d.setBounds((container/2)-(d.getIntrinsicWidth()/2), 0, (container/2)+(d.getIntrinsicWidth()/2) , d.getIntrinsicHeight());
						}
						
						
						ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
						
						spanableInfo.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else {
					if (temp.startsWith("http://")) {
						spanableInfo.setSpan(new Clickable(l,dulieu[i]), start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
					}
				}
			}
		  
		  return spanableInfo;
		 }

}



class Clickable extends ClickableSpan implements OnClickListener {
	 private final View.OnClickListener mListener;
	 private String link;
	 public Clickable(View.OnClickListener l,String link) {
	 mListener = l;
	 this.link = link;
	 }
	 @Override
	 public void onClick(View v) {
		 v.setTag(link);
		 mListener.onClick(v);
	 }
	}
