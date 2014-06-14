package models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import play.data.format.Formats;

public class Rental {
	
	private Long userId;
	private List<RentedVideo> videos = null;
		
	public void addVideo(Long id, String title, Date date) {
		RentedVideo v = new RentedVideo();
		v.setId(id);
		v.setTitle(title);
		v.setRentalDate(date);
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
		return "Rental [userId=" + userId + ", videos=" + videos
				+ "]";
	}



	public class RentedVideo {
		public RentedVideo() {
			
		}
		private Long id;
		private String title;
		@Formats.DateTime(pattern = "dd/MM/yyyy")
		private Date rentalDate;
		
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
		public Date getRentalDate() {
			return rentalDate;
		}
		public void setRentalDate(Date rentalDate) {
			this.rentalDate = rentalDate;
		}

		@Override
		public String toString() {
			return "RentedVideo [id=" + id + ", title=" + title + ", rentalDate=" + rentalDate + "]";
		}
		
	}
	
}
