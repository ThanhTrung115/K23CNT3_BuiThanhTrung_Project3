package k23cnt3.bttDay04.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.Private)
@Entity
@Getter
@Setter

public class BttUsers {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;
    String username;
    String password;
    String fullname;
    LocalDate birthDay;
    String email;
    String phone;
    int age;
    Boolean status;
}
