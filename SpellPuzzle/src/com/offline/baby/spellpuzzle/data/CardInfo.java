package com.offline.baby.spellpuzzle.data;

public class CardInfo {
	
	public static final int CARTOON_WIDTH = 318;
	public static final int CARTOON_HEIGHT = 304;
	
	private int id;
	private String name;
	private String chinese;
	private String filePath;
	private String description;
	private String letters;
	private String tips;
	private int length;

	private int cardType;
	private String cardTypeName;

	private int voiceType;
	private String voiceTypeName;
	private String voiceFilePath;
	private String voiceDescription;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLetters() {
		return letters;
	}

	public void setLetters(String letters) {
		this.letters = letters;
	}

	public String getTips() {
		return tips;
	}

	public void setTips(String tips) {
		this.tips = tips;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public int getCardType() {
		return cardType;
	}

	public void setCardType(int cardType) {
		this.cardType = cardType;
	}

	public String getCardTypeName() {
		return cardTypeName;
	}

	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}

	public int getVoiceType() {
		return voiceType;
	}

	public void setVoiceType(int voiceType) {
		this.voiceType = voiceType;
	}

	public String getVoiceTypeName() {
		return voiceTypeName;
	}

	public void setVoiceTypeName(String voiceTypeName) {
		this.voiceTypeName = voiceTypeName;
	}

	public String getVoiceFilePath() {
		return voiceFilePath;
	}

	public void setVoiceFilePath(String voiceFilePath) {
		this.voiceFilePath = voiceFilePath;
	}

	public String getVoiceDescription() {
		return voiceDescription;
	}

	public void setVoiceDescription(String voiceDescription) {
		this.voiceDescription = voiceDescription;
	}

	public String getChinese() {
		return chinese;
	}

	public void setChinese(String chinese) {
		this.chinese = chinese;
	}

}
