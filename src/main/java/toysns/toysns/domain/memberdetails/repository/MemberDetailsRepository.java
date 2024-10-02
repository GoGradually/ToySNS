package toysns.toysns.domain.memberdetails.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import toysns.toysns.domain.memberdetails.MemberDetails;

@Repository
public interface MemberDetailsRepository extends JpaRepository<MemberDetails, Long> {
}
