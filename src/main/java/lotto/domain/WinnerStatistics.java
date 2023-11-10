package lotto.domain;

import lotto.constants.LottoRank;

import static lotto.constants.LottoRank.*;
import static lotto.constants.message.OutputMessage.STATISTICS_RANK_INFO_OUTPUT_FORMAT;
import static lotto.constants.message.OutputMessage.STATISTICS_RANK_INFO_OUTPUT_FORMAT_INCLUDE_BONUS_BALL;

public class WinnerStatistics {

    private final RankCounts rankCounts;
    private final TotalPrizeAmount totalPrizeAmount;

    public WinnerStatistics(RankCounts rankCounts, TotalPrizeAmount totalPrizeAmount) {
        this.rankCounts = rankCounts;
        this.totalPrizeAmount = totalPrizeAmount;
    }

    public static WinnerStatistics collect(MatchingResults matchingResults) {
        RankCounts rankCounts = new RankCounts();
        TotalPrizeAmount totalPrizeAmount = new TotalPrizeAmount();

        collect(matchingResults, rankCounts, totalPrizeAmount);

        return new WinnerStatistics(rankCounts, totalPrizeAmount);
    }

    private static void collect(MatchingResults matchingResults, RankCounts rankCounts, TotalPrizeAmount totalPrizeAmount) {
        matchingResults.getLottoRanks()
                .stream()
                .forEach(lottoRank -> collect(lottoRank, rankCounts, totalPrizeAmount));
    }

    private static void collect(LottoRank lottoRank, RankCounts rankCounts, TotalPrizeAmount totalPrizeAmount) {
        rankCounts.addCount(lottoRank);
        totalPrizeAmount.addPrizeMoney(lottoRank.getPrizeMoney());
    }

    @Override
    public String toString() {
        return getOutPutMessage(FIFTH) +
                getOutPutMessage(FOURTH) +
                getOutPutMessage(THIRD) +
                getOutPutMessage(SECOND) +
                getOutPutMessage(FIRST);
    }

    private String getOutPutMessage(LottoRank lottoRank) {
        if (lottoRank.isBonusCorrect()) {
            return getOutPutMessageWithBonus(lottoRank);
        }

        return String.format(STATISTICS_RANK_INFO_OUTPUT_FORMAT.getMessage(),
                lottoRank.getCorrectCount(), lottoRank.getFormattedPrizeMoney(), rankCounts.getRankCount(lottoRank));
    }

    private String getOutPutMessageWithBonus(LottoRank lottoRank) {
        return String.format(STATISTICS_RANK_INFO_OUTPUT_FORMAT_INCLUDE_BONUS_BALL.getMessage(),
                lottoRank.getCorrectCount(), lottoRank.getFormattedPrizeMoney(), rankCounts.getRankCount(lottoRank));
    }

    public long getTotalPrizeAmount() {
        return totalPrizeAmount.getTotalPrizeAmount();
    }
}

