package com.example.praktikum12.model

import kotlinx.serialization.Serializable

@Serializable
data class Mahasiswa(
    val nim:String,
    val nama: String,
    val alamat: String,
@Serializable(value = "jenis_kelamin")
    val jeniskelamin: String,
    val kelas: String,
    val angkatan: String
)
