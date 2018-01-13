package es.Rafa.model;

import java.sql.Date;

public class VideoGame {

	private String title;
	private Integer pegi;
	private Date releaseDate;

	public String getTittle() {
		return title;
	}

	public void setTittle(String title) {
		this.title = title;
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
