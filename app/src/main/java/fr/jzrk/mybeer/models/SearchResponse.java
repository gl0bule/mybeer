package fr.jzrk.mybeer.models;

import java.util.List;

public class SearchResponse {

    private int currentPage;

    private int numberOfPages;

    private int totalResults;

    private List<Beer> data;

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Beer> getData() {
        return data;
    }

    public void setData(List<Beer> data) {
        this.data = data;
    }
}
