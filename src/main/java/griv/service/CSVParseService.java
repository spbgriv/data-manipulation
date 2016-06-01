package griv.service;

import griv.model.Site;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class CSVParseService {

    private final Logger log = LoggerFactory.getLogger(CSVParseService.class);

    private static final String SITE_ID = "id";
    private static final String SITE_NAME = "name";
    private static final String SITE_IS_MOBILE = "is mobile";
    private static final String SITE_SCORE = "score";

    //CSV file header
    private static final String[] FILE_HEADER_MAPPING = {SITE_ID, SITE_NAME, SITE_IS_MOBILE, SITE_SCORE};


    public List<Site> readCsvFile(final File file) {

        final KeywordService keywordService = ServiceFactory.serviceFactory().getKeywordService();
        final List<Site> sites = new ArrayList<>();

        //Create the CSVFormat object with the header mapping
        final CSVFormat csvFileFormat = CSVFormat.DEFAULT.withHeader(FILE_HEADER_MAPPING);

        try (
                FileReader fileReader = new FileReader(file);
                CSVParser csvFileParser = new CSVParser(fileReader, csvFileFormat);
        ) {

            //Get a list of CSV file records
            List<CSVRecord> csvRecords = csvFileParser.getRecords();

            //Read the CSV file records starting from the second record to skip the header
            for (int i = 1; i < csvRecords.size(); i++) {
                final CSVRecord record = csvRecords.get(i);
                final Site site = new Site(Long.parseLong(record.get(SITE_ID)), record.get(SITE_NAME), Boolean.parseBoolean(record.get(SITE_IS_MOBILE)), Double.parseDouble(record.get(SITE_SCORE)));
                site.setKeywords(keywordService.resolveKeywords(site));
                sites.add(site);
            }
        } catch (Exception e) {
            log.error("parse csv file error", e);
        }

        return sites;

    }

}