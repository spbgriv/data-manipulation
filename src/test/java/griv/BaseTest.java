package griv;

import griv.model.SitesResource;
import griv.service.DirectoryScannerService;
import griv.service.ServiceFactory;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class BaseTest {

    @Test
    public void testCount() {
        final DirectoryScannerService directoryScannerService = ServiceFactory.serviceFactory().getDirectoryScannerService();
        final List<SitesResource> sitesResources = directoryScannerService.readFiles("input");

        Assert.assertEquals(sitesResources.size(), 2);
    }
}
