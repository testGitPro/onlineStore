package org.vhrybyniuk.store.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class PageGenerator {
    private static final String PACKAGE_HTML = "src/main/resources/src";

    private static PageGenerator pageGenerator;
    private final Configuration configuration;

    public static PageGenerator generate() {
        if (pageGenerator == null)
            pageGenerator = new PageGenerator();
        return pageGenerator;
    }

    public String getPage(String filename, Map<String, Object> data) {
        Writer stream = new StringWriter();
        try {
            Template template = configuration.getTemplate(PACKAGE_HTML + File.separator + filename);
            data.replaceAll((key, value) -> value == null ? "" : value);

            template.process(data, stream);
        } catch (IOException | TemplateException e) {
            throw new RuntimeException(e);
        }
        return stream.toString();
    }

    public String getPage(String filename) {
        return getPagePath(Paths.get(PACKAGE_HTML, filename));
    }
    public String getPagePath(Path path) {
        String result = "";

        try {
            result = new String(Files.readAllBytes(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
    private PageGenerator() {
        configuration = new Configuration();
    }
}
