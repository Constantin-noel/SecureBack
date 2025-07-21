package SecureBack.dto;

import lombok.Data;

@Data
public class CreateUserDto {
    private String name;
    private String surname;
    private String phone;
    private String email;
    private String password;
    private int age;
}
