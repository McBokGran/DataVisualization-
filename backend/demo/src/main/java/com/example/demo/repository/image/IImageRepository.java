package com.example.demo.repository.image;

import com.example.demo.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface IImageRepository extends JpaRepository<Image, Long>, PagingAndSortingRepository<Image, Long> {
    Image getByUllId(Long UllId);
    @Query("Select SUM(i.imageLength * i.imageWidth), i.date, i.mediaType From Image i group by i.date, i.mediaType order by i.date")
    Page<Image> findImageParsed(Pageable pageable,Date startDate, Date endDate);
    @Query("Select i From Image i where i.date <= :endDate and i.date >= :startDate order by i.date asc")
    List<Image> findAllByDateBetween(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
    @Query("Select i.mediaType From Image i where i.date <= :endDate and i.date >= :startDate group by i.mediaType, i.date")
    List<String> getMediaTypes(Date startDate, Date endDate);
    void deleteByUllId(Long UllId);
    Image save (Image image);


}
