package simple.mentoring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ApplicationState {
    APPLY("신청"),
    CANCEL("취소");

    private final String krName;
}
