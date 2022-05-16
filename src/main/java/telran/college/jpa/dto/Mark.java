package telran.college.jpa.dto;

public class Mark {
	public long stid;
	public long sbid;
	public int mark;
	public Mark(long stid, long sbid, int mark) {
		this.stid = stid;
		this.sbid = sbid;
		this.mark = mark;
	} 
	public Mark() {
		
	}
}
