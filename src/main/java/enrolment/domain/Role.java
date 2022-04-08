package enrolment.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
@Getter
@RequiredArgsConstructor
public enum Role {

    STUDENT("ROLE_STUDENT", "학생"),
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String value;
}
