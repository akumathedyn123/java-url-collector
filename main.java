import java.io.*;
import java.net.*;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.*;
import org.jsoup.select.Elements;

public class ExtractLinks {
    
    public static void main(String[] args) {
        String input = "urls.txt";
        String outputDir = "/path/to/text";
        ArrayList<String> urls = readUrlsFromFile(input);
        int numCopied = 0;
        for (String url : urls) {
            String htmlContent = downloadHtml(url);
            String links = extractLinks(htmlContent);
            saveLinksToFile(links, url, outputDir);
            deleteUrl(url, input);
            numCopied++;
            System.out.printf("Links from %s have been copied%n", url);
        }
        System.out.printf("Total %d links copied and removed from input file%n", numCopied);
    }
    
    public static ArrayList<String> readUrlsFromFile(String filename) {
        ArrayList<String> urls = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                urls.add(line.trim());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return urls;
    }
    
    public static String downloadHtml(String url) {
        StringBuilder htmlContent = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) urlObj.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                htmlContent.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return htmlContent.toString();
    }
    
    public static String extractLinks(String htmlContent) {
        StringBuilder links = new StringBuilder();
        Document doc = Jsoup.parse(htmlContent);
        Elements aTags = doc.select("a[href]");
        for (Element aTag : aTags) {
            links.append(aTag.attr("href")).append("\n");
        }
        return links.toString();
    }
    
    public static void saveLinksToFile(String links, String url, String outputDir) {
        String pageName = url.substring(url.lastIndexOf('/') + 1);
        String filename = outputDir + "/URLs_" + pageName + "_" + url.replaceAll("[/:]", "_") + ".txt";
        File file = new File(filename);
        file.getParentFile().mkdirs();
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.write(links);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void deleteUrl(String url, String input) {
        ArrayList<String> urls = readUrlsFromFile(input);
        try (PrintWriter writer = new PrintWriter(new FileWriter(input))) {
            for (String line : urls) {
                if (!line.trim().equals(url)) {
                    writer.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}