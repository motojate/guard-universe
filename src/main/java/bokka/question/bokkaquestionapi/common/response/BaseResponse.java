package bokka.question.bokkaquestionapi.common.response;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@Builder
public class BaseResponse<T> {
    private T result;
    private int code;


    public static <T> BaseResponse<T> success(T data) {
        return BaseResponse.<T>builder().result(data).code(HttpStatus.OK.value()).build();
    }

    public static BaseResponse<Void> emptyData() {
        return BaseResponse.<Void>builder().code(HttpStatus.NO_CONTENT.value()).build();
    }
}
