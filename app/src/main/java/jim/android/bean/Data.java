package jim.android.bean;

import java.util.List;

/**
 * Created by Jim Huang on 2015/9/7.
 */
public class Data {
    private Integer count;
    private Integer num_pages;
    private Integer current_page;
    private List<Results> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getNum_pages() {
        return num_pages;
    }

    public void setNum_pages(Integer num_pages) {
        this.num_pages = num_pages;
    }

    public Integer getCurrent_page() {
        return current_page;
    }

    public void setCurrent_page(Integer current_page) {
        this.current_page = current_page;
    }

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }
}
