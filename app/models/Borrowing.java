package models;

public class Borrowing {
	public Borrowing() {
		
	}
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getVideoId() {
		return videoId;
	}
	public void setVideoId(String videoId) {
		this.videoId = videoId;
	}
	public String getVideoTitle() {
		return videoTitle;
	}
	public void setVideoTitle(String videoTitle) {
		this.videoTitle = videoTitle;
	}

	@Override
	public String toString() {
		return "borrowing [userName=" + userName + ", userId=" + userId
				+ ", videoId=" + videoId + ", videoTitle=" + videoTitle + "]";
	}

	public String userName;
	public String userId;
	public String videoId;
	public String videoTitle;
}
