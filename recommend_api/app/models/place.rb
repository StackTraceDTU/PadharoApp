class Place
  include Neo4j::ActiveNode
	self.include_root_in_json = false 

  property :place_id, type: Integer
  property :name,type: String
  property :rating,type: Float
  property :image,type: String
  property :description,type: String
  property :class_name, type: String
  property :reward_points, type: Integer
  property :city, type: String

end
