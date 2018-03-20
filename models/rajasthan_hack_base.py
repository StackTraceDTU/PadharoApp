import numpy as np
import pandas as pd
from sklearn import cross_validation as cv
from sklearn.metrics.pairwise import pairwise_distances
from sklearn.metrics import mean_squared_error
from math import sqrt
import scipy.sparse as sp
from scipy.sparse.linalg import svds
def rmse(prediction, ground_truth):
    prediction = prediction[ground_truth.nonzero()].flatten()
    
    ground_truth = ground_truth[ground_truth.nonzero()].flatten()
    
    return sqrt(mean_squared_error(prediction, ground_truth))

def predict(ratings, similarity, type='user'):
    if type == 'user':
        mean_user_rating = ratings.mean(axis=1)
        #You use np.newaxis so that mean_user_rating has same format as ratings
        ratings_diff = (ratings - mean_user_rating[:, np.newaxis])
        pred = mean_user_rating[:, np.newaxis] + similarity.dot(ratings_diff) / np.array([np.abs(similarity).sum(axis=1)]).T
    elif type == 'place':
        pred = ratings.dot(similarity) / np.array([np.abs(similarity).sum(axis=1)])
    return pred

header = ['user_id', 'place_id', 'ratings']
df = pd.read_csv('ratings.csv', delimiter=',', names=header)
n_users = df.user_id.unique().shape[0]
n_places = df.place_id.unique().shape[0]
train_data, test_data = cv.train_test_split(df, test_size=0.25)
train_data_matrix = np.zeros((n_users, n_places))
for line in train_data.itertuples():
	#print line
	train_data_matrix[int(line[1])-1, int(line[2])-1] = int(line[3])
test_data_matrix = np.zeros((n_users, n_places))

for line in test_data.itertuples():
    test_data_matrix[int(line[1])-1, int(line[2])-1] = int(line[3])
user_similarity = pairwise_distances(train_data_matrix, metric='cosine')
place_similarity = pairwise_distances(train_data_matrix.T, metric='cosine')
place_prediction = predict(train_data_matrix, place_similarity, type='place')
user_prediction = predict(train_data_matrix, user_similarity, type='user')
#print(type(place_prediction))
#print("yes")
print 'User-based CF RMSE: ' + str(rmse(user_prediction, test_data_matrix))
print 'place-based CF RMSE: ' + str(rmse(place_prediction, test_data_matrix))
sparsity=round(1.0-len(df)/float(n_users*n_places),3)
print 'The sparsity level of is ' +  str(sparsity*100) + '%'

#get SVD components from train matrix. Choose k.
u, s, vt = svds(train_data_matrix, k = 7)
s_diag_matrix=np.diag(s)
X_pred = np.dot(np.dot(u, s_diag_matrix), vt)
print X_pred
print 'User-based CF MSE: ' + str(rmse(X_pred, test_data_matrix))