package lotto.domain;

import java.util.List;

public class Lottos {

    private final List<Lotto> lottos;

    public Lottos(List<Lotto> lottos) {
        this.lottos = lottos;
    }

    public MatchingResults matchAll(WinningLotto winningLotto) {
        List<MatchingResult> matchingResults = lottos.stream()
                .map((issuedLotto -> issuedLotto.match(winningLotto)))
                .toList();

        return new MatchingResults(matchingResults);
    }

    public List<Lotto> getLottos() {
        return lottos;
    }
}
