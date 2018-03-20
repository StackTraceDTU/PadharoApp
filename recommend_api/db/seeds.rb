# This file should contain all the record creation needed to seed the database with its default values.
# The data can then be loaded with the rails db:seed command (or created alongside the database with db:setup).
#
# Examples:
#
#   movies = Movie.create([{ name: 'Star Wars' }, { name: 'Lord of the Rings' }])
#   Character.create(name: 'Luke', movie: movies.first)

require 'csv'

def seed_list csv_list, class_name, attributes
	puts "Destroying all #{class_name}..."
	class_name.destroy_all
	csv_list.each do |row|
		type = class_name.new
		type.image = row['image']
		type.city = row['city']
		type.description = row['description']
		type.name = row['name']
		type.place_id = row['id']
		type.save
		puts "** Creating new Place **"
	end
end

# p = Place.new
# p.name = "hot air ballooning"
# p.image = "https://www.indianholiday.com/blog/wp-content/uploads/2017/08/hot-air-balloning.jpg"
# p.description = "Hot air ballooning is one of the most popular sports in Rajasthan. A ride on a hot air balloon in the cities of Rajasthan provides an unforgettable experience. Gliding past the lakes, desert lands, forts and palaces provides a unique perspective of the state. The rides are arranged at dawn or dusk, depending largely on the weather conditions. However, do not forget to bring your camera along with you during the ride, as the views from above are truly captivating."
# p.city = "jaipur"
# p.place_id = 481
# p.class_name = "adventure"
# p.save

# p = Place.new
# p.name = "parsailing"
# p.image = "https://www.indianholiday.com/blog/wp-content/uploads/2017/08/parsailing.jpg"
# p.description = "Parasailing, also called par ascending, is a sport which you must try out when you are in Rajasthan. With its vast, spacious and open countryside, it’s perfectly suited for this thrilling sport. It’s always advisable to try out this activity from a professionally guided gliding school. This will ensure that you enjoy the activity while being safe at the same time."
# p.city = "jaipur"
# p.place_id = 482
# p.class_name = "adventure"
# p.save

csv_text = File.read(Rails.root.join('lib', 'seeds', 'new_data.csv'))
csv_list = CSV.parse(csv_text, :headers => true, :encoding => 'ISO-8859-1')

seed_list csv_list, Place, ['name', 'city', 'image', 'description', 'id' ]
