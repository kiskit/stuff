package models;

import java.util.Date;
import java.util.List;

public class History {
	public enum OperationType {CREATEUSER, UPDATEUSER, CREATEVIDEO, UPDATEVIDEO, CHECKIN, CHECKOUT};
	public enum ResultType {SUCCESS, FAILURE};
	// Admin login who did the action
	private Long who;
	private Long whatUser;
	private List<Long> whatVideos;
	private OperationType action;
	private Date timestamp;
	private ResultType result;
	private String additionalInfo;

	public History() {
	
	}
	
	public Long getWho() {
		return who;
	}
	public void setWho(Long who) {
		this.who = who;
	}
	public Long getWhatUser() {
		return whatUser;
	}
	public void setWhatUser(Long whatUser) {
		this.whatUser = whatUser;
	}
	public List<Long> getWhatVideos() {
		return whatVideos;
	}
	public void setWhatVideos(List<Long> whatVideos) {
		this.whatVideos = whatVideos;
	}
	public OperationType getAction() {
		return action;
	}
	public void setAction(OperationType action) {
		this.action = action;
	}
	public Date getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}
	public ResultType getResult() {
		return result;
	}
	public void setResult(ResultType result) {
		this.result = result;
	}
	public String getAdditionalInfo() {
		return additionalInfo;
	}
	public void setAdditionalInfo(String additionalInfo) {
		this.additionalInfo = additionalInfo;
	}

	@Override
	public String toString() {
		return "History [who=" + who + ", whatUser=" + whatUser
				+ ", whatVideos=" + whatVideos + ", action=" + action
				+ ", timestamp=" + timestamp + ", result=" + result
				+ ", additionalInfo=" + additionalInfo + "]";
	}

	
	
}
