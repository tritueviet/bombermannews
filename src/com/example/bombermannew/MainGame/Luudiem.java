package com.example.bombermannew.MainGame;

import mobi.vserv.android.ads.AdLoadCallback;
import mobi.vserv.android.ads.AdOrientation;
import mobi.vserv.android.ads.ViewNotEmptyException;
import mobi.vserv.android.ads.VservAd;
import mobi.vserv.android.ads.VservController;
import mobi.vserv.android.ads.VservManager;

import com.example.bombermannew.BomActivity;
import com.example.bombermannew.R;
import com.example.bombermannew.Database.Database;
import com.example.bombermannew.Dialog.DialogExit;
import com.example.bombermannew.Dialog.DialogXemDiem;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Luudiem extends Activity implements OnClickListener{
	private FrameLayout adView;
	private VservAd adObject;
	private Context context;
	private VservController controller;
	private VservManager manager;
	private static final String BILLBOARD_ZONE = "8081c431";
	private static final String BANNER_ZONE = "04f679b7";
	
	Button menu, choilai, luu, xem_diem_cao;
	TextView text_diem;
	EditText edit_ten;
	private Database db;
	
	int diem = 0;
	// ------------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.luudiem);
		
		db = new Database(this);
		
		Bundle b = getIntent().getExtras();
		
		if(b != null){
			diem = b.getInt("diem");
		}

		choilai = (Button) findViewById(R.id.choilai);
		choilai.setOnClickListener(this);
		
		luu = (Button) findViewById(R.id.luu);
		luu.setOnClickListener(this);
		
		menu = (Button) findViewById(R.id.menu);
		menu.setOnClickListener(this);
		
		xem_diem_cao = (Button) findViewById(R.id.xem_diem_cao);
		xem_diem_cao.setOnClickListener(this);
		
		
		text_diem = (TextView) findViewById(R.id.diem);
		text_diem.setText(String.valueOf(diem));
		
		edit_ten = (EditText) findViewById(R.id.name);
		
		adView = (FrameLayout) findViewById(R.id.container);
		this.context = Luudiem.this;
		if (null != controller) {
			controller.stopRefresh();
			controller = null;
		}
		if (null != adView) {
			adView.removeAllViews();
		}
		manager = VservManager.getInstance(context);

		manager.getAd(BANNER_ZONE, AdOrientation.PORTRAIT,
				new AdLoadCallback() {

					@Override
					public void onLoadSuccess(VservAd adObj) {
						//Toast.makeText(ChienThang.this,"Success in getting Ad", Toast.LENGTH_SHORT).show();
						adObject = adObj;
						if (null != adView) {
							adView.removeAllViews();
						}
						/****************************** APPLICABLE IF USED RENDER AD FUNCTIONALITY ****************************/
						if (null != controller) {
							controller.stopRefresh();
							controller = null;
						}

						if (null != adObject) {
							try {
								adObject.show(context, adView);
							} catch (ViewNotEmptyException e) {
								//Toast.makeText(context, e.getMessage(),Toast.LENGTH_SHORT).show();e.printStackTrace();
							}
						}
					}

					@Override
					public void onLoadFailure() {
						//Toast.makeText(ChienThang.this,"Failed in getting AD", Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNoFill() {
						//Toast.makeText(MainActivity.this, "No Ad Found",
						//		Toast.LENGTH_SHORT).show();
					}
				});
		
	
		//===========================================
		
	}


@Override
protected void onActivityResult(int requestCode, int resultCode,
	Intent intent) {
super.onActivityResult(requestCode, resultCode, intent);

if (requestCode == VservManager.REQUEST_CODE) {
	if (intent != null) {

		if (intent.hasExtra("showAt")
				&& intent.getStringExtra("showAt").equalsIgnoreCase(
						"end")) {

			VservManager.getInstance(context).release(this);
			super.finish();
		}
	} else {

		super.finish();
	}
}

}

@Override
protected void onStart() {

if (null != controller) {
	controller.resumeRefresh();
}
super.onStart();
}

@Override
protected void onStop() {

if (null != controller) {
	controller.stopRefresh();
}
super.onStop();
}

	// ------------------------------------------------------------------------------
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.choilai: {
				choilai();
				break;
			}
			case R.id.luu: {
				luu();
				break;
			}
			case R.id.menu: {
				Intent i = new Intent(this, BomActivity.class);
				this.startActivity(i);
				this.finish();
				break;
			}
			case R.id.xem_diem_cao: {
				xemdiemcao();
				break;
			}
		}
	}
	// ------------------------------------------------------------------------------
	private void luu() {
		String name = edit_ten.getText().toString();
		int diemchoi = Integer.parseInt(text_diem.getText().toString());
		// Nếu chưa nhập tên thì yêu cầu nhập tên
		if (name.length() == 0 || name == null) {
			Toast.makeText(this, "You need to fill in a name!", Toast.LENGTH_LONG).show();
			return;
		}
		try {
			db.open();
			Cursor c = db.getAllRows();
			if (c.moveToPosition(9)) {
					String id = c.getString(c.getColumnIndex(Database.KEY_ROWID));
					int math = c.getInt(c.getColumnIndex(Database.KEY_DIEM));
						if (math < diemchoi) {
							db.updatePlayer(id, name, diemchoi);
							LinearLayout l = (LinearLayout)findViewById(R.id.linearLayout_diem);
							l.setVisibility(View.GONE);
							luu.setVisibility(View.GONE);
							xem_diem_cao.setVisibility(View.VISIBLE);
							xemdiemcao();
						}

			}
			c.close();
			db.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}
	}

	public void xemdiemcao(){
		DialogXemDiem xemdiem = new DialogXemDiem(this);
		xemdiem.show();
	}
	// ------------------------------------------------------------------------------
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		this.finish();
		super.onPause();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		this.finish();
	};

	// ------------------------------------------------------------------------------
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK){
			DialogExit dialogexit = new DialogExit(this,this);
			dialogexit.show();
		}
		return super.onKeyDown(keyCode, event);
	}

	// ------------------------------------------------------------------------------
	private void choilai() {
		Intent i = new Intent(this, MainGameActivity.class);
		this.startActivity(i);
		this.finish();
	}
}
