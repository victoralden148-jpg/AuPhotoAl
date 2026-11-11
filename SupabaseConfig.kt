package com.autophoto.ai.data.remote

import com.autophoto.ai.BuildConfig
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.gotrue.Auth
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.storage.Storage

/**
 * Konfigurasi Supabase. URL & publishable key dibaca dari local.properties
 * lewat BuildConfig (lihat app/build.gradle.kts).
 * JANGAN pernah memasukkan secret key (sb_secret_...) ke aplikasi.
 */
object SupabaseConfig {
    val url: String = BuildConfig.SUPABASE_URL
    val publishableKey: String = BuildConfig.SUPABASE_KEY

    fun createClient(): SupabaseClient {
        require(url.isNotBlank() && publishableKey.isNotBlank()) {
            "Isi supabase.url dan supabase.key di local.properties"
        }
        return createSupabaseClient(supabaseUrl = url, supabaseKey = publishableKey) {
            install(Auth)
            install(Postgrest)
            install(Storage)
        }
    }
}
