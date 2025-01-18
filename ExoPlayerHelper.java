package com.example.drmplayer;

import android.content.Context;
import android.net.Uri;
import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.drm.DefaultDrmSessionManager;
import com.google.android.exoplayer2.drm.DrmSessionManager;
import com.google.android.exoplayer2.drm.FrameworkMediaDrm;
import com.google.android.exoplayer2.drm.HttpMediaDrmCallback;
import com.google.android.exoplayer2.drm.MediaDrmCallback;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.upstream.DefaultDataSource;
import com.google.android.exoplayer2.util.Util;
import java.util.HashMap;
import java.util.UUID;

public class ExoPlayerHelper {
    private Context context;
    private ExoPlayer player;

    public ExoPlayerHelper(Context context) {
        this.context = context;
        this.player = new ExoPlayer.Builder(context).build();
    }

    public void playStream(String url, String keyId, String key) {
        MediaItem mediaItem;
        if (url.contains(".mpd") && !keyId.isEmpty() && !key.isEmpty()) {
            UUID clearKeyUUID = new UUID(0xEDEF8BA979D64ACE, 0xA3C827DCD51D21ED);
            HashMap<String, String> keyMap = new HashMap<>();
            keyMap.put(keyId, key);
            MediaDrmCallback drmCallback = new HttpMediaDrmCallback(null, new DefaultDataSource.Factory(context));
            DrmSessionManager drmSessionManager = new DefaultDrmSessionManager.Builder()
                    .setUuidAndExoMediaDrmProvider(clearKeyUUID, FrameworkMediaDrm.DEFAULT_PROVIDER)
                    .build(drmCallback);

            mediaItem = new MediaItem.Builder()
                    .setUri(Uri.parse(url))
                    .setDrmUuid(clearKeyUUID)
                    .setDrmLicenseRequestHeaders(keyMap)
                    .build();
        } else {
            mediaItem = new MediaItem.Builder().setUri(Uri.parse(url)).build();
        }

        player.setMediaItem(mediaItem);
        player.prepare();
        player.play();
    }
}
