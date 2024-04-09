package com.seinfeld.search.repositories;

import com.seinfeld.search.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
