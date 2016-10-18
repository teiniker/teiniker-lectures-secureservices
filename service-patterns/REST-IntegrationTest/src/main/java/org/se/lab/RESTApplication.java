package org.se.lab;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/*")
public class RESTApplication 
    extends Application
{
	private Set<Object> resources = new HashSet<Object>();
	private Set<Class<?>> empty = new HashSet<Class<?>>();

	public RESTApplication()
	{
		resources.add(new VersionsResource());
	}

	@Override
	public Set<Class<?>> getClasses()
	{
		return empty;
	}

	@Override
	public Set<Object> getSingletons()
	{
		return resources;
	}
}
