package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static lotto.constants.message.ErrorMessage.DUPLICATED_BONUS_NUMBER_EXIST;
import static org.assertj.core.api.Assertions.assertThatNoException;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WinningLottoTest {

    @DisplayName("보너스 넘버 중복 예외 : winningLotto에 번호와 중복되는 번호가 존재하면 IllegalArgumentException을 발생")
    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    public void validateDuplication_exception(int duplicatedNumber) {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(duplicatedNumber);


        // when, then
        assertThatThrownBy(() -> new WinningLotto(winningLotto, lottoNumber))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(DUPLICATED_BONUS_NUMBER_EXIST.getMessage());
    }


    @DisplayName("보너스 넘버 중복 없음 : 예외 발생 안함")
    @ParameterizedTest
    @ValueSource(ints = {7, 8, 9, 40, 43, 45})
    public void validateDuplication_noError(int duplicatedNumber) {
        //given
        Lotto winningLotto = new Lotto(List.of(1, 2, 3, 4, 5, 6));
        LottoNumber lottoNumber = new LottoNumber(duplicatedNumber);

        // when, then
        assertThatNoException().isThrownBy(() -> new WinningLotto(winningLotto, lottoNumber));
    }
}
