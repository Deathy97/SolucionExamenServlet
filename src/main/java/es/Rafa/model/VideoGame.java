package es.Rafa.model;

import java.sql.Date;

public class VideoGame {

	private Integer id;
	private Integer pegi;
	private Date releaseDate;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
