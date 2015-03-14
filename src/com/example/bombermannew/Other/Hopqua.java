package com.example.bombermannew.Other;

import java.io.IOException;

import org.anddev.andengine.audio.sound.Sound;
import org.anddev.andengine.audio.sound.SoundFactory;
import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.Sprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TextureRegion;
import org.anddev.andengine.util.Debug;

import com.example.bombermannew.ClassStatic.ControlerStatic;
import com.example.bombermannew.InterfaceSprite.InterfaceSprite;
import com.example.bombermannew.Player.Player;
import com.example.bombermannew.Tools.Tools;
import android.content.Context;
import android.os.SystemClock;

public class Hopqua implements InterfaceSprite{

	 private BitmapTextureAtlas HopquaBitmapTextureAtlas;
	 private TextureRegion HopquaTextureRegion;
	 private Sprite HopquaSprite;
	 
	 private Sound sound_thuong;
	 
	 public Hopqua(){}
	
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Image/");
        this.HopquaBitmapTextureAtlas = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
        this.HopquaTextureRegion = BitmapTextureAtlasTextureRegionFactory.createFromAsset(this.HopquaBitmapTextureAtlas, mContext, "hopqua.png", 0, 0);
        mEngine.getTextureManager().loadTextures(this.HopquaBitmapTextureAtlas);
        
