package com.omaryusufonalan.Library_Management_System.core.result;

import com.omaryusufonalan.Library_Management_System.dto.response.CursorResponse;
import com.omaryusufonalan.Library_Management_System.dto.response.author.AuthorResponse;
import org.springframework.data.domain.Page;

public class ResultDataGenerator {
    public static <T> ResultData<T> generateCreatedFor(T data) {
        return new ResultData<>(true, ResultMessage.CREATED, ResultCode.CREATED, data);
    }

    public static <T> ResultData<T> generateValidationErrorFor(T data) {
        return new ResultData<>(false, ResultMessage.VALIDATION_ERROR, ResultCode.BAD_REQUEST, data);
    }

    public static <T> ResultData<T> generateOkFor(T data) {
        return new ResultData<>(true, ResultMessage.OK, ResultCode.OK, data);
    }

    public static Result generateOk() {
        return new Result(true, ResultMessage.OK, ResultCode.OK);
    }

    public static Result generateNotFoundErrorFor(String message) {
        return new Result(false, ResultMessage.NOT_FOUND, ResultCode.NOT_FOUND);
    }

    public static <T> ResultData<CursorResponse<T>> generateCursorFor(Page<T> pageData) {

        CursorResponse<T> cursor = new CursorResponse<>();
        cursor.setItems(pageData.getContent());
        cursor.setPageNumber(pageData.getNumber());
        cursor.setPageSize(pageData.getSize());
        cursor.setTotalElements(pageData.getTotalElements());

        return ResultDataGenerator.generateOkFor(cursor);
    }
}
