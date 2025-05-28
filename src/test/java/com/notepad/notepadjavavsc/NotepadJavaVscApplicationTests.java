package com.notepad.notepadjavavsc;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import com.notepad.notepadjavavsc.global.config.DotenvInitializer;

@SpringBootTest
@ContextConfiguration(initializers = DotenvInitializer.class)
class NotepadJavaVscApplicationTests {

	@Test
	void contextLoads() {
	}

}
