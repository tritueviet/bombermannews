package com.example.bombermannew.Bom;

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
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.util.Debug;

import com.example.bombermannew.ClassStatic.ControlerStatic;
import com.example.bombermannew.InterfaceSprite.InterfaceSprite;

import android.content.Context;
import android.os.SystemClock;

public class QuaBom implements InterfaceSprite{
	
	//Ban Ä‘áº§u toÃ n bá»™ cÃ¡c quáº£ bom Ä‘áº·t táº¡i vá»‹ trÃ­ -100,-100. NgoÃ i mÃ n hÃ¬nh
	public float pX = -100, pY = -100;
	
	//Ä�á»‘i tÆ°á»£ng bom
	private Bom bom;
	
	//Cho biáº¿t cáº¥p Ä‘á»™ cá»§a bom lÃ  cáº¥p Ä‘á»™ nÃ o
	public int cap = 1;
	
	//Array Ä‘á»‘i tÆ°á»£ng Ná»• Ä‘á»ƒ táº¡o ra vá»¥ ná»• khi quáº£ bom Ä‘Æ°á»£c Ä‘áº·t xuá»‘ng
	public No[] no;
	
	//Thá»�i gian xÃ¡c Ä‘á»‹nh báº¯t Ä‘áº§u Ä‘áº·t bom xuá»‘ng
	public long time  = 0;
	
	long time_no = 0;//Thá»�i gian xÃ¡c Ä‘á»‹nh lÃºc ná»• xong
	
	//Khi vá»¥ ná»• káº¿t thÃºc thÃ¬ no_end = true. Ban Ä‘áº§u lÃ  flase vÃ¬ bom chÆ°a ná»•
	public boolean no_end = false;
	
	//Biáº¿n cho biáº¿t khi nÃ o thÃ¬ bom Ä‘Æ°á»£c kÃ­ch hoáº¡t ná»•. Khi bom Ä‘Æ°á»£c kÃ­ch hoáº¡t ná»• thÃ¬ begin_no = true
	public boolean begin_no = false;
	
	//2 biáº¿n dÃ¹ng Ä‘á»ƒ xÃ©t va cháº¡m vá»›i maps, xÃ¡c Ä‘á»‹nh vÃ¹ng Ä‘Æ°á»£c di chuyá»ƒn vÃ  khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn
	private TMXTiledMap mTMXTiledMap;
	private TMXLayer VatCanTMXLayer;
	
