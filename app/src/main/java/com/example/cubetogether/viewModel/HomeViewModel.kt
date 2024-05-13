package com.example.cubetogether.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cubetogether.model.TiempoBO
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

class HomeViewModel : ViewModel() {
   var db = Firebase.firestore

    private val tiempoListMutableLiveData: MutableLiveData<List<TiempoBO>> by lazy {
        MutableLiveData<List<TiempoBO>>()
    }

    fun getTiempoListLiveData(): LiveData<List<TiempoBO>> = tiempoListMutableLiveData

    public suspend fun loadTiempoList() {
        var tiempoList: List<TiempoBO> = emptyList()
        val error = try {
            val snapshot = Firebase.firestore
                .collection("times")
                .get()
                .await()

            tiempoList = snapshot.documents.map { document ->
                document.toObject(TiempoBO::class.java) ?: TiempoBO("", "0")
            }

            null
        } catch (exception: Exception) {
            exception.localizedMessage
        }

        withContext(Dispatchers.Main) {
            tiempoListMutableLiveData.value = tiempoList
        }
    }
}
