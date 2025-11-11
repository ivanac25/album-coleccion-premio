package com.album.service.events;

import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

@Component
public class AlbumEventPublisher {
    private final List<AlbumObserver> observers = new ArrayList<>();
    public void subscribe(AlbumObserver obs){ observers.add(obs); }
    public void notifyCompleted(Long userId, Long albumId){
        for (AlbumObserver o: observers) o.onAlbumCompleted(userId, albumId);
    }
}
