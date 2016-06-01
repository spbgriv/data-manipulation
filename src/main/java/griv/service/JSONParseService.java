package griv.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import griv.model.Site;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class JSONParseService {

    private final Logger log = LoggerFactory.getLogger(JSONParseService.class);


    public List<Site> readJsonFile(final File file) {

        final KeywordService keywordService = ServiceFactory.serviceFactory().getKeywordService();
        final List<Site> sites = new ArrayList<>();
        final ObjectMapper mapper = new ObjectMapper();

        try {
            final Site[] siteArray = mapper.readValue(file, Site[].class);
            for (Site site : siteArray) {
                site.setKeywords(keywordService.resolveKeywords(site));
                sites.add(site);
            }
        } catch (IOException e) {
            log.error("parse json file error", e);
        }

        return sites;

    }

}