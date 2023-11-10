package lotto.util;

import java.util.List;

public class LottoGenerator  {

    public static List<Integer> generateLottoNumbers(LottoGenerateStrategy lottoGenerateStrategy) {
        return lottoGenerateStrategy.generateNumbes();
    }
}
