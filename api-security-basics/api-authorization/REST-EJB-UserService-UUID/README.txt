How to access the REST resource?
-------------------------------------------------------------------------------
URL: https://localhost:8443/REST-EJB-UserService-UUID/v1/users

How to create a new user?
-------------------------------------------------------------------------------

Add the following user:
<user>
    <username>burns</username>
    <password>YDYg2ELvjAWIllkU7wbECt/lr6w=</password>
</user>

$ curl -i -k -u student:student -X POST https://localhost:8443/REST-EJB-UserService-UUID/v1/users -H 'Content-type:application/xml' -d '<user><username>burns</username><password>YDYg2ELvjAWIllkU7wbECt/lr6w=</password></user>'

Retrieve the inserted user: (insert your UUID)

$ curl -i -k -u student:student https://localhost:8443/REST-EJB-UserService-UUID/v1/users/843a02da-25fb-4a28-9186-fe89c70cd683



How to implement UUIDs for your service?
-------------------------------------------------------------------------------
1. Change the SQL script for creating the table:
	id VARCHAR(64) NOT NULL PRIMARY KEY,

2. Change the Entity class:
	@Id
	@Column(name="ID")
	private String id;
	public String getId()
	{
		return this.id;
	}
	public void setId(String id)
	{
		if(id == null)
			throw new IllegalArgumentException();
		this.id = id;
	}
	
3. Change the DAO factory method:
	@Override
	public User createUser(String username, String password)
	{
		User u = new User();
		u.setId(UUID.randomUUID().toString());
		u.setUsername(username);
		u.setPassword(password);		
		insert(u);
		return u;
	}	
			
4. Also we have to change all of the id parameters to String...	

