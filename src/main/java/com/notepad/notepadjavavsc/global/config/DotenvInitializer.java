package com.notepad.notepadjavavsc.global.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

import org.springframework.lang.NonNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class DotenvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

  @Override
  public void initialize(@NonNull ConfigurableApplicationContext context) {

    // String projectRoot = System.getProperty("user.dir");
    // File envFile = new File(projectRoot, ".env");
    File envFile = new File(".env");
    System.out.println("123"+envFile);

    if (!envFile.exists()) {
      throw new RuntimeException(".env 파일이 없습니다.");
    }

    try (BufferedReader br = new BufferedReader(new FileReader(envFile))) {
      br.lines()
          .filter(line -> line.contains("=") && !line.trim().startsWith("#"))
          .forEach(line -> {
            String[] parts = line.split("=", 2);
            String key = parts[0].trim();
            String value = parts[1].trim();
            System.setProperty(key, value);

            System.out.println(key+"/"+value);
          });
    } catch (IOException e) {
      throw new RuntimeException(".env 파일을 읽는 중 오류 발생", e);
    }
  }
}
