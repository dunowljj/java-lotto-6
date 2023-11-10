package lotto.util;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.List;

import static lotto.constants.Constants.Integers.*;

public class RandomLottoGenerateStrategy implements LottoGenerateStrategy{
    @Override
    public List<Integer> generateNumbes() {
        return Randoms.pickUniqueNumbersInRange(
                        MIN_LOTTO_NUMBER.getValue(), MAX_LOTTO_NUMBER.getValue(), LOTTO_SIZE.getValue()
                ).stream()
                .sorted()
                .toList();
    }
}
