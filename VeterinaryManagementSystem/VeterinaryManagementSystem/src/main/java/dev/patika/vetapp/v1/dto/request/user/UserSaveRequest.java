package dev.patika.vetapp.v1.dto.request.user;

import dev.patika.vetapp.v1.entities.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserSaveRequest {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull(message = "Roles Not Null")
    private List<Role> roles;

    private boolean accountNonExpired = true;
    private boolean accountNonLocked = true;
    private boolean credentialNonExpired = true;
    private boolean enabled = true;

}
