package com.thief.idea.pojo.param;

public class GetBookChapterListParam{
	private Integer refresh;
	private String url;

	public GetBookChapterListParam() {
	}

	public Integer getRefresh() {
		return this.refresh;
	}

	public String getUrl() {
		return this.url;
	}

	public void setRefresh(Integer refresh) {
		this.refresh = refresh;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof GetBookChapterListParam)) return false;
		final GetBookChapterListParam other = (GetBookChapterListParam) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$refresh = this.getRefresh();
		final Object other$refresh = other.getRefresh();
		if (this$refresh == null ? other$refresh != null : !this$refresh.equals(other$refresh)) return false;
		final Object this$url = this.getUrl();
		final Object other$url = other.getUrl();
		if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof GetBookChapterListParam;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $refresh = this.getRefresh();
		result = result * PRIME + ($refresh == null ? 43 : $refresh.hashCode());
		final Object $url = this.getUrl();
		result = result * PRIME + ($url == null ? 43 : $url.hashCode());
		return result;
	}

	public String toString() {
		return "GetBookChapterListParam(refresh=" + this.getRefresh() + ", url=" + this.getUrl() + ")";
	}
}