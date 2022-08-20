package com.thief.idea.pojo.vo;

public class Book {
    private Long latestChapterTime;
    private String origin;
    private String latestChapterTitle;
    private Integer type;
    private Integer durChapterIndex;
    private Integer totalChapterNum;
    private Boolean useReplaceRule;
    private Integer durChapterPos;
    private String intro;
    private Integer lastCheckCount;
    private String bookUrl;
    private Integer group;
    private Integer order;
    private String durChapterTitle;
    private Boolean canUpdate;
    private Long durChapterTime;
    private String wordCount;
    private String author;
    private String kind;
    private String coverUrl;
    private Long lastCheckTime;
    private Integer originOrder;
    private String name;
    private String tocUrl;
    private String originName;

    public Book() {
    }

    public Long getLatestChapterTime() {
        return this.latestChapterTime;
    }

    public String getOrigin() {
        return this.origin;
    }

    public String getLatestChapterTitle() {
        return this.latestChapterTitle;
    }

    public Integer getType() {
        return this.type;
    }

    public Integer getDurChapterIndex() {
        return this.durChapterIndex;
    }

    public Integer getTotalChapterNum() {
        return this.totalChapterNum;
    }

    public Boolean getUseReplaceRule() {
        return this.useReplaceRule;
    }

    public Integer getDurChapterPos() {
        return this.durChapterPos;
    }

    public String getIntro() {
        return this.intro;
    }

    public Integer getLastCheckCount() {
        return this.lastCheckCount;
    }

    public String getBookUrl() {
        return this.bookUrl;
    }

    public Integer getGroup() {
        return this.group;
    }

    public Integer getOrder() {
        return this.order;
    }

    public String getDurChapterTitle() {
        return this.durChapterTitle;
    }

    public Boolean getCanUpdate() {
        return this.canUpdate;
    }

    public Long getDurChapterTime() {
        return this.durChapterTime;
    }

