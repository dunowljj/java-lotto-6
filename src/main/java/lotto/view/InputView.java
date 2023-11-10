package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.PurchaseBudget;
import lotto.domain.WinningLotto;

import static lotto.constants.message.InputMessage.*;
import static lotto.util.InputUtils.*;

public class InputView {

    public static PurchaseBudget getPurchaseBudget() {
        try {
            println(DEMAND_INPUT_BUDGET.getMessage());
            return new PurchaseBudget(readInt());
        } catch (IllegalArgumentException e) {
            println(e.getMessage());
            return getPurchaseBudget();
        }
    }

    public static Lotto getWinningLotto() {
        try {
            println(DEMAND_INPUT_WINNING_NUMBERS.getMessage());
            return new Lotto(readLottoNumbers());
        } catch (IllegalArgumentException e) {
            println(e.getMessage());
            return getWinningLotto();
        }
    }

    public static WinningLotto getBonusLottoNumber(Lotto answerLotto) {
        try {
            println(DEMAND_INPUT_BONUS_NUMBER.getMessage());
            return new WinningLotto(answerLotto, new LottoNumber(readInt()));
        } catch (IllegalArgumentException e) {
            println(e.getMessage());
            return getBonusLottoNumber(answerLotto);
        }
    }

    public static void println(String message) {
        System.out.println(message);
    }
}
