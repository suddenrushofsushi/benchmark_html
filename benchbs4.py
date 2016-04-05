import glob
import codecs
import json
import os
import http.client
import socket
import urllib.parse
import urllib.request
import urllib.error
import re
import sys
import hashlib
import snappy
import psutil
import multiprocessing
from bs4 import BeautifulSoup, Comment

for item in glob.glob("./test_files/*.html"):
    f = open(item, 'r', encoding='utf8')
    html_content = f.read()
    clean_html = html_content #re.sub('</?o:(p|style|div|st\d|!\[)[^>]*?>', '', html_content)
    soup = BeautifulSoup(clean_html, "lxml")
    links = soup.find_all('a')
    anchors = ""
    for link in links:
        anchors = anchors + str(link) + "\n"

    new_path = item.replace("test_files", "python_output")
    output = codecs.open(new_path, mode='wb')
    output.write(bytes(anchors, 'UTF-8'))
    output.close

