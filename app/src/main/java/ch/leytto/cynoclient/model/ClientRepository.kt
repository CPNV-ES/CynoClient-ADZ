package ch.leytto.cynoclient.model

import ch.leytto.cynoclient.db.dao.ClientDao
import ch.leytto.cynoclient.db.entities.Client
import kotlinx.coroutines.flow.Flow

class ClientRepository(private val clientDao: ClientDao)  : AbstractRepository() {

    val allClients: Flow<List<Client>> = clientDao.getClients()
    suspend fun insertClient(client: Client){
        clientDao.insert(client)
    }

}