package de.hartz.software.protegard.view.ui

import javax.sound.sampled.AudioFormat
import javax.sound.sampled.AudioSystem
import kotlin.math.PI
import kotlin.math.exp
import kotlin.math.sin

object SoundHelper {

    fun generateBellSound(
        frequency: Double,
        durationMs: Int,
        sampleRate: Float = 44100f
    ): ByteArray {
        val numSamples = (durationMs / 1000.0 * sampleRate).toInt()
        val wave = ByteArray(numSamples)

        for (i in wave.indices) {
            val time = i / sampleRate

            // Apply amplitude decay to simulate a bell ringing and fading out
            val decay = exp(-time * 1.2) // Slower decay, adjust for desired length

            // Generate harmonics for a bell-like tone (lower frequencies for a deeper sound)
            val sample = (
                    64 * decay * sin(2 * PI * frequency * time) +           // Base tone (fundamental frequency)
                            32 * decay * sin(2 * PI * (frequency * 1.2) * time) +   // First harmonic
                            16 * decay * sin(2 * PI * (frequency * 1.5) * time) +   // Second harmonic
                            8 * decay * sin(2 * PI * (frequency * 2.0) * time)     // Third harmonic
                    ).toInt()

            // Ensure the sample stays within the 8-bit range (-128 to 127)
            wave[i] = sample.coerceIn(-128, 127).toByte()
        }
        return wave
    }

    fun playSound(wave: ByteArray, sampleRate: Float = 44100f) {
        val format = AudioFormat(sampleRate, 8, 1, true, false) // 8-bit mono sound
        val line = AudioSystem.getSourceDataLine(format)
        line.open(format)
        line.start()
        line.write(wave, 0, wave.size)
        line.drain() // Ensure the sound completes before closing
        line.close()
    }

}