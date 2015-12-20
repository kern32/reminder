package web.app.service;


import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;


import web.app.dao.RoleDAO;
import web.app.entities.Role;

public class RoleService {
	private static final Logger LOG = Logger.getLogger(RoleService.class);
	
	@Autowired  
    private SessionFactory sessionFactory;  
	
	@Autowired  
    private RoleDAO roleDAO;
	
	private Session getCurrentSession() {  
		LOG.info("getting current sessionFactory");
		return sessionFactory.getCurrentSession();  
    }  
  
    public Role getRole(int id) {  
    	LOG.info("getting role by ID");
    	Role role = (Role) getCurrentSession().load(Role.class, id);  
        return role;  
    }  
    
    public Role getRoles(int id) {  
    	LOG.info("getting roles by ID");
        return getRole(id);  
    } 
    
}
