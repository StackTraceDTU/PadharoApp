class User 
  include Neo4j::ActiveNode

  property :uid, type: String
  property :total_reward_points,type: Integer
  property :redeemed_reward_points,type: Integer


end
