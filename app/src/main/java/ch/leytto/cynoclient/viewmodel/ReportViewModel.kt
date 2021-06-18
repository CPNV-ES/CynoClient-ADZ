package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.model.ClientRepository
import ch.leytto.cynoclient.model.DogRepository

class ReportViewModel(
    clientRepository: ClientRepository,
    dogRepository: DogRepository
) : ViewModel() {

    val allClients: LiveData<List<Client>> = clientRepository.allClients.asLiveData();

    val allDogs: LiveData<List<Dog>> = dogRepository.allDogs.asLiveData();
}