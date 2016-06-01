package griv.service;

public class ServiceFactory {


    private static ServiceFactory instance = new ServiceFactory();

    final private KeywordService randomResourceStringKeywordService = new RandomResourceStringKeywordService();
    final private CSVParseService csvParseService = new CSVParseService();
    final private JSONParseService jsonParseService = new JSONParseService();
    final private DirectoryScannerService directoryScannerService = new DirectoryScannerService();

    private ServiceFactory() {
    }

    public static ServiceFactory serviceFactory() {
        return instance;
    }

    public KeywordService getKeywordService() {
        return randomResourceStringKeywordService;
    }

    public CSVParseService getCsvParseService() {
        return csvParseService;
    }

    public JSONParseService getJsonParseService() {
        return jsonParseService;
    }

    public DirectoryScannerService getDirectoryScannerService() {
        return directoryScannerService;
    }
}
