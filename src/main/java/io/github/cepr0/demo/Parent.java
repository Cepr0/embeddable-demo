package io.github.cepr0.demo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Immutable;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.*;

import java.time.Instant;
import java.util.*;

import static lombok.AccessLevel.PROTECTED;

@Getter
@Setter
@Entity
@Table(name = "parents")
@Immutable
@DynamicUpdate
@DynamicInsert
public class Parent extends AbstractAggregateRoot<Parent> {
	
	@Id
	@GeneratedValue(generator = "UUID")
	@GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
	@Column(updatable = false, nullable = false)
	private UUID id;
	
	@Version
	private Long version;
	
	@Column(nullable = false, columnDefinition = "timestamp")
	private Instant createdAt;
	
	private String name;
	
	@ElementCollection
	@CollectionTable(name = "parent_children", joinColumns = @JoinColumn(name = "parent_id"), foreignKey = @ForeignKey(name = "fk_parent_children"))
	private Set<Child> children = new HashSet<>();
	
	public Parent() {
		if (createdAt == null) createdAt = Instant.now();
	}
	
	public Parent(String name, Set<Child> children) {
		this();
		this.name = name;
		this.children = children;
	}
	
	public Parent logIt() {
		this.registerEvent(new CreateObjectEvent(this));
		return this;
	}
	
	@Getter
	@Setter
	@NoArgsConstructor(access = PROTECTED)
	@Embeddable
	public static class Child {
		private String name;
		
		public Child(String name) {
			this.name = name;
		}
	}
}
