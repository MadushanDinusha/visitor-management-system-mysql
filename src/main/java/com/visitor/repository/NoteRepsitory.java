package hms.tap.servicestatusinquiry.repository;

import hms.tap.servicestatusinquiry.domain.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NoteRepsitory extends JpaRepository<Note, Long> {

}