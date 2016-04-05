require 'nokogiri'

Dir.glob('/Users/sushi/Research/tie/html/test_files/*.html') do |f|
  content = IO.read(f)
  doc = Nokogiri::HTML(content)
  File.open(f.sub('test_files', 'mri_output'), 'w') do |of|
    doc.css('a').each do |e|
      of.write("#{e}\n")
    end
  end
end
