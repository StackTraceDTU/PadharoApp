class CreatePlace < Neo4j::Migrations::Base
  def up
    add_constraint :Place, :uuid
  end

  def down
    drop_constraint :Place, :uuid
  end
end
