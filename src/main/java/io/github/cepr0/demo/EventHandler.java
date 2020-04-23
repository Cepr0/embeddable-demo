package io.github.cepr0.demo;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
public class EventHandler {
	private final HistoryRepo historyRepo;
	
	public EventHandler(HistoryRepo historyRepo) {
		this.historyRepo = historyRepo;
	}
	
	@EventListener
	@Transactional
	public void handleCreateEvent(CreateObjectEvent event) {
		historyRepo.save(new Event(event.getParent()));
	}
}
