from bs4 import BeautifulSoup as bs
#from urllib import urlopen
import sqlite3
import requests

db=sqlite3.connect("places.db")
cursor2 = db.cursor()
#cursor2.execute("drop table placesdata")
#cursor2.execute("create table placesdata ( id integer primary key autoincrement, imgurl varchar(100), city varchar(30), placename varchar(50), descr varchar(2000) )")

def innerHTML(element):
    return element.decode_contents(formatter="html")

src=requests.get("https://www.ixigo.com/nightlife-in-jaipur-lp-1140445").text
soup=bs(src,"html.parser")
soup1=soup.find_all("div",{"class":"ne-title"})
soup2=soup.find_all("div",{"class":"ne-content"})
soup3=soup.find_all("img",{"class":"lazy"})
for j in range(len(soup1)):
	k=innerHTML(soup1[j].find("a"))
	#m=innerHTML(soup2[j].find("div",{"class":"goodFor"}))
	desc=innerHTML(soup2[j].find("div",{"class":"famousFor"}))
	n=soup3[j].get('src')
	print(n)
	item = (n, "jaipur",k, desc)
	cursor2.execute("insert into placesdata(imgurl,city,placename,descr) values(?,?,?,?)",item)
db.commit()
db.close()
#print(soup)
#soup1=soup.find("h2", {"class": "NbdpWc"})

