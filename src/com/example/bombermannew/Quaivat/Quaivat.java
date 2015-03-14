package com.example.bombermannew.Quaivat;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXLayer;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXProperties;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTile;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTileProperty;
import org.anddev.andengine.entity.layer.tiled.tmx.TMXTiledMap;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.example.bombermannew.InterfaceSprite.InterfaceSprite;
import com.example.bombermannew.Tools.Tools;
import android.content.Context;

public class Quaivat implements InterfaceSprite{

	//XÃ¡c Ä‘á»‹nh má»©c tá»‘i da mÃ  sá»‘ quÃ¡i váº­t cÃ³.
	public int max_quai = 10; 
	
	//Cáº¥p phÃ¡t 1 array chá»©a toÃ n bá»™ cÃ¡c quÃ¡i.
	public Quaivat_1[] quaivat_1;
	
	//CÃ¡c biáº¿n load vÃ  lÆ°u áº£nh
	private TiledTextureRegion[] Quaivat_TiledTextureRegion;
	private BitmapTextureAtlas Quaivat_BitmapTextureAtlas;	
	

	//Maps
	private TMXTiledMap mTMXTiledMap;
	private TMXLayer VatCanTMXLayer;
	
	private Scene mScene;
	
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng khÃ´ng cÃ³ tham sá»‘
	 */
	public Quaivat(int max_quai){
		this.max_quai = max_quai;
		quaivat_1 = new Quaivat_1[this.max_quai];
		Quaivat_TiledTextureRegion = new TiledTextureRegion[this.max_quai];
	}
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadResources
	 */
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		this.Quaivat_BitmapTextureAtlas = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Quaivat/");		
		for(int i=0;i<this.max_quai;i++)
			this.Quaivat_TiledTextureRegion[i] = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.Quaivat_BitmapTextureAtlas, mContext, "quai_vat.png", 0, 0, 12, 8);
		mEngine.getTextureManager().loadTexture(this.Quaivat_BitmapTextureAtlas);			
			
	}
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadScene
	 */
	@Override
	public void onLoadScene(Scene mScene) {
		this.mScene = mScene;
		for(int i=0;i<this.max_quai;i++){	
			quaivat_1[i] = new Quaivat_1(this, mScene,-100,-100,this.Quaivat_TiledTextureRegion[i]);
		}
			
	}
	
	/**
	 * Khá»Ÿi táº¡o vá»‹ trÃ­ ngáº«u nhiÃªn
	 */
	public void reset(){
		for(int i=0;i<this.max_quai;i++){			
			while(true){
				int x = Tools.getRandomIndex(192, 416);
				int y = Tools.getRandomIndex(64, 256);
				if(!this.collidesWith(x, y)){
					quaivat_1[i] = new Quaivat_1(this, this.mScene,x,y,this.Quaivat_TiledTextureRegion[i]);
					break;
					}				
			}
		}
	}
	
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c set TMXTiledMap
	 */
	public void setTMXTiledMap(TMXTiledMap mTMXTiledMap){
		this.mTMXTiledMap = mTMXTiledMap;
	}
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c set TMXLayer
	 */
	public void setTMXLayer(TMXLayer VatCanTMXLayer){
		this.VatCanTMXLayer = VatCanTMXLayer;
	}
	//==================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c kiá»ƒm tra xem táº¡i vá»‹ trÃ­ pX, pY cÃ³ pháº£i thuá»™c vÃ o vÃ¹ng khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn.
	 * Náº¿u thuá»™c vÃ¹ng khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn thÃ¬ return true.
	 * Náº¿u khÃ´ng thuá»™c thÃ¬ return false. (Ä�á»‘i vá»›i cáº£ player vÃ  quÃ¡i váº­t Ä‘á»�u Ä‘Æ°á»£c di chuyá»ƒn náº¿u Ä‘iá»�u return false)
	 */
	public boolean collidesWith(float pX, float pY){
		TMXTile mTMXTile = VatCanTMXLayer.getTMXTileAt(pX,pY);
    	try{
			if(mTMXTile == null){
				System.out.println("mTMXTile = null");
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
	//==================================================================================
	//Khi quÃ¡i váº­t bá»‹ cháº¿t thÃ¬ chá»� trong thá»�i gian lÃ  5s sáº½ Ä‘Æ°á»£c hiá»‡n thá»‹ láº¡i.
	public void reset(Quaivat_1 quai_vat_1){
		quai_vat_1.Quaivat_1_AnimatedSprite.setVisible(true);
		quai_vat_1.Quaivat_1_AnimatedSprite.setPosition(416,128);
	}
}
