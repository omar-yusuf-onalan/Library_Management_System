package com.omaryusufonalan.Library_Management_System.api;

import com.omaryusufonalan.Library_Management_System.business.abstracts.PublisherService;
import com.omaryusufonalan.Library_Management_System.core.config.modelMapper.ModelMapperService;
import com.omaryusufonalan.Library_Management_System.core.result.Result;
import com.omaryusufonalan.Library_Management_System.core.result.ResultData;
import com.omaryusufonalan.Library_Management_System.core.result.ResultDataGenerator;
import com.omaryusufonalan.Library_Management_System.dto.request.publisher.PublisherSaveRequest;
import com.omaryusufonalan.Library_Management_System.dto.request.publisher.PublisherUpdateRequest;
import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.publisher.PublisherResponse;
import com.omaryusufonalan.Library_Management_System.entity.Publisher;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/publishers")
public class PublisherController {
    private final PublisherService publisherService;
    private final ModelMapperService modelMapperService;

    public PublisherController(PublisherService publisherService, ModelMapperService modelMapperService) {
        this.publisherService = publisherService;
        this.modelMapperService = modelMapperService;
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public ResultData<PublisherResponse> save(@Valid @RequestBody PublisherSaveRequest publisherSaveRequest) {
        Publisher savePublisher = this.modelMapperService.forRequest().map(publisherSaveRequest, Publisher.class);
        this.publisherService.save(savePublisher);

        PublisherResponse publisherResponse = this.modelMapperService.forResponse().map(savePublisher, PublisherResponse.class);
        return ResultDataGenerator.generateCreatedFor(publisherResponse);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> get(@PathVariable("id") int id) {
        Publisher publisher = this.publisherService.get(id);
        PublisherResponse publisherResponse = this.modelMapperService.forResponse().map(publisher, PublisherResponse.class);

        return ResultDataGenerator.generateOkFor(publisherResponse);
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<CursorResponse<PublisherResponse>> cursor(
            @RequestParam(name = "page", required = false, defaultValue = "0") int page,
            @RequestParam(name = "page", required = false, defaultValue = "10") int pageSize
    ) {
        Page<Publisher> publisherPage = this.publisherService.cursor(page, pageSize);

        Page<PublisherResponse> publisherResponsePage = publisherPage.map(publisher -> this.modelMapperService.forRequest().map(publisher, PublisherResponse.class));

        return ResultDataGenerator.generateCursorFor(publisherResponsePage);
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResultData<PublisherResponse> update(@Valid @RequestBody PublisherUpdateRequest publisherUpdateRequest) {
        Publisher updatePublisher = this.modelMapperService.forRequest().map(publisherUpdateRequest, Publisher.class);
        this.publisherService.update(updatePublisher);

        PublisherResponse publisherResponse = this.modelMapperService.forResponse().map(updatePublisher, PublisherResponse.class);
        return ResultDataGenerator.generateOkFor(publisherResponse);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Result delete(@PathVariable("id") int id) {
        this.publisherService.delete(id);
        return ResultDataGenerator.generateOk();
    }
}

