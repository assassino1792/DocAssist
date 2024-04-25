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
import jakarta.validation.constraints.Email;


@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "Patients")
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
    @Pattern(regexp = "^\\+(36)(30|70|20)\\d{7}$", message = "Invalid phone number")
    private String phonenumber;
    @NotEmpty
    private String problem;
    @Email
    @NotEmpty
    private String email;
    @NotEmpty
    @Column
    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate registrationDate;

    public ClientEntity(Long id, String firstName, String lastName, Integer age, String phonenumber, String problem, String email, LocalDate registrationDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.phonenumber = phonenumber;
        this.problem = problem;
        this.email = email;
        this.registrationDate = registrationDate;
    }



    public String getPhonenumber() {
        return phonenumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getProblem() {
        return problem;
    }

    public String getEmail() {
        return email;
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }
}
