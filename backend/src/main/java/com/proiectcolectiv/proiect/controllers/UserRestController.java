package com.proiectcolectiv.proiect.controllers;

import com.proiectcolectiv.proiect.entities.ApiResponse;
import com.proiectcolectiv.proiect.entities.CurrentUser;
import com.proiectcolectiv.proiect.entities.UserEntity;
import com.proiectcolectiv.proiect.repositories.UserRepository;
import io.swagger.annotations.Api;
import org.apache.catalina.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

import org.springframework.web.multipart.MultipartFile;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class UserRestController {

    @Autowired
    private UserRepository userRepository;


    @RequestMapping(value = "/employee/upload", method = RequestMethod.POST)
    public ResponseEntity<Object> uploadImage(@RequestParam("imageFile") MultipartFile file)
            throws IOException {
        System.out.println("Original Image Byte Size - " + file.getBytes().length);
        CurrentUser currentUser = (CurrentUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserEntity img = currentUser.getUser();
        img.setProfilePic(compressBytes(file.getBytes()));
        img.setProfilePicType(file.getContentType());
        img.setProfilePicName(file.getOriginalFilename());
        userRepository.save(img);
        return ResponseEntity.ok().build();
    }

    @RequestMapping(value = "employee/get/{imageName}", method = RequestMethod.POST)
    public UserEntity getImage(@PathVariable("imageName") String imageName) throws IOException {
        final Optional<UserEntity> retrievedImage = userRepository.findUserEntityByProfilePicName(imageName);
        //if(!retrievedImage.isEmpty()){
            UserEntity img = new UserEntity(retrievedImage.get().getProfilePicName(), retrievedImage.get().getProfilePicType(), decompressBytes(retrievedImage.get().getProfilePic()));
            return img;
       // }
        //return null;
    }

    // compress the image bytes before storing it in the database
    public static byte[] compressBytes(byte[] data) {
        Deflater deflater = new Deflater();
        deflater.setInput(data);
        deflater.finish();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        while (!deflater.finished()) {
            int count = deflater.deflate(buffer);
            outputStream.write(buffer, 0, count);
        }
        try {
            outputStream.close();
        } catch (IOException e) {
        }
        System.out.println("Compressed Image Byte Size - " + outputStream.toByteArray().length);
        return outputStream.toByteArray();
    }

    // uncompress the image bytes before returning it to the angular application
    public static byte[] decompressBytes(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] buffer = new byte[1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(buffer);
                outputStream.write(buffer, 0, count);
            }
            outputStream.close();
        } catch (IOException | DataFormatException ioe) {
        }
        return outputStream.toByteArray();
    }
}

