package hu.nye.docassist.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ClientRequest {

    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;
    @PositiveOrZero
    private Integer age;
    @NotEmpty
    private String disease;
    @Email
    private String email;
    @Pattern(regexp = "^\\+(36)(30|1|70|20)\\d{7}$", message = "Invalid phone number")
    private String phoneNumber;

    @JsonFormat(pattern = "yyyy.MM.dd")
    private LocalDate registrationDate;


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public String getDisease() {
        return disease;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