	private Sound sound_no;
	private boolean bool_sound_no = false;
	
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng
	 */
	public QuaBom(){}
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c set TMXTiledMap
	 * @param mTMXTiledMap
	 */
	public void setTMXTiledMap(TMXTiledMap mTMXTiledMap){
		this.mTMXTiledMap = mTMXTiledMap;
	}
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c set TMXLayer
	 */
	public void setTMXLayer(TMXLayer VatCanTMXLayer){
		this.VatCanTMXLayer = VatCanTMXLayer;
	}
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c kiá»ƒm tra xem táº¡i vá»‹ trÃ­ pX, pY cÃ³ pháº£i thuá»™c vÃ o vÃ¹ng khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn.
	 * Náº¿u thuá»™c vÃ¹ng khÃ´ng Ä‘Æ°á»£c di chuyá»ƒn thÃ¬ return true.
	 * Náº¿u khÃ´ng thuá»™c thÃ¬ return false. (Ä�á»‘i vá»›i cáº£ player vÃ  quÃ¡i váº­t Ä‘á»�u Ä‘Æ°á»£c di chuyá»ƒn náº¿u Ä‘iá»�u return false)
	 */
	public boolean collidesWith(float pX, float pY){
		TMXTile mTMXTile = VatCanTMXLayer.getTMXTileAt(pX,pY);
    	try{
			if(mTMXTile == null){
				System.out.println("mTMXTile = null");//Ä�i ra ngoÃ i báº£n Ä‘á»“
			}
			else{
				TMXProperties<TMXTileProperty> mTMXProperties= mTMXTile.getTMXTileProperties(mTMXTiledMap);
				TMXTileProperty mTMXTileProperty = mTMXProperties.get(0);
				if(mTMXTileProperty.getName().equals("vatcan")){
				}
			}			
			return true;//CÃ³ va cháº¡m
		}catch(Exception e){
			return false;//KhÃ´ng va cháº¡m
		}
	}
	//============================================================================
	/**
	 * Khá»Ÿi táº¡o láº¡i cÃ¡c biáº¿n vá»›i giÃ¡ trá»‹ máº·c Ä‘á»‹nh ban Ä‘áº§u
	 */
	public void init(){
		no_end = false;
		time = 0;
		time_no = 0;
		begin_no = false;
	}
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadResources.
	 * Load áº£nh vÃ  dáº·t táº¡i vá»‹ trÃ­ máº·c Ä‘á»‹nh
	 */
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		no = new No[(cap*4)+1];//Má»—i cáº¥p cÃ³ 4 Ä‘á»‘i tÆ°á»£ng ná»•, 1 Ä‘á»‘i tÆ°á»£ng ná»• sáº½ náº±m táº¡i vá»‹ trÃ­ Ä‘áº·t bom
		//Load Bom
		bom = new Bom(pX,pY);//Ä�áº·t vá»‹ trÃ­ ban Ä‘áº§u cho bom
		bom.onLoadResources(mEngine, mContext);
		
		int j=0;//Cho biáº¿t Ä‘á»‘i tÆ°á»£ng ná»• sáº½ náº±m á»Ÿ vá»‹ trÃ­ nÃ o so vá»›i bom
		int lop = 1;
		
		for(int i=0;i<no.length-1;i++){
			if(j == 0){//Pháº£i
				no[i] = new No(((bom.Bom_TiledTextureRegion.getWidth()/4)*lop + pX),pY);
				no[i].onLoadResources(mEngine, mContext);
			}else if(j == 1){//TrÃ¡i
				no[i] = new No((pX - (bom.Bom_TiledTextureRegion.getWidth()/4)*lop),pY);
				no[i].onLoadResources(mEngine, mContext);
			}
			else if(j == 2){//TrÃªn
				no[i] = new No(pX,pY - (bom.Bom_TiledTextureRegion.getHeight()*lop));
				no[i].onLoadResources(mEngine, mContext);			
			}
			else if(j == 3){
				no[i] = new No(pX,pY + (bom.Bom_TiledTextureRegion.getHeight()*lop));
				no[i].onLoadResources(mEngine, mContext);
				lop++;
			}
			j++;
			if(j >= 4)
				j=0;
		}
		//Ä�á»‘i tÆ°á»£ng cuá»‘i cÃ¹ng sáº½ Ä‘áº·t táº¡i vá»‹ trÃ­ cá»§a quáº£ bom
		no[no.length-1] = new No(pX,pY);
		no[no.length-1].onLoadResources(mEngine, mContext);
		
