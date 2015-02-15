package info.TheMFN.ItemCooldowns;

public enum Perms {
	epBP("ICD.bypass.enderpearl"),
	GABP("ICD.bypass.goldenapple");
	
	
	private String perm;
	
	Perms(String perm){
		this.setPerm(perm);
		
	}

	public String getPerm() {
		return perm;
	}

	public void setPerm(String perm) {
		this.perm = perm;
	}
	
}
