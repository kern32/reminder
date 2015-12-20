package web.app.configuration;

public enum RoleType {
	ROLE_ADMIN (1), ROLE_MODERATOR (2), ROLE_USER(3), ROLE_ANONYMOUS(4);
	
	private int type;
	
	RoleType (int t){
		this.type = t;
	}
	
	public int getType(){
		return type;
	}

}
