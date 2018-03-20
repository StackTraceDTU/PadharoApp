import pandas as pd
import graphlab
# pass in column names for each CSV and read them using pandas. 
# Column names available in the readme file

#Reading users file:
u_cols = ['user_id', 'age', 'sex', 'occupation', 'zip_code']
users = pd.read_csv('ml-100k/u.user', sep='|', names=u_cols,encoding='latin-1')

#Reading ratings file:
r_cols = ['user_id', 'place_id', 'heurestics', 'unix_timestamp']
ratings = pd.read_csv('ml-100k/u.data', sep='\t', names=r_cols,encoding='latin-1')

#Reading items file:
i_cols = ['place id', 'name' ,'image_url', 'descr', 'Adventure', 'Scenic', 'Religious','Historical', 'Cultural', 'NightLife', 'Sports', 'Event', 'Play', 'Movie',
 'Day-Out', 'Monument', 'Musical', 'Mystery', 'Gardens', 'Sci-Fi', 'Discos', 'Resturants', 'Clubs']
items = pd.read_csv('ml-100k/u.item', sep='|', names=i_cols,encoding='latin-1')

#print users.shape
#users.head()

#print ratings.shape
#ratings.head()

#print items.shape
#items.head()

r_cols = ['user_id', 'place_id', 'rating']
ratings_base = pd.read_csv('ratings.csv', sep=',', names=r_cols, encoding='latin-1')
#ratings_test = pd.read_csv('ml-100k/ua.test', sep='\t', names=r_cols, encoding='latin-1')
#print ratings_base.shape, ratings_test.shape

train_data = graphlab.SFrame(ratings_base)
#test_data = graphlab.SFrame(ratings_test)

#Train Model
item_sim_model = graphlab.recommender.ranking_factorization_recommender.create(train_data, user_id='user_id', item_id='place_id', target='rating')
#item_sim_recomm = item_sim_model.recommend(users=range(11,12),k=15)
#item_sim_recomm.print_rows(num_rows=30)
#print type(item_sim_model)

item_sim_model.save('final_model')
loaded_model = graphlab.load_model('final_model')
z=5
loaded_model.recommend(users=range(z,z+1),k=15).print_rows(num_rows=15)

#Make Recommendations:
#eval=loaded_model.evaluate(test_data)
#print eval
#loaded_model = pickle.load(open(filename, 'rb'))