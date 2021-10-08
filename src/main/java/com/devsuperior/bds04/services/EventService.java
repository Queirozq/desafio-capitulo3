package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.CityDTO;
import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.City;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.CityRepository;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Autowired
    private CityRepository cityRepository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAllSortedByName(Pageable pageable){
        Page<Event> list = repository.findAll(pageable);
        return list.map(x -> new EventDTO(x));
    }


    @Transactional
    public EventDTO insert(EventDTO obj){
        Event event = new Event();
        fromDTO(event, obj);
        event = repository.save(event);
        return new EventDTO(event);
    }

    private void fromDTO(Event event, EventDTO obj) {
        event.setName(obj.getName());
        event.setDate(obj.getDate());
        event.setUrl(obj.getUrl());
        event.setCity(cityRepository.getOne(obj.getCityId()));
    }
}
