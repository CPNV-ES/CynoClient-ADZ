package ch.leytto.cynoclient.viewmodel

import androidx.annotation.Nullable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ch.leytto.cynoclient.model.AbstractRepository
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.DogRepository
import ch.leytto.cynoclient.ui.dogs.DogViewModel

class ViewModelFactory(
    private val repository: AbstractRepository,
    private val repository2: AbstractRepository? = null
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DogViewModel::class.java)) {
            return DogViewModel(repository as DogRepository) as T
        }
        if (modelClass.isAssignableFrom(ClientViewModel::class.java)) {
            return ClientViewModel(repository as ClientRepository) as T
        }
        // TODO: We could use Dagger to inject the dependencies in the ReportViewModel
        // This would allow us to not rely on the order in which the repositories where given
        if (modelClass.isAssignableFrom(ReportViewModel::class.java)) {
            return ReportViewModel(repository as ClientRepository, repository2 as DogRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}