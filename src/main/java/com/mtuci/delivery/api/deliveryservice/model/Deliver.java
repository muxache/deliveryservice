package com.mtuci.delivery.api.deliveryservice.model;

import javax.persistence.*;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "delivers")
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler" })
public class Deliver {

    @Id
    @Column(name = "id", nullable = false)
    @SequenceGenerator(name = "deliver_seq", sequenceName = "deliver_id_seq")
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "deliver_seq")
    private Long id;

    @Column
    private String firstname;

    @Column
    private String lastname;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return this.firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return this.lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }   


    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", firstname='" + getFirstname() + "'" +
            ", lastname='" + getLastname() + "'" +
            "}";
    }

}
