package com.msengage.realgorithm.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.msengage.realgorithm.model.User;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class UserService
{
    public User getUserById(String id)
    {
        User selectedUser = null;
        ObjectMapper mapper = new ObjectMapper();
        List<User> userList = null;
        try
        {
            File userDataJSON = new ClassPathResource("/dataset/data-user.json").getFile();
            userList = Arrays.asList(mapper.readValue(userDataJSON, User[].class));
            for(User user : userList)
            {
                if(user.getUserId().equalsIgnoreCase(id))
                {
                    selectedUser = user;
                    break;
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return selectedUser;
    }
}
