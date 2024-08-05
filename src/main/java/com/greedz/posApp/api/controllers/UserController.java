package com.greedz.posApp.api.controllers;

import com.greedz.posApp.business.abstracts.UserService;
import com.greedz.posApp.business.requests.userRequests.CreateUserRequest;
import com.greedz.posApp.business.requests.userRequests.DeleteUserRequest;
import com.greedz.posApp.business.requests.userRequests.UpdateUserRequest;
import com.greedz.posApp.business.responses.userResponse.ListUserDto;
import com.greedz.posApp.core.utilities.results.DataResult;
import com.greedz.posApp.core.utilities.results.Result;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/add")
    public Result add(@RequestBody @Valid CreateUserRequest createUserRequest){
        return this.userService.add(createUserRequest);
    }

    @PostMapping("/update")
    public Result update(@RequestBody @Valid UpdateUserRequest updateUserRequest){
        return this.userService.update(updateUserRequest);
    }

    @PostMapping("/delete")
    public Result delete(@RequestBody @Valid DeleteUserRequest deleteUserRequest){
        return this.userService.delete(deleteUserRequest);
    }

    @GetMapping("/getall")
    public DataResult<List<ListUserDto>>getAll(){
        return this.userService.getAll();
    }
}
