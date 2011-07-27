package com.offline.baby.spellpuzzle.data;

import java.util.List;

public abstract class DBManager {

	// private static final int VERSION = 1;
	// private static final String PATH =
	// "/data/data/com.eric.babygame.studycards/databases";
	// private static final String PATH = "com/eric/babygame/studycards/data";
	// private static final String PATH_SD = "/sdcard/EricGames/studycards";
	// private static final String DB_FILE = "/studycard.db";
	// private static final String ASS_FILE = "database/studycard.db";

	// private DBHelper helper;
	// private Context context;

	public abstract List<CardInfo> getCardInfoList(int cardType, int voiceType);

	// public class CardTypeResultSet{
	// private int index = 0;
	// private int count = 0;
	//
	// private List<CardType> datalist;
	//
	// public CardTypeResultSet(){
	// }

	// public CardType getCardTypeByID(int cardtypeID){
	// SQLiteDatabase db = helper.getReadableDatabase();
	// Cursor c =
	// db.rawQuery("SELECT id, title, img_path, sound, is_free, is_enable, sort FROM card_type WHERE id=? ",
	// new String[]{String.valueOf(cardtypeID)});
	// CardType cardtype = new CardType();
	// if(c.moveToNext()){
	// cardtype.setId(c.getInt(0));
	// cardtype.setTitle(c.getString(1));
	// cardtype.setImgPath(c.getString(2));
	// cardtype.setSound(c.getString(3));
	// cardtype.setFree(c.getInt(4)==1);
	// cardtype.setEnable((c.getInt(5)==1));
	// cardtype.setSort(c.getInt(6));
	// }
	//
	// c.close();
	// db.close();
	//
	//
	// return cardtype;
	// }
	//
	// public List<CardType> getDataList(){
	// if(datalist==null){
	// datalist = new ArrayList<CardType>();
	// SQLiteDatabase db = helper.getReadableDatabase();
	// Cursor c =
	// db.rawQuery("SELECT id, title, img_path, sound, is_free, is_enable, sort FROM card_type ORDER BY sort ASC ",
	// new String[]{});
	//
	// index = 0;
	// count = c.getCount();
	// if(count==0){
	// c.close();
	// db.close();
	// throw new SQLException("没有数据");
	// }
	//
	// while(c.moveToNext()){
	// CardType cardtype = new CardType();
	// cardtype.setId(c.getInt(0));
	// cardtype.setTitle(c.getString(1));
	// cardtype.setImgPath(c.getString(2));
	// cardtype.setSound(c.getString(3));
	// cardtype.setFree(c.getInt(4)==1);
	// cardtype.setEnable((c.getInt(5)==1));
	// cardtype.setSort(c.getInt(6));
	// datalist.add(cardtype);
	// }
	// c.close();
	// db.close();
	// }
	// return datalist;
	// }
	//
	// }
	//
	//
	// public class CardResultSet{
	// private int index = 0;
	// private int cardTypeId = 0;
	// private int count = 0;
	// private Vector<Integer> v;
	// private Random rand;
	//
	// // public CardResultSet(String typeName){
	// // SQLiteDatabase db = helper.getReadableDatabase();
	// // Cursor c =
	// db.rawQuery("SELECT id,title,is_free,is_enable FROM card_type WHERE is_enable=1 AND title=? ",
	// new String[]{typeName});
	// // if(c.getCount()==0){
	// // c.close();
	// // db.close();
	// // throw new SQLException("找不到"+typeName+"的数据");
	// // }
	// // int id_index = c.getColumnIndex("id");
	// // if(c.moveToFirst()){
	// // cardTypeId = c.getInt(id_index);
	// // c.close();
	// // c = null;
	// //
	// // c =
	// db.rawQuery("SELECT COUNT(*) FROM cards WHERE card_type_id = ? ",new
	// String[]{String.valueOf(cardTypeId)});
	// // c.moveToFirst();
	// // count = c.getInt(0);
	// //
	// // c.close();
	// // db.close();
	// // }
	// // }
	//
	// public CardResultSet(int cardTypeID){
	// cardTypeId = cardTypeID;
	// SQLiteDatabase db = helper.getReadableDatabase();
	// Cursor c =
	// db.rawQuery("SELECT COUNT(*) FROM cards WHERE card_type_id = ? ",new
	// String[]{String.valueOf(cardTypeId)});
	// c.moveToFirst();
	// count = c.getInt(0);
	//
	// rand = new Random();
	// v = new Vector<Integer>(count);
	//
	// c.close();
	// db.close();
	// }
	//
	// public Cards first(){
	// index = 0;
	// return getCardByIndex(index);
	// }
	//
	// public Cards prev(){
	// index--;
	// if(index<0){
	// index=count-1;
	// }
	// return getCardByIndex(index);
	// }
	//
	// public Cards next(){
	// index++;
	// if(index>count-1){
	// index = 0;
	// }
	// return getCardByIndex(index);
	// }
	//
	// public Cards random(){
	// int __index = -1;
	// do{
	// __index = rand.nextInt(count);
	// }while(v.contains(__index));
	// v.add(__index);
	// index = __index;
	// if(v.size()==count){
	// v.clear();
	// }
	// return getCardByIndex(index);
	// }
	//
	// public List<Cards> getThumbnail(){
	// List<Cards> list = new ArrayList<Cards>();
	// int i = index-1;
	// if(i<0)
	// i = count-1;
	// list.add(getImgAndSoundByIndex(i));
	// i -= 1;
	// if(i<0)
	// i = count-1;
	// list.add(getImgAndSoundByIndex(i));
	// list.add(getImgAndSoundByIndex(index));
	// i = index+1;
	// if(i>count-1)
	// i = 0;
	// list.add(getImgAndSoundByIndex(i));
	// i+=1;
	// if(i>count-1)
	// i = 0;
	// list.add(getImgAndSoundByIndex(i));
	//
	//
	// return list;
	// }
	//
	// public int getIndex(){
	// return index;
	// }
	//
	// public int getCount(){
	// return count;
	// }
	//
	// private Cards getImgAndSoundByIndex(int index_){
	// SQLiteDatabase db = helper.getReadableDatabase();
	// Cursor c = db.rawQuery("SELECT id,img_path,sound FROM cards LIMIT ?, 1 ",
	// new String[]{String.valueOf(index_)});
	// Log.d(Contains.SQL_TAG,
	// "imgByIndex ColumnCount:"+c.getColumnCount()+" Count:"+c.getCount());
	// c.moveToFirst();
	//
	// Cards cc = new Cards();
	// cc.setId(c.getInt(0));
	// cc.setImgPath(c.getString(1));
	// cc.setSound(c.getString(2));
	// c.close();
	// db.close();
	//
	// return cc;
	// }
	//
	// private Cards getCardByIndex(int index_){
	// SQLiteDatabase db = helper.getReadableDatabase();
	// Cursor c =
	// db.rawQuery("SELECT id, chinese_name, pinyin, english, sound, img_path, pinyin_key, english_key FROM cards LIMIT ?, 1 ",
	// new String[]{String.valueOf(index_)});
	// c.moveToFirst();
	// Cards cc = new Cards();
	// cc.setId(c.getInt(0));
	// cc.setChineseName(c.getString(1));
	// cc.setPinyin( c.getString(2));
	// cc.setEnglish( c.getString(3));
	// cc.setSound(c.getString(4));
	// cc.setImgPath(c.getString(5));
	// cc.setPinyinKey(c.getString(6));
	// cc.setEnglishKey(c.getString(7));
	// c.close();
	// db.close();
	// return cc;
	// }
	//
	//
	//
	//
	// }
	//
	//
	// private class DBHelper extends SQLiteOpenHelper {
	//
	// public DBHelper(Context context, String name, CursorFactory factory,
	// int version) {
	// super(context, name, factory, version);
	// }
	//
	// @Override
	// public void onCreate(SQLiteDatabase db) {
	//
	// // db.close();
	//
	// }
	//
	// @Override
	// public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
	// {
	// Log.d("SQL", "Upgrade oldVersion:" + oldVersion + " newVersion:"
	// + newVersion);
	// }
	//
	// }

}
