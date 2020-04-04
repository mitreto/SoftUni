package com.mitre.productsshop.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mitre.productsshop.utils.FileIOUtil;
import com.mitre.productsshop.utils.FileIoUtilImpl;
import com.mitre.productsshop.utils.ValidationUtil;
import com.mitre.productsshop.utils.ValidationUtilImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public Gson gson(){
        return new GsonBuilder()
                .excludeFieldsWithoutExposeAnnotation()
                .setPrettyPrinting()
                .create();
    }

    @Bean
    public ValidationUtil validationUtil(){
        return new ValidationUtilImpl();
    }

    @Bean
    public BufferedReader bufferedReader(){
        return new BufferedReader(new InputStreamReader(System.in));
    }

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    public FileIOUtil fileIOUtil(){
        return new FileIoUtilImpl();
    }
}