package org.se.lab.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.log4j.Logger;

class UserDAOImpl // package private
	implements UserDAO
{
    private final Logger LOG = Logger.getLogger(UserDAOImpl.class);
    
	private List<User> table = new ArrayList<>();

	protected static AtomicInteger id = new AtomicInteger();
	
	protected UserDAOImpl()
	{
	    id.set(0);
	    insert(new User(0, "homer", "ijD8qepbRnIsva0kx0cKRCcYysg="));
	    insert(new User(0, "marge", "xCSuPDv2U6I5jEO1wqvEQ/jPYhY="));
	    insert(new User(0, "bart", "Ls4jKY8G2ftFdy/bHTgIaRjID0Q="));
	    insert(new User(0, "lisa", "xO0U4gIN1F7bV7X7ovQN2TlSUF4="));
	    
	    LOG.info(table);
	}
	
	
	/*
	 * CRUD methods
	 */	
	
	@Override
	public User insert(User user)
	{
	    user.setId(id.incrementAndGet());
	    table.add(user);
	    return user;
	}

	@Override
	public User update(User user)
	{
	    User u = findById(user.getId());
		u.setUsername(user.getUsername());
		u.setPassword(user.getPassword());	    
	    return u;
	}

	@Override
	public void delete(User user)
	{
		table.remove(user);
	}

	@Override
	public User findById(int id)
	{
	    for(User u : table)
	    {
	        if(u.getId() == id)
	            return u;
	    }
	    return null;
	}

	@Override
	public List<User> findAll()
	{
	    return table;
	}	
	
	
	/*
	 * Factory methods
	 */

	@Override
	public User createUser(String username, String password)
	{
		User u = new User();
		u.setUsername(username);
		u.setPassword(password);		
		insert(u);
		return u;
	}
}
