package staffAm.staffAmFilters;

public enum FiltersGroupName {
    JOB_CATEGORY("Job category", "Category:"),
    SPECIALIST_LEVEL("Specialist level", "Required candidate level"),
    JOB_TERMS("Job terms", "Employment term:");

    private final String nameInJobsPage;
    private final String nameInJobsDetailsPage;

    FiltersGroupName(String nameInJobsPage, String nameInJobsDetailsPage){
        this.nameInJobsPage = nameInJobsPage;
        this.nameInJobsDetailsPage = nameInJobsDetailsPage;
    }

    public String getNameInJobsPage() {
        return nameInJobsPage;
    }

    public String getNameInJobsDetailsPage() {
        return nameInJobsDetailsPage;
    }
}
