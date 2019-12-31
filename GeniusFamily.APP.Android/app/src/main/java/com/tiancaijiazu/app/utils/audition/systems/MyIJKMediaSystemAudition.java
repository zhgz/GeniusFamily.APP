package com.tiancaijiazu.app.utils.audition.systems;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.util.Log;
import android.view.Surface;

import com.tiancaijiazu.app.utils.audition.JZMediaInterfaceAudition;
import com.tiancaijiazu.app.utils.audition.JZMediaManagerAudition;
import com.tiancaijiazu.app.utils.audition.JZVideoPlayerManagerAudition;

import java.io.IOException;

import tv.danmaku.ijk.media.player.IMediaPlayer;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;
import tv.danmaku.ijk.media.player.IjkTimedText;

/**
 * @author wapchief
 * @data 2018/2/9
 */

public class MyIJKMediaSystemAudition extends JZMediaInterfaceAudition implements IMediaPlayer.OnPreparedListener, IMediaPlayer.OnVideoSizeChangedListener, IMediaPlayer.OnCompletionListener, IMediaPlayer.OnErrorListener, IMediaPlayer.OnInfoListener, IMediaPlayer.OnBufferingUpdateListener, IMediaPlayer.OnSeekCompleteListener, IMediaPlayer.OnTimedTextListener {
    IjkMediaPlayer ijkMediaPlayer;
    public float speeding=1f;
    @Override
    public void start() {
        ijkMediaPlayer.start();
    }

    @Override
    public void prepare() {
        ijkMediaPlayer = new IjkMediaPlayer();
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "mediacodec", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "opensles", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "overlay-format", IjkMediaPlayer.SDL_FCC_RV32);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "framedrop", 1);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_PLAYER, "start-on-prepared", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_FORMAT, "http-detect-range-support", 0);
        ijkMediaPlayer.setOption(IjkMediaPlayer.OPT_CATEGORY_CODEC, "skip_loop_filter", 48);

        ijkMediaPlayer.setOnPreparedListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnVideoSizeChangedListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnCompletionListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnErrorListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnInfoListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnBufferingUpdateListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnSeekCompleteListener(MyIJKMediaSystemAudition.this);
        ijkMediaPlayer.setOnTimedTextListener(MyIJKMediaSystemAudition.this);

        try {
            ijkMediaPlayer.setDataSource(currentDataSource.toString());
            ijkMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            ijkMediaPlayer.setScreenOnWhilePlaying(true);
            ijkMediaPlayer.prepareAsync();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause() {
        ijkMediaPlayer.pause();
    }

    @Override
    public boolean isPlaying() {
        return ijkMediaPlayer.isPlaying();
    }

    @Override
    public void seekTo(long time) {
        ijkMediaPlayer.seekTo(time);
    }

    @Override
    public void release() {
        if (ijkMediaPlayer != null)
            ijkMediaPlayer.release();
    }

    @Override
    public long getCurrentPosition() {
        return ijkMediaPlayer.getCurrentPosition();
    }

    @Override
    public long getDuration() {
        return ijkMediaPlayer.getDuration();
    }

    @Override
    public void setSurface(Surface surface) {
        ijkMediaPlayer.setSurface(surface);
    }

    @Override
    public void setVolume(float leftVolume, float rightVolume) {
        ijkMediaPlayer.setVolume(leftVolume, rightVolume);
    }

    @Override
    public void onPrepared(IMediaPlayer iMediaPlayer) {
        Log.e("speed=======:", getSpeeding() + "");
        ijkMediaPlayer.setSpeed(getSpeeding());
        ijkMediaPlayer.start();
        if (currentDataSource.toString().toLowerCase().contains("mp3")) {
            JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                        JZVideoPlayerManagerAudition.getCurrentJzvd().onPrepared();
                    }
                }
            });
        }
    }

    @Override
    public void onVideoSizeChanged(IMediaPlayer iMediaPlayer, int i, int i1, int i2, int i3) {
        JZMediaManagerAudition.instance().currentVideoWidth = iMediaPlayer.getVideoWidth();
        JZMediaManagerAudition.instance().currentVideoHeight = iMediaPlayer.getVideoHeight();
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    JZVideoPlayerManagerAudition.getCurrentJzvd().onVideoSizeChanged();
                }
            }
        });
    }

    @Override
    public void onCompletion(IMediaPlayer iMediaPlayer) {
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    JZVideoPlayerManagerAudition.getCurrentJzvd().onAutoCompletion();
                }
            }
        });
    }

    @Override
    public boolean onError(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    JZVideoPlayerManagerAudition.getCurrentJzvd().onError(what, extra);
                }
            }
        });
        return true;
    }

    @Override
    public boolean onInfo(IMediaPlayer iMediaPlayer, final int what, final int extra) {
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                        JZVideoPlayerManagerAudition.getCurrentJzvd().onPrepared();
                    } else {
                        JZVideoPlayerManagerAudition.getCurrentJzvd().onInfo(what, extra);
                    }
                }
            }
        });
        return false;
    }

    @Override
    public void onBufferingUpdate(IMediaPlayer iMediaPlayer, final int percent) {
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    JZVideoPlayerManagerAudition.getCurrentJzvd().setBufferProgress(percent);
                }
            }
        });
    }

    @Override
    public void onSeekComplete(IMediaPlayer iMediaPlayer) {
        JZMediaManagerAudition.instance().mainThreadHandler.post(new Runnable() {
            @Override
            public void run() {
                if (JZVideoPlayerManagerAudition.getCurrentJzvd() != null) {
                    JZVideoPlayerManagerAudition.getCurrentJzvd().onSeekComplete();
                }
            }
        });
    }

    @Override
    public void onTimedText(IMediaPlayer iMediaPlayer, IjkTimedText ijkTimedText) {

    }


    public float getSpeeding() {
        return speeding;
    }

    public void setSpeeding(float speeding) {
        this.speeding = speeding;
    }
}