package br.com.poc.utils;

public class Link {

	private String url;
	private String rel;
	private String title;

	public Link(String url, String rel, String title) {
		this.url = url;
		this.rel = rel;
		this.title = title;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		final StringBuilder builder = new StringBuilder("<");
		
		builder.append(this.url)
			   .append(">;")
			   .append(" rel=\"")
			   .append(this.rel)
			   .append("\"");
		
		return builder.toString();
	}
}