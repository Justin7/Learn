package my.andr.memorize.db;

public class SentenceVO {
	private int no;
	private String engSentence, korSentence;
	private int labtime;
	private String lastAccessedTime;
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getEngSentence() {
		return engSentence;
	}
	public void setEngSentence(String engSentence) {
		this.engSentence = engSentence;
	}
	public String getKorSentence() {
		return korSentence;
	}
	public void setKorSentence(String korSentence) {
		this.korSentence = korSentence;
	}
	public int getLabtime() {
		return labtime;
	}
	public void setLabtime(int labtime) {
		this.labtime = labtime;
	}
	public String getLastAccessedTime() {
		return lastAccessedTime;
	}
	public void setLastAccessedTime(String lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("SentenceVO [no=");
		builder.append(no);
		builder.append(", engSentence=");
		builder.append(engSentence);
		builder.append(", korSentence=");
		builder.append(korSentence);
		builder.append(", labtime=");
		builder.append(labtime);
		builder.append(", lastAccessedTime=");
		builder.append(lastAccessedTime);
		builder.append("]");
		return builder.toString();
	}
	
}//end class
