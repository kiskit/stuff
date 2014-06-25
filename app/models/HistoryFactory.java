package models;

import java.util.List;

public final class HistoryFactory {
	public static History checkin(Long adminId, Long userId, List<Long> videos) {
		History history = new History();
		history.setWho(adminId);
		history.setAction(History.OperationType.CHECKIN);
		history.setWhatVideos(videos);
		history.setWhatUser(userId);
		return null;
	}
}