        SoundFactory.setAssetBasePath("mfx/");
        try {
        	sound_thuong = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), mContext, "thuong.wav");
        } catch (final IOException e) {
                Debug.e(e);
        }
		
	}

	@Override
	public void onLoadScene(Scene mScene) {		
		this.HopquaSprite = new Sprite(-100, -100, this.HopquaTextureRegion);
		mScene.attachChild(HopquaSprite);
		mScene.registerTouchArea(HopquaSprite);
	}

	long time_random = 0, time_start = 0;
	int loai = -1;//Cho biáº¿t há»™p quÃ  lÃ  gÃ¬:
	long time_start_ht = 0;//XÃ¡c Ä‘á»‹nh thá»�i gian sáº½ hiá»‡n thá»‹ há»™p quÃ 
	long time_end_ht = 0;//Sau bao nhiÃªu s thÃ¬ áº©n há»™p quÃ 
	// 1: ThÃªm 1 máº¡ng
	// 2: ThÃªm 1 quáº£ bom
	// 3: TÄƒng thÃªm 1 cáº¥p bom ná»¯a
	public void hienThi(TMXTiledMap mTMXTiledMap, TMXLayer VatCanTMXLayer){
		if(time_random == 0){//Láº§n Ä‘áº§u tiÃªn gá»�i hÃ m
			//XÃ¡c Ä‘á»‹nh thá»�i gian chá»� Ä‘á»ƒ xuáº¥t hiá»‡n
			time_random = Tools.getRandomIndex(5000, 20000);//Trong khoáº£ng 5-20s sáº½ hiá»‡n thá»‹ 1 láº§n
			loai = Tools.getRandomIndex(1, 3);
			time_start = SystemClock.elapsedRealtime();
		}else{
			if(SystemClock.elapsedRealtime() - time_start > time_random && time_start != 0){				
				//Hiá»‡n thá»‹ há»™p quÃ 
				//TÃ¬m vá»‹ trÃ­
				while(true && time_start_ht == 0){
					int x = Tools.getRandomIndex(192, 416);
					int y = Tools.getRandomIndex(64, 256);
					
					if(!collidesWith(mTMXTiledMap,VatCanTMXLayer,x+HopquaSprite.getWidth()/2,y+HopquaSprite.getHeight()/2)){
						//Ä�Æ°á»£c phÃ©p hiá»‡n thá»‹. 
						//Di chuyá»ƒn há»™p quÃ  tá»›i vá»‹ trÃ­
						HopquaSprite.setPosition(x,y);			
						
						//XÃ¡c Ä‘á»‹nh thá»�i gian hiá»‡n thá»‹ cá»§a há»™p quÃ 
						time_start_ht = SystemClock.elapsedRealtime();
						time_end_ht = Tools.getRandomIndex(3000, 10000);//Thá»�i gian hiá»‡n thá»‹ náº±m trong khoáº£ng tá»« 3s-10s
						break;
					}
				}
				HopquaSprite.setRotation(10);
				//Báº¯t Ä‘áº§u tÃ­nh thá»�i gian hiá»‡n thá»‹
				if(time_start_ht != 0 && SystemClock.elapsedRealtime() - time_start_ht > time_end_ht){
					//Háº¿t thá»�i gian hiá»‡n thá»‹ cá»§a há»™p quÃ , ta di chuyá»ƒn há»™p quÃ  Ä‘áº¿n vá»‹ trÃ­ ngoÃ i mÃ n hÃ¬nh
					HopquaSprite.setPosition(-100,-100);
					
					//Tiáº¿p Ä‘Ã³ lÃ  ta xÃ¡c Ä‘á»‹nh láº¡i thá»�i gian chá»� cho há»™p quÃ 
					time_random = Tools.getRandomIndex(5000, 20000);//Trong khoáº£ng 5-20s sáº½ hiá»‡n thá»‹ 1 láº§n
					loai = Tools.getRandomIndex(1, 3);
					time_start = SystemClock.elapsedRealtime();
					
					time_start_ht = 0;
					time_end_ht = 0;
				}
			}
		}
	}
	
	//PhÆ°Æ¡ng thá»©c kiá»ƒm tra xem player cÃ³ va cháº¡m vá»›i há»™p quÃ  trong thá»�i gian há»™p quÃ  cÃ²n xuáº¥t hiá»‡n trÃªn mÃ n hÃ¬nh 
	//hay khÃ´ng. Náº¿u cÃ³ va cháº¡m thÃ¬ tÃ¡c xÃ¡c Ä‘á»‹nh xem há»™p quÃ  lÃ  loáº¡i gÃ¬ vÃ  tÄƒng cÃ¡c giÃ¡ trá»‹ Ä‘Ã³ cho player
	
	public void collidesWith(Player player){
		if(HopquaSprite.getX() > 0 && player.getAnimatedSprite().collidesWith(HopquaSprite)){
			if(ControlerStatic.SOUND)
				sound_thuong.play();
			
			//CÃ³ xá»± va cháº¡m giá»¯a há»™p quÃ  vÃ  player
			if(this.loai == 1){//ThÃªm 1 máº¡ng
				//Giá»›i háº¡n sá»‘ máº¡ng lÃ  nhá»� hÆ¡n 11
				if(player.getHeart() < 10)
					player.setHeart(player.getHeart()+1);
				System.out.println("TÄƒng thÃªm máº¡ng");
			}else if(loai == 2){//ThÃªm 1 quáº£ bom
				//KhÃ´ng thÃªm vÆ°á»£t quÃ¡ 10 quáº£ bom
				if(player.current_quabom < 10)
					player.setCurrent_quabom(player.getCurrent_quabom() + 1);
				System.out.println("ThÃªm bom");
			}else if(loai ==3 ){//TÄƒng 1 cáº¥p bom
				//KhÃ´ng tÄƒng vÆ°á»£t quÃ¡ cáº¥p 5
				if(player.getCurrent_cap_quabom() < 5)
					player.setCurrent_cap_quabom(player.getCurrent_cap_quabom() + 1);
				System.out.println("TÄƒng cáº¥p bom");
			}
			
			HopquaSprite.setPosition(-100,-100);
			
			//Tiáº¿p Ä‘Ã³ lÃ  ta xÃ¡c Ä‘á»‹nh láº¡i thá»�i gian chá»� cho há»™p quÃ 
			time_random = Tools.getRandomIndex(5000, 20000);//Trong khoáº£ng 5-20s sáº½ hiá»‡n thá»‹ 1 láº§n
			loai = Tools.getRandomIndex(1, 3);
			time_start = SystemClock.elapsedRealtime();
		}
	}
	public int getLoai(){
		return loai;
	}
	
	
	public boolean collidesWith(TMXTiledMap mTMXTiledMap, TMXLayer VatCanTMXLayer, float pX, float pY){
		TMXTile mTMXTile = VatCanTMXLayer.getTMXTileAt(pX,pY);
    	try{
			if(mTMXTile == null){
			}
			else{
				TMXProperties<TMXTileProperty> mTMXProperties= mTMXTile.getTMXTileProperties(mTMXTiledMap);
				TMXTileProperty mTMXTileProperty = mTMXProperties.get(0);
				if(mTMXTileProperty.getName().equals("vatcan")){
				}
			}
			
			return true;
		}catch(Exception e){
			return false;
		}
	}
}
