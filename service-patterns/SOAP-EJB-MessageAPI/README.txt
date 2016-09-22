How to access the WSDL definition?
-------------------------------------------------------------------------------

URL: http://localhost:8080/SOAP-EJB-MessageAPI/OrderService?wsdl


How to add a new attribute to Order?
-------------------------------------------------------------------------------

Order.java:
	/*
	 * Property: id
	 */
	
	@XmlAttribute
	private int id;
	public int getId()
    {
        return id;
    }
    public void setId(int id)
    {
        this.id = id;
    }

	    
    @Override
    public String toString()
    {
        return "Order [id=" + id + ", name=" + name + ", lines=" + lines + "]";
    }
	
	- redeploy service on Wildfly
	- run the client 
	
	     