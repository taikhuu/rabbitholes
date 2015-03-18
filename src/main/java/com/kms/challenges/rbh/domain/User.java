package com.kms.challenges.rbh.domain;

import com.kms.challenges.rbh.model.Role;
import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

/**
 * @author Petri Kainulainen
 */
@Entity
@Table(name = "user_accounts")
public class User extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email", length = 100, nullable = false, unique = true)
    private String email;

    @Column(name = "first_name", length = 100,nullable = false)
    private String firstName;

    @Column(name = "last_name", length = 100, nullable = false)
    private String lastName;

    @Column(name = "password", length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", length = 20, nullable = false)
    private Role role;

    public User() {

    }

    public static Builder getBuilder() {
        return new Builder();
    }

    @Override
    public Long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }

    public Role getRole() {
        return role;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("creationTime", this.getCreationTime())
                .append("email", email)
                .append("firstName", firstName)
                .append("lastName", lastName)
                .append("modificationTime", this.getModificationTime())
                .append("version", this.getVersion())
                .toString();
    }

    public static class Builder {

        private User user;

        public Builder() {
            user = new User();
            user.role = Role.ROLE_USER;
        }

        public Builder email(String email) {
            user.email = email;
            return this;
        }

        public Builder firstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder lastName(String lastName) {
            user.lastName = lastName;
            return this;
        }

        public Builder password(String password) {
            user.password = password;
            return this;
        }

        public User build() {
            return user;
        }
    }
}
