package com.example.praktikum12.ui.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.praktikum12.model.Mahasiswa
import com.example.praktikum12.repository.MahasiswaRepository
import kotlinx.coroutines.launch

class UpdateViewModel(
    private val mhs: MahasiswaRepository
) : ViewModel() {

    var uiState by mutableStateOf(UpdateUiState())
        private set

    fun updateUpdateMhsState(updateUiEvent: UpdateUiEvent) {
        uiState = uiState.copy(updateUiEvent = updateUiEvent)
    }

    fun getMahasiswaByNim(nim: String) {
        viewModelScope.launch {
            try {
                val mahasiswa = mhs.getMahasiswaById(nim)
                uiState = UpdateUiState(updateUiEvent = mahasiswa.toUpdateUiEvent())
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = UpdateUiState(isError = true, errorMessage = e.message)
            }
        }
    }

    fun updateMhs() {
        viewModelScope.launch {
            try {
                val mahasiswa = uiState.updateUiEvent.toMhs()
                mhs.updateMahasiswa(mahasiswa.nim, mahasiswa)
                uiState = uiState.copy(isSuccess = true)
            } catch (e: Exception) {
                e.printStackTrace()
                uiState = uiState.copy(isError = true, errorMessage = e.message)
            }
        }
    }
}
data class UpdateUiState(
    val updateUiEvent: UpdateUiEvent = UpdateUiEvent(),
    val isSuccess: Boolean = false,
    val isError: Boolean = false,
    val errorMessage: String? = null
)

data class UpdateUiEvent(
    val nama: String = "",
    val nim: String = "",
    val alamat: String = "",
    val jenisKelamin: String = "",
    val kelas: String = "",
    val angkatan: String = ""
)

fun UpdateUiEvent.toMhs(): Mahasiswa = Mahasiswa(
    nama = nama,
    nim = nim,
    alamat = alamat,
    jeniskelamin = jenisKelamin,
    kelas = kelas,
    angkatan = angkatan
)

fun Mahasiswa.toUpdateUiEvent(): UpdateUiEvent = UpdateUiEvent(
    nama = nama,
    nim = nim,
    alamat = alamat,
    jenisKelamin = jeniskelamin,
    kelas = kelas,
    angkatan = angkatan
)