package startfirst.truyen.creepypasta;

import java.io.IOException;

import startfirst.truyen.creepypasta.dto.Truyen;
import startfirst.truyen.creepypasta.utis.Utis;
import android.os.Bundle;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ImageSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class SearchActivity extends Activity {

	Truyen data;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_truyen);

		SharedPreferences settings = getSharedPreferences("UserInfo",
				MODE_PRIVATE);
		int BG = settings.getInt("BG", 1);

		data = (Truyen) getIntent().getExtras().getSerializable("SEARCH");

		setTitle(data.get_TenTruyen());

		TextView title = (TextView) findViewById(R.id.txt_title_fragment_tho);
		TextView content = (TextView) findViewById(R.id.txt_content_fragment_tho);

		RelativeLayout ll = (RelativeLayout) findViewById(R.id.RelativeLayout_Frag_Truyen);
		ll.setBackgroundResource(Utis.RandomBackground(BG));

		title.setText(data.get_TenTruyen());
		
		
		
		DisplayMetrics displaymetrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
		int width = displaymetrics.widthPixels;
		
		
		content.setText( getClickableSpan(width));
		
		content.setMovementMethod(LinkMovementMethod.getInstance());
	}

	private SpannableString getClickableSpan(int size) {

		View.OnClickListener l = new View.OnClickListener() {
			public void onClick(View v) {
				Utis.goToLink(SearchActivity.this, v.getTag().toString());
			}
		};

		String[] dulieu = data.get_NoiDung().split("\n");

		SpannableString spanableInfo = new SpannableString(data.get_NoiDung());

		for (int i = 0; i < dulieu.length; i++) {

			String temp = dulieu[i];
			int start = data.get_NoiDung().indexOf(temp);
			int end = data.get_NoiDung().indexOf(temp) + temp.length();

			if (temp.startsWith("<image>")) {
				try {

					Drawable d = Drawable.createFromStream(
							getAssets().open(
									temp.substring(7, dulieu[i].length() - 8)),
							null);

					if (d.getIntrinsicWidth() > size) {
						// man hinh nho

						float tile = d.getIntrinsicWidth()
								/ (size - 30);
						d.setBounds(10, 0, size - 30,
								(int) (d.getIntrinsicHeight() / tile));
					} else {
						// man hinh lon
						d.setBounds(
								(size / 2)
										- (d.getIntrinsicWidth() / 2),
								0,
								(size / 2)
										+ (d.getIntrinsicWidth() / 2),
								d.getIntrinsicHeight());
					}

					ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);

					spanableInfo.setSpan(span, start, end,
							Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);

				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				if (temp.startsWith("http://")) {
					spanableInfo.setSpan(new Clickable(l, dulieu[i]), start,
							end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
				}
			}
		}

		return spanableInfo;
	}

}

class Clickable extends ClickableSpan implements OnClickListener {
	private final View.OnClickListener mListener;
	private String link;

	public Clickable(View.OnClickListener l, String link) {
		mListener = l;
		this.link = link;
	}

	@Override
	public void onClick(View v) {
		v.setTag(link);
		mListener.onClick(v);
	}
}