    public String getWordCount() {
        return this.wordCount;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getKind() {
        return this.kind;
    }

    public String getCoverUrl() {
        return this.coverUrl;
    }

    public Long getLastCheckTime() {
        return this.lastCheckTime;
    }

    public Integer getOriginOrder() {
        return this.originOrder;
    }

    public String getName() {
        return this.name;
    }

    public String getTocUrl() {
        return this.tocUrl;
    }

    public String getOriginName() {
        return this.originName;
    }

    public void setLatestChapterTime(Long latestChapterTime) {
        this.latestChapterTime = latestChapterTime;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public void setLatestChapterTitle(String latestChapterTitle) {
        this.latestChapterTitle = latestChapterTitle;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public void setDurChapterIndex(Integer durChapterIndex) {
        this.durChapterIndex = durChapterIndex;
    }

    public void setTotalChapterNum(Integer totalChapterNum) {
        this.totalChapterNum = totalChapterNum;
    }

    public void setUseReplaceRule(Boolean useReplaceRule) {
        this.useReplaceRule = useReplaceRule;
    }

    public void setDurChapterPos(Integer durChapterPos) {
        this.durChapterPos = durChapterPos;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public void setLastCheckCount(Integer lastCheckCount) {
        this.lastCheckCount = lastCheckCount;
    }

    public void setBookUrl(String bookUrl) {
        this.bookUrl = bookUrl;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public void setDurChapterTitle(String durChapterTitle) {
        this.durChapterTitle = durChapterTitle;
    }

    public void setCanUpdate(Boolean canUpdate) {
        this.canUpdate = canUpdate;
    }

    public void setDurChapterTime(Long durChapterTime) {
        this.durChapterTime = durChapterTime;
    }

    public void setWordCount(String wordCount) {
        this.wordCount = wordCount;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setLastCheckTime(Long lastCheckTime) {
        this.lastCheckTime = lastCheckTime;
    }

    public void setOriginOrder(Integer originOrder) {
        this.originOrder = originOrder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTocUrl(String tocUrl) {
        this.tocUrl = tocUrl;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof Book)) return false;
        final Book other = (Book) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$latestChapterTime = this.getLatestChapterTime();
        final Object other$latestChapterTime = other.getLatestChapterTime();
        if (this$latestChapterTime == null ? other$latestChapterTime != null : !this$latestChapterTime.equals(other$latestChapterTime))
            return false;
        final Object this$origin = this.getOrigin();
        final Object other$origin = other.getOrigin();
        if (this$origin == null ? other$origin != null : !this$origin.equals(other$origin)) return false;
        final Object this$latestChapterTitle = this.getLatestChapterTitle();
        final Object other$latestChapterTitle = other.getLatestChapterTitle();
        if (this$latestChapterTitle == null ? other$latestChapterTitle != null : !this$latestChapterTitle.equals(other$latestChapterTitle))
            return false;
        final Object this$type = this.getType();
        final Object other$type = other.getType();
        if (this$type == null ? other$type != null : !this$type.equals(other$type)) return false;
        final Object this$durChapterIndex = this.getDurChapterIndex();
        final Object other$durChapterIndex = other.getDurChapterIndex();
        if (this$durChapterIndex == null ? other$durChapterIndex != null : !this$durChapterIndex.equals(other$durChapterIndex))
            return false;
        final Object this$totalChapterNum = this.getTotalChapterNum();
        final Object other$totalChapterNum = other.getTotalChapterNum();
        if (this$totalChapterNum == null ? other$totalChapterNum != null : !this$totalChapterNum.equals(other$totalChapterNum))
            return false;
        final Object this$useReplaceRule = this.getUseReplaceRule();
        final Object other$useReplaceRule = other.getUseReplaceRule();
        if (this$useReplaceRule == null ? other$useReplaceRule != null : !this$useReplaceRule.equals(other$useReplaceRule))
            return false;
        final Object this$durChapterPos = this.getDurChapterPos();
        final Object other$durChapterPos = other.getDurChapterPos();
        if (this$durChapterPos == null ? other$durChapterPos != null : !this$durChapterPos.equals(other$durChapterPos))
            return false;
        final Object this$intro = this.getIntro();
        final Object other$intro = other.getIntro();
        if (this$intro == null ? other$intro != null : !this$intro.equals(other$intro)) return false;
        final Object this$lastCheckCount = this.getLastCheckCount();
        final Object other$lastCheckCount = other.getLastCheckCount();
        if (this$lastCheckCount == null ? other$lastCheckCount != null : !this$lastCheckCount.equals(other$lastCheckCount))
            return false;
        final Object this$bookUrl = this.getBookUrl();
        final Object other$bookUrl = other.getBookUrl();
        if (this$bookUrl == null ? other$bookUrl != null : !this$bookUrl.equals(other$bookUrl)) return false;
        final Object this$group = this.getGroup();
        final Object other$group = other.getGroup();
        if (this$group == null ? other$group != null : !this$group.equals(other$group)) return false;
        final Object this$order = this.getOrder();
        final Object other$order = other.getOrder();
        if (this$order == null ? other$order != null : !this$order.equals(other$order)) return false;
        final Object this$durChapterTitle = this.getDurChapterTitle();
        final Object other$durChapterTitle = other.getDurChapterTitle();
        if (this$durChapterTitle == null ? other$durChapterTitle != null : !this$durChapterTitle.equals(other$durChapterTitle))
            return false;
        final Object this$canUpdate = this.getCanUpdate();
        final Object other$canUpdate = other.getCanUpdate();
        if (this$canUpdate == null ? other$canUpdate != null : !this$canUpdate.equals(other$canUpdate)) return false;
        final Object this$durChapterTime = this.getDurChapterTime();
        final Object other$durChapterTime = other.getDurChapterTime();
        if (this$durChapterTime == null ? other$durChapterTime != null : !this$durChapterTime.equals(other$durChapterTime))
            return false;
        final Object this$wordCount = this.getWordCount();
        final Object other$wordCount = other.getWordCount();
        if (this$wordCount == null ? other$wordCount != null : !this$wordCount.equals(other$wordCount)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        final Object this$kind = this.getKind();
        final Object other$kind = other.getKind();
        if (this$kind == null ? other$kind != null : !this$kind.equals(other$kind)) return false;
        final Object this$coverUrl = this.getCoverUrl();
        final Object other$coverUrl = other.getCoverUrl();
        if (this$coverUrl == null ? other$coverUrl != null : !this$coverUrl.equals(other$coverUrl)) return false;
        final Object this$lastCheckTime = this.getLastCheckTime();
        final Object other$lastCheckTime = other.getLastCheckTime();
        if (this$lastCheckTime == null ? other$lastCheckTime != null : !this$lastCheckTime.equals(other$lastCheckTime))
            return false;
        final Object this$originOrder = this.getOriginOrder();
        final Object other$originOrder = other.getOriginOrder();
        if (this$originOrder == null ? other$originOrder != null : !this$originOrder.equals(other$originOrder))
            return false;
        final Object this$name = this.getName();
        final Object other$name = other.getName();
        if (this$name == null ? other$name != null : !this$name.equals(other$name)) return false;
        final Object this$tocUrl = this.getTocUrl();
        final Object other$tocUrl = other.getTocUrl();
        if (this$tocUrl == null ? other$tocUrl != null : !this$tocUrl.equals(other$tocUrl)) return false;
        final Object this$originName = this.getOriginName();
        final Object other$originName = other.getOriginName();
        if (this$originName == null ? other$originName != null : !this$originName.equals(other$originName))
            return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Book;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $latestChapterTime = this.getLatestChapterTime();
        result = result * PRIME + ($latestChapterTime == null ? 43 : $latestChapterTime.hashCode());
        final Object $origin = this.getOrigin();
        result = result * PRIME + ($origin == null ? 43 : $origin.hashCode());
        final Object $latestChapterTitle = this.getLatestChapterTitle();
        result = result * PRIME + ($latestChapterTitle == null ? 43 : $latestChapterTitle.hashCode());
        final Object $type = this.getType();
        result = result * PRIME + ($type == null ? 43 : $type.hashCode());
        final Object $durChapterIndex = this.getDurChapterIndex();
        result = result * PRIME + ($durChapterIndex == null ? 43 : $durChapterIndex.hashCode());
        final Object $totalChapterNum = this.getTotalChapterNum();
        result = result * PRIME + ($totalChapterNum == null ? 43 : $totalChapterNum.hashCode());
        final Object $useReplaceRule = this.getUseReplaceRule();
        result = result * PRIME + ($useReplaceRule == null ? 43 : $useReplaceRule.hashCode());
        final Object $durChapterPos = this.getDurChapterPos();
        result = result * PRIME + ($durChapterPos == null ? 43 : $durChapterPos.hashCode());
        final Object $intro = this.getIntro();
        result = result * PRIME + ($intro == null ? 43 : $intro.hashCode());
        final Object $lastCheckCount = this.getLastCheckCount();
        result = result * PRIME + ($lastCheckCount == null ? 43 : $lastCheckCount.hashCode());
        final Object $bookUrl = this.getBookUrl();
        result = result * PRIME + ($bookUrl == null ? 43 : $bookUrl.hashCode());
        final Object $group = this.getGroup();
        result = result * PRIME + ($group == null ? 43 : $group.hashCode());
        final Object $order = this.getOrder();
        result = result * PRIME + ($order == null ? 43 : $order.hashCode());
        final Object $durChapterTitle = this.getDurChapterTitle();
        result = result * PRIME + ($durChapterTitle == null ? 43 : $durChapterTitle.hashCode());
        final Object $canUpdate = this.getCanUpdate();
        result = result * PRIME + ($canUpdate == null ? 43 : $canUpdate.hashCode());
        final Object $durChapterTime = this.getDurChapterTime();
        result = result * PRIME + ($durChapterTime == null ? 43 : $durChapterTime.hashCode());
        final Object $wordCount = this.getWordCount();
        result = result * PRIME + ($wordCount == null ? 43 : $wordCount.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        final Object $kind = this.getKind();
        result = result * PRIME + ($kind == null ? 43 : $kind.hashCode());
        final Object $coverUrl = this.getCoverUrl();
        result = result * PRIME + ($coverUrl == null ? 43 : $coverUrl.hashCode());
        final Object $lastCheckTime = this.getLastCheckTime();
        result = result * PRIME + ($lastCheckTime == null ? 43 : $lastCheckTime.hashCode());
        final Object $originOrder = this.getOriginOrder();
        result = result * PRIME + ($originOrder == null ? 43 : $originOrder.hashCode());
        final Object $name = this.getName();
        result = result * PRIME + ($name == null ? 43 : $name.hashCode());
        final Object $tocUrl = this.getTocUrl();
        result = result * PRIME + ($tocUrl == null ? 43 : $tocUrl.hashCode());
        final Object $originName = this.getOriginName();
        result = result * PRIME + ($originName == null ? 43 : $originName.hashCode());
        return result;
    }

    public String toString() {
        return "Book(latestChapterTime=" + this.getLatestChapterTime() + ", origin=" + this.getOrigin() + ", latestChapterTitle=" + this.getLatestChapterTitle() + ", type=" + this.getType() + ", durChapterIndex=" + this.getDurChapterIndex() + ", totalChapterNum=" + this.getTotalChapterNum() + ", useReplaceRule=" + this.getUseReplaceRule() + ", durChapterPos=" + this.getDurChapterPos() + ", intro=" + this.getIntro() + ", lastCheckCount=" + this.getLastCheckCount() + ", bookUrl=" + this.getBookUrl() + ", group=" + this.getGroup() + ", order=" + this.getOrder() + ", durChapterTitle=" + this.getDurChapterTitle() + ", canUpdate=" + this.getCanUpdate() + ", durChapterTime=" + this.getDurChapterTime() + ", wordCount=" + this.getWordCount() + ", author=" + this.getAuthor() + ", kind=" + this.getKind() + ", coverUrl=" + this.getCoverUrl() + ", lastCheckTime=" + this.getLastCheckTime() + ", originOrder=" + this.getOriginOrder() + ", name=" + this.getName() + ", tocUrl=" + this.getTocUrl() + ", originName=" + this.getOriginName() + ")";
    }
}