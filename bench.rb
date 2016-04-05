require 'nokogiri'

Dir.glob("/Users/sushi/Research/tie/html/test_files/*.html") do |f|
  content = IO.read(f)
  doc = Nokogiri::HTML(content)
  list = []
  doc.css('a').each do |e|
    list << e.to_s
  end
  IO.write(f.sub("test_files", "mri_output"), list.join("\n"))
end
