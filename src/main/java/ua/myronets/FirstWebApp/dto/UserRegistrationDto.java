package ua.myronets.FirstWebApp.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Value;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationDto {
    @NotBlank(message = "Це поле обов'язкове для заповнення")
    @Email(message = "Не вірний формат пошти")
    private String username;

    @NotBlank(message = "Це поле обов'язкове для заповнення")
    private String firstName;

    @NotBlank(message = "Це поле обов'язкове для заповнення")
    private String lastName;

    @NotBlank(message = "Це поле обов'язкове для заповнення")
    @Size(min = 6, message = "Потрібно щонайменше 6 символів")
    private String password;

    @NotBlank(message = "Це поле обов'язкове для заповнення")
    private String confirmPassword;


}
