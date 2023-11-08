package lotto.constants;

import lotto.domain.MatchingResult;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class LottoRankTest {

    @ParameterizedTest
    @CsvSource(value = {"1,false,0", "1,true,0",
            "2,false,0", "2,true,0",
            "3,true,5", "3,false,5",
            "4,true,4", "4,false,4",
            "5,true,2", "5,false,3",
            "6,true,1", "6,false,1"})
    public void from(int correctCount, boolean isBonusCorrect, int rank) throws Exception {
        //given
        MatchingResult matchingResult = new MatchingResult(correctCount, isBonusCorrect);

        //when
        LottoRank lottoRank = LottoRank.from(matchingResult);

        //then
        Assertions.assertThat(lottoRank.getRank()).isEqualTo(rank);
    }
}