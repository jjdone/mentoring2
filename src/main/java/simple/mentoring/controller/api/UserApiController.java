package simple.mentoring.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import simple.mentoring.dto.user.UserUpdateDto;
import simple.mentoring.service.UserService;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/{userId}/update")
    public ResponseEntity<?> update(@PathVariable Long userId, @ModelAttribute UserUpdateDto userUpdateDto) {
        return ResponseEntity.ok(userService.update(userId, userUpdateDto));
    }
}
