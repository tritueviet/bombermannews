package com.example.bombermannew.Player;

import org.anddev.andengine.engine.Engine;
import org.anddev.andengine.entity.scene.Scene;
import org.anddev.andengine.entity.sprite.AnimatedSprite;
import org.anddev.andengine.opengl.texture.TextureOptions;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.anddev.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.anddev.andengine.opengl.texture.region.TiledTextureRegion;

import com.example.bombermannew.Bom.QuaBom;
import com.example.bombermannew.InterfaceSprite.InterfaceSprite;
import android.content.Context;

public class Player implements InterfaceSprite{

	private int STATUS_PALYER = StatusPlayer.UN_MOVE_DOWN;//Tráº¡ng thÃ¡i ban Ä‘áº§u cá»§a nhÃ¢n váº­t lÃ  nhÃ¬n vá»� phÃ­a trÆ°á»›c
	
	//Táº¡o hiá»‡u á»©ng khi di chuyá»ƒn cho player
	public AnimatedSprite player_AnimatedSprite;
	
	//CÃ¡c biáº¿n lÆ°u vÃ  load áº£nh
	private TiledTextureRegion player_TiledTextureRegion;
	private BitmapTextureAtlas player_BitmapTextureAtlas;

	private int heart = 3;//Sá»‘ máº¡ng cá»§a player. Máº·c Ä‘á»‹nh ban Ä‘áº§u lÃ  cÃ³ 3 lÆ°á»£t chÆ¡i
	
	//Vá»‹ trÃ­ cá»§a Player
	private float positionX = 0;
	private float positionY = 0;
	
	private int max_quabom = 10;//Tá»•ng sá»‘ quáº£ bom mÃ  player cÃ³ thá»ƒ cÃ³ Ä‘Æ°á»£c.
	public int current_quabom = 1;// Sá»‘ lÆ°á»£ng quáº£ bom mÃ  player hiá»‡n cÃ³. Máº·c Ä‘á»‹nh  = 1 quáº£
	private int max_cap_quabom = 5;//LÃ  cáº¥p Ä‘á»™ lá»›n nháº¥t mÃ  1 quáº£ bom cÃ³ Ä‘Æ°á»£c.
	public int current_cap_quabom = 1;//LÃ  cáº¥p Ä‘á»™ bom hiá»‡n táº¡i cá»§a Player. Máº·c Ä‘á»‹nh lÃ  cáº¥p 1
	
	//Danh sÃ¡ch qua bom
	public QuaBom[] MyQuaBom;//Khai bÃ¡o vá»›i máº£ng vá»›i sá»‘ lÆ°á»£ng bom tá»‘i Ä‘a
	//=======================================|| Player ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c khá»Ÿi dá»±ng khÃ´ng cÃ³ tham sá»‘.
	 * @max_quabom : Tá»•ng sá»‘ quáº£ bom mÃ  player cÃ³ thá»ƒ cÃ³ Ä‘Æ°á»£c.
	 * @current_quabom : Sá»‘ lÆ°á»£ng quáº£ bom mÃ  player hiá»‡n cÃ³. Máº·c Ä‘á»‹nh  = 1 quáº£
	 * @max_cap_quabom : LÃ  cáº¥p Ä‘á»™ lá»›n nháº¥t mÃ  1 quáº£ bom cÃ³ Ä‘Æ°á»£c.
	 * @current_cap_quabom : LÃ  cáº¥p Ä‘á»™ bom hiá»‡n táº¡i cá»§a Player. Máº·c Ä‘á»‹nh lÃ  cáº¥p 1
	 */
	public Player(int max_quabom,int current_quabom, int max_cap_quabom, int current_cap_quabom){
		this.max_quabom = max_quabom;
		this.current_quabom = current_quabom;
		this.max_cap_quabom = max_cap_quabom;
		this.current_cap_quabom = current_cap_quabom;
		MyQuaBom = new QuaBom[this.max_quabom];
	}
	
	//=======================================|| onLoadResources ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadResources
	 */
	@Override
	public void onLoadResources(Engine mEngine, Context mContext) {
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/Player/");	
		this.player_BitmapTextureAtlas = new BitmapTextureAtlas(512, 512, TextureOptions.BILINEAR_PREMULTIPLYALPHA);		
		this.player_TiledTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.player_BitmapTextureAtlas, mContext, "nhan_vat_chinh.png", 0, 0, 9, 8);
		mEngine.getTextureManager().loadTexture(this.player_BitmapTextureAtlas);
		
