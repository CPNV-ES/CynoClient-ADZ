package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.leytto.cynoclient.model.AbstractRepository
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.DogRepository
import ch.leytto.cynoclient.ui.dogs.DogViewModel

class ViewModelFactory(private val repository: AbstractRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
                return DogViewModel(repository as DogRepository) as T
            }
            if (modelClass.isAssignableFrom(ClientViewModel::class.java)) {
                return ClientViewModel(repository as ClientRepository) as T
            }
            if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
                return ReportViewModel(repository as ClientRepository) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }
}