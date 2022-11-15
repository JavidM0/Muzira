package com.example.muzira

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.SeekBar.OnSeekBarChangeListener
import androidx.navigation.fragment.findNavController
import com.example.muzira.databinding.FragmentMusicPlayerBinding
import java.util.concurrent.TimeUnit.*

class MusicPlayerFragment : Fragment() {

    private var _binding: FragmentMusicPlayerBinding? = null
    private val binding get() = checkNotNull(_binding)

    private var mediaPlayer = MediaPlayer()

    private lateinit var runnable: Runnable
    private var handler = Handler(Looper.getMainLooper())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMusicPlayerBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.terminator_song)

        binding.sbSong.progress = 0
        binding.sbSong.max = mediaPlayer.duration

        binding.tvElapsedTime.text = convertToMMSS(mediaPlayer.currentPosition.toString())
        binding.tvTotalTime.text = convertToMMSS(mediaPlayer.duration.toString())

        binding.btnExit.setOnClickListener {
            findNavController().navigate(R.id.action_musicPlayerFragment_to_homePageFragment)
        }

        binding.btnPlayOrPause.setOnClickListener {
            playSong()
        }

        binding.sbSong.setOnSeekBarChangeListener(object : OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress1: Int, changed: Boolean) {
                if (changed) {
                    mediaPlayer.seekTo(progress1)
                }
                binding.tvElapsedTime.text = convertToMMSS(mediaPlayer.currentPosition.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }
        })

        runnable = Runnable {
            binding.sbSong.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, DELAY)
        }
        handler.postDelayed(runnable, DELAY)

        mediaPlayer.setOnCompletionListener {
            binding.btnPlayOrPause.setImageResource(R.drawable.play_button_icon)
            binding.sbSong.progress = 0
        }
    }

    private fun playSong() {
        if (!mediaPlayer.isPlaying) {
            binding.btnPlayOrPause.setImageResource(R.drawable.pause_button_icon)
            mediaPlayer.start()

        } else {
            binding.btnPlayOrPause.setImageResource(R.drawable.play_button_icon)
            mediaPlayer.pause()
        }
    }

    private fun stopPlay() {
        mediaPlayer.stop()
        binding.btnPlayOrPause.setImageResource(R.drawable.play_button_icon)
    }

    private fun convertToMMSS(duration: String): String {
        val millis = duration.toLong()
        return String.format(
            "%2d.%02d",
            MILLISECONDS.toMinutes(millis) % HOURS.toMinutes(1),
            MILLISECONDS.toSeconds(millis) % MINUTES.toSeconds(1)
        )
    }

    companion object {
        private const val DELAY = 1000L
    }

    override fun onDestroy() {
        _binding = null

        if (mediaPlayer.isPlaying) {
            stopPlay()
        }

        handler.removeCallbacks(runnable)
        super.onDestroy()
    }
}