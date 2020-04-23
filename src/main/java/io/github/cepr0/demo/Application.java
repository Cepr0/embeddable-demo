package io.github.cepr0.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;

import static java.util.Arrays.asList;

@SpringBootApplication
public class Application {
	
	private final ParentRepo parentRepo;
	
	public Application(ParentRepo parentRepo) {
		this.parentRepo = parentRepo;
	}
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@EventListener
	@Transactional
	public void onReady(ApplicationReadyEvent e) {
		parentRepo.save(new Parent("parent1", new HashSet<>(asList(
				new Parent.Child("child1"),
				new Parent.Child("child2")
		))).logIt());
	}
}
