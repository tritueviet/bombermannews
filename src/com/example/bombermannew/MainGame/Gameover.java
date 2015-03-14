package com.example.bombermannew.MainGame;

import com.example.bombermannew.Dialog.DialogExit;
import mobi.vserv.android.ads.AdLoadCallback;
import mobi.vserv.android.ads.AdOrientation;
import mobi.vserv.android.ads.ViewNotEmptyException;
import mobi.vserv.android.ads.VservAd;
import mobi.vserv.android.ads.VservController;
import mobi.vserv.android.ads.VservManager;

import com.example.bombermannew.BomActivity;
import com.example.bombermannew.R;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;

public class Gameover extends Activity{
	private FrameLayout adView;
	private VservAd adObject;
	private Context context;
	private VservController controller;
	private VservManager manager;
	private static final String BILLBOARD_ZONE = "8081c431";
	private static final String BANNER_ZONE = "04f679b7";
	@Override
	protected void onCreate(android.os.Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.gameover);
		
		Button button_gameover_choilai = (Button)findViewById(R.id.button_gameover_choilai);
		Button button_gameover_mainmenu = (Button)findViewById(R.id.button_gameover_mainmenu);
		Button button_gameover_thoat = (Button)findViewById(R.id.button_gameover_thoat);
		
		button_gameover_choilai.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Gameover.this, MainGameActivity.class);
				Gameover.this.startActivity(i);
				Gameover.this.finish();
			}
		});
		
		button_gameover_mainmenu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				Intent i = new Intent(Gameover.this, BomActivity.class);
				Gameover.this.startActivity(i);
				Gameover.this.finish();
			}
		});

		button_gameover_thoat.setOnClickListener(new View.OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				DialogExit dialogexit = new DialogExit(Gameover.this,Gameover.this);
				dialogexit.show();
			}
		});

		//=============================================================
		adView = (FrameLayout) findViewById(R.id.container);
		this.context = Gameover.this;
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
			DialogExit dialogexit = new DialogExit(Gameover.this, Gameover.this);
			dialogexit.show();
		}
		return super.onKeyDown(keyCode, event);
	}
}
