package main
import (
  "os"
  "bytes"
  "io/ioutil"
  "path/filepath"
  "github.com/PuerkitoBio/goquery"
  "log"
  "strings"
)

func main() {
  files_path := "/Users/sushi/Research/tie/html/test_files"
  files, _ := ioutil.ReadDir(files_path)
  for _, f := range files {
    where := filepath.Join(files_path, f.Name())
    file, _ := os.Open(where)
    doc, err := goquery.NewDocumentFromReader(file)
    if err != nil {
      log.Fatal(err)
    } else {
      var buf bytes.Buffer
      doc.Find("a").Each(func(i int, sel *goquery.Selection) {
        str, _ := goquery.OuterHtml(sel)
        buf.WriteString(str + "\n")
      });
      ioutil.WriteFile(strings.Replace(where, "test_files", "go_output", -1), buf.Bytes(), 0644)
    }
  }
}
