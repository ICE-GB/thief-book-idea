package com.thief.idea.pojo.param;

public class GetBookContentParam{
	private Integer index;
	private String url;

	public GetBookContentParam() {
	}

	public Integer getIndex() {
		return this.index;
	}

	public String getUrl() {
		return this.url;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean equals(final Object o) {
		if (o == this) return true;
		if (!(o instanceof GetBookContentParam)) return false;
		final GetBookContentParam other = (GetBookContentParam) o;
		if (!other.canEqual((Object) this)) return false;
		final Object this$index = this.getIndex();
		final Object other$index = other.getIndex();
		if (this$index == null ? other$index != null : !this$index.equals(other$index)) return false;
		final Object this$url = this.getUrl();
		final Object other$url = other.getUrl();
		if (this$url == null ? other$url != null : !this$url.equals(other$url)) return false;
		return true;
	}

	protected boolean canEqual(final Object other) {
		return other instanceof GetBookContentParam;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $index = this.getIndex();
		result = result * PRIME + ($index == null ? 43 : $index.hashCode());
		final Object $url = this.getUrl();
		result = result * PRIME + ($url == null ? 43 : $url.hashCode());
		return result;
	}

	public String toString() {
		return "GetBookContentParam(index=" + this.getIndex() + ", url=" + this.getUrl() + ")";
	}
}