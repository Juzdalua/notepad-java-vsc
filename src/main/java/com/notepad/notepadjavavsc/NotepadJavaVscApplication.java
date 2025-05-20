package com.notepad.notepadjavavsc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import com.notepad.notepadjavavsc.config.DotenvInitializer;

@EnableJpaAuditing
@SpringBootApplication
public class NotepadJavaVscApplication {

  public static void main(String[] args) {
    // SpringApplication.run(NotepadJavaVscApplication.class, args);

    SpringApplication app = new SpringApplication(NotepadJavaVscApplication.class);
    app.addInitializers(new DotenvInitializer());
    app.run(args);
  }

}
