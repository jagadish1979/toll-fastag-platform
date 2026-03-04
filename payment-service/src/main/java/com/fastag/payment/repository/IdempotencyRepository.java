package com.fastag.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fastag.common.entity.Idempotency;

@Repository
public interface IdempotencyRepository extends JpaRepository<Idempotency, String> {

}
