import org.jsoup.*
import org.jsoup.nodes.*

buf = new StringBuilder();
new File("/Users/sushi/Research/tie/html/test_files").eachFile() { file ->
  buf.setLength(0)
  new_path = file.getAbsolutePath().replace("test_files", "groovy_output")
  content = file.getText('UTF-8')
  doc = Jsoup.parse(content);
  doc.outputSettings(new Document.OutputSettings().prettyPrint(false));
  links = doc.select("a");
  links.each { a ->
    buf << a.toString() //.append(a.toString());
    buf << "\n" //.append("\n");
  }
  f = new File(new_path)
  f.write(buf.toString(), "UTF-8")
}
