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

public class No implements InterfaceSprite{
	
	//Cho biáº¿t Ä‘á»‘i tÆ°á»£ng nÃ y Ä‘Æ°á»£c hiá»‡n thá»‹ hay khÃ´ng Ä‘Æ°á»£c hiá»‡n thá»‹.
	//Náº¿u visiable = true thÃ¬ Ä‘Æ°á»£c hiá»‡n thá»‹. NgÆ°á»£c láº¡i lÃ  áº©n
	public boolean visiable = true;
	
	//2 giÃ¡ trá»‹ lÃ  vá»‹ trÃ­ cá»§a Ä‘á»‘i tÆ°á»£ng ná»•
	public float pX = 0, pY = 0;
	
	//Biáº¿n nÃ y Ä‘á»ƒ táº¡o ra hiá»‡u á»©ng ná»•
	public AnimatedSprite No_AnimatedSprite;
	
	//CÃ¡c biáº¿n dÃ¹ng Ä‘á»ƒ load áº£nh vÃ  lÆ°u áº£nh vÃ o bá»™ nhá»›.
	private TiledTextureRegion No_TiledTextureRegion;
	private BitmapTextureAtlas No_BitmapTextureAtlas;	
	
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng
	 * @param pX : vá»‹ trÃ­ hiá»‡n thá»‹ cá»§a Ä‘á»‘i tÆ°á»£ng Ná»•
	 * @param pY : vá»‹ trÃ­ hiá»‡n thá»‹ cá»§a Ä‘á»‘i tÆ°á»£ng Ná»•
	 */
	public No(float pX, float pY){
		this.pX = pX;
		this.pY = pY;
	}
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadResources
	 */
	public void onLoadResources(Engine mEngine, Context mContext) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Bom/");
		this.No_BitmapTextureAtlas = new BitmapTextureAtlas(256, 256, TextureOptions.BILINEAR_PREMULTIPLYALPHA);				
		this.No_TiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.No_BitmapTextureAtlas, mContext, "no.png", 0, 0, 5, 5);
		mEngine.getTextureManager().loadTexture(this.No_BitmapTextureAtlas);	
		
	}

	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadScene
	 */
	@Override
	public void onLoadScene(Scene mScene) {
		No_AnimatedSprite = new AnimatedSprite(pX, pY, No_TiledTextureRegion);
		No_AnimatedSprite.animate(60);
		mScene.attachChild(No_AnimatedSprite);
		
	}
	//==========================================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c sáº½ di chuyá»ƒn Ä‘á»‘i tÆ°á»£ng ná»• Ä‘áº¿n vá»‹ trÃ­ pX, pY. Khi di chuyá»ƒn Ä‘áº¿n vá»‹ trÃ­ Ä‘Ã³ máº·c Ä‘á»‹nh ban Ä‘áº§u lÃ  tráº¡ng
	 * thÃ¡i áº©n.
	 */
	public void moveNewXY(float pX, float pY){
		this.pX = pX;
		this.pY = pY;
		No_AnimatedSprite.setPosition(pX, pY);
		No_AnimatedSprite.setVisible(false);//Ä�áº·t tráº¡ng thÃ¡i áº©n
	}
}
