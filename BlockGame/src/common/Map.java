package common;

public class Map {
	String bgImg;
	String bgSound;
	
	public Map() {
		this.bgImg = "";
		this.bgSound = "";
	}

	public String getBgImg() {
		return bgImg;
	}

	public void setBgImg(String bgImg) {
		this.bgImg = bgImg;
	}

	public String getBgSound() {
		return bgSound;
	}

	public void setBgSound(String bgSound) {
		this.bgSound = bgSound;
	}

	public Map(String bgImg, String bgSound) {
		super();
		this.bgImg = bgImg;
		this.bgSound = bgSound;
	}
}
