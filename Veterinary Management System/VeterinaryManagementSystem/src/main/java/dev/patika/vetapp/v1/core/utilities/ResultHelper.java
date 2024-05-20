package dev.patika.vetapp.v1.core.utilities;

import dev.patika.vetapp.v1.core.result.Result;
import dev.patika.vetapp.v1.core.result.ResultData;

public class ResultHelper {

    public static <T> ResultData<T> CREATED(T data) {
        return new ResultData<>(true, Message.CREATED, "201", data);
    }


    public static <T> ResultData<T> OK(T data) {
        return new ResultData<>(true, Message.OK, "200", data);
    }


    public static <T> ResultData<T> RESOLVE(T data) {
        return new ResultData<>(true, Message.RESOLVE, "200", data);
    }

    public static <T> ResultData<T> DELETE(T data) {
        return new ResultData<>(true, Message.DELETED, "200", data);
    }


    public static <T> ResultData<T> ERROR(T data) {
        return new ResultData<>(false, Message.NOT_NULL_MESSAGE, "409", data);
    }

    public static Result NOT_FOUND_ID() {
        return new Result(false, Message.NOT_FOUND_ID, "404");
    }


    public static Result NULL_POINTER() {
        return new Result(false, Message.NULL_POINTER, "500");
    }

    public static Result NULL_VALUES() {
        return new Result(false, Message.NULL_VALUES, "404");
    }


    public static Result NOT_FOUND_CUSTOMER() {
        return new Result(false, Message.NOT_FOUND_CUSTOMER, "404");
    }


    public static Result NOT_FOUND() {
        return new Result(false, Message.NOT_FOUND, "404");
    }


    public static Result NOT_FOUND_ANIMAL() {
        return new Result(false, Message.NOT_FOUND_ANIMAL, "404");
    }


    public static Result DATE_MISMATCH() {
        return new Result(false, Message.DATE_MISMATCH, "417");
    }


    public static Result UPDATE_NOT_FOUND_ID() {
        return new Result(false, Message.UPDATE_NOT_FOUND_ID, "404");
    }


    public static Result BAD_DATE() {
        return new Result(false, Message.BAD_DATE, "415");
    }


    public static Result NOT_UNIQUE() {
        return new Result(false, Message.NOT_UNIQUE, "415");
    }


    public static Result NOT_FOUND_DOCTOR() {
        return new Result(false, Message.NOT_FOUND_DOCTOR, "404");
    }


    public static Result SAME_VALUES() {
        return new Result(false, Message.SAME_VALUES, "409");
    }


    public static Result RESOURCE_NOT_FOUND() {
        return new Result(false, Message.RESOURCE_NOT_FOUND, "409");
    }


    public static Result DAYS_CONFLICT() {
        return new Result(false, Message.DAYS_CONFLICT, "400");
    }


    public static Result APPOINTMENT_CONFLICT() {
        return new Result(false, Message.APPOINTMENT_CONFLICT, "400");
    }


    public static Result NOT_FOUND_APPOINTMENT() {
        return new Result(false, Message.NOT_FOUND_APPOINTMENT, "400");
    }


    public static Result EXIST_APPOINTMENT() {
        return new Result(false, Message.EXISTING_APPOINTMENT, "400");
    }


    public static Result UNREADABLE() {
        return new Result(false, Message.UNREADABLE, "502");
    }


    public static Result FORCE_UPDATE() {
        return new Result(false, Message.FORCE_UPDATE, "502");
    }

    public static Result MISSING_PARAMETER() {
        return new Result(false, Message.MISSING_PARAMETER, "502");
    }
}


