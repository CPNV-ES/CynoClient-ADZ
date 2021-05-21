package ch.leytto.cynoclient.ui.dogs

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Dog
import ch.leytto.cynoclient.model.DogRepository

class DogViewModel(private val repository: DogRepository) : ViewModel() {
    val allDogs: LiveData<List<Dog>> = repository.allDogs.asLiveData();
}
