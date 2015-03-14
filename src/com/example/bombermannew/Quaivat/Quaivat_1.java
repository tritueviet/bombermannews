package com.example.bombermannew.Quaivat;

import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import android.os.SystemClock;

import com.example.bombermannew.Tools.Tools;

public class Quaivat_1 {
	//Biáº¿n táº¡o hiá»‡u á»©ng
	public AnimatedSprite Quaivat_1_AnimatedSprite;
	
	private Scene mScene;
	
	//XÃ¡c Ä‘á»‹nh tá»‘c Ä‘á»™ di chuyá»ƒn cá»§a cÃ¡c quÃ¡i váº­t
	public int speed = 2;
	
	//Khai bÃ¡o Ä‘á»‘i tÆ°á»£ng quÃ¡i váº­t
	private Quaivat quaivat;
	
	//XÃ¡c Ä‘á»‹nh xem quÃ¡i váº­t nÃ y lÃ  loáº¡i nÃ o. Hiá»‡n táº¡i cÃ³ 7 loáº¡i
	private int loai = 0;
	//=============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng cÃ³ tham sá»‘. 
	 * @param quaivat
	 * @param mScene
	 * @param pX
	 * @param pY
	 * @param Quaivat_TiledTextureRegion
	 */
	public Quaivat_1(Quaivat quaivat, Scene mScene,float pX, float pY, TiledTextureRegion Quaivat_TiledTextureRegion){
		this.quaivat = quaivat;
		this.mScene = mScene;	
		loai = Tools.getRandomIndex(0, 6);
		Quaivat_1_AnimatedSprite = new AnimatedSprite(pX, pY, Quaivat_TiledTextureRegion); 
		statusMoveLeft();//Ban Ä‘áº§u thÃ¬ toÃ n bá»™ cÃ¡c quÃ¡i váº­t sáº½ di chuyá»ƒn sang hÆ°á»›ng trÃ¡i
		mScene.attachChild(Quaivat_1_AnimatedSprite);
	}
	//=============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c statusMoveUp
	 */
	public void statusMoveUp(){
		if(loai == 0)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},3,5,true);
		else if(loai == 1)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},6,8,true);
		else if(loai == 2)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},9,11,true);
		else if(loai == 3)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},48,50,true);
		else if(loai == 4)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},51,53,true);
		else if(loai == 5)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},54,56,true);
		else if(loai == 6)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},57,59,true);
	}
	//=============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c statusMoveRight
	 */
	public void statusMoveRight(){
		if(loai == 0)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},15,17,true);
		else if(loai == 1)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},18,20,true);
		else if(loai == 2)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},21,23,true);
		else if(loai == 3)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},60,62,true);
		else if(loai == 4)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},63,65,true);
		else if(loai == 5)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},66,68,true);
		else if(loai == 6)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},69,71,true);
	}
	//=============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c statusMoveDown
	 */
	public void statusMoveDown(){
		if(loai == 0)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},27,29,true);
		else if(loai == 1)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},30,32,true);
		else if(loai == 2)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},33,35,true);
		else if(loai == 3)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},72,74,true);
		else if(loai == 4)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},75,77,true);
		else if(loai == 5)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},78,80,true);
		else if(loai == 6)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},81,83,true);
	}
	//=============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c statusMoveLeft
	 */
	public void statusMoveLeft(){
		if(loai == 0)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},39,41,true);
		else if(loai == 1)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},42,44,true);
		else if(loai == 2)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},45,47,true);
		else if(loai == 3)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},84,86,true);
		else if(loai == 4)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},87,89,true);
		else if(loai == 5)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},90,92,true);
		else if(loai == 6)
			Quaivat_1_AnimatedSprite.animate(new long[]{100,100,100},93,95,true);
	}
	//=============================================================================
	/**
	 * Di chuyá»ƒn quÃ¡i váº­t tá»›i vá»‹ trÃ­ pX, pY náº¿u mÃ  khÃ´ng cÃ³ va cháº¡m vá»›i váº­t cáº£n
	 */
	public void moveXY(float pX, float pY){
		if(!quaivat.collidesWith(pX, pY))
			Quaivat_1_AnimatedSprite.setPosition(pX, pY);
	}
	//=============================================================================
	public void moveRelativeXY(float pX, float pY){
		Quaivat_1_AnimatedSprite.setPosition(Quaivat_1_AnimatedSprite.getX()+pX, Quaivat_1_AnimatedSprite.getY()+pY);
	}
	//=============================================================================
	/**
	 * Di chuyá»ƒn 1 cÃ¡ch ngáº«u nhiÃªn trÃªn mÃ n hÃ¬nh
	 */
	long time = SystemClock.elapsedRealtime();
	int huong = 0;
	public void moveRandom(){
		if(SystemClock.elapsedRealtime() - time > 3000){//Cá»© sau 3s thÃ¬ Ä‘á»‘i tÆ°á»£ng quÃ¡i váº­t tá»± xÃ¡c Ä‘á»‹nh láº¡i Ä‘Æ°á»�ng Ä‘i
			huong = Tools.getRandomIndex(0, 3);
			time = SystemClock.elapsedRealtime();
			if(huong == 0)
				statusMoveLeft();
			else if(huong == 1)
				statusMoveRight();
			else if(huong == 2)
				statusMoveUp();
			else if(huong == 3)
				statusMoveDown();
		}
		
		if(huong == 0){//TrÃ¡i
			if(!quaivat.collidesWith(Quaivat_1_AnimatedSprite.getX() - speed, Quaivat_1_AnimatedSprite.getY()+(Quaivat_1_AnimatedSprite.getHeight()/2)))
				moveRelativeXY(-speed,0);
		}else if(huong == 1){//Pháº£i
			if(!quaivat.collidesWith(Quaivat_1_AnimatedSprite.getX()+ Quaivat_1_AnimatedSprite.getWidth() + speed, Quaivat_1_AnimatedSprite.getY()+(Quaivat_1_AnimatedSprite.getHeight()/2)))
				moveRelativeXY(speed,0);
		}else if(huong == 2){//LÃªn
			if(!quaivat.collidesWith(Quaivat_1_AnimatedSprite.getX() + (Quaivat_1_AnimatedSprite.getWidth()/2), Quaivat_1_AnimatedSprite.getY() - speed))
				moveRelativeXY(0,-speed);
		}else if(huong == 3){//Xuá»‘ng
			if(!quaivat.collidesWith(Quaivat_1_AnimatedSprite.getX() + + (Quaivat_1_AnimatedSprite.getWidth()/2), Quaivat_1_AnimatedSprite.getY() + Quaivat_1_AnimatedSprite.getWidth() + speed))
				moveRelativeXY(0,speed);
		}
	}
	//=============================================================================
	/**
	 * XÃ³a bá»� quÃ¡i váº­t
	 */
	public void deleteQuaivat_1(){
		this.mScene.detachChild(Quaivat_1_AnimatedSprite);
	}
	//============================================================================
	long time_reset_begin = 0;
	public boolean bool_reset = false;
	public void reset(){
		if(bool_reset){
			if(!this.Quaivat_1_AnimatedSprite.isVisible() && time_reset_begin == 0)
				time_reset_begin = SystemClock.elapsedRealtime();
			
			if(time_reset_begin != 0 && SystemClock.elapsedRealtime() - time_reset_begin > 5000){
				//Khá»Ÿi táº¡o láº¡i quÃ¡i váº­t táº¡i vá»‹ trÃ­ ban Ä‘áº§u vÃ  cho hiá»‡n thá»‹
				this.Quaivat_1_AnimatedSprite.setPosition(416,128);
				this.Quaivat_1_AnimatedSprite.setVisible(true);
				time_reset_begin = 0;
				bool_reset = false;
			}
		}
	}
}
