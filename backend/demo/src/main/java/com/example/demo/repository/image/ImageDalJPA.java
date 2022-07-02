//package com.example.demo.repository.image;
//
//import com.example.demo.dalInterfaces.IImageDal;
//import com.example.demo.model.Image;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//
//@Service
//public class ImageDalJPA implements IImageDal {
//
//    @Autowired
//    IImageRepository repo;
//
//    @Override
//    public Image getImageByImageId(Long addressId) {
//        return repo.getAddressByUllId(addressId);
//    }
//
//    @Override
//    public List<Image> getAllImages() {
//        return repo.findAll();
//    }
//
//
//}
