package lotto.domain;

import java.util.HashSet;
import java.util.List;

import static lotto.constants.Constants.Integers.LOTTO_SIZE;
import static lotto.constants.message.ErrorMessage.DUPLICATED_NUMBER_EXIST;
import static lotto.constants.message.ErrorMessage.WRONG_LOTTO_SIZE;

public class Lotto {
    private final List<LottoNumber> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers.stream()
                .map(LottoNumber::new)
                .toList();
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != LOTTO_SIZE.getValue()) {
            throw new IllegalArgumentException(WRONG_LOTTO_SIZE.getMessage());
        }

        if (isDuplicatedNumberExist(numbers)) {
            throw new IllegalArgumentException(DUPLICATED_NUMBER_EXIST.getMessage());
        }
    }

    private boolean isDuplicatedNumberExist(List<Integer> numbers) {
        return new HashSet<>(numbers).size() != LOTTO_SIZE.getValue();
    }

    public MatchingResult match(WinningLotto winningLotto) {
        int count = winningLotto.countCorrectNumbers(this);
        boolean isBonusCorrect = winningLotto.checkBonusNumber(numbers);

        return new MatchingResult(count, isBonusCorrect);
    }

    protected int match(Lotto lotto) {
        return (int) this.numbers.stream()
                .filter((number) -> lotto.contains(number))
                .count();
    }

    public boolean contains(LottoNumber lottoNumber) {
        return numbers.contains(lottoNumber);
    }

    @Override
    public String toString() {
        return numbers.toString();
    }
}
