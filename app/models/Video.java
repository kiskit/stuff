package models;


import play.data.format.Formats;
import play.data.validation.Constraints;
import play.db.ebean.Model.Finder;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class Video {
	public Video() {
		
	}

	public enum SupportType {DVD, BLURAY};
	public enum ContentType {UNKNOWN, TV, MOVIE};
	public enum StateType {OK, BROKEN, LOST};
	
    public static Finder find = new Finder(Long.class, Video.class);
	
	@Id
	private Long id;
	@Constraints.Required
	private SupportType supportType;
	@Constraints.Required
	private ContentType contentType;
	private String movieId;
	@Constraints.Required
	private String inputTitle;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date creationDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    @Constraints.Required
    private Date updateDate;
    @Formats.DateTime(pattern = "dd/MM/yyyy")
    private Date rentalDate;
    private Long rentedTo;
	@Constraints.Required
    private StateType state;
    
    
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public SupportType getSupportType() {
		return supportType;
	}
	public void setSupportType(SupportType supportType) {
		this.supportType = supportType;
	}
	public ContentType getContentType() {
		return contentType;
	}
	public void setContentType(ContentType contentType) {
		this.contentType = contentType;
	}
	public String getMovieId() {
		return movieId;
	}
	public void setMovieId(String movieId) {
		this.movieId = movieId;
	}
	public String getInputTitle() {
		return inputTitle;
	}
	public void setInputTitle(String inputTitle) {
		this.inputTitle = inputTitle;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Date getUpdateDate() {
		return updateDate;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	public Long getRentedTo() {
		return rentedTo;
	}
	public void setRentedTo(Long rentedTo) {
		this.rentedTo = rentedTo;
	}
	
	
	public StateType getState() {
		return state;
	}
	public void setState(StateType state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Video [id=" + id + ", supportType=" + supportType
				+ ", contentType=" + contentType + ", movieId=" + movieId
				+ ", inputTitle=" + inputTitle + ", creationDate="
				+ creationDate + ", updateDate=" + updateDate + ", rentedTo="
				+ rentedTo + ", state=" + state + "]";
	}


}
