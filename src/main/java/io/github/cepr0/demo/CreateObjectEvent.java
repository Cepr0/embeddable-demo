package io.github.cepr0.demo;

import lombok.Getter;

public class CreateObjectEvent {
	@Getter private final Parent parent;
	
	public CreateObjectEvent(Parent parent) {
		this.parent = parent;
	}
}
