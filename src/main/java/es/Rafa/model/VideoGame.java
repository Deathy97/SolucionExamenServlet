package es.Rafa.model;

import java.sql.Date;

public class VideoGame {

	private String tittle;
	private Integer pegi;
	private Date releaseDate;

	public String getTittle() {
		return tittle;
	}

	public void setTittle(String tittle) {
		this.tittle = tittle;
	}

	public Integer getPegi() {
		return pegi;
	}

	public void setPegi(Integer pegi) {
		this.pegi = pegi;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

}
