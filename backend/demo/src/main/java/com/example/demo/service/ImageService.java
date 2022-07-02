package com.example.demo.service;

import com.example.demo.dalInterfaces.IImageDal;
import com.example.demo.model.Image;
import com.example.demo.repository.image.IImageRepository;
import com.example.demo.serviceInterfaces.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ImageService implements IImageService {

    private final IImageRepository imageRepository;

    public ImageService(IImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Image getImageByImageId(Long imageId) {
        return imageRepository.getByUllId(imageId);
    }

    @Override
    public List<Image> findAllByDateBetween(Date startDate, Date endDate) {
        return imageRepository.findAllByDateBetween(startDate, endDate);
    }

    @Override
    public Page<Image> findImageParsed(Pageable pageable, Date startDate, Date endDate) {
        return imageRepository.findImageParsed(pageable,startDate,endDate);
    }

    @Override
    public List<String> getMediaTypes(Date startDate, Date endDate) {
        return imageRepository.getMediaTypes(startDate, endDate);
    }

    @Override
    public void save(Image image) {
        imageRepository.save(image);
    }

    @Override
    public List<Image> getAllImages() { return this.imageRepository.findAll(); }

    @Override
    public void seedImages() {

    }
}