		//Thá»±c hiá»‡n load toÃ n bá»™ quáº£ bom
		for(int i=0;i<max_quabom;i++){
			MyQuaBom[i] = new QuaBom();			
			MyQuaBom[i].cap = max_cap_quabom;//Ban Ä‘áº§u thÃ¬ ta load toÃ n bá»™ bom vá»›i cáº¥p Ä‘á»™ lÃ  max
			MyQuaBom[i].onLoadResources(mEngine, mContext);
			
			//Khi ta load xong thÃ¬ ta Ä‘áº·t láº¡i cáº¥p bom cho Ä‘á»‘i tÆ°á»£ng quabom
			MyQuaBom[i].cap = this.current_cap_quabom;
			}
	}
	//=======================================|| onLoadScene ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c onLoadScene
	 */
	@Override
	public void onLoadScene(Scene mScene) {	
		//Ä�áº·t vá»‹ trÃ­ ban Ä‘áº§u cá»§a player 
		player_AnimatedSprite = new AnimatedSprite(this.positionX, this.positionY, this.player_TiledTextureRegion);
		//PhÆ°Æ¡ng thá»©c nÃ y cho biáº¿t player sáº½ hiá»‡n thá»‹ láº§n Ä‘áº§u tiÃªn vá»›i tráº¡ng thÃ¡i gÃ¬
		showPlayerStatus();
		mScene.attachChild(player_AnimatedSprite);
		//Ban Ä‘áº§u thÃ¬ load toÃ n bá»™ bom vÃ  cho nÃ³ á»Ÿ ngoÃ i mÃ n hÃ¬nh
		for(int i=0;i<max_quabom;i++){
			MyQuaBom[i].onLoadScene(mScene);//Ä�áº·t toÃ n bá»™ cÃ¡c quáº£ bom ngoÃ i mÃ n hÃ¬nh
		}
	}

	//=======================================|| Position ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c cho ta Ä‘áº·t vá»‹ trÃ­ cá»§a player theo trá»¥c X.
	 * @param positionX
	 */
	public void setPositionX(float positionX){
		Player.this.positionX = positionX;
	}
	
	/**
	 * Get vá»‹ trÃ­ x xá»§a player
	 * @return
	 */
	public float getPositionX(){
		return Player.this.positionX;
	}
	/**
	 * PhÆ°Æ¡ng thá»©c cho ta Ä‘áº¡t vá»‹ trÃ­ cá»§a player theo trá»¥c Y
	 * @param positionY
	 */
	public void setPositionY(float positionY){
		Player.this.positionY = positionY;
	}
	
	/**
	 * Get vá»‹ trÃ­ y cá»§a player
	 * @return
	 */
	public float getPositionY(){
		return Player.this.positionY;
	}
	
	/**
	 * Ä�áº·t vá»‹ trÃ­ khá»Ÿi táº¡o cho player
	 * @param positionX
	 * @param positionY
	 */
	public void setPositionXY(float positionX, float positionY){
		Player.this.positionX = positionX;
		Player.this.positionY = positionY;
	}
	//=======================================|| setStatusPlayer ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c set tráº¡ng thÃ¡i cho Player
	 * @param: statusplayer lÃ  tráº¡ng thÃ¡i báº¡n muá»‘n Ä‘áº·t. CÃ¡c tráº¡ng thÃ¡i báº¡n cÃ³ thá»ƒ
	 * láº¥y báº±ng cÃ¡ch gá»�i StatusPlayer. (cháº¥m) Ä‘á»ƒ tham chiáº¿u Ä‘áº¿n cÃ¡c tráº¡ng thÃ¡i
	 */
	public void setStatusPlayer(int statusplayer){
		Player.this.STATUS_PALYER = statusplayer;
		//Náº¿u setStatusPlayer thÃ¬ ta sáº½ cáº­p nháº­t xem player sáº½ hiá»‡n thá»‹ AnimatedSprite nÃ o.
		showPlayerStatus();
	}
	//=======================================|| getStatusPlayer ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c get tráº¡ng thÃ¡i cho Player
	 */
	public int getStatusPlayer(){
		return Player.this.STATUS_PALYER;
	}
	//=======================================|| showPlayerStatus ||================================
	/**
	 * PhÆ°Æ¡ng thá»©c xÃ¡c Ä‘á»‹nh xem Player Ä‘ang á»Ÿ tráº¡ng thÃ¡i nÃ o Ä‘á»ƒ hiá»‡n thá»‹
	 */
	private void showPlayerStatus(){
		
		switch(this.STATUS_PALYER){
			case StatusPlayer.MOVE_LEFT:{
				player_AnimatedSprite.animate(new long[]{100,100,100}, new int[]{12,21,30}, 1000);
				break;
			}
			case StatusPlayer.MOVE_RIGHT:{
				player_AnimatedSprite.animate(new long[]{100,100,100}, new int[]{4,13,22}, 1000);
				break;
			}
			case StatusPlayer.MOVE_UP:{
				player_AnimatedSprite.animate(new long[]{100,100,100}, new int[]{3,31,5}, 1000);
				break;
			}
			case StatusPlayer.MOVE_DOWN:{
				player_AnimatedSprite.animate(new long[]{100,100,100}, new int[]{14,23,32}, 1000);
				break;
			}	
			case StatusPlayer.UN_MOVE_LEFT:{
				player_AnimatedSprite.animate(new long[]{100,100}, new int[]{21,21}, 2);
				break;
			}
			case StatusPlayer.UN_MOVE_RIGHT:{
				player_AnimatedSprite.animate(new long[]{100,100}, new int[]{4,4}, 2);
				break;
			}
			case StatusPlayer.UN_MOVE_UP:{
				player_AnimatedSprite.animate(new long[]{100,100}, new int[]{3,3}, 2);
				break;
			}
			case StatusPlayer.UN_MOVE_DOWN:{
				player_AnimatedSprite.animate(new long[]{100,100}, new int[]{14,14}, 2);
				break;
			}
		}
	}
	//=======================================|| movePlayer ||================================
	/**
	 * Move player tá»›i vá»‹ trÃ­ X
	 * @param moveX
	 */
	public void moveX(float moveX){
		this.positionX = moveX;
		movePlayer();
	}
	/**
	 * Move player tá»›i vá»‹ trÃ­ Y
	 * @param moveY
	 */
	public void moveY(float moveY){
		this.positionY = moveY;
		movePlayer();
	}
	/**
	 * Move player tá»›i vá»‹ trÃ­ X,Y
	 * @param moveX
	 * @param moveY
	 */
	public void moveXY(float moveX, float moveY){
		this.positionX = moveX;
		this.positionY = moveY;
		movePlayer();
	}
	/**
	 * Move player táº¡i vá»‹ trÃ­ hiá»‡n táº¡i vÃ  cá»™ng thÃªm 1 giÃ¡ trá»‹ moveRelativeX
	 * @param moveRelativeX
	 */
	public void moveRelativeX(float moveRelativeX){
		this.positionX += moveRelativeX;
		movePlayer();
	}
	/**
	 * Move player táº¡i vá»‹ trÃ­ hiá»‡n táº¡i vÃ  cá»™ng thÃªm 1 giÃ¡ trá»‹ moveRelativeY
	 * @param moveRelativeY
	 */
	public void moveRelativeY(float moveRelativeY){
		this.positionY += moveRelativeY;
		movePlayer();
	}
	/**
	 * Move player táº¡i vá»‹ trÃ­ hiá»‡n táº¡i vÃ  cá»™ng thÃªm 1 giÃ¡ trá»‹ moveRelativeX, moveRelativeY
	 * @param moveRelativeX
	 * @param moveRelativeY
	 */
	public void moveRelativeXY(float moveRelativeX, float moveRelativeY){
		this.positionX += moveRelativeX;
		this.positionY += moveRelativeY;
		movePlayer();
	}
	
	/**
	 * Move player
	 */
	private void movePlayer(){
		player_AnimatedSprite.setPosition(this.positionX, this.positionY);
	}
	//=======================================|| sum_bom ||================================
	/**
	 * Ä�áº·t sá»‘ lÆ°á»£ng bom cho player
	 */
	public void setCurrent_quabom(int current_quabom){
		this.current_quabom = current_quabom;
	}
	/**
	 * Nháº­n sá»‘ lÆ°á»£ng bom mÃ  palyer cÃ³
	 * @return
	 */
	public int getCurrent_quabom(){
		return this.current_quabom;
	}
	//=======================================|| Heart ||================================
	/**
	 * Ä�áº·t sá»‘ lÆ°á»£ng bom cho heart
	 */
	public void setHeart(int heart){
		this.heart = heart;
	}
	/**
	 * Nháº­n sá»‘ lÆ°á»£ng heart mÃ  nhÃ¢n váº­t cÃ³
	 * @return
	 */
	public int getHeart(){
		return this.heart;
	}
	public AnimatedSprite getAnimatedSprite(){
		return this.player_AnimatedSprite;
	}
	
	public void setCurrent_cap_quabom(int current_cap_quabom){
		this.current_cap_quabom = current_cap_quabom;
		for(int i=0;i<max_quabom;i++){
			MyQuaBom[i].cap = this.current_cap_quabom;
			}
	}
	
	public int getCurrent_cap_quabom(){
		return this.current_cap_quabom;
	}
}


