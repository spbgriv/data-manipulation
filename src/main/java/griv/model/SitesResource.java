package griv.model;

import java.util.List;

public class SitesResource {

    private final String collectionId;

    private final List<Site> sites;

    public SitesResource(String collectionId, List<Site> sites) {
        this.collectionId = collectionId;
        this.sites = sites;
    }

    public String getCollectionId() {
        return collectionId;
    }

    public List<Site> getSites() {
        return sites;
    }
}
