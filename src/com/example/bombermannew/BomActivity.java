package com.example.bombermannew;

import mobi.vserv.android.ads.AdLoadCallback;
import mobi.vserv.android.ads.AdOrientation;
import mobi.vserv.android.ads.ViewNotEmptyException;
import mobi.vserv.android.ads.VservAd;
import mobi.vserv.android.ads.VservController;
import mobi.vserv.android.ads.VservManager;
import com.example.bombermannew.R;
import com.example.bombermannew.ClassStatic.ControlerStatic;
import com.example.bombermannew.Database.Database;
import com.example.bombermannew.Dialog.DialogExit;
import com.example.bombermannew.Dialog.DialogInfor;
import com.example.bombermannew.MainGame.Huongdan;
import com.example.bombermannew.MainGame.MainGameActivity;
import com.example.bombermannew.Sound.Sound;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class BomActivity extends Activity {
	Animation animation_xoay;
	Animation animation_alpha;
	int index = 0;

	// Biáº¿n Ä‘á»ƒ lÃ m viá»‡c vá»›i csdl
	public Database db;

	
	private int kiemTraMang = 0;

	private FrameLayout adView;

	private VservAd adObject;
	private Context context;
	private VservController controller;
	private VservManager manager;
	private static final String BILLBOARD_ZONE = "8081c431";
	private static final String BANNER_ZONE = "04f679b7";

	public void loadAd() {
		// ================================================================

		this.context = BomActivity.this;
		adView = (FrameLayout) findViewById(R.id.container);

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
						kiemTraMang = 1;
						Toast.makeText(BomActivity.this, "Network on.",
								Toast.LENGTH_SHORT).show();
						adObject = adObj;
						if (null != adView) {
							adView.removeAllViews();
						}

						if (null != controller) {
							controller.stopRefresh();
							controller = null;
						}

						if (null != adObject) {
							try {
								adObject.show(context, adView);
							} catch (ViewNotEmptyException e) {
								// Toast.makeText(context,
								// e.getMessage(),Toast.LENGTH_SHORT).show();e.printStackTrace();
							}
						}
					}

					@Override
					public void onLoadFailure() {

						Toast.makeText(BomActivity.this,
								"Network off, turn on network now!!",
								Toast.LENGTH_SHORT).show();
					}

					@Override
					public void onNoFill() {
						kiemTraMang = 1;
						// Toast.makeText(BomActivity.this,
						// "",Toast.LENGTH_SHORT).show();
					}
				});
		// ================================================================

	}

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
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		// set full man hinh va khong co title
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.main);

		animation_xoay = AnimationUtils.loadAnimation(this, R.anim.anim_xoay);
		animation_alpha = AnimationUtils.loadAnimation(this, R.anim.anim_alpha);


		csdlMau();
		
		//TelephonyManager mgr = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
		//if(mgr != null) {
		 //   mgr.listen(phoneStateListener, PhoneStateListener.LISTEN_CALL_STATE);
		//}	
		
		
		final ImageButton ImageButton_play = (ImageButton) findViewById(R.id.imageButton_play);
		ImageButton_play.startAnimation(animation_xoay);
		ImageButton_play.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				if (kiemTraMang == 1) {
					Intent i = new Intent(BomActivity.this, Huongdan.class);
					BomActivity.this.startActivity(i);
					BomActivity.this.finish();
				} else {
					Toast.makeText(
							BomActivity.this,
							"Please turn on Network to play and wait loading Advertisement!!",
							Toast.LENGTH_SHORT).show();
				}
			}
		});

		final ImageButton ImageButton_infor = (ImageButton) findViewById(R.id.imageButton_infor);
		ImageButton_infor.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogInfor dialoginfor = new DialogInfor(BomActivity.this);
				dialoginfor.show();
			}
		});

		final ImageButton ImageButton_exit = (ImageButton) findViewById(R.id.imageButton_exit);
		ImageButton_exit.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View arg0) {
				DialogExit dialogexit = new DialogExit(BomActivity.this,
						BomActivity.this);
				dialogexit.show();
			}
		});

		

		final Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
//				if (nhac_nen.complet && ControlerStatic.SOUND) {
//					nhac_nen.complet = false;
//					nhac_nen.replay();
//					System.out.println("replay");
//				}
				switch (index) {
				case 0:
					ImageButton_exit.clearAnimation();
					ImageButton_infor.clearAnimation();
					ImageButton_play.startAnimation(animation_xoay);
					break;
				case 1:
					ImageButton_play.clearAnimation();
					ImageButton_infor.clearAnimation();
					ImageButton_exit.startAnimation(animation_xoay);
					break;
				case 2:
					ImageButton_exit.clearAnimation();
					ImageButton_play.clearAnimation();
					ImageButton_infor.startAnimation(animation_xoay);
					break;
				default:
					break;
				}
				super.handleMessage(msg);
			}
		};

		Thread th = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(8000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					index++;
					if (index > 2)
						index = 0;
					Message msg = handler.obtainMessage();
					handler.sendMessage(msg);
				}
			}
		});
		th.start();
		loadAd();
	}

	@Override
	protected void onDestroy() {
		
		super.onDestroy();
	}

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK
				&& event.getAction() == KeyEvent.ACTION_DOWN) {
			DialogExit dialogexit = new DialogExit(BomActivity.this,
					BomActivity.this);
			dialogexit.show();
		}
		return super.onKeyDown(keyCode, event);
	}

	// --------------------------------------------------------------------------
	// Náº¿u lÃ  láº§n chÆ¡i Ä‘áº§u tiÃªn thÃ¬ ta táº¡o ra 1 csdl giáº£
	public void csdlMau() {
		// Láº§n Ä‘áº§u khi báº¯t Ä‘áº§u chÆ¡i
		// Giáº£ láº­p dá»¯ liá»‡u máº«u
		db = new Database(this);
		// Dá»¯ liá»‡u máº«u gá»“m 10 ngÆ°á»�i
		try {
			db.open();
			Cursor c = db.getAllRows();
			if (!c.moveToFirst()) {
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
				db.isertRow("Player", 100);
			}
			c.close();
			db.close();
		} catch (SQLiteException e) {
			e.printStackTrace();
		}

	}
}