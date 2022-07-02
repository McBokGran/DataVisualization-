package com.example.demo.dalInterfaces;

import com.example.demo.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

public interface IImageDal{
    Image getImageByImageId (Long imageId);
    List<Image> findAllByDateBetween(Date startDate, Date endDate);
    Page<Image> findImageParsed(Pageable pageable,Date startDate, Date endDate);
    List<String> getMediaTypes(Date startDate, Date endDate);
    List<Image> getAllImages();
    void save(Image image);

}
