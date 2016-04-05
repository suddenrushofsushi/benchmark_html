fs = require('fs');
cheerio = require('cheerio');
var path = "/Users/sushi/Research/tie/html/test_files"
fs.readdir(path, function(err, items) {
    for (var i=0; i<items.length; i++) {
      var fp = path + "/" + items[i];
      var file = fs.readFileSync(fp, "utf8");
      var $ = cheerio.load(file);
      fun_times = $.html("a");
      new_path = fp.replace("test_files", "node_output");
      fs.writeFileSync(new_path, fun_times, "utf8");
    }
});
