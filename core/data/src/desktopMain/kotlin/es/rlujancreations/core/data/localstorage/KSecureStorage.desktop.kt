package es.rlujancreations.core.data.localstorage

import es.rlujancreations.core.domain.IKSecureStorage
import java.io.File
import java.nio.file.Files
import java.security.SecureRandom
import java.util.Base64
import java.util.prefs.Preferences
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.SecretKeySpec

/**
 * Created by Raúl L.C. on 13/3/25.
 */
actual class KSecureStorage actual constructor() : IKSecureStorage {
    private var preferences: Preferences? = null
    private var masterKey: SecretKey? = null
    private val ivSize = 16
    private val algorithm = "AES/CBC/PKCS5Padding"
    private val keyDirectory =
        System.getProperty("user.home") + File.separator + ".appdata" + File.separator + "yourapp"
    private val saltFile = File(keyDirectory, "salt.bin")

    fun initialize(
        nodeName: String = "NotificatorAPP/securestorage",
        password: String = "default_password",
    ) {
        this.preferences = Preferences.userRoot().node(nodeName)

        // Make sure the directory exists to store the salt
        File(keyDirectory).mkdirs()

        // Get or create the salt
        val salt = if (saltFile.exists()) {
            Files.readAllBytes(saltFile.toPath())
        } else {
            val newSalt = ByteArray(16)
            SecureRandom().nextBytes(newSalt)
            saltFile.parentFile.mkdirs()
            Files.write(saltFile.toPath(), newSalt)
            newSalt
        }

        // Generates a new key based on the password
        val factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")
        val spec = PBEKeySpec(password.toCharArray(), salt, 65536, 256)
        val tmp = factory.generateSecret(spec)
        this.masterKey = SecretKeySpec(tmp.encoded, "AES")
    }

    private fun checkInitialization() {
        if (preferences == null || masterKey == null) {
            throw IllegalStateException(
                "KSecureStorage is not initialized. Call initialize() first.",
            )
        }
    }

    override fun setItem(key: String, value: String?) {
        checkInitialization()

        if (value == null) {
            preferences?.remove(key)
            return
        }

        val encryptedValue = encrypt(value)
        preferences?.put(key, encryptedValue)
    }

    override fun getItem(key: String): String? {
        checkInitialization()
        val encryptedValue = preferences?.get(key, null) ?: return null
        return decrypt(encryptedValue)
    }

    override fun removeItem(key: String) {
        checkInitialization()
        preferences?.remove(key)
    }

    override fun clear() {
        checkInitialization()
        preferences?.clear()
    }

    private fun encrypt(value: String): String {
        val cipher = Cipher.getInstance(algorithm)
        val iv = ByteArray(ivSize)
        SecureRandom().nextBytes(iv)
        val ivSpec = IvParameterSpec(iv)

        cipher.init(Cipher.ENCRYPT_MODE, masterKey, ivSpec)
        val encrypted = cipher.doFinal(value.toByteArray())

        // Concat IV with encrypted data to send
        val combined = ByteArray(iv.size + encrypted.size)
        System.arraycopy(iv, 0, combined, 0, iv.size)
        System.arraycopy(encrypted, 0, combined, iv.size, encrypted.size)

        return Base64.getEncoder().encodeToString(combined)
    }

    private fun decrypt(encryptedValue: String): String? {
        try {
            val combined = Base64.getDecoder().decode(encryptedValue)

            // Extract IV
            val iv = ByteArray(ivSize)
            System.arraycopy(combined, 0, iv, 0, iv.size)
            val ivSpec = IvParameterSpec(iv)

            // Extract encrypted data and decrypt it
            val encryptedBytes = ByteArray(combined.size - iv.size)
            System.arraycopy(combined, iv.size, encryptedBytes, 0, encryptedBytes.size)

            val cipher = Cipher.getInstance(algorithm)
            cipher.init(Cipher.DECRYPT_MODE, masterKey, ivSpec)

            val decrypted = cipher.doFinal(encryptedBytes)
            return String(decrypted)
        } catch (e: Exception) {
            println("Error decrypting value: ${e.message}")
            return null
        }
    }
}
