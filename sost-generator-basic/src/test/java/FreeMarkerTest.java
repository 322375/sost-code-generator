import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.*;

public class FreeMarkerTest  {
    @Test
    public void test() throws IOException, TemplateException {
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_32);
        configuration.setDirectoryForTemplateLoading(new File("src/main/resources/templates"));
        configuration.setDefaultEncoding("utf-8");

        Template template = configuration.getTemplate("myweb.html.ftl");

        HashMap<String, Object> dataModel = new HashMap<>();
        dataModel.put("currentYear", 2023);
        List<Map<String, Object>> menuItems = new ArrayList<>();
        Map<String, Object> menuItems1 = new HashMap<>();
        menuItems1.put("url", "http://baidu.com");
        menuItems1.put("label", "百度");
        Map<String, Object> menuItems2 = new HashMap<>();
        menuItems2.put("url", "http://google.com");
        menuItems2.put("label", "谷歌");
        menuItems.add(menuItems1);
        menuItems.add(menuItems2);
        dataModel.put("menuItems", menuItems);

        Writer out = new FileWriter("myweb.html");
        template.process(dataModel, out);
        out.close();
    }
}