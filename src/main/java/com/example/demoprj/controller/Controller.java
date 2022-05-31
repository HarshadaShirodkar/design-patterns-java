package com.example.demoprj.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import exception.SongNotFoundException;
import model.Song;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Controller {

    @RequestMapping("/api/songs")
    ResponseEntity<Object> getResponse(@RequestParam String genre) throws IOException {
        return getArtistsByGenre(genre);
    }

    @RequestMapping("/api/distance")
    ResponseEntity<Object> getDist() {
        return new ResponseEntity<>(distance(), HttpStatus.ACCEPTED);
    }

    public ResponseEntity<Object> getArtistsByGenre(String genre) throws IOException {
        List<Song> collect;
        ObjectMapper objectMapper = new ObjectMapper();
        try {

            Song[] test1 = objectMapper.readValue(new File("test.json"), Song[].class);
            System.out.println("name is:" + genre);
            List<Song> songList = Arrays.asList(objectMapper.readValue(new File("test.json"), Song[].class));

            collect = songList.stream()
                    .filter(it -> genre.equalsIgnoreCase(it.getGenre()))
                    .collect(Collectors.toList());

            if (collect == null || collect.size() == 0) {
                System.out.println("Map Size is" + collect.size());
                throw new SongNotFoundException(genre);
            }
        } finally {
            System.out.println("getArtistsByGenre ended");
        }
        return new ResponseEntity<>(collect, HttpStatus.ACCEPTED);
    }

    public double distance(){
        double lat1 = 44.40239182909422;
        double lon1 = 8.930511474608954;
        double lat2 = 30.297017883371236;
        double lon2 = 122.3822021484364;
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 1.609344 * 1000;
        System.out.println("distance:::::::::::" +dist);
        return (dist); // 134910.69784909734
    }

    /* The function to convert decimal into radians */
    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }
    /* The function to convert radians into decimal */
    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }




}