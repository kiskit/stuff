package models;

import java.util.ArrayList;
import java.util.List;

public class Rental {
	
	private Long userId;
	private List<RentedVideo> videos = null;
	
	
	public void addVideo(Long id, String title) {
		RentedVideo v = new RentedVideo();
		v.setId(id);
		v.setTitle(title);
		if (videos == null) {
			videos = new ArrayList<RentedVideo>();
		}
		videos.add(v);
	}
	public Long getUserId() {
		return userId;
	}



	public void setUserId(Long userId) {
		this.userId = userId;
	}



	public List<RentedVideo> getVideos() {
		return videos;
	}



	public void setVideos(List<RentedVideo> videos) {
		this.videos = videos;
	}



	@Override
	public String toString() {
		return "Rental [userId=" + userId + ", videos=" + videos + "]";
	}



	public class RentedVideo {
		public RentedVideo() {
			
		}
		private Long id;
		private String title;
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getTitle() {
			return title;
		}
		public void setTitle(String title) {
			this.title = title;
		}
		@Override
		public String toString() {
			return "RentedVideo [id=" + id + ", title=" + title + "]";
		}
		
	}
	
}
