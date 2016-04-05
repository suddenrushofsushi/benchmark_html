import java.io.*;
import java.io.IOException;
import java.util.Collection;
import java.util.Iterator;
import org.jsoup.Jsoup;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.nio.charset.StandardCharsets;
import java.nio.charset.Charset;
import java.nio.file.Paths;
import java.nio.file.Files;

public class Soupy {
  public static void main(String args[]) throws IOException {
    File dir = new File("/Users/sushi/Research/tie/html/test_files");
    System.out.println(dir.toString());
    File[] directoryListing = dir.listFiles();
    StringBuilder buf = new StringBuilder();
    String path;
    Document doc;
    Elements links;
    if (directoryListing != null) {
      for (File child : directoryListing) {
        buf.setLength(0);
        path = child.getAbsolutePath();
        doc = Jsoup.parse(readFile(path, StandardCharsets.UTF_8));
        doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
        links = doc.select("a");
        for(Element a : links) {
          buf.append(a.toString());
          buf.append("\n");
        }
        String new_path = path.replace("test_files", "java_output");
        Files.write(Paths.get(new_path), buf.toString().getBytes());
      }
    } else {
      System.out.println("No listing");
    }
  }
  static String readFile(String path, Charset encoding) throws IOException {
    byte[] encoded = Files.readAllBytes(Paths.get(path));
    return new String(encoded, encoding);
  }
}
