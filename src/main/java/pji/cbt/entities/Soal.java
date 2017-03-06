package pji.cbt.entities;

import java.io.Serializable;

/*
 * soal entity class
 */

public class Soal implements Serializable{
	
	private int id_soal;
	private String soal;
	private String pilihan_1;
	private String pilihan_2;
	
	
	public int getId_soal() {
		return id_soal;
	}
	public void setId_soal(int id_soal) {
		this.id_soal = id_soal;
	}
	public String getSoal() {
		return soal;
	}
	public void setSoal(String soal) {
		this.soal = soal;
	}
	public String getPilihan_1() {
		return pilihan_1;
	}
	public void setPilihan_1(String pilihan_1) {
		this.pilihan_1 = pilihan_1;
	}
	public String getPilihan_2() {
		return pilihan_2;
	}
	public void setPilihan_2(String pilihan_2) {
		this.pilihan_2 = pilihan_2;
	}	
}