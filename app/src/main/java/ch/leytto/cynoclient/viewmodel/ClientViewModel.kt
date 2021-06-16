package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import ch.leytto.cynoclient.model.ClientRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {
    val allClients: LiveData<List<Client>> = repository.allClients.asLiveData()

    /**
     * Get all the client information of the given [id]
     */
    fun getById(id: String) : ClientWithLocality {
        return repository.getById(id)
    }
}