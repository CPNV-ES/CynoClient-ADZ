package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.model.ClientRepository

class ReportViewModel(private val clientRepository: ClientRepository) : ViewModel() {

    val allClients: LiveData<List<Client>> = clientRepository.allClients.asLiveData();
}