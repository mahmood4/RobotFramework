package pojoClasses;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BookingDetails {
	
	@JsonProperty
	private String title;
	
	@JsonProperty
	private String author;

	@JsonProperty
	private String id;


	@JsonProperty
	private String createdat;

	public String gettitle(String title) {
		return this.title;
	}
	
	public void settitle(String title) {
		this.title = title;
	}

	public String getId(String id) {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}
	
	public String getAuthor(String author) {
		return this.author;
	}
	
	public void setAuthor(String author) {
		this.author = author;
	}


	public String getCreatedat(String createdat) {
		return this.createdat;
	}

	public void setcreatedat(String createdat) {
		this.createdat = createdat;
	}

}
