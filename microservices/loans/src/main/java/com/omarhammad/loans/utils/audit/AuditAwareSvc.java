package com.omarhammad.loans.utils.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareSvc implements AuditorAware<String> {

    @Override

    public Optional<String> getCurrentAuditor() {
        return Optional.of("LOANS_MS");
    }
}
