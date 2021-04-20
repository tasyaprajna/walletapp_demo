package org.bsim.intern.io.entity;

import javax.persistence.*;
import java.io.Serializable;


@Entity
@Table(name = "user_table")
@SequenceGenerator(name = "seqUser", initialValue = 100, allocationSize = 10)
public class UserEntity implements Serializable {
    private static final long serialVersionUID = 4880465015283284470L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seqUser")
    private long id;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, columnDefinition = "VARHCAR(50)", length = 50)
    private String userName;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
