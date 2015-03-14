package com.example.bombermannew.Bom;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.example.bombermannew.InterfaceSprite.InterfaceSprite;

import android.content.Context;


public class Bom implements InterfaceSprite{

	//Vá»‹ trÃ­ cá»§a quáº£ bom
	public float pX = -100, pY = -100;//Máº·c Ä‘á»‹nh ban Ä‘áº§u luÃ´n lÃ  -100 Ä‘á»ƒ bom náº±m ngoÃ i mÃ n hÃ¬nh
	
	//Animation dÃ¹ng Ä‘á»ƒ táº¡o hiá»‡u á»©ng
	public AnimatedSprite Bom_AnimatedSprite;
	
	//Biáº¿n load áº£nh vÃ  lÆ°u áº£nh vÃ o bá»™ nhá»›.
	public TiledTextureRegion Bom_TiledTextureRegion;
	private BitmapTextureAtlas Bom_BitmapTextureAtlas;	
	
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng vá»›i vá»‹ trÃ­ Ä‘Æ°á»£c chuyá»�n vÃ o.
	 */
	
	public Bom(float pX, float pY){
		this.pX = pX;
		this.pY = pY;
	}
	
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadResources
	 */
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Bom/");
		this.Bom_BitmapTextureAtlas = new BitmapTextureAtlas(128, 32, TextureOptions.BILINEAR_PREMULTIPLYALPHA);				
		this.Bom_TiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.Bom_BitmapTextureAtlas, mContext, "bom.png", 0, 0, 4, 1);
		mEngine.getTextureManager().loadTexture(this.Bom_BitmapTextureAtlas);	
		
	}
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadScene
	 */
	@Override
	public void onLoadScene(Scene mScene) {
		Bom_AnimatedSprite = new AnimatedSprite(pX, pY, Bom_TiledTextureRegion);
		Bom_AnimatedSprite.animate(100);
		mScene.attachChild(Bom_AnimatedSprite);
		
	}
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c di chuyá»ƒn Ä‘á»‘i tÆ°á»£ng bom Ä‘áº¿n vá»‹ trÃ­ pX,pY. Ä�áº·t tráº¡ng thÃ¡i cá»§a bom lÃ  hiá»‡n thá»‹
	 */
	public void moveNewXY(float pX, float pY){
		this.pX = pX;
		this.pY = pY;
		Bom_AnimatedSprite.setPosition(pX, pY);
		Bom_AnimatedSprite.setVisible(true);//Ä�áº·t tráº¡ng thÃ¡i hiá»‡n thá»‹
	}

}
