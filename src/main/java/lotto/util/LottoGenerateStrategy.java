package lotto.util;

import java.util.List;

@FunctionalInterface
public interface LottoGenerateStrategy {

    List<Integer> generateNumbes();
}
