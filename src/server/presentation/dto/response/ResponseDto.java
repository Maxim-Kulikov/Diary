package server.presentation.dto.response;

public class ResponseDto<T> {
    private T result;
    private ErrorDto errorDto;

    public ResponseDto(T result, ErrorDto errorDto) {
        this.result = result;
        this.errorDto = errorDto;
    }

    public ResponseDto(ErrorDto errorDto) {
        this.errorDto = errorDto;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }

    public ErrorDto getErrorDto() {
        return errorDto;
    }

    public void setErrorDto(ErrorDto errorDto) {
        this.errorDto = errorDto;
    }

    @Override
    public String toString() {
        return "ResponseDto{" +
                "result=" + result +
                ", errorDto=" + errorDto +
                '}';
    }
}
