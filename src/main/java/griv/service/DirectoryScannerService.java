package griv.service;

import griv.model.SitesResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DirectoryScannerService {

    final public static String CSV = "csv";
    final public static String JSON = "json";

    private final Logger log = LoggerFactory.getLogger(DirectoryScannerService.class);

    public List<SitesResource> readFiles(final String pathToDirectory) {

        final List<SitesResource> sitesResources = new ArrayList<>();
        final CSVParseService csvParseService = ServiceFactory.serviceFactory().getCsvParseService();
        final JSONParseService jsonParseService = ServiceFactory.serviceFactory().getJsonParseService();

        try {
            Files.walk(Paths.get(pathToDirectory)).forEach(filePath -> {
                if (Files.isRegularFile(filePath)) {
                    final File file = filePath.toFile();
                    final String extension = com.google.common.io.Files.getFileExtension(file.getName()).toLowerCase();
                    switch (extension) {
                        case CSV:
                            sitesResources.add(new SitesResource(file.getName(), csvParseService.readCsvFile(file)));
                            break;
                        case JSON:
                            sitesResources.add(new SitesResource(file.getName(), jsonParseService.readJsonFile(file)));
                            break;
                        default:
                            log.info("unsupported file {}", file.getName());
                    }
                }
            });
        } catch (IOException e) {
            log.error("load files from path {} error", pathToDirectory, e);
        }

        return sitesResources;
    }
}
