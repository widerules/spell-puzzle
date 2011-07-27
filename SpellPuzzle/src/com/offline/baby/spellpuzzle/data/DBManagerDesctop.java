package com.offline.baby.spellpuzzle.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManagerDesctop extends DBManager {

	@Override
	public List<CardInfo> getCardInfoList(int cardType, int voiceType) {
		List<CardInfo> list = new ArrayList<CardInfo>();
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:data/root.db");
			Statement stat = conn.createStatement();
			ResultSet rs = stat
					.executeQuery("SELECT card_info.*,card_voice_info.card_id, "
							+ "card_voice_info.type AS card_type, card_voice_info.file_path AS voice_path "
							+ " FROM card_info, card_voice_info WHERE card_info.id = card_voice_info.card_id "
							+ " AND card_info.type="
							+ cardType
							+ " AND card_voice_info.type=" + voiceType);
			
			CardInfo ci = null;
			while (rs.next()) {
				ci = new CardInfo();
				ci.setId(rs.getInt("id"));
				ci.setCardType(rs.getInt("card_type"));
				ci.setCardTypeName("");
				ci.setDescription(rs.getString("description"));
				ci.setFilePath(rs.getString("file_path"));
				ci.setChinese(rs.getString("chinese_path"));
				ci.setLength(rs.getInt("length"));
				ci.setLetters(rs.getString("letters"));
				ci.setName(rs.getString("name"));
				ci.setTips(rs.getString("tips"));
				ci.setVoiceDescription("");
				ci.setVoiceFilePath(rs.getString("voice_path"));
				ci.setVoiceType(rs.getInt("card_type"));
				ci.setVoiceTypeName("");
				list.add(ci);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;
	}
}
