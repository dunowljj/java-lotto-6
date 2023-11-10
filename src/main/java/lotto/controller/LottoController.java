package lotto.controller;

import lotto.domain.*;

import static lotto.view.InputView.*;
import static lotto.view.OutputView.*;

public class LottoController {

    public void play() {
        PurchaseBudget purchaseBudget = getPurchaseBudget();
        lineBreak();

        announcePurchaseQuantity(purchaseBudget.createQuantity());
        Lottos issuedLottos = getIssuedLottos(purchaseBudget.createQuantity());
        showIssuedLottos(issuedLottos);
        lineBreak();

        Lotto OriginalWinningLotto = getWinningLotto();
        lineBreak();

        WinningLotto winningLotto = getBonusLottoNumber(OriginalWinningLotto);
        lineBreak();

        WinnerStatistics winnerStatistic = getWinnerStatistic(issuedLottos, winningLotto);
        announceWinningStatistics(winnerStatistic);
        printObject(EarningRate.of(purchaseBudget, winnerStatistic));
    }

    private WinnerStatistics getWinnerStatistic(Lottos issuedLottos, WinningLotto winningLotto) {
        MatchingResults matchingResults = issuedLottos.matchAll(winningLotto);
        return WinnerStatistics.collect(matchingResults);
    }
}
