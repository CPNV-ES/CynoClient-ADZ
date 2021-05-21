package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.model.ClientRepository

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {

    val allClients: LiveData<List<Client>> = repository.allClients.asLiveData();
}