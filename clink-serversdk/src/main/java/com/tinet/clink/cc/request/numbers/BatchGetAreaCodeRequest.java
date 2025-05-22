package com.tinet.clink.cc.request.numbers;

import com.tinet.clink.cc.PathEnum;
import com.tinet.clink.cc.response.numbers.BatchGetAreaCodeResponse;
import com.tinet.clink.core.request.AbstractRequestModel;

import java.util.List;
import java.util.Objects;

public class BatchGetAreaCodeRequest extends AbstractRequestModel<BatchGetAreaCodeResponse> {
    private List<String> numbers;

    public void setNumbers(List<String> numbers) {
        this.numbers = numbers;
        if (Objects.nonNull(numbers)) {
            // jdk 1.6 has no method String.join()
            StringBuilder numbersParam = new StringBuilder();
            for (String number : numbers) {
                numbersParam.append(number);
                numbersParam.append(",");
            }
            if (numbersParam.length() > 0) {
                numbersParam.deleteCharAt(numbersParam.length() - 1);
            }
            putQueryParameter("numbers", numbersParam.toString());
        }
    }

    public List<String> getNumbers() {
        return numbers;
    }

    public BatchGetAreaCodeRequest() {
        super(PathEnum.BatchGetAreaCode.value());
    }

    @Override
    public Class<BatchGetAreaCodeResponse> getResponseClass() {
        return BatchGetAreaCodeResponse.class;
    }
}
