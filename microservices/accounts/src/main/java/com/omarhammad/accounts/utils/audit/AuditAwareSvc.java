package com.omarhammad.accounts.utils.audit;

import org.springframework.data.domain.AuditorAware;

import java.util.Optional;

public class AuditAwareSvc implements AuditorAware<String> {

    @Override

    public Optional<String> getCurrentAuditor() {
        return Optional.of("ACCOUNTS_MS");
    }
}
