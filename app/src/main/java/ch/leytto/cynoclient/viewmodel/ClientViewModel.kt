package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.model.ClientRepository
import kotlinx.coroutines.launch

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {

    val allClients: LiveData<List<Client>> = repository.allClients.asLiveData();
    fun insertClient(client: Client){
        viewModelScope.launch {
            repository.insertClient(client)
        }
    }
}