		SoundFactory.setAssetBasePath("mfx/");
        try {
                sound_no = SoundFactory.createSoundFromAsset(mEngine.getSoundManager(), mContext, "no1.wav");
        } catch (final IOException e) {
                Debug.e(e);
        }
	}
	//============================================================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadScene
	 */
	@Override
	public void onLoadScene(Scene mScene) {
		//Ban Ä‘áº§u thÃ¬ toáº¡n bá»™ á»Ÿ tráº¡ng thÃ¡i áº©n
		//Bom
		bom.onLoadScene(mScene);
		bom.Bom_AnimatedSprite.setVisible(false);
		//No
		for(int i=0;i<no.length;i++){			
			no[i].onLoadScene(mScene);
			no[i].No_AnimatedSprite.setVisible(false);
		}
	}
	//============================================================================
	/**
	 * Khi 1 quáº£ bom Ä‘Æ°á»£c Ä‘áº·t xuá»‘ng thÃ¬ nÃ³ sáº½ chá»� trong 3s sau Ä‘Ã³ nÃ³ phÃ¡t ná»• trong 1s.
	 * Khi nÃ³ báº¯t Ä‘áº§u chá»� thÃ¬ time != 0, khi nÃ³ báº¯t Ä‘áº§u ná»• thÃ¬ time_no != 0. Khi ná»• káº¿t thÃºc thÃ¬ 
	 * no_end = true.Khi má»›i báº¯t Ä‘áº§u ná»• thÃ¬ begin_no = true.
	 */
	public void delayNo(){
		if(no_end)//Khi bom Ä‘Ã£ ná»• rá»“i thÃ¬ khÃ´ng gá»�i ná»¯a
			return;
		//Khi mÃ  bom Ä‘Æ°á»£c Ä‘áº·t xuá»‘ng thÃ¬ ta báº¯t Ä‘áº§u tÃ­nh thá»�i gian
		if(time == 0)
			time = SystemClock.elapsedRealtime();
		
		if(SystemClock.elapsedRealtime() - time > 3000){//Chá»� Ä‘á»§ 3s thÃ¬ ta cho phÃ©p ná»•
			//Ä�á»‘i vá»›i cÃ¡c Ä‘á»‘i tÆ°á»£ng khÃ´ng Ä‘Æ°á»£c ná»• thÃ¬ khÃ´ng Ä‘Æ°á»£c hiá»‡n thá»‹
			for(int i=0;i<no.length-1;i++){				
				if(i>=cap*4)
					no[i].No_AnimatedSprite.setVisible(false);
				else {
					if(no[i].visiable)
						no[i].No_AnimatedSprite.setVisible(true);
					else no[i].No_AnimatedSprite.setVisible(false);
				}
			}
			no[no.length-1].No_AnimatedSprite.setVisible(true);
			
			//Báº¯t Ä‘áº§u ná»•.
			begin_no = true;
			if(!bool_sound_no && ControlerStatic.SOUND){
				sound_no.play();
				bool_sound_no = true;
			}
			//Báº¯t Ä‘áº§u tÃ­nh thá»�i gian ná»•
			if(time_no == 0)
				time_no = SystemClock.elapsedRealtime();
			if(SystemClock.elapsedRealtime() - time_no > 1000){//Cho phÃ©p ná»• trong 1s
				//Háº¿t 1s ta cho áº©n toÃ n bá»™ vÃ  cho di chuyá»ƒn Ä‘áº¿n vá»‹ trá»‹ -100,-100
				for(int i=0;i<no.length;i++){
					no[i].No_AnimatedSprite.setVisible(false);
					no[i].moveNewXY(-100, -100);
				}
				bom.Bom_AnimatedSprite.setPosition(-100, -100);
				bom.Bom_AnimatedSprite.setVisible(false);
				init();
				no_end = true;
			}
		}
	}

	//============================================================================
	/**
	 * Di chuyá»ƒn toÃ n bá»™ Ä‘áº¿n vá»‹ trÃ­ X,Y má»›i
	 * 
	 */
	public void moveNewXY(float pX, float pY){
		init();//khá»Ÿi táº¡o láº¡i cÃ¡c biáº¿n máº¡c Ä‘á»‹nh
		
		this.pX = pX;
		this.pY = pY;
		
		bom.moveNewXY(pX, pY);//Khi gá»�i move thÃ¬ bom sáº½ Ä‘Æ°á»£c hiá»‡n thá»‹
		
		int j=0;
		int lop = 1;
		
		boolean left = true, right = true, up = true, down = true;
		
		float x = 0, y = 0;
		for(int i=0;i<no.length-1;i++){
			if(j == 0){//Pháº£i					
				no[i].moveNewXY(((bom.Bom_TiledTextureRegion.getWidth()/4)*lop + pX), pY);
				if(!right){
					no[i].visiable = false;//KhÃ´ng cho hiá»‡n thá»‹ cÃ¡c quáº£ bom mÃ  náº±m trÃªn váº­t cáº£n
				}		
				else{
					x = (bom.Bom_TiledTextureRegion.getWidth()/4)*lop + pX + (bom.Bom_TiledTextureRegion.getWidth()/4)/2;
					y = (bom.Bom_TiledTextureRegion.getHeight()/2) + pY;
					if(this.collidesWith(x,y)){
						//Khi gá»�i move thÃ¬ toáº¡n bá»™ á»Ÿ tráº¡ng thÃ¡i áº©n					
						no[i].visiable = false;
						right = false;
					}else no[i].visiable = true;
				}				
							
			}else if(j == 1){//TrÃ¡i					
				no[i].moveNewXY((pX - (bom.Bom_TiledTextureRegion.getWidth()/4)*lop), pY);
				if(!left){
					no[i].visiable = false;
				}		
				else{
					x = (pX - (bom.Bom_TiledTextureRegion.getWidth()/4)*lop) -  (bom.Bom_TiledTextureRegion.getWidth()/4)/2;
					y = pY + (bom.Bom_TiledTextureRegion.getHeight()/2);
					if(this.collidesWith(x,y)){
						//Khi gá»�i move thÃ¬ toáº¡n bá»™ á»Ÿ tráº¡ng thÃ¡i áº©n					
						no[i].visiable = false;
						left = false;
					}else no[i].visiable = true;
				}
				
			}
			else if(j == 2){//TrÃªn				
				no[i].moveNewXY(pX, pY - (bom.Bom_TiledTextureRegion.getHeight()*lop));
				if(!up){
					no[i].visiable = false;
				}		
				else{
					x = (bom.Bom_TiledTextureRegion.getWidth()/4)/2 + pX;
					y = pY - (bom.Bom_TiledTextureRegion.getHeight()*lop) - (bom.Bom_TiledTextureRegion.getHeight()/2);
					if(this.collidesWith(x,y)){
						//Khi gá»�i move thÃ¬ toáº¡n bá»™ á»Ÿ tráº¡ng thÃ¡i áº©n					
						no[i].visiable = false;
						up = false;
					}else no[i].visiable = true;
				}
				
			}
			else if(j == 3){				
				no[i].moveNewXY(pX, pY + (bom.Bom_TiledTextureRegion.getHeight()*lop));
				if(!down){
					no[i].visiable = false;
				}		
				else{
					x = (bom.Bom_TiledTextureRegion.getWidth()/4)/2 + pX;
					y = pY + (bom.Bom_TiledTextureRegion.getHeight()*lop) + (bom.Bom_TiledTextureRegion.getHeight()/2);
					if(this.collidesWith(x,y)){
						//Khi gá»�i move thÃ¬ toáº¡n bá»™ á»Ÿ tráº¡ng thÃ¡i áº©n					
						no[i].visiable = false;
						down = false;
					}else no[i].visiable = true;
				}
				
				lop++;
			}
			j++;
			if(j >= 4)
				j=0;
		}
		no[no.length-1].moveNewXY(pX, pY);
		
		bool_sound_no = false;
	}
	//============================================================================
	/**
	 * kiá»ƒm tra xem 1 animated cÃ³ va cháº¡m vá»›i ná»• khÃ´ng. Náº¿u cÃ³ va cháº¡m thÃ¬ return true.
	 * ngÆ°á»£c láº¡i lÃ  false
	 */
	public boolean collidesWith(AnimatedSprite animatedSprite){
		for(int i=0;i<no.length;i++){
			if(no[i].No_AnimatedSprite.isVisible() && no[i].No_AnimatedSprite.collidesWith(animatedSprite))
				return true;//CÃ³ va cháº¡m
		}
		return false;//KhÃ´ng cÃ³ va cháº¡m
	}
}
