package com.thief.idea.pojo.vo;

public class BookChapter{
	private String baseUrl;
	private Boolean isVolume;
	private Integer index;
	private String tag;
	private String title;
	private String bookUrl;
	private String url;

	public BookChapter() {
	}

	public String getBaseUrl() {
		return this.baseUrl;
	}

	public Boolean getIsVolume() {
		return this.isVolume;
	}

	public Integer getIndex() {
		return this.index;
	}

	public String getTag() {
		return this.tag;
	}

	public String getTitle() {
		return this.title;
	}

	public String getBookUrl() {
		return this.bookUrl;
	}

	public String getUrl() {
		return this.url;
	}

	public void setBaseUrl(String baseUrl) {
		this.baseUrl = baseUrl;
	}

	public void setIsVolume(Boolean isVolume) {
		this.isVolume = isVolume;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setBookUrl(String bookUrl) {
		this.bookUrl = bookUrl;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof BookChapter)) return false;
		final BookChapter other = (BookChapter) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$baseUrl = this.getBaseUrl();
		final Object other$baseUrl = other.getBaseUrl();
		if (this$baseUrl == null ? other$baseUrl != null : !this$baseUrl.equals(other$baseUrl)) return false;
		final Object this$isVolume = this.getIsVolume();
		final Object other$isVolume = other.getIsVolume();
		if (this$isVolume == null ? other$isVolume != null : !this$isVolume.equals(other$isVolume)) return false;
		final Object this$index = this.getIndex();
		final Object other$index = other.getIndex();
		if (this$index == null ? other$index != null : !this$index.equals(other$index)) return false;
		final Object this$tag = this.getTag();
		final Object other$tag = other.getTag();
		if (this$tag == null ? other$tag != null : !this$tag.equals(other$tag)) return false;
		final Object this$title = this.getTitle();
		final Object other$title = other.getTitle();
		if (this$title == null ? other$title != null : !this$title.equals(other$title)) return false;
		final Object this$bookUrl = this.getBookUrl();
		final Object other$bookUrl = other.getBookUrl();
		if (this$bookUrl == null ? other$bookUrl != null : !this$bookUrl.equals(other$bookUrl)) return false;
		final Object this$url = this.getUrl();
		final Object other$url = other.getUrl();
		if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof BookChapter;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $baseUrl = this.getBaseUrl();
		result = result * PRIME + ($baseUrl == null ? 43 : $baseUrl.hashCode());
		final Object $isVolume = this.getIsVolume();
		result = result * PRIME + ($isVolume == null ? 43 : $isVolume.hashCode());
		final Object $index = this.getIndex();
		result = result * PRIME + ($index == null ? 43 : $index.hashCode());
		final Object $tag = this.getTag();
		result = result * PRIME + ($tag == null ? 43 : $tag.hashCode());
		final Object $title = this.getTitle();
		result = result * PRIME + ($title == null ? 43 : $title.hashCode());
		final Object $bookUrl = this.getBookUrl();
		result = result * PRIME + ($bookUrl == null ? 43 : $bookUrl.hashCode());
		final Object $url = this.getUrl();
		result = result * PRIME + ($url == null ? 43 : $url.hashCode());
		return result;
	}

	public String toString() {
		return "BookChapter(baseUrl=" + this.getBaseUrl() + ", isVolume=" + this.getIsVolume() + ", index=" + this.getIndex() + ", tag=" + this.getTag() + ", title=" + this.getTitle() + ", bookUrl=" + this.getBookUrl() + ", url=" + this.getUrl() + ")";
	}
}