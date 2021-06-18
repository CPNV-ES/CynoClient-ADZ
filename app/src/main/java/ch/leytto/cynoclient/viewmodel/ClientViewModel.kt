package ch.leytto.cynoclient.viewmodel

import androidx.lifecycle.*
import ch.leytto.cynoclient.db.entities.Client
import ch.leytto.cynoclient.db.entities.relations.ClientWithLocality
import ch.leytto.cynoclient.model.ClientRepository
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.*

class ClientViewModel(private val repository: ClientRepository) : ViewModel() {
    val allClients: LiveData<List<Client>> = repository.allClients.asLiveData()

    private val _firstname = MutableStateFlow("")
    private val _lastname = MutableStateFlow("")
    private val _email = MutableStateFlow("")
    private val _street = MutableStateFlow("")
    private val _locality = MutableStateFlow("")
    private val _phone = MutableStateFlow("")

    // Return true if all the conditions are valid
    val isSubmitEnabled: Flow<Boolean> = combine(_firstname, _lastname, _email, _street, _locality) { firstname, lastname, email, street, locality ->
        val isFirstnameCorrect = firstname.length > 2
        val isLastnameCorrect = lastname.length > 2
        val isEmailCorrect = email.length > 2
        val isStreetCorrect = street.length > 2
        val isLocalityCorrect = locality.length > 2
        return@combine isFirstnameCorrect and isLastnameCorrect and isEmailCorrect and isStreetCorrect and isLocalityCorrect
    }

    fun insertClient(client: Client){
        viewModelScope.launch {
            repository.insertClient(client)
        }
    }
    fun setFirstname(firstname:String){
        _firstname.value = firstname
    }
    fun setLastname(lastname:String){
        _lastname.value = lastname
    }
    fun setEmail(email:String){
        _email.value = email
    }
    fun setStreet(street:String){
        _street.value = street
    }
    fun setLocality(locality:String){
        _locality.value = locality
    }
    fun setPhone(phone:String){
        _phone.value = phone
    }


    /**
     * Get all the client information of the given [id]
     */
    fun getById(id: String) : ClientWithLocality {
        return repository.getById(id)
    }
}