package com.example.demo.serviceInterfaces;

import com.example.demo.model.Image;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface IImageService {

    Image getImageByImageId (Long addressId);
    List<Image> getAllImages();
    List<Image> findAllByDateBetween(Date startDate, Date endDate);
    Page<Image> findImageParsed(Pageable pageable,Date startDate, Date endDate);
    List<String> getMediaTypes(Date startDate, Date endDate);
    void seedImages();
    void save(Image image);

}
