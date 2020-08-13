package com.todotresde.interbanking.stockoption.model;

/**
 * The type File info.
 */
public class FileInfo {
    private String name;
    private String url;

    /**
     * Instantiates a new File info.
     *
     * @param name the name
     * @param url  the url
     */
    public FileInfo(String name, String url) {
        this.name = name;
        this.url = url;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets url.
     *
     * @return the url
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * Sets url.
     *
     * @param url the url
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
