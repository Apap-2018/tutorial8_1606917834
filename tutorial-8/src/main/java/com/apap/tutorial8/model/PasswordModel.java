package com.apap.tutorial8.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="user_role")
public class PasswordModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	

	@NotNull
	@Lob
	@Column(name="old_password", nullable = false)
	private String oldPass;
	
	@NotNull
	@Lob
	@Column(name="new_password", nullable = false)
	private String newPass;
	
	@NotNull
	@Lob
	@Column(name="conf_password", nullable = false)
	private String confPass;

	public long getId() {
		return id;
	}

	public String getOldPass() {
		return oldPass;
	}

	public void setOldPass(String oldPass) {
		this.oldPass = oldPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	public String getConfPass() {
		return confPass;
	}

	public void setConfPass(String confPass) {
		this.confPass = confPass;
	}

	public void setId(long id) {
		this.id = id;
	}

	
}
