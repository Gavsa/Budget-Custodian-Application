package at.htl.budgetcustodianapplication.facades.entities;

import java.util.Date;

/**
 * Created by juliakajic on 07.01.18.
 */

public class Holiday {

    private String title;
    private Date dateFrom;
    private Date dateTo;

    public Holiday(String title, Date dateFrom, Date dateTo) {
        this.title = title;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
    }

    //region Getter and Setter
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }
    //endregion
}
