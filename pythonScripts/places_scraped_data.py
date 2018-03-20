from bs4 import BeautifulSoup as bs
#from urllib import urlopen
import sqlite3
import requests

db=sqlite3.connect("places.db")
cursor2 = db.cursor()
cursor2.execute("drop table placesdata")
cursor2.execute("create table placesdata ( id integer primary key autoincrement, imgurl varchar(100), city varchar(30), placename varchar(50), descr varchar(2000) )")

def innerHTML(element):
    return element.decode_contents(formatter="html")

src=requests.get("http://tourism.rajasthan.gov.in/tourist-destinations.html").text
soup=bs(src,"html.parser")
#soup1=soup.find("h2", {"class": "NbdpWc"})

soup1=soup.find_all("div", {"class": "innerTitle"})
k=[]
for i in range(len(soup1)):
	k.append(innerHTML(soup1[i].find("p")).lower())

for i in range(len(k)):
	new_url = "http://tourism.rajasthan.gov.in/"+k[i]+".html"
	new_src = requests.get(new_url).text
	new_soup = bs(new_src,"html.parser")
	img_soup = new_soup.find_all("div",{"class":"articleImage"})
	imgs = []
	placename_soup = new_soup.find_all("div",{"class":"articleCont"})
	names = []
	desc = []
	print("hi")
	for j in range(len(placename_soup)):
		#print(placename_soup[j])
		#imgs.append("http://tourism.rajasthan.gov.in"+img_soup[j].find("img").get('src'))
		name = innerHTML(placename_soup[j].find("h4")).lower();
		desc = innerHTML(placename_soup[j].find("p")).lower();
		if(name and desc):
			r=img_soup[j].find("img").get('data-src')
			m="http://tourism.rajasthan.gov.in"+r
			#print(m)
			item = (m, k[i],name, desc)
			# print(item)
			#cursor2.execute("INSERT INTO placesdata(imgurl,placename,descr )VALUES({imgs[j]}, {name}, {desc} )")
			cursor2.execute("insert into placesdata(imgurl,city,placename,descr) values(?,?,?,?)",item)
				# ({imgs[j]}, {name}, {desc} )")
			#print("did some stuff")
			db.commit()
#print("life")
cursor2.execute("select * from placesdata")
#print("bancho beech ka gayab kar diye")
db.commit()
cursor2.close()
db.close()		


