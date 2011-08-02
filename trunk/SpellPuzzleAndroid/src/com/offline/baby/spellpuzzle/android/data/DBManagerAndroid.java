package com.offline.baby.spellpuzzle.android.data;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

import com.offline.baby.spellpuzzle.data.CardInfo;
import com.offline.baby.spellpuzzle.data.DBManager;

public class DBManagerAndroid extends DBManager {

	private DBHelper helper;

	private static final int VERSION = 1;
	private static final String PATH_SD = "/sdcard/offlinegames/spellpuzzle";
	private static final String DB_FILE = "/root.db";
	private static final String ASS_FILE = "data/root.db";

	private Context context;

	public DBManagerAndroid(Context context) throws IOException {
		super();
		this.context = context;

		File dir = new File(PATH_SD);
		if (!dir.exists()) {
			dir.mkdirs();
		}
		
		// copy文件到rom和sd卡
		InputStream is = context.getAssets().open(ASS_FILE);
		OutputStream os = new FileOutputStream(PATH_SD + DB_FILE, false);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = is.read(buffer)) > 0) {
			os.write(buffer, 0, length);
		}
		os.flush();
		os.close();
		is.close();

		helper = new DBHelper(this.context, PATH_SD + DB_FILE, null, VERSION);
	}

	@Override
	public List<CardInfo> getCardInfoList(int cardType, int voiceType) {
		List<CardInfo> list = new ArrayList<CardInfo>();
		SQLiteDatabase db = helper.getReadableDatabase();
		Cursor c = db
				.rawQuery(
						"SELECT ci.id, ci.type, ci.name, ci.file_path, ci.description, ci.letters,"
								+ "ci.length, ci.tips, ci.chinese_path, vi.type, vi.file_path "
								+ " FROM card_info ci, card_voice_info vi WHERE ci.id = vi.card_id "
								+ " AND ci.type=" + cardType + " AND vi.type="
								+ voiceType, null);
		CardInfo ci = null;
		while (c.moveToNext()) {
			ci = new CardInfo();
			ci.setId(c.getInt(0));
			ci.setCardType(c.getInt(1));
			ci.setName(c.getString(2));
			ci.setFilePath(c.getString(3));
			ci.setDescription(c.getString(4));
			ci.setLetters(c.getString(5));
			ci.setLength(c.getInt(6));
			ci.setTips(c.getString(7));
			ci.setChinese(c.getString(8));
			ci.setVoiceType(c.getInt(9));
			ci.setVoiceFilePath(c.getString(10));
			ci.setCardTypeName("");
			ci.setVoiceDescription("");
			ci.setVoiceTypeName("");
			list.add(ci);
		}

		c.close();
		// db.close();

		return list;
	}

	public static class CardResultSet {

		public static List<CardInfo> getCardInfo() {
			List<CardInfo> list = new ArrayList<CardInfo>();
			// try {
			// Statement stat = conn.createStatement();
			// ResultSet rs = stat
			// .executeQuery("SELECT card_info.*,card_voice_info.card_id, "
			// +
			// "card_voice_info.type AS card_type, card_voice_info.file_path AS voice_path "
			// +
			// " FROM card_info, card_voice_info where card_info.id = card_voice_info.card_id");
			// CardInfo ci = null;
			// while (rs.next()) {
			// ci = new CardInfo();
			// ci.setId(rs.getInt("id"));
			// ci.setCardType(rs.getInt("card_type"));
			// ci.setCardTypeName("");
			// ci.setDescription(rs.getString("description"));
			// ci.setFilePath(rs.getString("file_path"));
			// ci.setChinese(rs.getString("chinese_path"));
			// ci.setLength(rs.getInt("length"));
			// ci.setLetters(rs.getString("letters"));
			// ci.setName(rs.getString("name"));
			// ci.setTips(rs.getString("tips"));
			// ci.setVoiceDescription("");
			// ci.setVoiceFilePath(rs.getString("voice_path"));
			// ci.setVoiceType(rs.getInt("card_type"));
			// ci.setVoiceTypeName("");
			// list.add(ci);
			// }
			// rs.close();
			// } catch (SQLException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
			// }

			return list;
		}
	}

	private class DBHelper extends SQLiteOpenHelper {

		public DBHelper(Context context, String name, CursorFactory factory,
				int version) {
			super(context, name, factory, version);
		}

		@Override
		public void onCreate(SQLiteDatabase arg0) {
			// TODO Auto-generated method stub
		}

		@Override
		public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {
			// TODO Auto-generated method stub

		}
	}

}
