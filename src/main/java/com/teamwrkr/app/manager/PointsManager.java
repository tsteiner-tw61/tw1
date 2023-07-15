package com.teamwrkr.app.manager;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.teamwrkr.app.dto.ActionPoints;
import com.teamwrkr.app.dto.Points;
import com.teamwrkr.app.dto.Profile;
import com.teamwrkr.app.dto.Tx;
import com.teamwrkr.app.service.DataService;
import com.teamwrkr.app.util.SiteConstants;


/**
 * This class manages any points awarded by the
 * Teamwrkr system.
 * 
 * @author 		Ted E. Steiner
 * @date		05.22.2023
 *
 */
public class PointsManager {
	
	
	public PointsManager() { }
	
	
	/**
	 * Convenience method to grab the current total
	 * points for a given user.
	 * 
	 * @param profile
	 * @return int pts
	 */
	public static int getTotalPoints(Profile profile) {
		
		int pts = 0;
		
		int twid = profile.getTwid();
		Points points = new Points();
		points.setTwid(twid);
		Points points1 = (Points) DataService.selectOne(points);
		pts = points1.getMisc();
		
		return pts;
		
	}
	
	
	
	/**
	 * Returns points of a particular type or category of
	 * types for possible future display and or calculation
	 * of points before and after the addition of newly 
	 * awarded points.
	 * 
	 * @param twid
	 * @param type
	 * @return int pts
	 */
	public static int getPointsOfType(int twid, int type) {
		
		SiteConstants sc = new SiteConstants();
		
		int pts = 0;
		
		Points points = new Points();
		points.setTwid(twid);
		Points points1 = (Points) DataService.selectOne(points);
		
		if (type == sc.POINTS_TYPE_LOGIN) {
			pts = points1.getLogins();
		}
		if (type == sc.POINTS_TYPE_PROFILE) {
			pts = points1.getProfileEdit() +
					points1.getProfileNiche() +
					points1.getProfilePhoto() +
					points1.getProfileReview() +
					points1.getProfileSkills() +
					points1.getProfileURL() +
					points1.getProfileVideo() +
					points1.getProfileView();
		}
		if (type == sc.POINTS_TYPE_JOBS) {
			pts = points1.getJobApplies() +
					points1.getJobDones() +
					points1.getJobPays() +
					points1.getJobPosts();
		}
		if (type == sc.POINTS_TYPE_CHAT) {
			pts = points1.getChat();
		}
		
		return pts;
		
	}
	
	
	/**
	 * Add points to the user's Points record, when applicable.
	 * 
	 * @param twid
	 * @param actionCode
	 */
	public static void addPoints(int twid, int actionCode) {
		
		SiteConstants sc = new SiteConstants();
		
		/*
		 * Determine the ActionPoints for the actionCode.
		 */
		System.out.println("twid==" + twid);
		ActionPoints ap = new ActionPoints();
		ap.setActId(actionCode);
		ActionPoints apts = (ActionPoints) DataService.selectOne(ap);
		System.out.println("apts=" + apts.getPoints());
		
		/*
		 * Set up the Points update
		 */
		Points reference = new Points();
		reference.setTwid(twid);
		Points update = (Points) DataService.selectOne(reference);
		
		int currentTotalPts = update.getMisc();
		
		boolean ptsAwarded = false;
		
		if (actionCode == sc.ACTION_POINTS_REGISTER) {
			ptsAwarded = true;
		}
		if (actionCode == 2) {
			if (update.getProfilePhoto() == 0) {
				update.setProfilePhoto(apts.getPoints());
				ptsAwarded = true;
			}
		}
		if (actionCode == 3) {
			if (update.getProfileNiche() == 0) {
				update.setProfileNiche(apts.getPoints());
				ptsAwarded = true;
			}
		}
		if (actionCode == 4) {
			if (update.getProfileURL() == 0) {
				update.setProfileURL(apts.getPoints());
				ptsAwarded = true;
			}
		}
		if (actionCode == 5) {
			if (update.getProfileSkills() == 0) {
				update.setProfileSkills(apts.getPoints());
				ptsAwarded = true;
			}
		}
		if (actionCode == 6 || actionCode == 7) {
			if (update.getProfileReview() < 600) {
				int currentProfileReviewPts = update.getProfileReview();
				update.setProfileReview(currentProfileReviewPts + apts.getPoints());
				ptsAwarded = true;
			}
		}
		
		if (ptsAwarded) {
			update.setMisc(currentTotalPts + apts.getPoints());
			Points update2 = new Points(update);
			DataService.update(update2, reference);
		}
		
		
		
		

	}
	
	
	/**
	 * Convenience method to quickly sum all of the points
	 * for each category or type into the total for updating
	 * the record.
	 * 
	 * @param points
	 * @return int total
	 */
	public static int sumAllPointsToTotal(Points points) {
		
		int total = 0;
		
		total = points.getAvailability() +
				points.getBonus() +
				points.getChat() +
				points.getContacts() +
				points.getContribution() +
				points.getCurrency() +
				points.getFeedback() +
				points.getJobApplies() +
				points.getJobDones() +
				points.getJobPays() +
				points.getJobPosts() +
				points.getLogins() +
				points.getOnlineTime() +
				points.getProfileEdit() +
				points.getProfileNiche() +
				points.getProfilePhoto() +
				points.getProfileReview() +
				points.getProfileSkills() +
				points.getProfileURL() +
				points.getProfileVideo() +
				points.getProfileView() +
				points.getReferrals() +
				points.getSpecial() +
				points.getSurveys() +
				points.getUpvotes();
		
		
		return total;
		
	}
	
	
	
	/**
	 * Note these calculated points are not to be added
	 * to an existing total, but rather the calculated value
	 * returned by this method is to replace the existing
	 * value for login points earned by the user.
	 * 
	 * @param twid
	 * @return int pts
	 */
	public static int calculateLoginPoints(int twid) {
		
		int pts = 0;
		Tx tx1 = new Tx();
		String qs = "twid = " + twid + " AND type = 1 ORDER BY createDate ASC";
		Object[] txs = DataService.selectMany(tx1, qs);
		
		
		List<Tx> txList = new ArrayList<Tx>();
		Date initialLoginDate = new Date();
		Date previousLoginDate = new Date();
		int idx = 0;
		int loginStreak = 0;
		for (Object tx : txs) {
			Tx tx2 = (Tx) tx;
			System.out.println("tx(login)@" + tx2.getCreateDate().toString());
			if (idx == 0) {
				initialLoginDate = tx2.getCreateDate();
				previousLoginDate = tx2.getCreateDate();
				pts = pts + 1;
				loginStreak = loginStreak + 1;
			}
			else {
				Date currentLoginDate = tx2.getCreateDate();
				loginStreak = loginStreak + 1;
				int loginGap = currentLoginDate.compareTo(previousLoginDate);
				System.out.println("loginGap=" + loginGap);
				if (loginGap == 0) {
					continue;
				}
				if (loginGap == 1) {
					pts = pts + 1;
					loginStreak = loginStreak + 1;
					if (loginStreak == 3) {
						pts = pts + 5;
					}
					if (loginStreak == 5) {
						pts = pts + 10;
					}
					if (loginStreak == 7) {
						pts = pts + 20;
					}
				}
				if (loginGap > 1) {
					pts = pts + 1;
					loginStreak = 0;
				}
				previousLoginDate = tx2.getCreateDate();
			}
			txList.add(tx2);
			idx++;
		}
		
		return pts;
		
	}

	

}
