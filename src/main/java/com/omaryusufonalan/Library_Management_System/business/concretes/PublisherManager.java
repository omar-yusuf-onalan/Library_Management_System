package com.omaryusufonalan.Library_Management_System.business.concretes;

import com.omaryusufonalan.Library_Management_System.business.abstracts.PublisherService;
import com.omaryusufonalan.Library_Management_System.core.exception.NotFoundException;
import com.omaryusufonalan.Library_Management_System.core.result.ResultMessage;
import com.omaryusufonalan.Library_Management_System.dao.PublisherRepo;
import com.omaryusufonalan.Library_Management_System.entity.Publisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PublisherManager implements PublisherService {
    private final PublisherRepo publisherRepo;

    public PublisherManager(PublisherRepo publisherRepo) {
        this.publisherRepo = publisherRepo;
    }

    @Override
    public Publisher save(Publisher publisher) {
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Publisher get(int id) {
        return this.publisherRepo.findById(id).orElseThrow(() -> new NotFoundException(ResultMessage.NOT_FOUND));
    }

    @Override
    public Page<Publisher> cursor(int page, int pageSize) {
        Pageable pageable = PageRequest.of(page, pageSize);
        return this.publisherRepo.findAll(pageable);
    }

    @Override
    public Publisher update(Publisher publisher) {
        this.get(publisher.getId());
        return this.publisherRepo.save(publisher);
    }

    @Override
    public Boolean delete(int id) {
        Publisher publisher = this.get(id);
        this.publisherRepo.delete(publisher);

        return true;
    }

}
