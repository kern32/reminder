package web.app.configuration;

public enum RoleType {
	ROLE_ADMIN (1), ROLE_USER(2), ROLE_ANONYMOUS(3);
	
	private int type;
	
	RoleType (int type){
		this.type = type;
	}
	
	public int getType(){
		return type;
	}
}
