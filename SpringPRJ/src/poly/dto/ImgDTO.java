package poly.dto;

public class ImgDTO {
	private String seq; //게시글 번호
	private String save_folder_name;//폴더명(저장된 폴더명)
	private String full_file_info; //파일전체경로(저장경로+파일명)
	private String save_file_name; //파일명
	private String save_file_path; //파일경로(저장된 물리적 경로)
	private String org_file_name; //오리지널파일명(업로드하는 실제 파일명)
	private String note;//글
	private String ext;//확장자
	private String reg_id;//등록아이디
	private String reg_dt;//등록일
	private String chg_id;//수정아이디
	private String chg_dt;//수정일
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getSave_folder_name() {
		return save_folder_name;
	}
	public void setSave_folder_name(String save_folder_name) {
		this.save_folder_name = save_folder_name;
	}
	public String getFull_file_info() {
		return full_file_info;
	}
	public void setFull_file_info(String full_file_info) {
		this.full_file_info = full_file_info;
	}
	public String getSave_file_name() {
		return save_file_name;
	}
	public void setSave_file_name(String save_file_name) {
		this.save_file_name = save_file_name;
	}
	public String getSave_file_path() {
		return save_file_path;
	}
	public void setSave_file_path(String save_file_path) {
		this.save_file_path = save_file_path;
	}
	public String getOrg_file_name() {
		return org_file_name;
	}
	public void setOrg_file_name(String org_file_name) {
		this.org_file_name = org_file_name;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getExt() {
		return ext;
	}
	public void setExt(String ext) {
		this.ext = ext;
	}
	public String getReg_id() {
		return reg_id;
	}
	public void setReg_id(String reg_id) {
		this.reg_id = reg_id;
	}
	public String getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(String reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getChg_id() {
		return chg_id;
	}
	public void setChg_id(String chg_id) {
		this.chg_id = chg_id;
	}
	public String getChg_dt() {
		return chg_dt;
	}
	public void setChg_dt(String chg_dt) {
		this.chg_dt = chg_dt;
	}

}
