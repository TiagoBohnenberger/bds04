package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public EventDTO insert(EventDTO dto) {
        Event entity = repository.getOne(dto.getCityId());
        entity.setName(dto.getName());
        entity.setDate(dto.getDate());
        entity.setUrl(dto.getUrl());

        entity.setCity(entity.getCity());

        entity = repository.save(entity);

        return new EventDTO(entity);
    }

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageRequest) {
        Page<Event> events = repository.findAll(pageRequest);
        return events.map(entity -> new EventDTO(entity));
    }
}
