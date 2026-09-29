package staffAm.businessPage;

import java.util.Objects;

public class JobAnnouncement {
    private String title;
    private String companyName;
    private String location;
    private String date;

    public JobAnnouncement(String title, String companyName, String location, String date) {
        this.title = title;
        this.companyName = companyName;
        this.location = location;
        this.date = date;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCompanyName() {
        return companyName;
    }

    public String getTitle() {
        return title;
    }

    public String getLocation() {
        return location;
    }

    public String getDate() {
        return date;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        JobAnnouncement that = (JobAnnouncement) o;
        return Objects.equals(title, that.title) &&
                Objects.equals(companyName, that.companyName) &&
                Objects.equals(location, that.location) && Objects.equals(date, that.date);
    }

}
