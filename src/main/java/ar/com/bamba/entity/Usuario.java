package ar.com.bamba.entity;

public class Usuario {
	
	private String name;
	
	private String password;
	
	private String twitterAccessToken;
	
	private String twitterSecret;

	
	
	public Usuario(String name, String password, String twitterAccessToken,
			String twitterSecret) {
		super();
		this.name = name;
		this.password = password;
		this.twitterAccessToken = twitterAccessToken;
		this.twitterSecret = twitterSecret;
	}

	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTwitterAccessToken() {
		return twitterAccessToken;
	}

	public void setTwitterAccessToken(String twitterAccessToken) {
		this.twitterAccessToken = twitterAccessToken;
	}

	public String getTwitterSecret() {
		return twitterSecret;
	}

	public void setTwitterSecret(String twitterSecret) {
		this.twitterSecret = twitterSecret;
	}

	
	
	
	
}
