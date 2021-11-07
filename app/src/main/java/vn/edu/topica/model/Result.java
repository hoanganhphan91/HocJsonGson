package vn.edu.topica.model;

public class Result {
    private String url;
    private String titleNoformating;

    public Result(String url, String titleNoformating) {
        this.url = url;
        this.titleNoformating = titleNoformating;
    }

    @Override
    public String toString() {
        return this.url+ "\n"+this.titleNoformating;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitleNoformating() {
        return titleNoformating;
    }

    public void setTitleNoformating(String titleNoformating) {
        this.titleNoformating = titleNoformating;
    }
}
