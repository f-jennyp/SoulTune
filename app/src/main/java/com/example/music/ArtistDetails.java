package com.example.music;

import android.media.MediaMetadataRetriever;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.example.music.MainActivity.musicFiles;

public class ArtistDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    ImageView artistPhoto;
    String artistName;
    TextView artistName_2;
    ArrayList<MusicFiles> artistSongs = new ArrayList<>();
    ArtistDetailsAdapter artistDetailsAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist_details);
        recyclerView = findViewById(R.id.recycleView);
        artistPhoto = findViewById(R.id.artistPhoto);
        artistName_2 = findViewById(R.id.artistName_2);
        artistName = getIntent().getStringExtra("artistName");
        int j = 0;
        for (int i = 0 ; i < musicFiles.size() ; i ++){
            if (artistName.equals(musicFiles.get(i).getArtist())){
                artistSongs.add(j, musicFiles.get(i));
                j ++;
                artistName_2.setText(musicFiles.get(i).getArtist());
            }
        }
        byte[] image = getAlbumArt(artistSongs.get(0).getPath());
        if (image != null){
            Glide.with(this)
                    .load(image)
                    .into(artistPhoto);
        }else{
            Glide.with(this)
                    .load(R.drawable.music)
                    .into(artistPhoto);
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if (!(artistSongs.size()<1)){
            artistDetailsAdapter = new ArtistDetailsAdapter(this, artistSongs);
            recyclerView.setAdapter(artistDetailsAdapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(this,
                    RecyclerView.VERTICAL, false));
        }
    }
    private byte[] getAlbumArt(String uri){
        MediaMetadataRetriever retriever = new MediaMetadataRetriever();
        retriever.setDataSource(uri);
        byte[] art = retriever.getEmbeddedPicture();
        retriever.release();
        return art;
    }
}