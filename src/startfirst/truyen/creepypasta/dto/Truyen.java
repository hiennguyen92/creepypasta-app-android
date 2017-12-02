package startfirst.truyen.creepypasta.dto;

import java.io.Serializable;

public class Truyen implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int _SoThuTu;
	private String _TenTruyen;
	private String _NoiDung;
	public int get_SoThuTu() {
		return _SoThuTu;
	}
	public void set_SoThuTu(int _SoThuTu) {
		this._SoThuTu = _SoThuTu;
	}
	public String get_TenTruyen() {
		return _TenTruyen;
	}
	public void set_TenTruyen(String _TenTruyen) {
		this._TenTruyen = _TenTruyen;
	}
	public String get_NoiDung() {
		return _NoiDung;
	}
	public void set_NoiDung(String _NoiDung) {
		this._NoiDung = _NoiDung;
	}

}
