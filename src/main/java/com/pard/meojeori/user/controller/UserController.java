package com.pard.meojeori.user.controller;

import com.pard.meojeori.user.dto.UserDTO;
import com.pard.meojeori.user.servise.UserService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping("")
    @Operation(summary = "유저 등록",description = "이름과 이메일로 유저 생성")
    public String createUser(@RequestBody UserDTO.Create dto){
        userService.createUser(dto);
        return "유저 추가됨";
    }

    @GetMapping("")
    @Operation(summary = "모든 유저 리스팅",description = "DB 내 모든 유저 리스팅")
    public List<UserDTO.Read> readAll(){
        return userService.readAll();
    }

    @GetMapping("/{userId}")
    @Operation(summary = "유저 검색",description = "ID를 통해 DB 내 해당 유저 검색")
    public UserDTO.Read readById(@PathVariable UUID userId){ return userService.readById(userId); }
}
