package com.atlantic.turnstiles.config.security;

import java.util.Collection;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.atlantic.turnstiles.common.exception.CannotEditPropsException;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "user")
@Entity(name = "User")
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {
	private static final long serialVersionUID = 1L;

	public User(String username) {
		this.username = username;
	}
	
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Integer id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = true)
	private String password;

	@CreationTimestamp
	@Column(updatable = false, name = "created_at")
	private String createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private String updatedAt;

	@Column(nullable = false)
	private Boolean deleted = false;
	
	public void setId(int id) {
		throw new CannotEditPropsException("Cannot edit id!");
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !this.deleted;
    }
    
	

	
}
