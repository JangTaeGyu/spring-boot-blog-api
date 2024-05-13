package com.example.springbootblogapi.controller;

import com.example.springbootblogapi.controller.request.UserCreateRequest;
import com.example.springbootblogapi.controller.request.UserUpdateRequest;
import com.example.springbootblogapi.controller.response.CreatedResponse;
import com.example.springbootblogapi.controller.response.PaginationResponse;
import com.example.springbootblogapi.domain.user.UserRole;
import com.example.springbootblogapi.domain.user.UserService;
import com.example.springbootblogapi.domain.user.data.UserSearchData;
import com.example.springbootblogapi.domain.user.dto.UserDto;
import com.example.springbootblogapi.support.constant.PageConstant;
import com.example.springbootblogapi.support.data.PaginatedData;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminUserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> search(
            @RequestParam(defaultValue = "", required = false) String keyword,
            @RequestParam(defaultValue = "", required = false) UserRole role,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PAGE, required = false) int page,
            @RequestParam(defaultValue = PageConstant.DEFAULT_PER_PAGE, required = false) int perPage
    ) {
        UserSearchData request = new UserSearchData(keyword, role);
        Pageable pageable = PageRequest.of(page, perPage);
        Page<UserDto> pageUser = userService.searchUsersBy(request, pageable);
        PaginationResponse<List<UserDto>> response = new PaginationResponse<>(pageUser.getContent(), PaginatedData.of(pageUser));
        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<CreatedResponse> create(@RequestBody @Valid UserCreateRequest request) {
        Long userId = userService.createUser(request.toData());
        CreatedResponse response = new CreatedResponse(userId);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<Void> update(@PathVariable Long userId, @RequestBody @Valid UserUpdateRequest request) {
        userService.updateUser(userId, request.toData());
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> delete(@PathVariable Long userId) {
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }
}
