package vn.demo.starter.controller.admin;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vn.demo.starter.service.UserService;
import vn.demo.starter.service.criteria.UserCriteria;
import vn.demo.starter.service.dto.UserDto;
import vn.demo.starter.util.PaginationUtils;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")
@RequiredArgsConstructor
@Tag(name = "Admin User Resource")
public class AdminUserController {

    private final UserService userService;


    @GetMapping
    public ResponseEntity<List<UserDto>> getUsers(UserCriteria criteria,
                                                      @ParameterObject @PageableDefault Pageable pageable) {
        final Page<UserDto> page = userService.getUsers( pageable,criteria);
        final HttpHeaders headers = PaginationUtils
                .generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(userService.getUserDto(userId));
    }

}
