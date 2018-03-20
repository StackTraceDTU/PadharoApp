import graphlab
import json
if __name__=="__main__":
	loaded_model = graphlab.load_model('final_model')
	z=5
	j=list(loaded_model.recommend(users=range(z,z+1),k=15)["place_id"])
	m={}
	m["place_id"]=j
	with open('places.json', 'w') as outfile:
	    json.dump(m, outfile)