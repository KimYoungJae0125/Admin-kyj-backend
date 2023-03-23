package kr.kimyoungjae.admin.domain.profiles.repository;


import kr.kimyoungjae.admin.domain.profiles.model.ProfilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfilesRepository extends JpaRepository<ProfilesEntity, Long> {
}
