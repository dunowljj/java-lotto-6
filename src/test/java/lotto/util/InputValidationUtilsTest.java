package lotto.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static lotto.constants.ErrorMessage.CANNOT_READ_EMPTY_INPUT;
import static lotto.constants.ErrorMessage.INVALID_NUMBER_FORMAT;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class InputValidationUtilsTest {

    @DisplayName("입력 문자열 숫자 변환 예외 : 입력받은 문자열을 검증 시 공백만 존재하거나 빈 값이면 IllegalArgumentException을 발생")
    @ParameterizedTest
    @ValueSource(strings = {" \n\t", "\n", "  ", " ", ""})
    public void validateHasInput_exception_emptyInput(String input) {
        // when, then
        assertThatThrownBy(() ->
                InputValidationUtils.validateHasInput(input)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(CANNOT_READ_EMPTY_INPUT.getMessage());
    }

    @DisplayName("입력 문자열 숫자 변환 예외 : 숫자 형태의 문자열 사이에 공백 또는 기호 존재하면 Integer로 변환 불가로 IllegalArgumentException을 발생")
    @ParameterizedTest
    @ValueSource(strings = {"3 1", "1_000", "-1_000", "12\n34"})
    public void validateIsIntFormat_exception_numberFormat(String input) {
        // when, then
        assertThatThrownBy(() ->
                InputValidationUtils.validateIsIntFormat(input)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_FORMAT.getMessage());
    }

    @DisplayName("입력 문자열 숫자 변환 예외 : Integer 범위를 넘어서는 숫자인 경우 IllegalArgumentException을 발생")
    @ParameterizedTest
    // parameters = -30억, 30억, Integer_MAX_VALUE + 1, Integer_MIN_VALUE - 1
    @ValueSource(strings = {"3000000000", "-3000000000", "2147483648", "-2147483649"})
    public void validateCanBeInt_exception_format(String input) {
        // when, then
        assertThatThrownBy(() ->
                InputValidationUtils.validateIsIntFormat(input)
        )
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(INVALID_NUMBER_FORMAT.getMessage());
    }
}
