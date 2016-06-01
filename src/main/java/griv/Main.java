package griv;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import griv.model.SitesResource;
import griv.service.DirectoryScannerService;
import griv.service.ServiceFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {

    private final static Logger log = LoggerFactory.getLogger(Main.class);

    public static final String OUTPUT_JSON_DEFAULT_FILE_NAME = "output.json";

    public static void main(String[] args) {
        final DirectoryScannerService directoryScannerService = ServiceFactory.serviceFactory().getDirectoryScannerService();

        if (args.length == 0 || Strings.isNullOrEmpty(args[0])) {
            log.error("no path to directory");
            System.exit(1);
        }

        final String pathToDirectory = args[0];
        final String outputFileName = (args.length > 1) ? args[1] : OUTPUT_JSON_DEFAULT_FILE_NAME;

        final List<SitesResource> sitesResources = directoryScannerService.readFiles(pathToDirectory);

        if (sitesResources.size() == 0) {
            log.warn("no data for writing");
            System.exit(0);
        }

        final ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(outputFileName), sitesResources);
        } catch (IOException e) {
            log.error("write to file {} error", outputFileName, e);
        }


    }
}
