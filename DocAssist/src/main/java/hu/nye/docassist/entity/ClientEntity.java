package hu.nye.docassist.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Clients")
public class ClientEntity {
    private static final Logger logger = LoggerFactory.getLogger(ClientEntity.class);

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @PositiveOrZero
    private Integer age;

    public String getPhonenumber() {
        return phonenumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    @Pattern(regexp = "^\\+(36)(30|70|20)\\d{7}$", message = "Invalid phone number")
    private String phonenumber;
    @NotEmpty
    private String disease;
    @Email
    private String email;
    @Column
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate registrationDate;


    // konstruktor
    public ClientEntity(String firstName, String lastName, Integer age, String doctorsName, String disease) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.disease = disease;
        logger.info("ClientEntity created: {} {}", firstName, lastName, age, doctorsName, disease);
    }

    // Getterek és setterek
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public void setEmail(String email) {
        this.email = email;
    }



    public String getEmail() {
        return email;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
