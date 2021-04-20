package com.aneto.todolist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TodolistApplication {

	static final Logger log =
			LoggerFactory.getLogger(TodolistApplication.class);

	public static void main(String[] args) {
		log.info("Before Starting application");

		SpringApplication.run(TodolistApplication.class, args);

		log.debug("Starting todo list in debug with {} args", args.length);
		log.info("Starting todo list with {} args.", args.length);
	}

}
