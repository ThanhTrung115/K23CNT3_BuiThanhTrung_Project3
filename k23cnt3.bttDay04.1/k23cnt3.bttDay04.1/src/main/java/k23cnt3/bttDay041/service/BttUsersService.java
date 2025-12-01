package k23cnt3.bttDay041.service;

import k23cnt3.bttDay04.dto.BttUsersDTO;
import k23cnt3.bttDay04.entity.BttUsers;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service

public class BttUsersService {
    List<Users> userList = new ArrayList<Users>();

    public BttUsersService() {
        userList.add(new Users(1L, "user1", "pass1", "John Doe",
                LocalDate.parse("1990-01-01"), "john@example.com", "1234567890", 34, true));
        userList.add(new Users(2L, "user2", "pass2", "Jane Smith",
                LocalDate.parse("1992-05-15"), "jane@example.com", "0987654321", 32, false));
        userList.add(new Users(3L, "user3", "pass3", "Alice Johnson",
                LocalDate.parse("1985-11-22"), "alice@example.com", "1122334455", 39, true));
        userList.add(new Users(4L, "user4", "pass4", "Bob Brown",
                LocalDate.parse("1988-03-18"), "bob@example.com", "6677889900", 36, true));
        userList.add(new Users(5L, "user5", "pass5", "Charlie White",
                LocalDate.parse("1995-09-30"), "charlie@example.com", "4433221100", 29, false));
    }

    public List<Users> findAll() {
        return userList;
    }

    public Boolean create(UsersDTO usersDTO) {
        try {
            Users user = new Users();
            user.setId(userList.stream().count() + 1);
            user.setUsername(usersDTO.getUsername());
            user.setPassword(usersDTO.getPassword());
            user.setEmail(usersDTO.getEmail());
            user.setPhone(usersDTO.getPhone());
            user.setAge(usersDTO.getAge());
            user.setFullName(usersDTO.getFullName());
            user.setBirthDay(usersDTO.getBirthDay());
            user.setStatus(usersDTO.getStatus());
            userList.add(user);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
