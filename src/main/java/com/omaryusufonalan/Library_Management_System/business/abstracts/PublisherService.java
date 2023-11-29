package com.omaryusufonalan.Library_Management_System.business.abstracts;

import com.omaryusufonalan.Library_Management_System.entity.Author;
import com.omaryusufonalan.Library_Management_System.entity.Publisher;
import org.springframework.data.domain.Page;

public interface PublisherService {
    Publisher save(Publisher publisher);
    Publisher get(int id);
    Page<Publisher> cursor(int page, int pageSize);
    Publisher update(Publisher publisher);
    Boolean delete(int id);
}
