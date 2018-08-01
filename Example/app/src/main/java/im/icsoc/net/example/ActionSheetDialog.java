package im.icsoc.net.example;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class ActionSheetDialog implements OnClickListener  {
	private Context context;
	private Dialog dialog;
	private TextView txt_title;
	private TextView txt_cancel;
	private LinearLayout lLayout_content;
	private ScrollView sLayout_content;
	private boolean showTitle = false;
	private List<SheetItem> sheetItemList;
	private Display display;

	OnSheetItemClickListener mOnSheetItemClickListener;

	public ActionSheetDialog(Context context) {
		this.context = context;
		WindowManager windowManager = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		display = windowManager.getDefaultDisplay();
	}

	public ActionSheetDialog builder() {
		View view = LayoutInflater.from(context).inflate(R.layout.toast_view_actionsheet, null);

		view.setMinimumWidth(display.getWidth());

		sLayout_content = (ScrollView) view.findViewById(R.id.sLayout_content);
		lLayout_content = (LinearLayout) view
				.findViewById(R.id.lLayout_content);
		txt_title = (TextView) view.findViewById(R.id.txt_title);
		txt_cancel = (TextView) view.findViewById(R.id.txt_cancel);
		txt_cancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog = new Dialog(context, R.style.AlertDialogStyle);
		dialog.setContentView(view);
		Window dialogWindow = dialog.getWindow();
		dialogWindow.setGravity(Gravity.LEFT | Gravity.BOTTOM);
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		lp.x = 0;
		lp.y = 0;
		dialogWindow.setAttributes(lp);

		return this;
	}

	public ActionSheetDialog setTitle(String title) {
		showTitle = true;
		txt_title.setVisibility(View.VISIBLE);
		txt_title.setText(title);
		return this;
	}

	public ActionSheetDialog setCancelable(boolean cancel) {
		dialog.setCancelable(cancel);
		return this;
	}

	public ActionSheetDialog setOnSheetItemClickListener(OnSheetItemClickListener itemClickListener) {
 		mOnSheetItemClickListener=itemClickListener;
		return this;
	}

	public ActionSheetDialog setCanceledOnTouchOutside(boolean cancel) {
		dialog.setCanceledOnTouchOutside(cancel);
		return this;
	}

	public ActionSheetDialog addSheetItem(String strItem, int type ) {
		if (sheetItemList == null)
			sheetItemList = new ArrayList<SheetItem>();

		sheetItemList.add(new SheetItem(strItem, type));
		return this;
	}

	public ActionSheetDialog addSheetItems(String... strItems){
		if (sheetItemList == null)
			sheetItemList = new ArrayList<SheetItem>();

		int i=0;
		for (String action : strItems) {
			sheetItemList.add(new SheetItem(action, i));
			i++;
		}
		return this;
	}

	public ActionSheetDialog addSheetItems(List<String> strItems){
		if (sheetItemList == null)
			sheetItemList = new ArrayList<SheetItem>();

		int i=0;
		for (String action : strItems) {
			sheetItemList.add(new SheetItem(action, i));
			i++;
		}
		return this;
	}

	private void setSheetItems() {
		if (sheetItemList == null || sheetItemList.size() <= 0) {
			return;
		}

		int size = sheetItemList.size();
		if (size >= 7) {
			LayoutParams params = (LayoutParams) sLayout_content.getLayoutParams();
			params.height = display.getHeight() / 2;
			sLayout_content.setLayoutParams(params);
		}

		for (int i = 1; i <= size; i++) {
			final int index = i;
			SheetItem sheetItem = sheetItemList.get(i - 1);
			String strItem = sheetItem.name;

			TextView textView = new TextView(context);
			textView.setText(strItem);
			textView.setTextSize(18);
			textView.setTextColor(Color.parseColor("#333333"));
			textView.setGravity(Gravity.CENTER);
			textView.setTag(i-1);

			if (size == 1) {
 				textView.setBackgroundResource(R.drawable.dialog_singel_bg);

			} else {

				if (i == 1) {
					textView.setBackgroundResource(R.drawable.dialog_top_bg);
				}  else if (i < size) {
 					textView.setBackgroundResource(R.drawable.sheet_line_bg);
				} else {

					textView.setBackgroundResource(R.drawable.dialog_bottom_bg);

				}

			}

			float scale = context.getResources().getDisplayMetrics().density;
			int height = (int) (45 * scale + 0.5f);
			textView.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, height));

		/*	textView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					listener.onClick(index);
					dialog.dismiss();
				}
			});*/

			textView.setOnClickListener(this);
			lLayout_content.addView(textView);
		}
	}

	public void show() {
		setSheetItems();
		dialog.show();
	}

	@Override
	public void onClick(View v){

		if(v.getTag()==null) return;
		int pos= Integer.parseInt(v.getTag().toString());
	    if(null!=mOnSheetItemClickListener)
			mOnSheetItemClickListener.onClick(pos,v);
		dialog.dismiss();
	}

	public interface OnSheetItemClickListener {
		void onClick(int which, View V);
	}

	public class SheetItem {
		public String name;
		public int type;

		public SheetItem(String name, int type) {
			this.name = name;
			this.type = type;
		}
	}

}
