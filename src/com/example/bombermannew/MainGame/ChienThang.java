package com.example.bombermannew.MainGame;

import com.example.bombermannew.Database.Database;
import com.example.bombermannew.Dialog.DialogExit;

import com.example.bombermannew.BomActivity;
import com.example.bombermannew.R;

import mobi.vserv.android.ads.AdLoadCallback;
import mobi.vserv.android.ads.AdOrientation;
import mobi.vserv.android.ads.ViewNotEmptyException;
import mobi.vserv.android.ads.VservAd;
import mobi.vserv.android.ads.VservController;
import mobi.vserv.android.ads.VservManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

public class ChienThang extends Activity{
	int DIEM = 0;
	private Database db;
	
	private FrameLayout adView;
	private VservAd adObject;
	private Context context;
	private VservController controller;
	private VservManager manager;
	private static final String BILLBOARD_ZONE = "8081c431";
	private static final String BANNER_ZONE = "04f679b7";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.chienthang);

		Bundle bun = getIntent().getExtras();
		
		if(bun != null){
			DIEM = bun.getInt("diem");
		}
		
		db = new Database(this);
		
		Button b = (Button)findViewById(R.id.button_dongy);
		b.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				if(db.kt_luu(DIEM)){
					Intent i = new Intent(ChienThang.this, Luudiem.class);
					i.putExtra("diem", DIEM);
					ChienThang.this.startActivity(i);
					ChienThang.this.finish();	
				}else {
					Intent i = new Intent(ChienThang.this, BomActivity.class);
					i.putExtra("diem", DIEM);
					ChienThang.this.startActivity(i);
					ChienThang.this.finish();	
				}
			}
		});
		
		//=============================================================
		adView = (FrameLayout) findViewById(R.id.container);
		this.context = ChienThang.this;
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
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_DOWN){
			DialogExit dialogexit = new DialogExit(ChienThang.this, ChienThang.this);
			dialogexit.show();
		}
		return super.onKeyDown(keyCode, event);
	}
	



}
