package api.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

public class User {
	private String username = "";
	private String email = "";
	private String password = "";
	private String street = "";
	private String state_province = "";
	private String postal_code = "";
	private String country = "";
	private String name = "";
	private String some_words_like = "";
	private String skills = "";
	private List<String> volunteering_interests = null;

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public String getStreet() {
		return street;
	}

	public String getState_province() {
		return state_province;
	}

	public String getPostal_code() {
		return postal_code;
	}

	public String getCountry() {
		return country;
	}

	public String getName() {
		return name;
	}

	public String getSome_words_like() {
		return some_words_like;
	}

	public String getSkills() {
		return skills;
	}

	public List<String> getVolunteering_interests() {
		return volunteering_interests;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public void setState_province(String state_province) {
		this.state_province = state_province;
	}

	public void setPostal_code(String postal_code) {
		this.postal_code = postal_code;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setSome_words_like(String some_words_like) {
		this.some_words_like = some_words_like;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public void setVolunteering_interests(List<String> volunteering_interests) {
		this.volunteering_interests = volunteering_interests;
	}

	public Map<String, Object> toMap() {
		Map<String, Object> params = new HashMap<String, Object>();

		if (!username.equals(""))
			params.put("username", username);
		if (!email.equals(""))
			params.put("email", email);
		if (!password.equals(""))
			params.put("password", password);
		if (!street.equals(""))
			params.put("street", street);
		if (!state_province.equals(""))
			params.put("state_province", state_province);
		if (!postal_code.equals(""))
			params.put("postal_code", postal_code);
		if (!country.equals(""))
			params.put("country", country);
		if (!name.equals(""))
			params.put("name", name);
		if (!some_words_like.equals(""))
			params.put("some_words_like", some_words_like);
		if (!skills.equals(""))
			params.put("skills", skills);
		if (volunteering_interests != null)
			params.put("field_model", StringUtils.join(volunteering_interests, "\n"));
		return params;

	}
}
