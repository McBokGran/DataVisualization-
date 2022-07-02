package com.example.demo.repository.mediaprepare;
import com.example.demo.model.Image;
import com.example.demo.model.MediaPrepare;
import com.example.demo.model.PrintCycle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IMediaPrepareRepository extends JpaRepository<MediaPrepare, Long> {
    MediaPrepare getById(Long appointmentId);
    MediaPrepare save (MediaPrepare mediaPrepare);
    @Query("Select p From MediaPrepare p where p.date <= :endDate and p.date >= :startDate order by p.date asc")
    List<MediaPrepare> findAllByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("Select i.mediaTypeDisplayName From MediaPrepare i where i.date <= :endDate and i.date >= :startDate group by i.mediaTypeDisplayName, i.date")
    List<String> getMediaTypes(Date startDate, Date endDate);
}
