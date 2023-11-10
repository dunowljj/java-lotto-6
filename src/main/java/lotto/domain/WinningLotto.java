package lotto.domain;

import java.util.List;

import static lotto.constants.message.ErrorMessage.DUPLICATED_BONUS_NUMBER_EXIST;

public class WinningLotto {

    private final Lotto lotto;
    private final LottoNumber lottoNumber;

    public WinningLotto(Lotto originWinningLotto, LottoNumber bonusNumber) {
        if (originWinningLotto.contains(bonusNumber)) {
            throw new IllegalArgumentException(DUPLICATED_BONUS_NUMBER_EXIST.getMessage());
        }

        this.lotto = originWinningLotto;
        this.lottoNumber = bonusNumber;
    }

    public boolean contains(LottoNumber number) {
        return lotto.contains(number);
    }

    public boolean checkBonusNumber(List<LottoNumber> numbers) {
        return numbers.contains(lottoNumber);
    }

    public int countCorrectNumbers(Lotto lotto) {
        return this.lotto.match(lotto);
    }
}
