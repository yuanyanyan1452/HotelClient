package vo;

import java.io.Serializable;

public class EvaluationVO implements Serializable{
	double score;
	String comments;
	public EvaluationVO(double score, String comments) {
		this.score = score;
		this.comments = comments;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
}
