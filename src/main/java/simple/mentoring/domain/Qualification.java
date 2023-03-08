package simple.mentoring.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Qualification {
    MENTEE("멘티"),
    MENTOR("멘토");

    private final String krName;
}
