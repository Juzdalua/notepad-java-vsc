package com.notepad.notepadjavavsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.notepad.notepadjavavsc.config.DotenvInitializer;

@SpringBootApplication
@ComponentScan(basePackages = { "com.notepad.notepadjavavsc" })
public class NotepadJavaVscApplication {

  public static void main(String[] args) {
    // SpringApplication.run(NotepadJavaVscApplication.class, args);

    SpringApplication app = new SpringApplication(NotepadJavaVscApplication.class);
    app.addInitializers(new DotenvInitializer());
    app.run(args);
  }

}
