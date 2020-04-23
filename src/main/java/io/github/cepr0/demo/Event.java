package io.github.cepr0.demo;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "history")
@Immutable
@DynamicUpdate
@DynamicInsert
public class Event {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;
	
	@Version
	private Long version;
	
	@Column(nullable = false, columnDefinition = "timestamp")
	private Instant createdAt;
	
	@ManyToOne
	private Parent parent;
	
	public Event() {
		if (createdAt == null) createdAt = Instant.now();
	}
	
	public Event(Parent parent) {
		this();
		this.parent = parent;
	}
}
