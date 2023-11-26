package com.example.Proj1_2019202085.repository;

import com.example.Proj1_2019202085.domain.Gallery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface GalleryRepo extends JpaRepository<Gallery, Long> { //Interface which Access Crated DB

}

